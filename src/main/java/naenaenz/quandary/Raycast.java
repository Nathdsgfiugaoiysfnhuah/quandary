package naenaenz.quandary;

import net.minecraft.block.Blocks;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.List;

public class Raycast {
    public static Entity rayCast(Vec3d pos, Vec2f lookAngle, World world, Entity banned, int maxDist, boolean showParticles)
    {
        Vec3d moveVec = new Vec3d(Math.cos(lookAngle.x)*Math.cos(lookAngle.y),Math.sin(lookAngle.x)*Math.cos(lookAngle.y),Math.sin(lookAngle.y));
        int dist = maxDist;
        while (dist >= 0)
        {
            if(showParticles)
            {
                float offX = world.getRandom().nextFloat()-0.5f;
                float offY = world.getRandom().nextFloat()-0.5f;
                float offZ = world.getRandom().nextFloat()-0.5f;
                world.addParticle(ParticleTypes.ASH,pos.x-offX,pos.y-offY,pos.z-offZ,0,0,0);
                offX = world.getRandom().nextFloat()-0.5f;
                offY = world.getRandom().nextFloat()-0.5f;
                offZ = world.getRandom().nextFloat()-0.5f;
                world.addParticle(ParticleTypes.CRIT,pos.x-offX,pos.y-offY,pos.z-offZ,0,0,0);
                offX = world.getRandom().nextFloat()-0.5f;
                offY = world.getRandom().nextFloat()-0.5f;
                offZ = world.getRandom().nextFloat()-0.5f;
                world.addParticle(ParticleTypes.CLOUD,pos.x-offX,pos.y-offY,pos.z-offZ,0,0,0);
                offX = world.getRandom().nextFloat()-0.5f;
                offY = world.getRandom().nextFloat()-0.5f;
                offZ = world.getRandom().nextFloat()-0.5f;
                world.addParticle(ParticleTypes.ENCHANTED_HIT,pos.x-offX,pos.y-offY,pos.z-offZ,0,0,0);
            }
            dist -= 0.2;
            List<Entity> entities = world.getOtherEntities(banned,new Box(pos.x-0.125,pos.y-0.125,pos.z-0.125,pos.x+0.125,pos.y+0.125,pos.z+0.125));
            if(!entities.isEmpty())
            {
                return entities.get(0);
            }
            if (world.getBlockState(new BlockPos(pos.x,pos.y,pos.z)).getBlock().canMobSpawnInside())
            {
                return null;
            }
            pos = new Vec3d(moveVec.x*0.2+pos.x, moveVec.y*0.2+pos.y, moveVec.z*0.2+pos.z);
        }
        return null;
    }
}
