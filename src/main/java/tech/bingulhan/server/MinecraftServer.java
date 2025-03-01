package tech.bingulhan.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.handler.MinecraftProtocolHandler;
import tech.bingulhan.server.packet.impl.clientbound.impl.ServerKeepAlivePacket;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MinecraftServer {

    int port;

    private TimerTask timerTask;
    private Timer keepAliveTask;

    public MinecraftServer(int port) {
        this.port = port;
    }

    public static List<PlayerClient> clients = new ArrayList<>();
    public static List<PlayerClient> queryListConnectList = new ArrayList<>();

    public void start() throws InterruptedException {

        timerTask = new TimerTask() {
            @Override
            public void run() {
                queryListConnectList.clear();
                for (PlayerClient playerClient : clients) {
                    queryListConnectList.add(playerClient);
                    playerClient.sendPacket(new ServerKeepAlivePacket(playerClient).setData());
                }
                new Timer("queryTimer").schedule(new TimerTask() {
                    @Override
                    public void run() {
                        for (PlayerClient client : queryListConnectList) {
                            clients.remove(client);
                        }
                    }
                }, 1000);
            }
        };
        keepAliveTask = new Timer("keepalive");
        keepAliveTask.schedule(timerTask, 0, 5000);

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MinecraftProtocolHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
