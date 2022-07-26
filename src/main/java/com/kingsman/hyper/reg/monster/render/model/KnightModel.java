package com.kingsman.hyper.reg.monster.render.model;

import com.kingsman.hyper.ProjectHyper;
import com.kingsman.hyper.reg.monster.boss.Knight;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KnightModel extends AnimatedGeoModel<Knight>
    {
        @Override
        public ResourceLocation getModelLocation(Knight object)
        {
            return new ResourceLocation(ProjectHyper.MODID, "geo/knight.geo.json");
        }

        @Override
        public ResourceLocation getTextureLocation(Knight object)
        {
            return new ResourceLocation(ProjectHyper.MODID, "textures/entity/knight.png");
        }

        @Override
        public ResourceLocation getAnimationFileLocation(Knight animatable)
        {
            return new ResourceLocation(ProjectHyper.MODID, "animations/knight.anim.json");
        }
    }