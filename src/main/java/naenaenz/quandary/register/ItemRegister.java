package naenaenz.quandary.register;

import naenaenz.quandary.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegister {
    public static final Item TROLL = new TrollItem(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item GRAPE = new GrapeItem(new FabricItemSettings().group(ItemGroup.FOOD));
    public static final Item RAPIER = new RapierItem(new FabricItemSettings().group(ItemGroup.COMBAT));
    public static final Item STAFF = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item GRUNK_CLUB = new GrunkClub(new FabricItemSettings().group(ItemGroup.COMBAT));
    public static final Item COPPER_BOOTS = new CopperArmour(EquipmentSlot.FEET);
    public static final Item COPPER_LEGGINGS = new CopperArmour(EquipmentSlot.LEGS);
    public static final Item COPPER_CHESTPLATE = new CopperArmour(EquipmentSlot.CHEST);
    public static final Item COPPER_HELMET = new CopperArmour(EquipmentSlot.HEAD);
    public static final Item MECHANICAL_CLUB = new MechanicalClub(new FabricItemSettings().group(ItemGroup.COMBAT));
    public static final Item COPPER_KNUCKLES = new CopperKnuckles(new FabricItemSettings().group(ItemGroup.COMBAT));
    public static final Item FLOWER_WAND = new FlowerWand(new FabricItemSettings().group(ItemGroup.COMBAT));
    public static final Item FLOWER_PETAL = new Item(new FabricItemSettings()); //This item is not real just to make it work

    public static void register()
    {
        Registry.register(Registry.ITEM, new Identifier("quandary", "troll"), TROLL);
        Registry.register(Registry.ITEM, new Identifier("quandary", "grape"), GRAPE);
        Registry.register(Registry.ITEM, new Identifier("quandary", "rapier"), RAPIER);
        Registry.register(Registry.ITEM, new Identifier("quandary", "staff"), STAFF);
        Registry.register(Registry.ITEM, new Identifier("quandary", "grunk_club"), GRUNK_CLUB);
        Registry.register(Registry.ITEM, new Identifier("quandary", "copper_boots"), COPPER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("quandary", "copper_leggings"), COPPER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("quandary", "copper_chestplate"), COPPER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("quandary", "copper_helmet"), COPPER_HELMET);
        Registry.register(Registry.ITEM, new Identifier("quandary", "mechanical_club"), MECHANICAL_CLUB);
        Registry.register(Registry.ITEM, new Identifier("quandary", "copper_knuckles"), COPPER_KNUCKLES);
        Registry.register(Registry.ITEM, new Identifier("quandary", "flower_wand"), FLOWER_WAND);
        Registry.register(Registry.ITEM, new Identifier("quandary", "flower_petal"), FLOWER_PETAL);
    }
}
