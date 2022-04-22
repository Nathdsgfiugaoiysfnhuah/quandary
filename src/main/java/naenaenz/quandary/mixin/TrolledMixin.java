package naenaenz.quandary.mixin;

import naenaenz.quandary.register.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(LivingEntity.class)

public abstract class TrolledMixin extends Entity {
    public TrolledMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void quandary$dropTrollMixin(CallbackInfo info) {
        if (!this.world.isClient) {
            if (this.world.getRandom().nextFloat()<0.005)
            {
                ItemEntity itemEntity = new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), new ItemStack(ItemRegister.TROLL));
                this.world.spawnEntity(itemEntity);
            }
        }
    }
}
