package coffee.dislike.expee;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import coffee.dislike.expee.network.packet.c2s.payload.PeePayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.util.math.Vec3d;

public class Expee implements ModInitializer {
	public static final String MOD_ID = "expee";

    @Override
	public void onInitialize(ModContainer mod) {
		PayloadTypeRegistry.playC2S().register(PeePayload.ID, PeePayload.CODEC);

		
		ServerPlayNetworking.registerGlobalReceiver(PeePayload.ID, (payload, context) -> {
            final Vec3d peeSpawnPosition = context.player().getPos().add(ExpeeConfig.INSTANCE.worldAlignedPeePositionOffsetX.value(), ExpeeConfig.INSTANCE.worldAlignedPeePositionOffsetY.value(), ExpeeConfig.INSTANCE.worldAlignedPeePositionOffsetZ.value());
            final Vec3d peeSpawnVelocity = context.player().getRotationVector().multiply(ExpeeConfig.INSTANCE.peeInitialVelocity.value());

            if (context.player().totalExperience >= ExpeeConfig.INSTANCE.minimumRequiredTotalExperience.value()) {
                context.player().addExperience(-ExpeeConfig.INSTANCE.experiencePointsCost.value());;
                context.player().experiencePickUpDelay = ExpeeConfig.INSTANCE.experiencePickupDelay.value();
                
                if (ExpeeConfig.INSTANCE.roundPeeExperienceAmountToOrbSize.value()) {
                    int remainingExperienceAmount = ExpeeConfig.INSTANCE.peeExperienceAmount.value();
    
                    while (remainingExperienceAmount > 0) {
                        final int roundedExperienceAmount = ExperienceOrbEntity.roundToOrbSize(remainingExperienceAmount);
                        remainingExperienceAmount -= roundedExperienceAmount;
    
                        ExperienceOrbEntity newPee = new ExperienceOrbEntity(context.player().getServerWorld(), peeSpawnPosition.getX(), peeSpawnPosition.getY(), peeSpawnPosition.getZ(), roundedExperienceAmount);
                        newPee.setVelocity(peeSpawnVelocity);
                        context.player().getServerWorld().spawnEntity(newPee);
                    }
                } else {
                    ExperienceOrbEntity newPee = new ExperienceOrbEntity(context.player().getServerWorld(), peeSpawnPosition.getX(), peeSpawnPosition.getY(), peeSpawnPosition.getZ(), ExpeeConfig.INSTANCE.peeExperienceAmount.value());
                    newPee.setVelocity(peeSpawnVelocity);
                    context.player().getServerWorld().spawnEntity(newPee);
                }
            }
            

        });
	}
    
}
