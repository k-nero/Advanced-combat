package com.kingsman.hyper.reg.block.entity.orbs;

import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.Objects;

public class OverFluxEntity extends BlockEntity implements IAnimatable
{
    private final AnimationFactory factory = new AnimationFactory(this);

    public OverFluxEntity(BlockPos p_155229_, BlockState p_155230_)
    {
        super(RegistryHandler.OVERFLUX_ENTITY.get(), p_155229_, p_155230_);
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<OverFluxEntity>(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.rotation", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t)
    {
        if (!level.isClientSide())
        {
            double x = blockPos.getX();
            double y = blockPos.getY();
            double z = blockPos.getZ();
            float f2 = 20;
            int k1 = Mth.floor(x - (double) f2 - 1.0D);
            int l1 = Mth.floor(x + (double) f2 + 1.0D);
            int i2 = Mth.floor(y - (double) f2 - 1.0D);
            int i1 = Mth.floor(y + (double) f2 + 1.0D);
            int j2 = Mth.floor(z - (double) f2 - 1.0D);
            int j1 = Mth.floor(z + (double) f2 + 1.0D);
            List<Entity> list = level.getEntities(null, new AABB(k1, i2, j2, l1, i1, j1));
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

