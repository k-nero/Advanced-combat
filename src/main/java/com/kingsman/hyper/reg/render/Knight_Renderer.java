package com.kingsman.hyper.reg.render;

import com.kingsman.hyper.ProjectHyper;
import com.kingsman.hyper.reg.monster.boss.Knight;
import com.kingsman.hyper.reg.render.model.Bloody_knight;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class Knight_Renderer extends MobRenderer<Knight, Bloody_knight<Knight>>
{

    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(ProjectHyper.MODID, "textures/entity/bloody_knight.png");

    public Knight_Renderer(EntityRendererProvider.Context p_174304_, Bloody_knight<Knight> p_174305_, float p_174306_)
    {
        super(p_174304_, p_174305_, p_174306_);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Knight p_114482_)
    {
        return RESOURCE_LOCATION;
    }
}
// Code below this line has been added to remove errors