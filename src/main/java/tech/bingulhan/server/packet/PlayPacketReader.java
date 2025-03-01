package tech.bingulhan.server.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.MinecraftServer;

public class PlayPacketReader implements PacketReader{
    @Override
    public void read(ByteBuf buf, ChannelHandlerContext ctx, PlayerClient client, int packetId) {

        if (packetId==0) {
            MinecraftServer.queryListConnectList.remove(client);
        }

    }
}
