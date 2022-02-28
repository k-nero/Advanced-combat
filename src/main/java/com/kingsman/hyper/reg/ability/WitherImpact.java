package com.kingsman.hyper.reg.ability;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WitherImpact extends Explosion
{
    public WitherImpact(Level p_151471_, @Nullable Entity p_151472_, double p_151473_, double p_151474_, double p_151475_, float p_151476_, BlockInteraction none)
    {
        super(p_151471_, p_151472_, p_151473_, p_151474_, p_151475_, p_151476_);
    }

    public void BlastDmg(float radius, Level level, Entity source, double x, double y, double z, float dmg)
    {
        float f2 = radius * 2.0F;
        int k1 = Mth.floor(x - (double) f2 - 1.0D);
        int l1 = Mth.floor(x + (double) f2 + 1.0D);
        int i2 = Mth.floor(y - (double) f2 - 1.0D);
        int i1 = Mth.floor(y + (double) f2 + 1.0D);
        int j2 = Mth.floor(z - (double) f2 - 1.0D);
        int j1 = Mth.floor(z + (double) f2 + 1.0D);
        List<Entity> list = level.getEntities(source, new AABB(k1, i2, j2, l1, i1, j1));
        net.minecraftforge.event.ForgeEventFactory.onExplosionDetonate(level, this, list, f2);
        for (Entity entity : list)
        {
            if (!entity.ignoreExplosion() && entity instanceof Monster)
            {
                entity.hurt(this.getDamageSource(), dmg);
            }
        }
    }
}
