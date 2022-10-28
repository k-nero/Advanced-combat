package com.kingsman.hyper;

import com.kingsman.hyper.reg.RegistryHandler;
import com.kingsman.hyper.reg.monster.render.Knight_Renderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ProjectHyper.MODID)
public class ProjectHyper
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "hyper";

    public ProjectHyper()
    {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RegistryHandler.init(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);
        GeckoLib.initialize();
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        EntityRenderers.register(RegistryHandler.KNIGHT.get(), Knight_Renderer::new);
    }
}
