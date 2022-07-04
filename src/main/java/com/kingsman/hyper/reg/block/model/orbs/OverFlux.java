package com.kingsman.hyper.reg.block.model.orbs;

import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OverFlux extends BaseEntityBlock
{

    public OverFlux(Properties p_49224_)
    {
        super(p_49224_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos p_153215_, @NotNull BlockState p_153216_)
    {
        return RegistryHandler.OVERFLUX_ENTITY.get().create(p_153215_, p_153216_);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState)
    {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
