package com.myim.register;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cj
 */
public class zkTest {
    private static final String CONNECT_ADDR = "47.110.41.97:2181";
    private static final int SESSION_TIMEOUT = 5000;

    @Test
    public void zkCRUDTest() throws Exception {
        //重试策略，初试时间1秒，重试10次
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
        //通过工厂创建Curator
        CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(policy).build();
        //开启连接
        curator.start();
        ExecutorService executor = Executors.newCachedThreadPool();

        /**创建节点，creatingParentsIfNeeded()方法的意思是如果父节点不存在，则在创建节点的同时创建父节点；
         * withMode()方法指定创建的节点类型，跟原生的Zookeeper API一样，不设置默认为PERSISTENT类型。
         * */
        curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .inBackground((framework, event) -> { //添加回调
                    System.out.println("Code：" + event.getResultCode());
                    System.out.println("Type：" + event.getType());
                    System.out.println("Path：" + event.getPath());
                }, executor).forPath("/super/c1", "c1内容".getBytes());

        curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .inBackground((framework, event) -> { //添加回调
                    System.out.println("Code：" + event.getResultCode());
                    System.out.println("Type：" + event.getType());
                    System.out.println("Path：" + event.getPath());
                }, executor).forPath("/super/c2", "c2内容".getBytes());


        Thread.sleep(5000); //为了能够看到回调信息
        String data = new String(curator.getData().forPath("/super/c1")); //获取节点数据
        System.out.println(data);
        Stat stat = curator.checkExists().forPath("/super/c1"); //判断指定节点是否存在
        System.out.println(stat);
        curator.setData().forPath("/super/c1", "c1新内容".getBytes()); //更新节点数据
        data = new String(curator.getData().forPath("/super/c1"));
        System.out.println(data);
        List<String> children = curator.getChildren().forPath("/super"); //获取子节点
        for (String child : children) {
            System.out.println(child);
        }
        //放心的删除节点，deletingChildrenIfNeeded()方法表示如果存在子节点的话，同时删除子节点
        curator.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super");
        curator.close();
    }

    @Test
    public void zkCreateTest() throws Exception {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(policy).build();
        curator.start();

//        curator.create().withMode(CreateMode.PERSISTENT).forPath("/super", "1234".getBytes());
        curator.create().withMode(CreateMode.EPHEMERAL).forPath("/super/c1", "1234".getBytes());
        Thread.sleep(1000 * 500);
    }

    @Test
    public void zkCreateTest1() throws Exception {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(policy).build();
        curator.start();

//        curator.create().withMode(CreateMode.PERSISTENT).forPath("/super", "1234".getBytes());
        curator.create().withMode(CreateMode.EPHEMERAL).forPath("/super/c2", "1234".getBytes());
        Thread.sleep(1000 * 500);
    }

    @Test
    public void zkNodeListenerTest() throws Exception {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(policy).build();
        curator.start();

        //最后一个参数表示是否进行压缩
        NodeCache cache = new NodeCache(curator, "/super/c1", false);
        cache.start(true);
        //只会监听节点的创建和修改，删除不会监听
        cache.getListenable().addListener(() -> {
            if (cache.getCurrentData() != null) {
                System.out.println("路径：" + cache.getCurrentData().getPath());
                System.out.println("数据：" + new String(cache.getCurrentData().getData()));
                System.out.println("状态：" + cache.getCurrentData().getStat());
            }
        });
        Thread.sleep(1000 * 500);
    }

    @Test
    public void zkPathListenerTest() throws Exception {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curator = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(policy).build();
        curator.start();
        //第三个参数表示是否接收节点数据内容
        PathChildrenCache childrenCache = new PathChildrenCache(curator, "/super", true);
        /**
         * 如果不填写这个参数，则无法监听到子节点的数据更新
         如果参数为PathChildrenCache.StartMode.BUILD_INITIAL_CACHE，则会预先创建之前指定的/super节点
         如果参数为PathChildrenCache.StartMode.POST_INITIALIZED_EVENT，效果与BUILD_INITIAL_CACHE相同，只是不会预先创建/super节点
         参数为PathChildrenCache.StartMode.NORMAL时，与不填写参数是同样的效果，不会监听子节点的数据更新操作
         */
        childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        childrenCache.getListenable().addListener((framework, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                    System.out.println("CHILD_ADDED，类型：" + event.getType() + "，路径：" + event.getData().getPath() + "，数据：" +
                            new String(event.getData().getData()) + "，状态：" + event.getData().getStat());
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

        curator.create().forPath("/super", "123".getBytes());
        curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/super/c1", "c1内容".getBytes());
        //经测试，不会监听到本节点的数据变更，只会监听到指定节点下子节点数据的变更
        curator.setData().forPath("/super", "456".getBytes());
        Thread.sleep(2000);
        curator.setData().forPath("/super/c1", "c1新内容".getBytes());
        curator.delete().guaranteed().deletingChildrenIfNeeded().forPath("/super");
        Thread.sleep(5000);
        curator.close();
    }
}
