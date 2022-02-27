package com.kingsman.hyper.reg;

import com.kingsman.hyper.ModSetup;
import com.kingsman.hyper.ProjectHyper;
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
    public static final RegistryObject<Item> WITHER_BLOOD = ITEMS.register("wither_blood", () -> new Item(new Item.Properties().tab(ModSetup.ITEM_GROUP).stacksTo(16)));
    public static final RegistryObject<Item> HYPERION = ITEMS.register("hyperion", () -> new Hyperion(ItemTier.WITHER, 10, 7f, new Item.Properties().tab(ModSetup.ITEM_GROUP).rarity(Rarity.EPIC).fireResistant()));
    public static final RegistryObject<Item> WITHER_BLADE = ITEMS.register("wither_blade", () -> new WitherBlade(ItemTier.WITHER, 8, 5f, new Item.Properties().tab(ModSetup.ITEM_GROUP).rarity(Rarity.RARE).fireResistant()));
    public static final Attribute ABILITY_DAMAGE = new RangedAttribute("attribute.name.generic.ability_damage", 2.0D, 0.0D, 2048.0D).setSyncable(true);

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
