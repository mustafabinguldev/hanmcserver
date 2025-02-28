package tech.bingulhan.server.packet.impl.serverbound.impl;

import io.netty.buffer.ByteBuf;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketState;
import tech.bingulhan.server.packet.impl.clientbound.impl.ServerClientPongPacket;
import tech.bingulhan.server.packet.impl.serverbound.ServerBoundPacket;
import tech.bingulhan.server.util.ByteUtil;


public class ClientServerPongPacket extends ServerBoundPacket {

    public ClientServerPongPacket() {
        super(0x01, PacketState.LOGIN);
    }

    public ClientServerPongPacket(PlayerClient client) {
        super(0x01, client, PacketState.LOGIN);
    }


    @Override
    public void handler(ByteBuf buf) {
        long payload = buf.readLongLE();
        ServerClientPongPacket packet = new ServerClientPongPacket(getClient());
        packet.setPong(payload);
        getClient().sendPacket(packet);
    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new ClientServerPongPacket(client);
    }
}
