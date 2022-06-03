package naenaenz.quandary;

import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class Raycast {
    public static Entity rayCast(Vec3d pos, Vec2f lookAngle, World world, Entity banned, int maxDist, boolean showParticles)
    {
        Vec3d moveVec = new Vec3d(-Math.sin(lookAngle.x)*Math.cos(lookAngle.y),-Math.sin(lookAngle.y),Math.cos(lookAngle.x)*Math.cos(lookAngle.y));
        int dist = maxDist;
        while (dist >= 0)
        {
            if(showParticles)
            {
                float offX = world.getRandom().nextFloat()-0.5f;
                float offY = world.getRandom().nextFloat()-0.5f;
                float offZ = world.getRandom().nextFloat()-0.5f;
                world.addParticle(ParticleTypes.SMOKE,pos.x-offX,pos.y-offY,pos.z-offZ,0,0,0);
            }
            dist -= 1.0;
            List<Entity> entities = world.getOtherEntities(banned,new Box(pos.x-0.6,pos.y-0.6,pos.z-0.6,pos.x+0.6,pos.y+0.6,pos.z+0.6));
            if(!entities.isEmpty())
            {
                Main.LOGGER.info("hit an entity");
                return entities.get(0);
            }
            if (!world.getBlockState(new BlockPos(pos.x,pos.y,pos.z)).getBlock().canMobSpawnInside())
            {
                Main.LOGGER.info("hit a block");
                return null;
            }
            pos = new Vec3d(moveVec.x+pos.x, moveVec.y+pos.y, moveVec.z+pos.z);
        }
        return null;
    }
}
