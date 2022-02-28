package com.kingsman.hyper.reg.armor;

import com.kingsman.hyper.reg.weapon.Hyperion;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class StormArmor extends WitherArmor
{
    private static final int intelligent = 10;
    private static int AbilityDamage = 0;

    public static int getAbilityDamage()
    {
        return AbilityDamage;
    }

    public static int getIntelligent()
    {
        return intelligent;
    }

    public static void setAbilityDamage(int abilityDamage)
    {
        AbilityDamage = abilityDamage;
    }

    public StormArmor(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_)
    {
        super(p_40386_, p_40387_, p_40388_);
    }

    private String setDescription()
    {
        switch (Objects.requireNonNull(slot))
        {
            case FEET -> {
                return " When on Feet";
            }
            case LEGS -> {
                return " When on Legs";
            }
            case CHEST -> {
                return " When on Chest";
            }
            case HEAD -> {
                return " When on Head";
            }
            default -> throw new IllegalStateException("Unexpected value: " + slot);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_)
    {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add((new TextComponent("+" + intelligent + " Ability damage ")).withStyle(ChatFormatting.AQUA));
    }

    public static boolean hasCorrectArmorOn(ArmorMaterial material, Player player)
    {
        AbilityDamage = 0;
        for (int i = 0; i < 4; i++)
        {
            if (!player.getInventory().getArmor(i).isEmpty())
            {
                ArmorItem armor = ((ArmorItem) player.getInventory().getArmor(i).getItem());
                if (armor.getMaterial().equals(material))
                {
                    AbilityDamage += intelligent;
                }
            }
        }
        return AbilityDamage != 0;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player)
    {
        super.onArmorTick(stack, world, player);
        if (!world.isClientSide)
        {
            Hyperion.setAbilityDmg(0);
            hasCorrectArmorOn(WitherArmorMaterial.STORM, player);
            Hyperion.setAbilityDmg(AbilityDamage);
        }
    }
}
