package com.kingsman.hyper.reg.block.model.orbs;

import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class OverFlux extends BaseEntityBlock
{

    public OverFlux(Properties p_49224_)
    {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos p_153215_, @NotNull BlockState p_153216_)
    {
        return RegistryHandler.OVERFLUX_ENTITY.get().create(p_153215_, p_153216_);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState)
    {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public void animateTick(@NotNull BlockState p_49888_, @NotNull Level p_49889_, @NotNull BlockPos p_49890_, @NotNull Random p_49891_)
    {
        if (!p_49889_.isClientSide())
        {
            super.animateTick(p_49888_, p_49889_, p_49890_, p_49891_);
            double x = p_49890_.getX();
            double y = p_49890_.getY();
            double z = p_49890_.getZ();
            float f2 = 20;
            int k1 = Mth.floor(x - (double) f2 - 1.0D);
            int l1 = Mth.floor(x + (double) f2 + 1.0D);
            int i2 = Mth.floor(y - (double) f2 - 1.0D);
            int i1 = Mth.floor(y + (double) f2 + 1.0D);
            int j2 = Mth.floor(z - (double) f2 - 1.0D);
            int j1 = Mth.floor(z + (double) f2 + 1.0D);
            List<Entity> list = p_49889_.getEntities(null, new AABB(k1, i2, j2, l1, i1, j1));
            if (!list.isEmpty())
            {
                for (Entity entity : list)
                {
                    if (entity instanceof Player player && entity.isAlive())
                    {
                        if (player.hasEffect(MobEffects.REGENERATION))
                        {
                            if (Objects.requireNonNull(player.getEffect(MobEffects.REGENERATION)).getDuration() < 30)
                            {
                                player.removeEffect(MobEffects.REGENERATION);
                                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0));
                            }
                        }
                        else
                        {
                            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
                        }
                    }
                }
            }
        }
    }

    public void applySupportingEffects(Player p_149174_)
    {
        MobEffectInstance mobeffectinstance = p_149174_.getEffect(MobEffects.REGENERATION);
        int i = mobeffectinstance != null ? mobeffectinstance.getDuration() : 0;
        if (i < 2400)
        {
            i = Math.min(2400, 100 + i);
            p_149174_.addEffect(new MobEffectInstance(MobEffects.REGENERATION, i, 0), p_149174_);
        }

        p_149174_.removeEffect(MobEffects.DIG_SLOWDOWN);
    }
}
