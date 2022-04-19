package naenaenz.quandary.item;

import naenaenz.quandary.entity.FlowerPetal;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Random;

public class FlowerWand extends Item {

    public FlowerWand(Settings settings) {
        super(settings.maxCount(1).maxDamage(1723));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(itemStack.getItem(),15);
        world.playSound((PlayerEntity)null, user.getX(),user.getY(),user.getZ(), SoundEvents.BLOCK_AZALEA_STEP, SoundCategory.NEUTRAL,1.0F,0.5F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        world.playSound((PlayerEntity)null, user.getX(),user.getY(),user.getZ(), SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.NEUTRAL,1.0F,1.3F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!world.isClient) {
            FlowerPetal flowerPetal = new FlowerPetal(world, user);
            flowerPetal.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 4.0F, 0.0F);
            world.spawnEntity(flowerPetal);
        }

        if (!user.isCreative())
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
                itemStack.damage(1, user, entity -> entity.sendEquipmentBreakStatus(finalSlot));
            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}