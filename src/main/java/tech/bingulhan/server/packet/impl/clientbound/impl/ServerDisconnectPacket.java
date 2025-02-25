package tech.bingulhan.server.packet.impl.clientbound.impl;

import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.impl.clientbound.ClientBoundPacket;
import tech.bingulhan.server.util.ByteUtil;

public class ServerDisconnectPacket extends ClientBoundPacket {

    private String json;

    public ServerDisconnectPacket(PlayerClient client) {
        super(0x00 ,client);
        ByteUtil.writeVarInt(getResponseBuffer(), 0x00);
    }

    public ServerDisconnectPacket() {
        super(0x00);
    }

    public void setMessage(String json) {
        this.json = json;
        ByteUtil.writeString(getResponseBuffer(), this.json);
    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new ServerDisconnectPacket(client);
    }

}
