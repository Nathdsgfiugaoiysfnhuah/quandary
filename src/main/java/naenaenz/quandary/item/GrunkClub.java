package naenaenz.quandary.item;

import naenaenz.quandary.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class GrunkClub extends GenericWeapon {

    public GrunkClub(Settings settings) {
        super(settings,0.3f,14f,2786);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR, SoundCategory.NEUTRAL, 1.5F, 0.3F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 150);
        for(int i = 0; i<35; i++)
        {
            float angle = (float) (world.getRandom().nextFloat() * Math.PI * 2);
            double dist = world.getRandom().nextDouble()*0.3+0.7;
            world.addParticle(ParticleTypes.EXPLOSION, user.getX() + Math.sin(angle) * 10 * dist, user.getY() + 0.2, user.getZ() + Math.cos(angle) * 10 * dist,0.0, 0.0, 0.0);
        }
        if(!world.isClient)
        {
            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();
            List<Entity> entityList = world.getOtherEntities(user,new Box(x-10,y-10,z-10,x+10,y+10,z+10));
            for(int i = 0; i<entityList.size(); i++)
            {
                Entity entity = entityList.get(i);
                double dist = entity.distanceTo(user);
                if (dist < 10.0)
                {
                    double mul = Math.min(3.1622777-Math.pow(dist,0.5),2) * 0.3;
                    double xDif = entity.getX() - user.getX();
                    double zDif = entity.getZ() - user.getZ();
                    double magnitude = Math.sqrt(Math.pow(xDif, 2) + Math.pow(zDif, 2));
                    //fix pearl cannon crash
                    if (magnitude < 0.00001)
                    {
                        magnitude = 0.00001;
                    }
                    double xNorm = xDif / magnitude;
                    double zNorm = zDif / magnitude;
                    if(!(entity instanceof ItemEntity))
                    {
                        entity.damage(DamageSource.player(user), (float) (10 * mul));
                    }
                    entity.addVelocity(xNorm * mul, 3 * mul, zNorm * mul);
                }
            }
        }
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
                itemStack.damage(10, user, entity -> entity.sendEquipmentBreakStatus(finalSlot));
            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
