package com.kingsman.hyper.reg.Enchantment;

import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class LifeStealEnchantment extends Enchantment
{
    private static double finalDmg = 0;
    private static final double baseStat = 0.05;

    public LifeStealEnchantment(Rarity p_44676_, EquipmentSlot... p_44678_)
    {
        super(p_44676_, EnchantmentCategory.WEAPON, p_44678_);
    }

    public static double getBaseStat()
    {
        return baseStat;
    }

    public static double getFinalDmg()
    {
        return finalDmg;
    }

    public static void setFinalDmg(double finalDmg)
    {
        LifeStealEnchantment.finalDmg = finalDmg;
    }

    @Override
    public int getMinLevel()
    {
        return 1;
    }

    @Override
    public int getMaxLevel()
    {
        return 5;
    }

    public static void LifeSteal(LivingEntity player, LivingEntity target)
    {
        float maxHP = player.getMaxHealth();
        float HP = player.getHealth();
        double finalHeal = 0;
        double LF = EnchantmentHelper.getItemEnchantmentLevel(RegistryHandler.LIFE_STEAL.get(), player.getMainHandItem()) * LifeStealEnchantment.getBaseStat();
        if (LF >= 0)
        {
            finalHeal = LF * finalDmg;
        }
        player.heal((float) finalHeal);
    }
}
