package com.kingsman.hyper.reg.weapon;

import com.kingsman.hyper.reg.ability.ShadowAssassin;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ShadowFury extends SwordItem
{

    public ShadowFury(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_)
    {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_40920_, @NotNull Player p_40921_, @NotNull InteractionHand p_40922_)
    {
        ItemStack itemstack = p_40921_.getItemInHand(p_40922_);
        p_40921_.getCooldowns().addCooldown(this, 20);
        if (p_40922_ == InteractionHand.MAIN_HAND && !p_40920_.isClientSide)
        {
            ShadowAssassin shadowAssassin = new ShadowAssassin();
            try
            {
                shadowAssassin.deathArea(p_40920_, p_40921_);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        return InteractionResultHolder.success(itemstack);
    }

}
