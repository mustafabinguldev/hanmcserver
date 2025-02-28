package tech.bingulhan.server.packet;

import lombok.Getter;
import tech.bingulhan.server.packet.impl.clientbound.impl.*;
import tech.bingulhan.server.packet.impl.serverbound.impl.ClientServerPongPacket;
import tech.bingulhan.server.packet.impl.serverbound.impl.HandshakePacket;

public enum PacketType {

    SERVERBOUND(new Packet[]{new HandshakePacket(), new ClientServerPongPacket()}),
    CLIENTBOUND(new Packet[]{
            new ServerClientPongPacket(),
            new ServerClientStatusPacket(),
            new ServerDisconnectPacket(),
            new ServerJoinPacket(),
            new ServerKeepAlivePacket(),
            new ServerLoginSuccessPacket(),
            new ServerPlayerPositionAndLookPacket()});

    @Getter
    private Packet[] packets;
    PacketType(Packet[] packets) {
        this.packets = packets;
    }

}
