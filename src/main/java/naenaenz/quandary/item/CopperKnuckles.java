package naenaenz.quandary.item;

import naenaenz.quandary.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CopperKnuckles extends Item {

    public CopperKnuckles(Settings settings) {
        super(settings);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.hurtTime = 0;
        target.maxHurtTime = 0;
        Main.LOGGER.info(String.valueOf(target.hurtTime));
        return false;
    }
}
