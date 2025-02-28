package tech.bingulhan.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.MinecraftServer;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketReader;
import tech.bingulhan.server.packet.PacketType;
import tech.bingulhan.server.packet.impl.serverbound.ServerBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

import java.util.Optional;

public class MinecraftProtocolHandler extends ChannelInboundHandlerAdapter
{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;
            int packetLength = ByteUtil.readVarInt(byteBuf);
            int packetId = ByteUtil.readVarInt(byteBuf);
            Optional<PlayerClient> optionalPlayerClient = MinecraftServer.clients.stream().
               filter(c->c.getSocketAddress().equals(ctx.channel().remoteAddress().toString())).findAny();
                    PlayerClient client;
                    client = optionalPlayerClient.orElseGet(() -> new PlayerClient(ctx));
            PacketReader.getPacketReader(client).read(byteBuf, ctx, client, packetId);
    }


}
