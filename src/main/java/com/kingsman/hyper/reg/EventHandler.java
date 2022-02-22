package com.kingsman.hyper.reg;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Deprecated
@Mod.EventBusSubscriber
public class EventHandler
{
    @SubscribeEvent
    public static void avoidKnowBack(LivingKnockBackEvent knockBackEvent)
    {
        Entity entity = knockBackEvent.getEntityLiving();
        if (entity instanceof Player)
        {
            if (((Player) entity).getItemInHand(InteractionHand.MAIN_HAND).sameItem(new ItemStack(RegistryHandler.HYPERION.get())))
            {
                knockBackEvent.setCanceled(true);
            }
        }
    }

}
