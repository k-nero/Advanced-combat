package com.kingsman.hyper.reg.Effects;

import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Objects;

public class Bleed extends MobEffect
{
    public Bleed(MobEffectCategory p_19451_, int p_19452_)
    {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_)
    {
        if (this == RegistryHandler.BLEED.get())
        {
            int i = 25 >> p_19456_;
            if (i > 0)
            {
                return p_19455_ % i == 0;
            }
            else
            {
                return true;
            }
        }
        return super.isDurationEffectTick(p_19455_, p_19456_);
    }

    public static void applyingEffect(Player player, ItemStack itemStack, LivingEntity target)
    {
        int level = EnchantmentHelper.getItemEnchantmentLevel(RegistryHandler.BLOOD_LOST.get(), itemStack);
        if (target != null && level >= 0)
        {
            if (target.getEffect(RegistryHandler.BLEED.get()) == null)
            {
                target.addEffect(new MobEffectInstance(RegistryHandler.BLEED.get(), 200, level));
            }
            else
            {
                int tmp = Objects.requireNonNull(target.getEffect(RegistryHandler.BLEED.get())).getAmplifier();
                target.removeEffect(RegistryHandler.BLEED.get());
                target.addEffect(new MobEffectInstance(RegistryHandler.BLEED.get(), 200, level + tmp));
                if (Objects.requireNonNull(target.getEffect(RegistryHandler.BLEED.get())).getAmplifier() >= 5)
                {
                    target.hurt(new DamageSource("Bleed"), 50.0F);
                    target.removeEffect(RegistryHandler.BLEED.get());
                }
            }
        }
    }
}
