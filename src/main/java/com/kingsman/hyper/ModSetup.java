package com.kingsman.hyper;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class ModSetup
{
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("hyperion")
    {
        @Override
        public @NotNull ItemStack makeIcon()
        {
            return new ItemStack(Items.OBSIDIAN);
        }
    };
}
