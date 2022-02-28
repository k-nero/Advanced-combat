package com.kingsman.hyper.reg.armor;

import com.kingsman.hyper.ProjectHyper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum WitherArmorMaterial implements ArmorMaterial
{
    WITHER("wither", 40, new int[]{5, 7, 9, 4}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.15F, () ->
    {
        return Ingredient.of(Items.WITHER_SKELETON_SKULL);
    }),
    STORM("storm", 40, new int[]{5, 7, 9, 4}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.15F, () ->
    {
        return Ingredient.of(Items.WITHER_SKELETON_SKULL);
    }),
    NECRON("necron", 40, new int[]{5, 7, 9, 4}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.15F, () ->
    {
        return Ingredient.of(Items.WITHER_SKELETON_SKULL);
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    WitherArmorMaterial(String name, int durabilityMultiplier, int[] slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient)
    {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    public int getDurabilityForSlot(EquipmentSlot p_40484_)
    {
        return HEALTH_PER_SLOT[p_40484_.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot p_40487_)
    {
        return this.slotProtections[p_40487_.getIndex()];
    }

    public int getEnchantmentValue()
    {
        return this.enchantmentValue;
    }

    public @NotNull SoundEvent getEquipSound()
    {
        return this.sound;
    }

    public @NotNull Ingredient getRepairIngredient()
    {
        return this.repairIngredient.get();
    }

    public @NotNull String getName()
    {
        return ProjectHyper.MODID + ":" + name;
    }

    public float getToughness()
    {
        return this.toughness;
    }

    public float getKnockbackResistance()
    {
        return this.knockbackResistance;
    }
}
