package com.kingsman.hyper.reg.block.entity.client;

import com.kingsman.hyper.reg.block.entity.orbs.OverFluxEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import javax.annotation.Nullable;

public class OverFluxRenderer  extends GeoBlockRenderer<OverFluxEntity>
{
    public OverFluxRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn)
    {
        super(rendererDispatcherIn, new OverFluxModel());
    }

    @Override
    public RenderType getRenderType(OverFluxEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation)
    {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
