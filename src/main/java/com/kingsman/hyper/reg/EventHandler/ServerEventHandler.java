package com.kingsman.hyper.reg.EventHandler;

import com.kingsman.hyper.reg.Effects.Bleed;
import com.kingsman.hyper.reg.Enchantment.LifeStealEnchantment;
import com.kingsman.hyper.reg.RegistryHandler;
import com.kingsman.hyper.reg.armor.NecronArmor;
import com.kingsman.hyper.reg.armor.StormArmor;
import com.kingsman.hyper.reg.armor.WitherArmor;
import com.kingsman.hyper.reg.armor.WitherArmorMaterial;
import com.kingsman.hyper.reg.weapon.Hyperion;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber
public class ServerEventHandler
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
        Item isSwordHolding = event.getPlayer().getMainHandItem().getItem();
        if (!(isSwordHolding instanceof SwordItem || isSwordHolding instanceof BowItem))
        {
            event.setCanceled(true);
            event.getTarget().hurt(DamageSource.playerAttack(event.getPlayer()), 2);
        }
    }

    @SubscribeEvent
    public static void applyingBleed(AttackEntityEvent event)
    {
        ItemStack itemStack = event.getPlayer().getMainHandItem();
        LivingEntity target = null;
        if (event.getTarget() instanceof LivingEntity)
        {
            target = (LivingEntity) event.getTarget();
        }
        Bleed.applyingEffect(event.getPlayer(), itemStack, target);
    }

    @SubscribeEvent
    public static void LifeSteal(AttackEntityEvent event)
    {
        Player player = event.getPlayer();
        LivingEntity target = null;
        if (event.getTarget() instanceof LivingEntity)
        {
            target = (LivingEntity) event.getTarget();
        }
        LifeStealEnchantment.LifeSteal(player, target);
    }

    @SubscribeEvent
    public static void actualDmg(LivingDamageEvent event)
    {
        LifeStealEnchantment.setFinalDmg(event.getAmount());
    }
}
