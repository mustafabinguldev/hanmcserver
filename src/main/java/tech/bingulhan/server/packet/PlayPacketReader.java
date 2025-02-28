package tech.bingulhan.server.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import tech.bingulhan.client.PlayerClient;

public class PlayPacketReader implements PacketReader{
    @Override
    public void read(ByteBuf buf, ChannelHandlerContext ctx, PlayerClient client, int packetId) {
        System.out.println("PACKET: "+packetId+" "+client.getPlayerName());
    }
}
