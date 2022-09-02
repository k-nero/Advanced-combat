package com.kingsman.hyper.reg.ability;

import com.kingsman.hyper.reg.EventHandler.ServerEventHandler;
import com.kingsman.hyper.reg.util.IScanEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WitherImpact extends Explosion implements IScanEntity
{
    public WitherImpact(Level p_151471_, @Nullable Entity p_151472_, double p_151473_, double p_151474_, double p_151475_, float p_151476_, int AOE, float dmg)
    {
        super(p_151471_, p_151472_, p_151473_, p_151474_, p_151475_, p_151476_);
        Implosion(p_151471_, (Player) p_151472_, AOE, dmg);
    }

    @Override
    public List<Entity> getEntities(@NotNull Level level, @NotNull Vec3 vec3, float radius)
    {
        return IScanEntity.super.getEntities(level, vec3, radius);
    }

    public void BlastAura(float radius, Level level, double x, double y, double z, float dmg)
    {
        List<Entity> list =  getEntities(level, new Vec3(x, y, z), radius);
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(level, this, list, 2 * radius);
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
            this.BlastAura(8, p_40920_, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), AOE + dmg);
        }
        else
        {
            p_40921_.displayClientMessage(new TextComponent("Your Wither Explosion deal ").append(String.valueOf(ServerEventHandler.getTrueDamage())).withStyle(ChatFormatting.AQUA), true);
            p_40920_.addParticle(ParticleTypes.EXPLOSION, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), 12.0D, 0.0D, 0.0D);
            p_40920_.playLocalSound(p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (p_40920_.random.nextFloat() - p_40920_.random.nextFloat()) * 0.2F) * 0.7F, false);
        }
    }
}
