package com.kingsman.hyper.reg.block.entity.orbs;

import com.kingsman.hyper.reg.RegistryHandler;
import com.kingsman.hyper.reg.util.IScanEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.List;
import java.util.Objects;

public class OverFluxEntity extends BlockEntity implements IAnimatable, IScanEntity
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
            List<Entity> list = new IScanEntity(){
                @Override
                public List<Entity> getEntities(@NotNull Level level, @NotNull Vec3 vec3, float radius)
                {
                    return IScanEntity.super.getEntities(level, vec3, radius);
                }
            }.getEntities(level, new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ()), 10);

            if (!list.isEmpty())
            {
                for (Entity entity : list)
                {
                    if (entity instanceof Player player && entity.isAlive())
                    {
                        applySupportingEffects(player);
                    }
                }
            }
        }
    }

    public static void applySupportingEffects(Player player)
    {
        if (player.hasEffect(MobEffects.REGENERATION))
        {
            if (Objects.requireNonNull(player.getEffect(MobEffects.REGENERATION)).getDuration() < 30)
            {
                player.removeEffect(MobEffects.REGENERATION);
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 1));
            }
        }
        else
        {
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
        }
    }
}

