package com.rydelfox.morestoragedrawers.network;

import com.rydelfox.morestoragedrawers.MoreStorageDrawers;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MoreStorageDrawersPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(MoreStorageDrawers.rl("main_channel"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    public static void init() {
        INSTANCE.registerMessage(0, ItemUpdateMessage.class, ItemUpdateMessage::encode, ItemUpdateMessage::decode, ItemUpdateMessage::handle);
    }
}
