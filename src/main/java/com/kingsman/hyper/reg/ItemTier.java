package com.kingsman.hyper.reg;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ItemTier
{
    public static final ForgeTier WITHER = new ForgeTier(2, 2000, 2f, 10f, 35, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.WITHER_SKELETON_SKULL));
}
