package tech.bingulhan.server.packet.impl.clientbound;

import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketState;
import tech.bingulhan.server.packet.PacketType;

public abstract class ClientBoundPacket extends Packet {

    public ClientBoundPacket(int id, PlayerClient client, PacketState state) {
        super(id, PacketType.SERVERBOUND, client, state);
    }

    public ClientBoundPacket(int id, PacketState state) {
        super(id, PacketType.SERVERBOUND, state);
    }

}
