package naenaenz.quandary.item;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;

public class GrapeItem extends Item {
    public GrapeItem(Settings settings) {
        super(settings.maxCount(16).food(new FoodComponent.Builder().hunger(2).saturationModifier(1f).snack().alwaysEdible().build()));
    }
}
