package com.kingsman.hyper.reg.item.orbs;

import com.kingsman.hyper.ProjectHyper;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OverFluxItemModel extends AnimatedGeoModel<OverFluxItem>
{

    @Override
    public ResourceLocation getModelLocation(OverFluxItem object)
    {
        return new ResourceLocation(ProjectHyper.MODID, "geo/overflux.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(OverFluxItem object)
    {
        return new ResourceLocation(ProjectHyper.MODID, "textures/entity/overflux_entity.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(OverFluxItem animatable)
    {
        return new ResourceLocation(ProjectHyper.MODID, "animations/overflux.animation.json");
    }
}
