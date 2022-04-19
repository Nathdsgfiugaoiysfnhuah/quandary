package naenaenz.quandary.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import naenaenz.quandary.material.CopperMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;

import java.util.UUID;

public class CopperArmour extends ArmorItem {
    private static final UUID[] MODIFIERS = new UUID[]
            {UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    private final ImmutableMultimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public CopperArmour(EquipmentSlot slot) {
        super(new CopperMaterial(), slot, new FabricItemSettings().group(ItemGroup.COMBAT));
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        UUID uUID = MODIFIERS[slot.getEntitySlotId()];
        builder.
                put(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                    new EntityAttributeModifier(uUID, "MS", 0.0125D, EntityAttributeModifier.Operation.ADDITION)).
                put(EntityAttributes.GENERIC_ARMOR,
                        new EntityAttributeModifier(uUID, "MS", new CopperMaterial().getProtectionAmount(slot), EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == this.slot ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }
}
