package tech.bingulhan.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.Setter;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.impl.clientbound.impl.ServerKeepAlivePacket;
import tech.bingulhan.server.util.ByteUtil;

import java.util.TimerTask;
import java.util.UUID;

@Getter
@Setter
public class PlayerClient {

    private String playerName;
    private UUID uuid;

    private ChannelHandlerContext ctx;

    private String socketAddress;

    private TimerTask timerTask;

    private GameState state = GameState.LOGIN;

    public PlayerClient(ChannelHandlerContext handlerContext) {
        this.ctx = handlerContext;

        this.socketAddress = ctx.channel().remoteAddress().toString();
    }

    public void sendPacket(Packet packet) {

        ByteBuf response = Unpooled.buffer();
        int size = packet.getResponseBuffer().readableBytes();
        ByteUtil.writeVarInt(response, size);
        response.writeBytes(packet.getResponseBuffer());
        ctx.writeAndFlush(response);

    }

    public enum GameState {
        LOGIN, PLAY
    }



}
