package com.kingsman.hyper.reg.ability;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ShadowFury
{
    public void DeadArea(Level level, Entity source, double x, double y, double z, float radius)
    {
        float f2 = radius * 2.0F;
        int k1 = Mth.floor(x - (double) f2 - 1.0D);
        int l1 = Mth.floor(x + (double) f2 + 1.0D);
        int i2 = Mth.floor(y - (double) f2 - 1.0D);
        int i1 = Mth.floor(y + (double) f2 + 1.0D);
        int j2 = Mth.floor(z - (double) f2 - 1.0D);
        int j1 = Mth.floor(z + (double) f2 + 1.0D);
        List<net.minecraft.world.entity.Entity> list = level.getEntities(source, new AABB(k1, i2, j2, l1, i1, j1));
        int counter = 0;
        for (net.minecraft.world.entity.Entity entity : list)
        {
            if (entity instanceof Monster)
            {
                Vec3 vec3 = new Vec3(entity.getX(), entity.getY(), entity.getZ());
                source.setPos(vec3);
                Player player = (Player) source;
                player.attack(entity);
                counter++;
            }
            if (counter >= 5)
            {
                break;
            }
        }
    }

    public void teleportToBack(LivingEntity entity, Player player)
    {
        Vec3 vec3 = entity.getViewVector(0).normalize().reverse();
        BlockPos blockPos = ((BlockHitResult) entity.pick(1, 0F, false)).getBlockPos();
        player.setPos(blockPos.getX(), blockPos.getY() + 0.5, blockPos.getZ());
        Minecraft minecraft = Minecraft.getInstance();
        if (!(minecraft.crosshairPickEntity instanceof LivingEntity))
        {
            if (minecraft.crosshairPickEntity != null)
            {
                if (!minecraft.crosshairPickEntity.equals(entity))
                {
                    //TODO: camera rotation
                }
            }
        }
    }
}
