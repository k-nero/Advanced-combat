package com.kingsman.hyper.reg.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class WitherArmor extends ArmorItem
{

    public WitherArmor(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_)
    {
        super(p_40386_, p_40387_, p_40388_);
    }

    public static int hasCorrectArmorOn(ArmorMaterial material, Player player)
    {
        int correctArmors = 0;
        for (int i = 0; i < 4; i++)
        {
            if (!player.getInventory().getArmor(i).isEmpty())
            {
                ArmorItem armor = ((ArmorItem) player.getInventory().getArmor(i).getItem());
                if (armor.getMaterial().equals(material))
                {
                    correctArmors += 1;
                }
            }
        }
        return correctArmors;
    }
}
