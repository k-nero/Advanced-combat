package com.kingsman.hyper.reg.EventHandler;

import com.kingsman.hyper.reg.RegistryHandler;
import com.kingsman.hyper.reg.block.entity.client.OverFluxRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientEventHandler
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        EntityRenderersEvent.RegisterRenderers renderers = new EntityRenderersEvent.RegisterRenderers();
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(RegistryHandler.OVERFLUX_ENTITY.get(), OverFluxRenderer::new);
    }
}
