package tech.bingulhan.server.packet.impl.clientbound.impl;

import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketState;
import tech.bingulhan.server.packet.impl.clientbound.ClientBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

import java.util.concurrent.ThreadLocalRandom;

public class ServerKeepAlivePacket extends ClientBoundPacket {

    private int tlcr = ThreadLocalRandom.current().nextInt(129);

    public ServerKeepAlivePacket(PlayerClient client) {
        super(0x00 ,client, PacketState.LOGIN);
        ByteUtil.writeVarInt(getResponseBuffer(), 0x00);
    }

    public ServerKeepAlivePacket() {
        super(0x00, PacketState.LOGIN);
    }

    public ServerKeepAlivePacket setData() {
        ByteUtil.writeVarInt(getResponseBuffer(), tlcr);
        return this;
    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new ServerKeepAlivePacket(client);
    }
}
