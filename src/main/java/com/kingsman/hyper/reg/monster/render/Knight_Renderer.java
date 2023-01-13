package com.kingsman.hyper.reg.monster.render;

import com.kingsman.hyper.ProjectHyper;
import com.kingsman.hyper.reg.monster.boss.Knight;
import com.kingsman.hyper.reg.monster.render.model.KnightModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class Knight_Renderer extends GeoEntityRenderer<Knight>
{
    private MultiBufferSource rtb;
    private ResourceLocation whTexture;

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
        return RenderType.entityTranslucent(getTextureLocation(entity));
    }

    @Override
    public void renderEarly(Knight animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        this.rtb = renderTypeBuffer;
        this.whTexture = this.getTextureLocation(animatable);
        super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha)
    {
        if (bone.getName().equals("r_arm"))
        { // rArmRuff is the name of the bone you to set the item to attach too. Please see Note
            stack.pushPose();
            // You'll need to play around with these to get item to render in the correct orientation
            stack.mulPose(Vector3f.XP.rotationDegrees(-75));
            stack.mulPose(Vector3f.YP.rotationDegrees(0));
            stack.mulPose(Vector3f.ZP.rotationDegrees(0));
            // You'll need to play around with this to render the item in the correct spot.
            stack.translate(0.09D, 0.09D, 0.18D);
            // Sets the scaling of the item.
            stack.scale(0.25f, 0.25f, 0.25f);
            // Change mainHand to predefined Itemstack and TransformType to what transform you would want to use.
            Minecraft.getInstance().getItemRenderer().renderStatic(mainHand, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 0);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
