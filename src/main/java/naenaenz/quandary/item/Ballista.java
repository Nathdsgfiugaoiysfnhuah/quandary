package naenaenz.quandary.item;

import naenaenz.quandary.Raycast;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.World;

public class Ballista extends Item {


    public Ballista(Settings settings) {
        super(settings.group(ItemGroup.COMBAT).maxDamage(1712));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        Entity hitEntity = Raycast.rayCast(user.getPos(), new Vec2f((float) Math.toRadians(user.getYaw()), (float) Math.toRadians(user.getPitch())), world, user,200,true);
        if(hitEntity != null)
        {
            if(!world.isClient)
            {
                hitEntity.damage(DamageSource.player(user),39);
            }
        }
        user.getItemCooldownManager().set(itemStack.getItem(),0);
        if(!user.isCreative())
        {
            if(!world.isClient)
            {
                EquipmentSlot slot = switch (hand) {
                    case MAIN_HAND -> EquipmentSlot.MAINHAND;
                    case OFF_HAND -> EquipmentSlot.OFFHAND;
                };

                EquipmentSlot finalSlot = slot;
                itemStack.damage(1, user, entity -> entity.sendEquipmentBreakStatus(finalSlot));            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}