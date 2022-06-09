package com.kingsman.hyper.reg.DataGen;

import com.kingsman.hyper.ProjectHyper;
import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class HyperItemModelProvider extends ItemModelProvider
{
    public HyperItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, ProjectHyper.MODID, existingFileHelper);
    }

    private ItemModelBuilder simpleItem(Item item)
    {
        return withExistingParent(Objects.requireNonNull(item.getRegistryName()).getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ProjectHyper.MODID, "item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item)
    {
        return withExistingParent(Objects.requireNonNull(item.getRegistryName()).getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(ProjectHyper.MODID, "item/" + item.getRegistryName().getPath()));
    }

    @Override
    public void registerModels()
    {
        simpleItem(RegistryHandler.WITHER_BLOOD.get());
        simpleItem(RegistryHandler.NECRON_CHESTPLATE.get());
        simpleItem(RegistryHandler.NECRON_HELMET.get());
        simpleItem(RegistryHandler.NECRON_LEGGING.get());
        simpleItem(RegistryHandler.NECRON_BOOTS.get());
        simpleItem(RegistryHandler.STORM_BOOTS.get());
        simpleItem(RegistryHandler.STORM_CHESTPLATE.get());
        simpleItem(RegistryHandler.STORM_HELMET.get());
        simpleItem(RegistryHandler.STORM_LEGGING.get());
        simpleItem(RegistryHandler.WITHER_BOOTS.get());
        simpleItem(RegistryHandler.WITHER_CHESTPLATE.get());
        simpleItem(RegistryHandler.WITHER_HELMET.get());
        simpleItem(RegistryHandler.WITHER_LEGGING.get());
        handheldItem(RegistryHandler.WITHER_BLADE.get());
        handheldItem(RegistryHandler.HYPERION.get());
        handheldItem(RegistryHandler.SHADOW_FURY.get());
    }
}
