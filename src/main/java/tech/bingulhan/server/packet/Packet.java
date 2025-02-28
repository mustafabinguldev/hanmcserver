package tech.bingulhan.server.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.util.ByteUtil;

public abstract class Packet {

    @Getter
    private int packetId;

    @Getter
    private PacketType type;

    @Getter
    private PacketState state;

    @Getter
    private ByteBuf responseBuffer = Unpooled.buffer();

    @Getter
    private PlayerClient client;
    public Packet(int packetId, PacketType type, PacketState state) {
        this.packetId = packetId;
        this.type = type;
        this.state = state;
    }

    public Packet(int packetId, PacketType type, PlayerClient client, PacketState state) {
        this.packetId = packetId;
        this.type = type;
        this.client = client;
        this.state = state;
    }

    public Packet() {

    }

    public Packet(PlayerClient client) {
        this.client = client;
    }

    public byte[] getData() {
        return responseBuffer.array();
    }

    public abstract Packet doBuild(PlayerClient client);



}
