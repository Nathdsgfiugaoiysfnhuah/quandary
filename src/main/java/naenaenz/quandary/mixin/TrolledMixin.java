package naenaenz.quandary.mixin;

import naenaenz.quandary.register.ItemRegister;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(LivingEntity.class)

public class TrolledMixin {
    @Inject(at = @At("HEAD"), method = "onDeath()V")
    private void init(CallbackInfo info) {
        if (!((LivingEntity)(Object)this).world.isClient) {
            LivingEntity fakeThis = ((LivingEntity) (Object) this);
            if (fakeThis.world.getRandom().nextFloat()<0.005)
            {
                ItemEntity itemEntity = new ItemEntity(fakeThis.world, fakeThis.getX(), fakeThis.getY(), fakeThis.getZ(), new ItemStack(ItemRegister.TROLL));
                fakeThis.world.spawnEntity(itemEntity);
            }
        }
    }
}
