package com.myim.client.connect;

import com.myim.client.model.Protobufable;

public interface ConnectionStrategy {
    void connect(final String host, final int port);

    void send(final Protobufable body);

//    ConnectionStrategy getManager();
}
