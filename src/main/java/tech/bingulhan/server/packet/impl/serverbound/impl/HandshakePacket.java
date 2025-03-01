package tech.bingulhan.server.packet.impl.serverbound.impl;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.MinecraftServer;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketState;
import tech.bingulhan.server.packet.impl.clientbound.impl.*;
import tech.bingulhan.server.packet.impl.serverbound.ServerBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

import java.nio.charset.Charset;

@Getter
@Setter
@ToString
public class HandshakePacket extends ServerBoundPacket {


    private int protocol;
    private String address;
    private int port;
    private int nextState = -1;

    public HandshakePacket() {
        super(0x00, PacketState.LOGIN);
    }

    public HandshakePacket(PlayerClient client) {
        super(0x00, client, PacketState.LOGIN);
    }


    @Override
    public void handler(ByteBuf buf) {
        this.protocol = ByteUtil.readVarInt(buf);


            int addressLength = ByteUtil.readVarInt(buf);
            this.address = buf.readCharSequence(addressLength, Charset.forName("UTF-8")).toString();
            this.port = buf.readUnsignedShort();
            this.nextState = ByteUtil.readVarInt(buf);
            if (nextState == 1) {
                ServerClientStatusPacket serverStatusPacket = new ServerClientStatusPacket(getClient());
                String serverName = "HanServer";
                String motd = "HanServer example motd!";
                int onlinePlayers = MinecraftServer.clients.size();
                int maxPlayers = 999;
                String version = "1.8.8";
                String jsonResponse = String.format(
                        "{\"version\":{\"name\":\"%s\",\"protocol\":47},\"players\":{\"max\":%d,\"online\":%d},\"description\":{\"text\":\"%s\"},\"favicon\":\"\"}",
                        version, maxPlayers, onlinePlayers, motd
                );

                serverStatusPacket.setMotd(jsonResponse);
                getClient().sendPacket(serverStatusPacket);
            }else if (nextState == 2) {
                int userNameLength = ByteUtil.readVarInt(buf);
                String userName = buf.readCharSequence(userNameLength,
                        Charset.forName("UTF-8")).toString();
                getClient().setState(PlayerClient.GameState.LOGIN);
                ServerLoginSuccessPacket serverLoginSuccessPacket = new ServerLoginSuccessPacket(getClient());
                serverLoginSuccessPacket.setUser(userName);
                getClient().sendPacket(serverLoginSuccessPacket);
                ServerJoinPacket joinPacket= new ServerJoinPacket(getClient());
                joinPacket.setData();
                getClient().sendPacket(joinPacket);
                ServerPlayerPositionAndLookPacket serverPlayerPositionAndLookPacket =
                        new ServerPlayerPositionAndLookPacket(getClient());
                serverPlayerPositionAndLookPacket.setData(0,0,0,0,0);
                getClient().sendPacket(serverPlayerPositionAndLookPacket);
                getClient().setState(PlayerClient.GameState.PLAY);
                MinecraftServer.clients.add(getClient());
            }


    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new HandshakePacket(client);
    }
}
