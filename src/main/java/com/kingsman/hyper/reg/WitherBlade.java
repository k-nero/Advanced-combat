package com.kingsman.hyper.reg;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WitherBlade extends SwordItem
{
    public WitherBlade(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_)
    {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack p_43278_, LivingEntity p_43279_, @NotNull LivingEntity p_43280_)
    {
        p_43279_.addEffect(new MobEffectInstance(MobEffects.WITHER, 500), p_43280_);
        return super.hurtEnemy(p_43278_, p_43279_, p_43280_);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_)
    {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TextComponent("\u00A76Ability: Death's Touch \u00A7r"));
        p_41423_.add(new TextComponent("\u00A77Upon hitting enemy inflict"));
        p_41423_.add(new TextComponent("\u00A77the wither effect for 20 seconds."));
    }
}
