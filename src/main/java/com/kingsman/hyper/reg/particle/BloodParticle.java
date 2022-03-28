package com.kingsman.hyper.reg.particle;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import java.util.Objects;

public class BloodParticle
{
    public static final ParticleOptions
            BLOOD = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
            ZOMBIFIED_PIG_PARTICLE = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
            PHANTOM_PARTICLE_DATA = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.GRAY_STAINED_GLASS.defaultBlockState()),
            BLAZE_PARTICLE_DATA = ParticleTypes.FLAME,
            SLIME_PARTICLE_DATA = ParticleTypes.ITEM_SLIME,
            MAGMA_CUBE_PARTICLE_DATA = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MAGMA_BLOCK.defaultBlockState()),
            ENDER_PARTICLE_DATA = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.PURPLE_CONCRETE.defaultBlockState()),
            LAVA_PARTICLE_DATA = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.LAVA.defaultBlockState());

    public static ParticleOptions getParticle(LivingEntity entity)
    {
        ParticleOptions particle = null;
        if (entity instanceof Piglin || entity instanceof Zoglin || Objects.requireNonNull(entity.getType().getRegistryName()).getPath().contains("skeleton"))
        {
            particle = ZOMBIFIED_PIG_PARTICLE;
        }
        else if (entity instanceof Phantom)
        {
            particle = PHANTOM_PARTICLE_DATA;
        }
        else if (entity instanceof Blaze)
        {
            particle = BLAZE_PARTICLE_DATA;
        }
        else if (entity instanceof MagmaCube)
        {
            particle = MAGMA_CUBE_PARTICLE_DATA;
        }
        else if (entity instanceof Slime)
        {
            particle = SLIME_PARTICLE_DATA;
        }
        else if (entity instanceof EnderMan || entity instanceof EnderDragon || entity instanceof Endermite)
        {
            particle = ENDER_PARTICLE_DATA;
        }
        else if (entity.getType().getRegistryName().getPath().equals("lava_monster"))
        {
            particle = LAVA_PARTICLE_DATA;
        }
        else
        {
            particle = BLOOD;
        }
        return particle;
    }

    public static void addParticle(LivingEntity entity)
    {
        if (entity != null)
        {
            double x = entity.getX();
            double y = entity.getY() + entity.getBbHeight() / 1.5;
            double z = entity.getZ();
            ParticleOptions particle = null;
            particle = getParticle(entity);
            if (entity.getLevel().isClientSide())
            {
                for (int i = 0; i < 50; i++)
                {
                    entity.getLevel().addParticle(particle, false, x, y, z, 0, 0, 0);
                }
            }
        }
    }
}
