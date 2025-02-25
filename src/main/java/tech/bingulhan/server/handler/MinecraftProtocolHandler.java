package tech.bingulhan.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketType;
import tech.bingulhan.server.packet.impl.serverbound.ServerBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

public class MinecraftProtocolHandler extends ChannelInboundHandlerAdapter
{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("Bağlantı kuruldu.");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


            ByteBuf byteBuf = (ByteBuf) msg;
            int packetLength = ByteUtil.readVarInt(byteBuf);
            int packetId = ByteUtil.readVarInt(byteBuf);
            for (Packet handlePacket : PacketType.SERVERBOUND.getPackets()) {
                if (handlePacket.getPacketId() == packetId) {
                    PlayerClient client = new PlayerClient(ctx);
                    ServerBoundPacket packet = (ServerBoundPacket) handlePacket.doBuild(client);
                    packet.handler(byteBuf);
                }
            }


    }


}
