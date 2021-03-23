package com.myim.router.service.impl;

import com.myim.register.ZookeeperClient;
import com.myim.register.curator.CuratorZookeeperClient;
import com.myim.router.bo.ImServerInfo;
import com.myim.router.dto.request.ImRouterReqDto;
import com.myim.router.dto.response.ImRouterRespDto;
import com.myim.router.service.ImRouterService;
import com.myim.router.strategy.Strategy;
import com.myim.router.strategy.StrategyEnum;
import org.apache.catalina.util.ServerInfo;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cj
 */
@Component
public class ImRouterServiceImpl implements ImRouterService, BeanPostProcessor {
    private ConcurrentHashMap<String, List<ImServerInfo>> imServerInfoMaps = new ConcurrentHashMap<>();
    private ConcurrentHashMap<StrategyEnum, Strategy> strategyInfoMaps = new ConcurrentHashMap<>();
    private ZookeeperClient zookeeperClient = new CuratorZookeeperClient("47.110.41.97:2181");

    public ImRouterRespDto doRouter(ImRouterReqDto imRouterReqDto) {
        ImRouterRespDto imRouterRespDto = new ImRouterRespDto();

        //获取配置的负载均衡策略，默认随机
        StrategyEnum se = getStrategy();
        //从imServerInfoMaps中选取一个im服务器

        Strategy strategy = strategyInfoMaps.get(se);
        ImServerInfo imServerInfo = strategy.doStrategy(imServerInfoMaps, se);

        trans(imRouterRespDto, imServerInfo);
        return imRouterRespDto;
    }

    private void trans(ImRouterRespDto imRouterReqDto, ImServerInfo serverInfo) {
        imRouterReqDto.setHost(serverInfo.getHost());
        imRouterReqDto.setPort(serverInfo.getPort());
    }

    private StrategyEnum getStrategy() {
        return StrategyEnum.RANDOM;
    }

     public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Strategy)
            strategyInfoMaps.put(StrategyEnum.valueOf(beanName), (Strategy)bean);
        return bean;
    }

    //监听ZK变化,动态更新imServerInfoMaps存活的im服务器
    @PostConstruct
    public void doZkListen() {
        System.out.println("start.....");
        //启动的时候拉取一次全量信息，之后监听zk路径变化
        List<String> children = zookeeperClient.getChildren("/myim/host");
        imServerInfoMaps.put("imServer", new ArrayList<>());
        children.forEach(c -> {
            try {
                String s = zookeeperClient.getValue("/myim/host" + "/" + c);
                ImServerInfo imServerInfo = new ImServerInfo();

                imServerInfo.setHost(s.split(":")[0]);
                imServerInfo.setPort(s.split(":")[1]);

                imServerInfoMaps.get("imServer").add(imServerInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        zookeeperClient.addChildListener("/myim/host", (framework, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    String s = new String(event.getData().getData());

                    ImServerInfo imServerInfo = new ImServerInfo();

                    imServerInfo.setHost(s.split(":")[0]);
                    imServerInfo.setPort(s.split(":")[1]);

                    imServerInfoMaps.get("imServer").add(imServerInfo);
//                    List<ImServerInfo> list = new ArrayList<>();
//                    list.add(imServerInfo);
//
//                    imServerInfoMaps.put("imServer", list);

                    System.out.println("CHILD_ADDED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            s + "，状态：" + event.getData().getStat());
                    break;
                case CHILD_UPDATED:
                    System.out.println("CHILD_UPDATED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            new String(event.getData().getData()) + "，状态：" + event.getData().getStat());
                    break;
                case CHILD_REMOVED:
                    System.out.println("CHILD_REMOVED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            new String(event.getData().getData()) + "，状态：" + event.getData().getStat());
                    break;
                default:
                    break;
            }
        });
    }
}
