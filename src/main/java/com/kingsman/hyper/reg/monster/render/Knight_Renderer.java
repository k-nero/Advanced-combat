package com.kingsman.hyper.reg.monster.render;

import com.kingsman.hyper.ProjectHyper;
import com.kingsman.hyper.reg.monster.boss.Knight;
import com.kingsman.hyper.reg.monster.render.model.KnightModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class Knight_Renderer extends GeoEntityRenderer<Knight>
{
    public Knight_Renderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new KnightModel());
        this.shadowRadius = 1F;
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Knight object)
    {
        return new ResourceLocation(ProjectHyper.MODID, "textures/entity/knight.png");
    }

    @Override
    public RenderType getRenderType(@NotNull Knight entity, float partialTicks, @NotNull PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation)
    {
        stack.scale(3.8F, 3.8F, 3.8F);
        return super.getRenderType(entity, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
