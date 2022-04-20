package naenaenz.quandary.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class DubEntity extends PathAwareEntity {
    public DubEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

}
