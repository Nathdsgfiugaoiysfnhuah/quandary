package naenaenz.quandary.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
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

public class MechanicalClub extends GenericWeapon {

    public MechanicalClub(Settings settings) {
        super(settings,0.3f,28f,5357);
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.NEUTRAL, 1.5F, 0.3F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 250);
        for(int i = 0; i<100; i++)
        {
            float angle = (float) (world.getRandom().nextFloat() * Math.PI * 2);
            double dist = world.getRandom().nextDouble()*0.8+0.2;
            world.addParticle(ParticleTypes.ENCHANTED_HIT, user.getX() + Math.sin(angle) * 16 * dist, user.getY() + 0.3, user.getZ() + Math.cos(angle) * 16 * dist,0.0, 2.0, 2.0);
            dist = world.getRandom().nextDouble()*0.7+0.3;
            world.addParticle(ParticleTypes.EXPLOSION, user.getX() + Math.sin(angle) * 16 * dist, user.getY() + 0.2, user.getZ() + Math.cos(angle) * 16 * dist,0.0, 0.0, 0.0);
            dist = world.getRandom().nextDouble()*0.6+0.4;
            world.addParticle(ParticleTypes.EXPLOSION_EMITTER, user.getX() + Math.sin(angle) * 16 * dist, user.getY() + 0.2, user.getZ() + Math.cos(angle) * 16 * dist,0.0, 0.0, 0.0);
        }
        if(!world.isClient)
        {
            double x = user.getX();
            double y = user.getY();
            double z = user.getZ();
            List<Entity> entityList = world.getOtherEntities(user,new Box(x-16,y-16,z-16,x+16,y+16,z+16));
            for(int i = 0; i<entityList.size(); i++)
            {
                Entity entity = entityList.get(i);
                double dist = entity.distanceTo(user);
                if (dist < 16.0)
                {
                    double mul = Math.min(4-Math.pow(dist,0.5),3) * 0.4;
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
                        entity.damage(DamageSource.player(user), (float) (24 * mul));
                    }
                    entity.addVelocity(xNorm * mul, 3 * mul, zNorm * mul);
                    if (entity instanceof LivingEntity)
                    {
                        LivingEntity livingEntity = ((LivingEntity)entity);
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,80,0));
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,80,1));
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,80,1));
                    }
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
                itemStack.damage(15, user, entity -> entity.sendEquipmentBreakStatus(finalSlot));            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
