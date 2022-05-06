package com.kingsman.hyper.reg.ability;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class InstantTransportation
{
    private static int distance;
    static BlockPos pos;
    static LivingEntity entity;

    public InstantTransportation(int distance, LivingEntity entity)
    {
        InstantTransportation.entity = entity;
        InstantTransportation.distance = distance;
        pos = desPos(pickedPos());
    }


    public void setDistance(int distance)
    {
        InstantTransportation.distance = distance;
    }

    public int getDistance()
    {
        return distance;
    }

    //get picked block location
    public HitResult pickedPos()
    {
        return entity.pick(distance, 0.0F, false);
    }

    public HitResult pickedBlock()
    {
        return entity.pick(distance * 3, 0.0F, false);
    }

    public BlockPos desPos(HitResult hitResult)
    {
        return ((BlockHitResult) hitResult).getBlockPos();
    }

    public void teleport()
    {
        entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 5, 0, false, false, false), entity);
        float x = pos.getX() - 0.5F;
        float y = pos.getY();
        float z = pos.getZ() - 0.5F;
        if (entity.getY() > y)
        {
            y += 2;
        }
        entity.setPos(x, y, z);
    }

    public void teleportToBlock(LivingEntity entity)
    {
        HitResult hitResult = pickedBlock();
        if (hitResult.getType() == HitResult.Type.BLOCK)
        {
            BlockPos pos2 = desPos(hitResult);
            float x = pos2.getX() - 0.5F;
            float y = pos2.getY();
            float z = pos2.getZ() - 0.5F;
            if (entity.getY() > y)
            {
                y += 2;
            }
            entity.teleportTo(x, y, z);
        }
        ;
    }
}
