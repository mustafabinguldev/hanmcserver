package tech.bingulhan.server.packet.impl.clientbound.impl;

import lombok.Getter;
import lombok.Setter;
import tech.bingulhan.client.PlayerClient;
import tech.bingulhan.server.packet.Packet;
import tech.bingulhan.server.packet.PacketState;
import tech.bingulhan.server.packet.impl.clientbound.ClientBoundPacket;
import tech.bingulhan.server.util.ByteUtil;


@Getter
@Setter
public class ServerClientStatusPacket extends ClientBoundPacket {

    private String json;

    public ServerClientStatusPacket(PlayerClient client) {
        super(0x00,client, PacketState.LOGIN);
        ByteUtil.writeVarInt(getResponseBuffer(), 0x00);
    }

    public ServerClientStatusPacket() {
        super(0x00, PacketState.LOGIN);
    }

    public void setMotd(String json) {
        this.json = json;
        ByteUtil.writeString(getResponseBuffer(), this.json);
    }

    @Override
    public Packet doBuild(PlayerClient client) {
        return new ServerClientStatusPacket(client);
    }
}
