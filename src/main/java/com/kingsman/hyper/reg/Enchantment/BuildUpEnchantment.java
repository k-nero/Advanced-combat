package com.kingsman.hyper.reg.Enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BuildUpEnchantment extends Enchantment
{
    public BuildUpEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot... p_44678_)
    {
        super(p_44676_, p_44677_, p_44678_);
    }

    @Override
    public int getMaxLevel()
    {
        return 3;
    }

    @Override
    public int getMinLevel()
    {
        return 1;
    }

    @Override
    public boolean isTreasureOnly()
    {
        return true;
    }
}
