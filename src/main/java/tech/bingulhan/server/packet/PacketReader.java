package tech.bingulhan.server.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import tech.bingulhan.client.PlayerClient;

public interface PacketReader {

    void read(ByteBuf buf, ChannelHandlerContext ctx, PlayerClient client, int packetId);

    static PacketReader getPacketReader(PlayerClient client) {
        if (client.getState() == PlayerClient.GameState.LOGIN) return new LoginPacketReader();
        else return new PlayPacketReader();
    }
}
