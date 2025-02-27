package tech.bingulhan.server.packet.impl.clientbound.impl;

import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.impl.clientbound.ClientBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

import java.util.UUID;

public class ServerJoinPacket extends ClientBoundPacket {


    public ServerJoinPacket(PlayerClient client) {
        super(0x01 ,client);
        ByteUtil.writeVarInt(getResponseBuffer(), 0x01);
    }

    public ServerJoinPacket() {
        super(0x01);
    }

    public void setData() {

        getResponseBuffer().writeInt(0);
        getResponseBuffer().writeByte(0);
        getResponseBuffer().writeByte(0);
        getResponseBuffer().writeByte(0);
        getResponseBuffer().writeByte(10);
        ByteUtil.writeString(getResponseBuffer(), "default");
        getResponseBuffer().writeBoolean(false);

    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new ServerJoinPacket(client);
    }
}
