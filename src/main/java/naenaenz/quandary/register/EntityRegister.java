package naenaenz.quandary.register;

//import naenaenz.quandary.entity.DubEntity;
import naenaenz.quandary.entity.projectile.FlowerPetal;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegister {
    public static final EntityType<FlowerPetal> FlowerSporeEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("quadary", "flower_spore"),
            FabricEntityTypeBuilder.<FlowerPetal>create(SpawnGroup.MISC, FlowerPetal::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile (hitbox)
                    .trackRangeBlocks(4).trackedUpdateRate(10) // Makes it work
                    .build() // build or it dont work :(
    );
//    public static final EntityType<DubEntity> DUB = Registry.register(
//            Registry.ENTITY_TYPE,
//            new Identifier("quandary", "dub"),
//            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DubEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
//    );

}
