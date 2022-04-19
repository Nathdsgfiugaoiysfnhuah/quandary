package naenaenz.quandary.register;

import naenaenz.quandary.block.EvilWood;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegister {
    public static final EvilWood EVIL_WOOD = new EvilWood();
    public static void register()
    {
        //block
        Registry.register(Registry.BLOCK, new Identifier("quandary", "evil_wood"), EVIL_WOOD);
        //block item
        Registry.register(Registry.ITEM, new Identifier("quandary","evil_wood"), new BlockItem(EVIL_WOOD, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
