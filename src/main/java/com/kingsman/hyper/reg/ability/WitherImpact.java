package com.kingsman.hyper.reg.ability;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WitherImpact extends Explosion
{
    public WitherImpact(Level p_151471_, @Nullable Entity p_151472_, double p_151473_, double p_151474_, double p_151475_, float p_151476_, BlockInteraction none)
    {
        super(p_151471_, p_151472_, p_151473_, p_151474_, p_151475_, p_151476_);
    }

    public void BlastDmg(float radius, Level level, Entity source, double x, double y, double z, float dmg)
    {
        float f2 = radius * 2.0F;
        int k1 = Mth.floor(x - (double) f2 - 1.0D);
        int l1 = Mth.floor(x + (double) f2 + 1.0D);
        int i2 = Mth.floor(y - (double) f2 - 1.0D);
        int i1 = Mth.floor(y + (double) f2 + 1.0D);
        int j2 = Mth.floor(z - (double) f2 - 1.0D);
        int j1 = Mth.floor(z + (double) f2 + 1.0D);
        List<Entity> list = level.getEntities(source, new AABB(k1, i2, j2, l1, i1, j1));
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(level, this, list, f2);
        for (Entity entity : list)
        {
            if (!entity.ignoreExplosion() && entity instanceof Monster)
            {
                entity.hurt(DamageSource.MAGIC, dmg);
            }
        }
    }

    public void Implosion(@NotNull Level p_40920_, Player p_40921_, int AOE, float dmg)
    {
        if (!p_40920_.isClientSide())
        {
            if (!p_40921_.isCreative())
            {
                if (p_40921_.hasEffect(MobEffects.ABSORPTION))
                {
                    p_40921_.removeEffect(MobEffects.ABSORPTION);
                }
                if (p_40921_.getHealth() <= 2)
                {
                    p_40921_.hurt(DamageSource.WITHER, 500);
                }
                else
                {
                    p_40921_.setHealth(p_40921_.getHealth() - 2);
                }
                p_40921_.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1), p_40921_);
            }
            this.BlastDmg(8, p_40920_, p_40921_, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), AOE + dmg);
        }
        else
        {
            p_40920_.addParticle(ParticleTypes.EXPLOSION, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), 12.0D, 0.0D, 0.0D);
            p_40920_.playLocalSound(p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (p_40920_.random.nextFloat() - p_40920_.random.nextFloat()) * 0.2F) * 0.7F, false);
        }
    }
}
