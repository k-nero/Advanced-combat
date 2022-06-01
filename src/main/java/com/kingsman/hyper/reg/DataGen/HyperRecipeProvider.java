package com.kingsman.hyper.reg.DataGen;

import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class HyperRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public HyperRecipeProvider(DataGenerator p_125973_)
    {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> p_240632_)
    {
        ShapedRecipeBuilder.shaped(RegistryHandler.HYPERION.get())
                .define('E', Items.END_CRYSTAL.asItem())
                .define('N', RegistryHandler.WITHER_BLADE.get())
                .pattern("EEE")
                .pattern("ENE")
                .pattern("EEE")
                .unlockedBy("has_wither_blade", inventoryTrigger(ItemPredicate.Builder.item().of(RegistryHandler.WITHER_BLADE.get()).build()))
                .save(p_240632_);
        ShapedRecipeBuilder.shaped(RegistryHandler.WITHER_BLOOD.get())
                .define('W', Items.WITHER_SKELETON_SKULL.asItem())
                .define('N', Items.NETHER_STAR.asItem())
                .pattern("WWW")
                .pattern("WNW")
                .pattern("WWW")
                .unlockedBy("has_nether_star", inventoryTrigger(ItemPredicate.Builder.item().of(Items.NETHER_STAR.asItem()).build()))
                .save(p_240632_);
        ShapedRecipeBuilder.shaped(RegistryHandler.NECRON_CHESTPLATE.get())
                .define('W', RegistryHandler.WITHER_CHESTPLATE.get())
                .define('N', Items.WITHER_SKELETON_SKULL.asItem())
                .pattern("NNN")
                .pattern("NWN")
                .pattern("NNN")
                .unlockedBy("has_wither_chestplate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistryHandler.WITHER_CHESTPLATE.get()).build()))
                .save(p_240632_);
        ShapedRecipeBuilder.shaped(RegistryHandler.NECRON_LEGGING.get())
                .define('W', RegistryHandler.WITHER_LEGGING.get())
                .define('N', Items.WITHER_SKELETON_SKULL.asItem())
                .pattern("NNN")
                .pattern("NWN")
                .pattern("NNN")
                .unlockedBy("has_wither_chestplate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistryHandler.WITHER_LEGGING.get()).build()))
                .save(p_240632_);
        ShapedRecipeBuilder.shaped(RegistryHandler.NECRON_HELMET.get())
                .define('W', RegistryHandler.WITHER_HELMET.get())
                .define('N', Items.WITHER_SKELETON_SKULL.asItem())
                .pattern("NNN")
                .pattern("NWN")
                .pattern("NNN")
                .unlockedBy("has_wither_chestplate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistryHandler.WITHER_HELMET.get()).build()))
                .save(p_240632_);
        ShapedRecipeBuilder.shaped(RegistryHandler.NECRON_BOOTS.get())
                .define('W', RegistryHandler.WITHER_BOOTS.get())
                .define('N', Items.WITHER_SKELETON_SKULL.asItem())
                .pattern("NNN")
                .pattern("NWN")
                .pattern("NNN")
                .unlockedBy("has_wither_chestplate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistryHandler.WITHER_BOOTS.get()).build()))
                .save(p_240632_);
        ShapedRecipeBuilder.shaped(RegistryHandler.STORM_CHESTPLATE.get())
                .define('W', RegistryHandler.WITHER_CHESTPLATE.get())
                .define('N', Items.END_CRYSTAL.asItem())
                .pattern("NNN")
                .pattern("NWN")
                .pattern("NNN")
                .unlockedBy("has_wither_chestplate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistryHandler.WITHER_CHESTPLATE.get()).build()))
                .save(p_240632_);
        ShapedRecipeBuilder.shaped(RegistryHandler.STORM_LEGGING.get())
                .define('W', RegistryHandler.WITHER_LEGGING.get())
                .define('N', Items.END_CRYSTAL.asItem())
                .pattern("NNN")
                .pattern("NWN")
                .pattern("NNN")
                .unlockedBy("has_wither_chestplate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistryHandler.WITHER_LEGGING.get()).build()))
                .save(p_240632_);
        ShapedRecipeBuilder.shaped(RegistryHandler.STORM_HELMET.get())
                .define('W', RegistryHandler.WITHER_HELMET.get())
                .define('N', Items.END_CRYSTAL.asItem())
                .pattern("NNN")
                .pattern("NWN")
                .pattern("NNN")
                .unlockedBy("has_wither_chestplate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistryHandler.WITHER_HELMET.get()).build()))
                .save(p_240632_);
        ShapedRecipeBuilder.shaped(RegistryHandler.STORM_BOOTS.get())
                .define('W', RegistryHandler.WITHER_BOOTS.get())
                .define('N', Items.END_CRYSTAL.asItem())
                .pattern("NNN")
                .pattern("NWN")
                .pattern("NNN")
                .unlockedBy("has_wither_chestplate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistryHandler.WITHER_BOOTS.get()).build()))
                .save(p_240632_);
        UpgradeRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_SWORD.asItem()), Ingredient.of(RegistryHandler.WITHER_BLOOD.get()), RegistryHandler.WITHER_BLADE.get())
                .unlocks("has_wither_blood", has(RegistryHandler.WITHER_BLOOD.get()))
                .save(p_240632_, Registry.ITEM.getKey(RegistryHandler.WITHER_BLADE.get()).getPath() + "_smithing");
        UpgradeRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_CHESTPLATE.asItem()), Ingredient.of(RegistryHandler.WITHER_BLOOD.get()), RegistryHandler.WITHER_CHESTPLATE.get())
                .unlocks("has_wither_blood", has(RegistryHandler.WITHER_BLOOD.get()))
                .save(p_240632_, Registry.ITEM.getKey(RegistryHandler.WITHER_CHESTPLATE.get()).getPath() + "_smithing");
        UpgradeRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_LEGGINGS.asItem()), Ingredient.of(RegistryHandler.WITHER_BLOOD.get()), RegistryHandler.WITHER_LEGGING.get())
                .unlocks("has_wither_blood", has(RegistryHandler.WITHER_BLOOD.get()))
                .save(p_240632_, Registry.ITEM.getKey(RegistryHandler.WITHER_LEGGING.get()).getPath() + "_smithing");
        UpgradeRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_BOOTS.asItem()), Ingredient.of(RegistryHandler.WITHER_BLOOD.get()), RegistryHandler.WITHER_BOOTS.get())
                .unlocks("has_wither_blood", has(RegistryHandler.WITHER_BLOOD.get()))
                .save(p_240632_, Registry.ITEM.getKey(RegistryHandler.WITHER_BOOTS.get()).getPath() + "_smithing");
        UpgradeRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_HELMET.asItem()), Ingredient.of(RegistryHandler.WITHER_BLOOD.get()), RegistryHandler.WITHER_HELMET.get())
                .unlocks("has_wither_blood", has(RegistryHandler.WITHER_BLOOD.get()))
                .save(p_240632_, Registry.ITEM.getKey(RegistryHandler.WITHER_HELMET.get()).getPath() + "_smithing");

    }
}

