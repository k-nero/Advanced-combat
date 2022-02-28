package com.kingsman.hyper.reg.weapon;

import com.kingsman.hyper.reg.ability.WitherImpact;
import com.kingsman.hyper.reg.armor.StormArmor;
import com.kingsman.hyper.reg.armor.WitherArmorMaterial;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Hyperion extends WitherBlade
{
    private static final int baseAbilityDmg = 5;
    private static int AbilityDmg = 0;

    public static int getBaseAbilityDmg()
    {
        return baseAbilityDmg;
    }

    public static void setAbilityDmg(int abilityDmg)
    {
        AbilityDmg = abilityDmg;
    }

    public static int getAbilityDmg()
    {
        return AbilityDmg;
    }

    public Hyperion(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_)
    {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_40920_, Player p_40921_, @NotNull InteractionHand p_40922_)
    {
        ItemStack itemstack = p_40921_.getItemInHand(p_40922_);
        WitherImpact witherImpact = new WitherImpact(p_40920_, p_40921_, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), 8, Explosion.BlockInteraction.NONE);
        if (!p_40920_.isClientSide())
        {
            if (!StormArmor.hasCorrectArmorOn(WitherArmorMaterial.STORM, p_40921_))
            {
                setAbilityDmg(0);
            }
            if (!p_40921_.isCreative())
            {
                if (p_40921_.hasEffect(MobEffects.ABSORPTION))
                {
                    p_40921_.removeEffect(MobEffects.ABSORPTION);
                }
                if (p_40921_.getHealth() <= 2)
                {
                    p_40921_.hurt(DamageSource.WITHER, 500);
                }
                else
                {
                    p_40921_.setHealth(p_40921_.getHealth() - 2);
                }
                p_40921_.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 500, 1), p_40921_);
            }
            witherImpact.BlastDmg(8, p_40920_, p_40921_, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), AbilityDmg + baseAbilityDmg);
        }
        else
        {
            p_40920_.addParticle(ParticleTypes.EXPLOSION, p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), 12.0D, 0.0D, 0.0D);
            p_40920_.playLocalSound(p_40921_.getX(), p_40921_.getY(), p_40921_.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 4.0F, (1.0F + (p_40920_.random.nextFloat() - p_40920_.random.nextFloat()) * 0.2F) * 0.7F, false);
        }
        return InteractionResultHolder.success(itemstack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_)
    {

        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TextComponent("\u00A76Ability: Death's Touch \u00A7r"));
        p_41423_.add(new TextComponent("\u00A77Upon hitting enemy inflict"));
        p_41423_.add(new TextComponent("\u00A77the wither effect for 20 seconds."));
        p_41423_.add(new TextComponent(""));
        p_41423_.add(new TextComponent("\u00A76Ability: Wither Impact \u00A7r\u00A7e\u00A7lRIGHT CLICK \u00A7r"));
        p_41423_.add(new TextComponent("\u00A77When implode dealing \u00A7r").append(String.valueOf(AbilityDmg + baseAbilityDmg)).withStyle(ChatFormatting.RED));
        p_41423_.add(new TextComponent("\u00A77damages to nearby enemies. "));
        p_41423_.add(new TextComponent("\u00A77Also applies the Absorption effect\u00A7r"));
        p_41423_.add(new TextComponent("\u00A77in 5 seconds.\u00A7r"));
        p_41423_.add(new TextComponent("\u00A78Shield cool-downs: \u00A7r\u00A735 seconds"));
        p_41423_.add(new TextComponent("\u00A78Health cost: \u00A7r\u00A7c2 HP"));
    }
}
