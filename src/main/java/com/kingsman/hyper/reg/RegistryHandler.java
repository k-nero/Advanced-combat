package com.kingsman.hyper.reg;

import com.kingsman.hyper.ModSetup;
import com.kingsman.hyper.ProjectHyper;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> HYPERION = ITEMS.register("hyperion", () -> new WitherBlade(ItemTier.WITHER, 10, 7f, new Item.Properties().tab(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<Item> WITHER_BLADE = ITEMS.register("wither_blade", () -> new WitherBlade(ItemTier.WITHER, 8, 5f, new Item.Properties().tab(ModSetup.ITEM_GROUP)));

    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block, CreativeModeTab tab)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab)
    {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void init(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
