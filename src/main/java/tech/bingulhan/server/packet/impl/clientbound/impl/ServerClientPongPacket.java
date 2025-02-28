package tech.bingulhan.server.packet.impl.clientbound.impl;

import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketState;
import tech.bingulhan.server.packet.impl.clientbound.ClientBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

public class ServerClientPongPacket extends ClientBoundPacket {

    private long pong;

    public ServerClientPongPacket(PlayerClient client) {
        super(0x01, client, PacketState.PLAY);
        ByteUtil.writeVarInt(getResponseBuffer(), 0x01);

    }

    public ServerClientPongPacket() {
        super(0x01, PacketState.PLAY);
    }

    public void setPong(long pong) {
        this.pong = pong;
        getResponseBuffer().writeLong(pong);
    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new ServerClientPongPacket(client);
    }



}
