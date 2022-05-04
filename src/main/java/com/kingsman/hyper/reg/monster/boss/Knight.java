package com.kingsman.hyper.reg.monster.boss;

import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Knight extends Monster
{
    public Knight(EntityType<? extends Monster> p_33002_, Level p_33003_)
    {
        super(p_33002_, p_33003_);
    }

    @Override
    public @NotNull Iterable<ItemStack> getArmorSlots()
    {
        return null;
    }

    @Override
    public @NotNull ItemStack getItemBySlot(@NotNull EquipmentSlot p_21127_)
    {
        return null;
    }

    @Override
    public void setItemSlot(@NotNull EquipmentSlot p_21036_, @NotNull ItemStack p_21037_)
    {
        super.setItemSlot(p_21036_, p_21037_);
    }

    @Override
    public @NotNull HumanoidArm getMainArm()
    {
        return null;
    }

    @Override
    public boolean alwaysAccepts()
    {
        return super.alwaysAccepts();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt)
    {
        super.deserializeNBT(nbt);
    }

    @Override
    public CompoundTag serializeNBT()
    {
        return super.serializeNBT();
    }

    /**
     * Used in model rendering to determine if the entity riding this entity should be in the 'sitting' position.
     *
     * @return false to prevent an entity that is mounted to this entity from displaying the 'sitting' animation.
     */
    @Override
    public boolean shouldRiderSit()
    {
        return super.shouldRiderSit();
    }

    /**
     * Called when a user uses the creative pick block button on this entity.
     *
     * @param target The full target the player is looking at
     * @return A ItemStack to add to the player's inventory, empty ItemStack if nothing should be added.
     */
    @Override
    public ItemStack getPickedResult(HitResult target)
    {
        return super.getPickedResult(target);
    }

    /**
     * If a rider of this entity can interact with this entity. Should return true on the
     * ridden entity if so.
     *
     * @return if the entity can be interacted with from a rider
     */
    @Override
    public boolean canRiderInteract()
    {
        return super.canRiderInteract();
    }

    /**
     * Checks if this entity can continue to be ridden while it is underwater.
     *
     * @param rider The entity that is riding this entity
     * @return {@code true} if the entity can continue to ride underwater. {@code false} otherwise.
     */
    @Override
    public boolean canBeRiddenInWater(Entity rider)
    {
        return super.canBeRiddenInWater(rider);
    }

    /**
     * Returns The classification of this entity
     *
     * @param forSpawnCount If this is being invoked to check spawn count caps.
     * @return If the creature is of the type provided
     */
    @Override
    public MobCategory getClassification(boolean forSpawnCount)
    {
        return super.getClassification(forSpawnCount);
    }

    /**
     * This is used to specify that your entity has multiple individual parts, such as the Vanilla Ender Dragon.
     * <p>
     * See {@link EnderDragon} for an example implementation.
     *
     * @return true if this is a multipart entity.
     */
    @Override
    public boolean isMultipartEntity()
    {
        return super.isMultipartEntity();
    }

    /**
     * Gets the individual sub parts that make up this entity.
     * <p>
     * The entities returned by this method are NOT saved to the world in nay way, they exist as an extension
     * of their host entity. The child entity does not track its server-side(or client-side) counterpart, and
     * the host entity is responsible for moving and managing these children.
     * <p>
     * Only used if {@link #isMultipartEntity()} returns true.
     * <p>
     * See {@link EnderDragon} for an example implementation.
     *
     * @return The child parts of this entity. The value to be returned here should be cached.
     */
    @Nullable
    @Override
    public PartEntity<?>[] getParts()
    {
        return super.getParts();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap)
    {
        return super.getCapability(cap);
    }

    @Override
    protected void dropAllDeathLoot(@NotNull DamageSource p_21192_)
    {
        super.dropAllDeathLoot(p_21192_);
    }

    @Override
    protected void dropEquipment()
    {
        super.dropEquipment();
    }

    @Override
    public void startAutoSpinAttack(int p_21327_)
    {
        super.startAutoSpinAttack(p_21327_);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(6, new RangedAttackGoal((RangedAttackMob) this, 2.0D, 20, 20.0F)
        {
            private int attackTime = -1;
            private int seeTime;

            @Override
            public boolean canUse()
            {
                super.canUse();
                Vec3 thisPos = new Vec3(getX(), getY(), getZ());
                Vec3 targetPos = new Vec3(Objects.requireNonNull(getTarget()).getX(), getTarget().getY(), getTarget().getZ());
                return thisPos.distanceTo(targetPos) > 10 && hasLineOfSight(getTarget());
            }

            @Override
            public void tick()
            {
                double d0 = distanceToSqr(Objects.requireNonNull(getTarget()).getX(), getTarget().getY(), getTarget().getZ());
                boolean flag = getSensing().hasLineOfSight(getTarget());
                if (flag)
                {
                    ++this.seeTime;
                }
                else
                {
                    this.seeTime = 0;
                }

                double attackRadiusSqr = 20.0D * 20.0D;
                if (!(d0 > (double) attackRadiusSqr) && this.seeTime >= 5)
                {
                    getNavigation().stop();
                }
                else
                {
                    double speedModifier = 2.0D;
                    getNavigation().moveTo(getTarget(), speedModifier);
                }

                getLookControl().setLookAt(getTarget(), 30.0F, 30.0F);
                double attackIntervalMax = 20.0D;
                double attackIntervalMin = 20.0D;
                double attackRadius = 20.0F;
                if (--this.attackTime == 0)
                {
                    if (!flag)
                    {
                        return;
                    }

                    float f = (float) ((float) Math.sqrt(d0) / attackRadius);

                    this.attackTime = Mth.floor(f * (float) (attackIntervalMax - attackIntervalMin) + (float) attackIntervalMin);
                }
                else if (this.attackTime < 0)
                {
                    this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double) attackRadius, (double) attackIntervalMin, (double) attackIntervalMax));
                }
            }
        });
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, WitherBoss.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EnderDragon.class, true));
    }

    @Override
    protected void populateDefaultEquipmentSlots(@NotNull DifficultyInstance p_21383_)
    {
        ItemStack stack = new ItemStack(RegistryHandler.WITHER_BLADE.get());
        stack.enchant(RegistryHandler.BLOOD_LOST.get(), 1);
        this.setItemSlot(EquipmentSlot.MAINHAND, stack);
    }

    @Override
    public boolean isPreventingPlayerRest(@NotNull Player p_33036_)
    {
        return true;
    }

    @Override
    protected void setItemSlotAndDropWhenKilled(@NotNull EquipmentSlot p_21469_, @NotNull ItemStack p_21470_)
    {
        super.setItemSlotAndDropWhenKilled(p_21469_, p_21470_);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 160.0D).add(Attributes.MOVEMENT_SPEED, (double) 1.0F).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.ATTACK_KNOCKBACK, 1.0D).add(Attributes.ATTACK_SPEED, (double) 4.0F).add(Attributes.ARMOR, (double) 10.0F).add(Attributes.ARMOR_TOUGHNESS, (double) 5.0F).add(Attributes.KNOCKBACK_RESISTANCE, (double) 0.8F).add(Attributes.FOLLOW_RANGE, (double) 20.0F);
    }
}
