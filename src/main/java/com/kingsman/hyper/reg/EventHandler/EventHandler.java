package com.kingsman.hyper.reg.EventHandler;

import com.kingsman.hyper.reg.armor.NecronArmor;
import com.kingsman.hyper.reg.armor.StormArmor;
import com.kingsman.hyper.reg.armor.WitherArmor;
import com.kingsman.hyper.reg.armor.WitherArmorMaterial;
import com.kingsman.hyper.reg.weapon.Hyperion;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber
public class EventHandler
{
    @SubscribeEvent
    public static void onArmorChange(LivingEquipmentChangeEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        if (entity instanceof Player)
        {
            Hyperion.setAbilityDmg(WitherArmor.hasCorrectArmorOn(WitherArmorMaterial.STORM, (Player) entity) * StormArmor.getIntelligent());
            float dmg = WitherArmor.hasCorrectArmorOn(WitherArmorMaterial.NECRON, (Player) entity) * NecronArmor.getPhysicDmg();
            double speed = WitherArmor.hasCorrectArmorOn(WitherArmorMaterial.NECRON, (Player) entity) * NecronArmor.getAttackSpeed();
            Objects.requireNonNull(entity.getAttribute(Attributes.ATTACK_DAMAGE.setSyncable(true))).setBaseValue(dmg == 0 ? 1 : dmg);
            Objects.requireNonNull(entity.getAttribute(Attributes.ATTACK_SPEED)).setBaseValue(4d + speed);
        }
    }

    @SubscribeEvent
    public static void onBareHandAttack(AttackEntityEvent event)
    {
        boolean isSwordHolding = event.getPlayer().getMainHandItem().getItem() instanceof SwordItem;
        if (!isSwordHolding)
        {
            event.setCanceled(true);
        }
    }
}
