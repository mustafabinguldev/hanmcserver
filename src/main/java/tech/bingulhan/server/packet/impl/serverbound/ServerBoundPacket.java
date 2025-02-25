package tech.bingulhan.server.packet.impl.serverbound;

import io.netty.buffer.ByteBuf;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketType;

public abstract class ServerBoundPacket extends Packet {

    public ServerBoundPacket(int id, PlayerClient client) {
        super(id, PacketType.SERVERBOUND, client);
    }

    public ServerBoundPacket(int id) {
        super(id, PacketType.SERVERBOUND);
    }


    public abstract void handler(ByteBuf buf);
}
