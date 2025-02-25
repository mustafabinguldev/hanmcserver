package tech.bingulhan.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.util.ByteUtil;

import java.util.UUID;

@Getter
@Setter
public class PlayerClient {

    private String playerName="";
    private UUID uuid;

    private ChannelHandlerContext ctx;
    public PlayerClient(ChannelHandlerContext handlerContext) {
        this.ctx = handlerContext;
    }

    public void sendPacket(Packet packet) {

        ByteBuf response = Unpooled.buffer();
        int size = packet.getResponseBuffer().readableBytes();
        ByteUtil.writeVarInt(response, size);
        response.writeBytes(packet.getResponseBuffer());
        ctx.writeAndFlush(response);

    }

}
