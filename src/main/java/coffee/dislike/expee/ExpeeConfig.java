package coffee.dislike.expee;

import org.quiltmc.config.api.ReflectiveConfig;
import org.quiltmc.config.api.values.TrackedValue;
import org.quiltmc.loader.api.config.v2.QuiltConfig;

public class ExpeeConfig extends ReflectiveConfig {
    public static final ExpeeConfig INSTANCE = QuiltConfig.create(Expee.MOD_ID, Expee.MOD_ID, ExpeeConfig.class);

    public final TrackedValue<Double> worldAlignedPeePositionOffsetX = this.value(0d);
    public final TrackedValue<Double> worldAlignedPeePositionOffsetY = this.value(.5d);
    public final TrackedValue<Double> worldAlignedPeePositionOffsetZ = this.value(0d);

    public final TrackedValue<Double> peeInitialVelocity = this.value(.7d);

    public final TrackedValue<Integer> minimumRequiredTotalExperience = this.value(1);

    public final TrackedValue<Integer> experiencePointsCost = this.value(minimumRequiredTotalExperience.value());

    public final TrackedValue<Integer> peeExperienceAmount = this.value(minimumRequiredTotalExperience.value());

    public final TrackedValue<Boolean> roundPeeExperienceAmountToOrbSize = this.value(false);

    public final TrackedValue<Integer> experiencePickupDelay = this.value(3);
}