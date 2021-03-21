package com.myim.register;

import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import java.util.List;

public interface ZookeeperClient {

    void create(String path, boolean ephemeral, String value);

    void delete(String path);

    List<String> getChildren(String path);

    void addChildListener(String path, PathChildrenCacheListener listener);

    void removeChildListener(String path);

    public boolean checkExists(String path);

    boolean isConnected();

    void close();
}
