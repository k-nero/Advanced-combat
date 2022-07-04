package com.kingsman.hyper.reg.block.entity.client;

import com.kingsman.hyper.ProjectHyper;
import com.kingsman.hyper.reg.block.entity.orbs.OverFluxEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OverFluxModel extends AnimatedGeoModel<OverFluxEntity>
{
    @Override
    public ResourceLocation getModelLocation(OverFluxEntity object)
    {
        return new ResourceLocation(ProjectHyper.MODID, "geo/overflux.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(OverFluxEntity object)
    {
        return new ResourceLocation(ProjectHyper.MODID, "textures/entity/overflux_entity.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(OverFluxEntity animatable)
    {
        return new ResourceLocation(ProjectHyper.MODID, "animations/overflux.animation.json");
    }
}
