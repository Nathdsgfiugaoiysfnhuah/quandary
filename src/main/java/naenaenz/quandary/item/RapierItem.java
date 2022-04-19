package naenaenz.quandary.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Random;

public class RapierItem extends GenericWeapon {
    public RapierItem(Settings settings) {
        super(settings,2,5,423);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        float speed = 1.0f;
        float p = (float) Math.toRadians(user.getPitch());
        float y = (float) Math.toRadians(user.getYaw());
        user.addVelocity(-Math.cos(p)*Math.sin(y)*speed,-0.5*p*speed,Math.cos(p)*Math.cos(y)*speed);
        user.getItemCooldownManager().set(this, 80);
        if(!user.isCreative())
        {
            if(!world.isClient)
            {
                EquipmentSlot slot = null;
                switch (hand)
                {
                    case MAIN_HAND:
                        slot = EquipmentSlot.MAINHAND;
                        break;
                    case OFF_HAND:
                        slot = EquipmentSlot.OFFHAND;
                        break;
                }

                EquipmentSlot finalSlot = slot;
                itemStack.damage(5, user, entity -> entity.sendEquipmentBreakStatus(finalSlot));            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
