package naenaenz.quandary.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EvilWood extends Block {
    public EvilWood() {
        super(FabricBlockSettings.of(Material.WOOD).strength(2.0f).resistance(3.0f).sounds(BlockSoundGroup.WOOD));
    }
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = ((LivingEntity)entity);
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS,30,0));
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,20,1));
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
