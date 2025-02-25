package tech.bingulhan.server.packet.impl.clientbound;

import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketType;

public abstract class ClientBoundPacket extends Packet {

    public ClientBoundPacket(int id, PlayerClient client) {
        super(id, PacketType.SERVERBOUND, client);
    }

    public ClientBoundPacket(int id) {
        super(id, PacketType.SERVERBOUND);
    }

}
