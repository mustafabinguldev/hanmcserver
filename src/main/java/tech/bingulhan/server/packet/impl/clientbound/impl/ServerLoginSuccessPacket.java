package tech.bingulhan.server.packet.impl.clientbound.impl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.impl.clientbound.ClientBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

import java.io.IOException;
import java.util.UUID;

public class ServerLoginSuccessPacket extends ClientBoundPacket {


    public ServerLoginSuccessPacket(PlayerClient client) {
        super(0x02 ,client);
        ByteUtil.writeVarInt(getResponseBuffer(), 0x02);
    }

    public ServerLoginSuccessPacket() {
        super(0x02);
    }

    public void setUser(String username) {

            UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + username).getBytes());
            ByteUtil.writeString(getResponseBuffer(), uuid.toString());
            ByteUtil.writeString(getResponseBuffer(), username);


    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new ServerDisconnectPacket(client);
    }
}
