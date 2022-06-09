package com.kingsman.hyper.reg.Effects;

import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BleedingImmunity extends MobEffect
{
    public BleedingImmunity(MobEffectCategory p_19451_, int p_19452_)
    {
        super(p_19451_, p_19452_);
    }

    @Override
    public boolean isBeneficial()
    {
        return true;
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_)
    {
        if (this == RegistryHandler.BLEEDING_IMMUNITY.get())
        {
            int i = 25 >> p_19456_;
            if (i > 0)
            {
                return p_19455_ % i == 0;
            }
            else
            {
                return true;
            }
        }
        return super.isDurationEffectTick(p_19455_, p_19456_);
    }
}
