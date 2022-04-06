package com.kingsman.hyper.reg.ability;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class InstantTransportation
{
    private static int distance;

    static
    {
        distance = 10;
    }

    public static void setDistance(int distance)
    {
        InstantTransportation.distance = distance;
    }

    public static int getDistance()
    {
        return InstantTransportation.distance;
    }

    //get view direction of entity
    public static void getViewDirection(LivingEntity entity)
    {

    }


}
