package com.kingsman.hyper.reg;

import com.kingsman.hyper.ProjectHyper;
import com.kingsman.hyper.reg.UI.CreativeTab;
import com.kingsman.hyper.reg.armor.NecronArmor;
import com.kingsman.hyper.reg.armor.StormArmor;
import com.kingsman.hyper.reg.armor.WitherArmor;
import com.kingsman.hyper.reg.armor.WitherArmorMaterial;
import com.kingsman.hyper.reg.item.ItemTier;
import com.kingsman.hyper.reg.weapon.Hyperion;
import com.kingsman.hyper.reg.weapon.WitherBlade;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryHandler
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectHyper.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectHyper.MODID);
    public static final Attribute ABILITY_DAMAGE = new RangedAttribute("attribute.name.generic.ability_damage", 2.0D, 0.0D, 2048.0D).setSyncable(true);
    public static final Attribute STRENGTH = new RangedAttribute("attribute.name.generic.strength", 0.0D, 0.0D, 2048.0D).setSyncable(true);
    public static final RegistryObject<Item> WITHER_BLOOD = ITEMS.register("wither_blood", () -> new Item(new Item.Properties().tab(CreativeTab.ITEM_GROUP).stacksTo(16)));
    public static final RegistryObject<Item> HYPERION = ITEMS.register("hyperion", () -> new Hyperion(ItemTier.WITHER, 10, -2.5f, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> WITHER_BLADE = ITEMS.register("wither_blade", () -> new WitherBlade(ItemTier.WITHER, 8, -3f, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> WITHER_HELMET = ITEMS.register("wither_helmet", () -> new WitherArmor(WitherArmorMaterial.WITHER, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> WITHER_CHESTPLATE = ITEMS.register("wither_chestplate", () -> new WitherArmor(WitherArmorMaterial.WITHER, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> WITHER_LEGGING = ITEMS.register("wither_leggings", () -> new WitherArmor(WitherArmorMaterial.WITHER, EquipmentSlot.LEGS, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> WITHER_BOOTS = ITEMS.register("wither_boots", () -> new WitherArmor(WitherArmorMaterial.WITHER, EquipmentSlot.FEET, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.RARE).fireResistant()));
    public static final RegistryObject<Item> STORM_HELMET = ITEMS.register("storm_helmet", () -> new StormArmor(WitherArmorMaterial.STORM, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> STORM_CHESTPLATE = ITEMS.register("storm_chestplate", () -> new StormArmor(WitherArmorMaterial.STORM, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> STORM_LEGGING = ITEMS.register("storm_leggings", () -> new StormArmor(WitherArmorMaterial.STORM, EquipmentSlot.LEGS, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> STORM_BOOTS = ITEMS.register("storm_boots", () -> new StormArmor(WitherArmorMaterial.STORM, EquipmentSlot.FEET, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> NECRON_HELMET = ITEMS.register("necron_helmet", () -> new NecronArmor(WitherArmorMaterial.NECRON, EquipmentSlot.HEAD, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> NECRON_CHESTPLATE = ITEMS.register("necron_chestplate", () -> new NecronArmor(WitherArmorMaterial.NECRON, EquipmentSlot.CHEST, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> NECRON_LEGGING = ITEMS.register("necron_leggings", () -> new NecronArmor(WitherArmorMaterial.NECRON, EquipmentSlot.LEGS, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> NECRON_BOOTS = ITEMS.register("necron_boots", () -> new NecronArmor(WitherArmorMaterial.NECRON, EquipmentSlot.FEET, new Item.Properties().tab(CreativeTab.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));

    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> void registryBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab)
    {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void init(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
