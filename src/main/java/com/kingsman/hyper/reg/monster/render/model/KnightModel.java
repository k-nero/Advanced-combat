package com.kingsman.hyper.reg.monster.render.model;

import com.kingsman.hyper.ProjectHyper;
import com.kingsman.hyper.reg.monster.boss.Knight;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

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

    @SuppressWarnings({"unchecked"})
    @Override
    public void setLivingAnimations(Knight entity, Integer uniqueID, AnimationEvent customPredicate)
    {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}