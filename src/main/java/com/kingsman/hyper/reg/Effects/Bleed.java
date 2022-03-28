package com.kingsman.hyper.reg.Effects;

import com.kingsman.hyper.reg.RegistryHandler;
import com.kingsman.hyper.reg.particle.BloodParticle;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import org.jetbrains.annotations.NotNull;

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

    public static void applyingEffect(LivingAttackEvent event)
    {
        Player player = null;
        if (event.getSource().getEntity() instanceof Player)
        {
            player = (Player) event.getSource().getEntity();
        }
        LivingEntity target = null;
        if (event.getEntity() instanceof LivingEntity)
        {
            target = (LivingEntity) event.getEntity();
        }
        if (player != null)
        {
            ItemStack itemStack = player.getMainHandItem();

            int level = EnchantmentHelper.getItemEnchantmentLevel(RegistryHandler.BLOOD_LOST.get(), itemStack);
            if (target != null && level >= 0)
            {
                if (target.getEffect(RegistryHandler.BLEED.get()) == null)
                {
                    target.addEffect(new MobEffectInstance(RegistryHandler.BLEED.get(), 200, level - 1));
                }
                else
                {
                    int tmp = Objects.requireNonNull(target.getEffect(RegistryHandler.BLEED.get())).getAmplifier();
                    target.removeEffect(RegistryHandler.BLEED.get());
                    target.addEffect(new MobEffectInstance(RegistryHandler.BLEED.get(), 200, level + tmp));
                }
            }
        }
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity p_19467_, int p_19468_)
    {
        super.applyEffectTick(p_19467_, p_19468_);
        if (Objects.requireNonNull(p_19467_.getEffect(RegistryHandler.BLEED.get())).getAmplifier() >= 4)
        {
            p_19467_.hurt(new DamageSource("Bleed").bypassArmor().bypassInvul().bypassMagic(), 50.0F);
            p_19467_.removeEffect(RegistryHandler.BLEED.get());
            BloodParticle.addParticle(p_19467_);
        }
    }
}
