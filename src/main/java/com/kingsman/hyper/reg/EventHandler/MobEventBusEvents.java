package com.kingsman.hyper.reg.EventHandler;

import com.kingsman.hyper.ProjectHyper;
import com.kingsman.hyper.reg.RegistryHandler;
import com.kingsman.hyper.reg.monster.boss.Knight;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectHyper.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEventBusEvents
{
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event)
    {
        event.put(RegistryHandler.KNIGHT.get(), Knight.createAttributes());
    }
}
