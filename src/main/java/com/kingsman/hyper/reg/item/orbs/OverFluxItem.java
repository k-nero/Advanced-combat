package com.kingsman.hyper.reg.item.orbs;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.function.Consumer;

public class OverFluxItem extends BlockItem implements IAnimatable
{
    public AnimationFactory factory = new AnimationFactory(this);

    public OverFluxItem(Block p_40565_, Properties p_40566_)
    {
        super(p_40565_, p_40566_);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> entity)
    {
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController<OverFluxItem>(this, "controller", 0, this::predicate));
    }

    @Override
    public void initializeClient(@NotNull Consumer<IItemRenderProperties> consumer)
    {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties()
        {
            private final BlockEntityWithoutLevelRenderer renderer = new OverFluxItemRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer()
            {
                return renderer;
            }
        });
    }

    @Override
    public AnimationFactory getFactory()
    {
        return null;
    }
}
