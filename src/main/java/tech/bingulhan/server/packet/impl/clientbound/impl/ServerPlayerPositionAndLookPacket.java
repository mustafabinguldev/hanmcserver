package tech.bingulhan.server.packet.impl.clientbound.impl;

import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.impl.clientbound.ClientBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

import java.util.UUID;

public class ServerPlayerPositionAndLookPacket extends ClientBoundPacket {


    public ServerPlayerPositionAndLookPacket(PlayerClient client) {
        super(0x08 , client);
        ByteUtil.writeVarInt(getResponseBuffer(), 0x08);
    }

    public ServerPlayerPositionAndLookPacket() {
        super(0x08);
    }

    public void setData(double x, double y, double z, float yaw, float pitch) {

        getResponseBuffer().writeDouble(x);
        getResponseBuffer().writeDouble(y);
        getResponseBuffer().writeDouble(z);
        getResponseBuffer().writeFloat(yaw);
        getResponseBuffer().writeFloat(pitch);
        getResponseBuffer().writeBoolean(false);


    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new ServerDisconnectPacket(client);
    }

}
