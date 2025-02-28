package tech.bingulhan.server.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.impl.serverbound.ServerBoundPacket;

public class LoginPacketReader implements PacketReader{

    @Override
    public void read(ByteBuf buf, ChannelHandlerContext ctx, PlayerClient client, int packetId) {

        for (Packet packet: PacketType.SERVERBOUND.getPackets()) {
            if (packetId != packet.getPacketId()) continue;
            if (packet.getState().equals(PacketState.LOGIN)) {
                ServerBoundPacket p = (ServerBoundPacket) packet.doBuild(client);
                p.handler(buf);
            }
        }
    }

}
