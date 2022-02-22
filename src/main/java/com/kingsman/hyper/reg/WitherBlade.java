package com.kingsman.hyper.reg;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_40920_, Player p_40921_, @NotNull InteractionHand p_40922_)
    {
        ItemStack itemstack = p_40921_.getItemInHand(p_40922_);
        WitherImpact witherImpact = new WitherImpact(p_40920_, p_40921_, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), 8, Explosion.BlockInteraction.NONE);
        if (!p_40920_.isClientSide())
        {
            if (p_40921_.hasEffect(MobEffects.ABSORPTION))
            {
                p_40921_.removeEffect(MobEffects.ABSORPTION);
            }
            p_40921_.setHealth(p_40921_.getHealth() - 2);
            //p_40921_.hurt(DamageSource.sting(p_40921_), 2);
            witherImpact.BlastDmg(8, p_40920_, p_40921_, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), 40);
            p_40921_.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 500, 1), p_40921_);
        }
        else
        {
            p_40920_.addParticle(ParticleTypes.EXPLOSION, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), 12.0D, 0.0D, 0.0D);
            p_40920_.playLocalSound(p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (p_40920_.random.nextFloat() - p_40920_.random.nextFloat()) * 0.2F) * 0.7F, false);
        }
        return InteractionResultHolder.success(itemstack);
    }
}
