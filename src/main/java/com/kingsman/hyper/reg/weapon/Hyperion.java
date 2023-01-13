package com.kingsman.hyper.reg.weapon;

import com.kingsman.hyper.reg.ability.InstantTransportation;
import com.kingsman.hyper.reg.ability.WitherImpact;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Hyperion extends WitherBlade
{
    private static final int baseAbilityDmg = 10;
    private static int AbilityDmg = 0;

    public static int getBaseAbilityDmg()
    {
        return baseAbilityDmg;
    }

    public static void setAbilityDmg(int abilityDmg)
    {
        AbilityDmg = abilityDmg;
    }

    public static int getAbilityDmg()
    {
        return AbilityDmg;
    }

    public Hyperion(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_)
    {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_40920_, @NotNull Player p_40921_, @NotNull InteractionHand p_40922_)
    {
        InstantTransportation transportation = new InstantTransportation(10, p_40921_);
        transportation.teleport();
        ItemStack itemstack = p_40921_.getItemInHand(p_40922_);
        if (p_40922_ == InteractionHand.MAIN_HAND)
        {
            new WitherImpact(p_40920_, p_40921_, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), 8, AbilityDmg, baseAbilityDmg);
        }
        return InteractionResultHolder.success(itemstack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_)
    {

        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TextComponent("§6Ability: Death's Touch §r"));
        p_41423_.add(new TextComponent("§7Upon hitting enemy inflict"));
        p_41423_.add(new TextComponent("§7the wither effect for 20 seconds."));
        p_41423_.add(new TextComponent(""));
        p_41423_.add(new TextComponent("§6Ability: Wither Impact §r§e§lRIGHT CLICK §r"));
        p_41423_.add(new TextComponent("§7When implode dealing §r").append(String.valueOf(AbilityDmg + baseAbilityDmg)).withStyle(ChatFormatting.AQUA));
        p_41423_.add(new TextComponent("§7damages to nearby enemies. "));
        p_41423_.add(new TextComponent("§7Also applies the Absorption effect§r"));
        p_41423_.add(new TextComponent("§7in 5 seconds.§r"));
        p_41423_.add(new TextComponent("§8Shield cool-downs: §r§35 seconds"));
        p_41423_.add(new TextComponent("§8Health cost: §r§c2 HP"));
    }
}
