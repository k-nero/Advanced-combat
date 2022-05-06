package com.kingsman.hyper.reg.ability;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ShadowAssassin
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

    public void deathArea(Level level, Entity source)
    {
        double x = source.getX();
        double y = source.getY();
        double z = source.getZ();
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
                Player player = (Player) source;
                teleportToBack((LivingEntity) entity, player);
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
        Minecraft minecraft = Minecraft.getInstance();
        BlockPos pos = ((BlockHitResult) pickEntityBack(1.0D, 0.0F, false, entity)).getBlockPos();
        player.teleportTo(pos.getX(), pos.getY(), pos.getZ() + 0.5);
        if (!(minecraft.crosshairPickEntity instanceof LivingEntity))
        {
            player.setXRot(entity.getXRot());
            player.setYRot(entity.getYRot());
            //TODO: set camera rotation
            player.attack(entity);
        }
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
