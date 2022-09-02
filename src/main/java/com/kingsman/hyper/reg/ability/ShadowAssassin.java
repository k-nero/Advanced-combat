package com.kingsman.hyper.reg.ability;

import com.kingsman.hyper.reg.util.IScanEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ShadowAssassin implements IScanEntity
{
    private int radius = 10;

    public void setRadius(int radius)
    {
        this.radius = radius;
    }

    public int getRadius()
    {
        return radius;
    }

    @Override
    public List<Entity> getEntities(@NotNull Level level, @NotNull Vec3 vec3, float radius)
    {
        return IScanEntity.super.getEntities(level, vec3, radius);
    }

    public void deathArea(Level level, Entity source) throws InterruptedException
    {
        Player player = (Player) source;
        List<Entity> list = getEntities(level, new Vec3(player.getX(), player.getY(), player.getZ()), getRadius());
        if (!list.isEmpty())
        {
            for (Entity entity : list)
            {
                if (entity instanceof Monster)
                {
                    teleportToBack((LivingEntity) entity, player);
                    entity.hurt(DamageSource.playerAttack(player), (float) Objects.requireNonNull(player.getAttribute(Attributes.ATTACK_DAMAGE)).getValue());
                    Thread.sleep(1000);
                }
            }
        }
    }

    public void teleportToBack(LivingEntity entity, Player player)
    {
        BlockPos pos = ((BlockHitResult) pickEntityBack(2.0D, 0.0F, false, entity)).getBlockPos();
        if (!player.getViewVector(0).equals(entity.getViewVector(0)))
        {
            player.setXRot(entity.xRotO);
            player.setYRot(entity.yRotO);
            player.setYHeadRot(player.getYRot());
            player.xRotO = player.getXRot();
            player.yRotO = player.getYRot();
            player.yHeadRotO = player.yHeadRot;
            player.yBodyRot = player.yHeadRot;
            player.yBodyRotO = player.yBodyRot;
        }
        player.teleportTo(pos.getX(), pos.getY(), pos.getZ());
    }

    public HitResult pickEntityBack(double p_19908_, float p_19909_, boolean p_19910_, LivingEntity entity)
    {
        entity.setYRot(0.0F);
        Vec3 vec3 = entity.getEyePosition(p_19909_);
        Vec3 vec31 = entity.getViewVector(p_19909_).reverse();
        Vec3 vec32 = vec3.add(vec31.x * p_19908_, vec31.y * p_19908_, vec31.z * p_19908_);
        return entity.level.clip(new ClipContext(vec3, vec32, ClipContext.Block.OUTLINE, p_19910_ ? ClipContext.Fluid.ANY : ClipContext.Fluid.NONE, entity));
    }
}
