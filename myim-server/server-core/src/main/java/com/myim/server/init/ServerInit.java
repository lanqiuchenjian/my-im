package com.myim.server.init;

import com.myim.register.ZookeeperClient;
import com.myim.register.curator.CuratorZookeeperClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author cj
 */
@Component
public class ServerInit {
//    @PostConstruct
    public void init() throws UnknownHostException {
        String host = InetAddress.getLocalHost().getHostAddress();
        String port = "34567";
        String address = host+ ":" + port;

        ZookeeperClient zookeeperClient = new CuratorZookeeperClient("127.0.0.1:2181");
        zookeeperClient.create("/myim/host", false, "ni hao a");
        zookeeperClient.create("/myim/host/server", true, address);
    }
}
