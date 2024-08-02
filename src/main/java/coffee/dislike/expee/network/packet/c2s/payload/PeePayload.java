package coffee.dislike.expee.network.packet.c2s.payload;

import coffee.dislike.expee.ExpeeNetworkingConstants;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.payload.CustomPayload;

public record PeePayload(int ignoreme) implements CustomPayload {
    public static final PacketCodec<PacketByteBuf, PeePayload> CODEC = PacketCodec.tuple(PacketCodecs.INT, PeePayload::ignoreme, PeePayload::new);
    public static final CustomPayload.Id<PeePayload> ID = new CustomPayload.Id<>(ExpeeNetworkingConstants.PEE_PACKET_ID);

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}