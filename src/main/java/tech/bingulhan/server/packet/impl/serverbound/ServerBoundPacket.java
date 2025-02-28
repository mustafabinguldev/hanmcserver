package tech.bingulhan.server.packet.impl.serverbound;

import io.netty.buffer.ByteBuf;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketState;
import tech.bingulhan.server.packet.PacketType;

public abstract class ServerBoundPacket extends Packet {

    public ServerBoundPacket(int id, PlayerClient client, PacketState state) {
        super(id, PacketType.SERVERBOUND, client, state);
    }

    public ServerBoundPacket(int id, PacketState state) {
        super(id, PacketType.SERVERBOUND, state);
    }


    public abstract void handler(ByteBuf buf);
}
