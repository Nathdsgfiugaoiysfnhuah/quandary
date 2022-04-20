package naenaenz.quandary.entity.projectile;

import naenaenz.quandary.ClientMain;
import naenaenz.quandary.EntitySpawnPacket;
import naenaenz.quandary.register.EntityRegister;
import naenaenz.quandary.register.ItemRegister;
import naenaenz.quandary.register.ParticleRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class FlowerPetal extends ThrownItemEntity {
    @Override
    protected Item getDefaultItem() {
        return ItemRegister.FLOWER_PETAL;
    }
    public FlowerPetal(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public FlowerPetal(World world, LivingEntity owner) {
        super(EntityRegister.FlowerSporeEntityType, owner, world);
    }

    public FlowerPetal(World world, double x, double y, double z) {
        super(EntityRegister.FlowerSporeEntityType, x, y, z, world);
    }
    protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity(); // sets a new Entity instance as the EntityHitResult (victim)
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float)11); // deals damage
    }

    protected void onCollision(HitResult hitResult) { // called on collision with a block
        super.onCollision(hitResult);
        if (!this.world.isClient) { // checks if the world is client
            this.world.sendEntityStatus(this, (byte)3); // particle?
            this.kill(); // kills the projectile
        }

    }
    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, ClientMain.PacketID);
    }

    public void tick()
    {
        super.tick();
        double x = this.getX() + world.getRandom().nextDouble() - 0.5;
        double y = this.getY() + world.getRandom().nextDouble() - 0.5;
        double z = this.getZ() + world.getRandom().nextDouble() - 0.5;
        world.addParticle(ParticleRegister.FLOWER_PETAL_PARTICLE, x, y, z,0f,0f,0f);
    }
}
