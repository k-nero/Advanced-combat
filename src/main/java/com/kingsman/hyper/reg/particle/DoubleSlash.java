package com.kingsman.hyper.reg.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
import org.jetbrains.annotations.NotNull;

public class DoubleSlash extends TextureSheetParticle
{
    protected DoubleSlash(ClientLevel p_108328_, double p_108329_, double p_108330_, double p_108331_, double p_108332_, double p_108333_, double p_108334_)
    {
        super(p_108328_, p_108329_, p_108330_, p_108331_, p_108332_, p_108333_, p_108334_);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType()
    {
        return null;
    }
}
