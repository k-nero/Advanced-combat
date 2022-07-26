package com.kingsman.hyper.reg.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IScanEntity
{
    default List<Entity> getEntities(@NotNull Level level, @NotNull Vec3 vec3, float radius)
    {
        double x = vec3.x();
        double y = vec3.y();
        double z = vec3.z();
        float f2 = radius * 2;
        int k1 = Mth.floor(x - (double) f2 - 1.0D);
        int l1 = Mth.floor(x + (double) f2 + 1.0D);
        int i2 = Mth.floor(y - (double) f2 - 1.0D);
        int i1 = Mth.floor(y + (double) f2 + 1.0D);
        int j2 = Mth.floor(z - (double) f2 - 1.0D);
        int j1 = Mth.floor(z + (double) f2 + 1.0D);
        return level.getEntities(null, new AABB(k1, i2, j2, l1, i1, j1));
    }
}
