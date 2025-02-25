package tech.bingulhan.server.packet.impl.clientbound.impl;

import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.impl.clientbound.ClientBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

import java.util.UUID;

public class ServerJoinPacket extends ClientBoundPacket {


    public ServerJoinPacket(PlayerClient client) {
        super(0x23 ,client);
        ByteUtil.writeVarInt(getResponseBuffer(), 0x23);
    }

    public ServerJoinPacket() {
        super(0x23);
    }

    public void setData() {


    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new ServerDisconnectPacket(client);
    }
}
