package tech.bingulhan.server.packet;

import lombok.Getter;
import tech.bingulhan.server.packet.impl.clientbound.impl.ServerClientPongPacket;
import tech.bingulhan.server.packet.impl.clientbound.impl.ServerClientStatusPacket;
import tech.bingulhan.server.packet.impl.serverbound.impl.ClientServerPongPacket;
import tech.bingulhan.server.packet.impl.serverbound.impl.HandshakePacket;

public enum PacketType {

    SERVERBOUND(new Packet[]{new HandshakePacket(), new ClientServerPongPacket()}),
    CLIENTBOUND(new Packet[]{new ServerClientPongPacket(), new ServerClientStatusPacket()});

    @Getter
    private Packet[] packets;
    PacketType(Packet[] packets) {
        this.packets = packets;
    }

}
