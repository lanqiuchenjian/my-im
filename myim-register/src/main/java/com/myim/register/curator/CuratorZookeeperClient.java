package com.myim.register.curator;

import com.myim.register.ZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.KeeperException.NodeExistsException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

//TODO: 断线重试
public class CuratorZookeeperClient implements ZookeeperClient {
    //设定相同路径只有一个监听
    private Map<String, Map<PathChildrenCache, PathChildrenCacheListener>> map = new ConcurrentHashMap<>();

    private final CuratorFramework client;

    public CuratorZookeeperClient(String connectionUrl) {
        try {
            RetryPolicy policy = new ExponentialBackoffRetry(1000, 10);
            client = CuratorFrameworkFactory.builder().connectString(connectionUrl)
                    .sessionTimeoutMs(5000).retryPolicy(policy).build();

            client.start();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void create(String path, boolean ephemeral, String value) {
        try {
            if (ephemeral) {
                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, value.getBytes());
            } else {
                client.create().creatingParentContainersIfNeeded().forPath(path);
            }
        }catch (NodeExistsException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void delete(String path) {
        try {
            client.delete().forPath(path);
        } catch (NoNodeException e) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public List<String> getChildren(String path) {
        try {
            return client.getChildren().forPath(path);
        } catch (NoNodeException e) {
            return null;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public boolean checkExists(String path) {
        try {
            if (client.checkExists().forPath(path) != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    public boolean isConnected() {
        return client.getZookeeperClient().isConnected();
    }

    public void close() {
        client.close();
    }

    public void addChildListener(String path, PathChildrenCacheListener listener) {
        try {
            if (map.containsKey(path))
                throw new RuntimeException("exist path");

            //第三个参数表示是否接收节点数据内容
            PathChildrenCache cache = new PathChildrenCache(client, path, true);
            /**
             * 如果不填写这个参数，则无法监听到子节点的数据更新
             如果参数为PathChildrenCache.StartMode.BUILD_INITIAL_CACHE，则会预先创建之前指定的/super节点
             如果参数为PathChildrenCache.StartMode.POST_INITIALIZED_EVENT，效果与BUILD_INITIAL_CACHE相同，只是不会预先创建/super节点
             参数为PathChildrenCache.StartMode.NORMAL时，与不填写参数是同样的效果，不会监听子节点的数据更新操作
             */
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener(listener);
            ConcurrentHashMap<PathChildrenCache, PathChildrenCacheListener> value = new ConcurrentHashMap<>();
            value.put(cache, listener);
            map.put(path, value);
        } catch (NoNodeException ignored) {
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void removeChildListener(String path) {
        Map<PathChildrenCache, PathChildrenCacheListener> ncncl = map.get(path);
        Set<PathChildrenCache> nodeCaches = ncncl.keySet();
        for (PathChildrenCache nc : nodeCaches) {
            nc.getListenable().removeListener(ncncl.get(nc));
        }
    }
}
