package com.kingsman.hyper.reg.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class NecronArmor extends WitherArmor
{
    private static final float physicDmg = 10;
    private static final double attackSpeed = 0.5;
    Multimap<Attribute, AttributeModifier> defaultModifiers;

    public static double getAttackSpeed()
    {
        return attackSpeed;
    }

    public static float getPhysicDmg()
    {
        return physicDmg;
    }

    public NecronArmor(ArmorMaterial p_40386_, EquipmentSlot p_40387_, Properties p_40388_)
    {
        super(p_40386_, p_40387_, p_40388_);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        defaultModifiers = super.getDefaultAttributeModifiers(p_40387_);
        builder.put(RegistryHandler.STRENGTH, new AttributeModifier(UUID.randomUUID(), "Armor modifier", physicDmg, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "Armor modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        for (Attribute attributes : defaultModifiers.keySet())
        {
            Collection<AttributeModifier> attributeModifiers = defaultModifiers.get(attributes);
            for (AttributeModifier modifier : attributeModifiers)
            {
                builder.put(attributes, modifier);
            }
        }
        defaultModifiers = builder.build();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_)
    {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player)
    {
        super.onArmorTick(stack, world, player);
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot p_40390_)
    {
        return p_40390_ == this.slot ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_40390_);
    }
}
