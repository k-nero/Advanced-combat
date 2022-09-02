package com.kingsman.hyper.reg.monster.boss;

import com.kingsman.hyper.reg.RegistryHandler;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Objects;

public class Knight extends Monster implements IAnimatable, IAnimationTickable
{
    private final AnimationFactory factory = new AnimationFactory( this);
    public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Knight.class, EntityDataSerializers.INT);
    private final ServerBossEvent bossInfo = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true).setCreateWorldFog(true);

    public Knight(EntityType<? extends Monster> entityType, Level level)
    {
        super(entityType, level);
    }

    @Override
    public void defineSynchedData()
    {
        super.defineSynchedData();
        this.getEntityData().define(VARIANT, 0);
    }
    public void readAdditionalSaveData(@NotNull CompoundTag tag)
    {
        super.readAdditionalSaveData(tag);
        this.setVariant(tag.getInt("Variant"));
        if (this.hasCustomName())
        {
            this.bossInfo.setName(Objects.requireNonNull(this.getCustomName()));
        }
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer p_20119_)
    {
        super.startSeenByPlayer(p_20119_);
        this.bossInfo.addPlayer(p_20119_);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer p_20174_)
    {
        super.stopSeenByPlayer(p_20174_);
        this.bossInfo.removePlayer(p_20174_);
    }

    public void setCustomName(Component name)
    {
        super.setCustomName(name);
        this.bossInfo.setName(name);
    }
    public int getVariant()
    {
        return Mth.clamp((Integer) this.entityData.get(VARIANT), 1, 2);
    }

    public int getVariants()
    {
        return 2;
    }

    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor worldIn, @NotNull DifficultyInstance difficultyIn, @NotNull MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag)
    {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        populateDefaultEquipmentSlots(difficultyIn);
        this.setVariant(this.random.nextInt());
        return spawnDataIn;
    }

    public void setVariant(int variant)
    {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public boolean isBaby()
    {
        return false;
    }

    @Override
    public @NotNull MobType getMobType()
    {
        return MobType.UNDEAD;
    }

    @Override
    public void customServerAiStep()
    {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.walking", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public @NotNull Iterable<ItemStack> getArmorSlots()
    {
        return super.getArmorSlots();
    }

    @Override
    public @NotNull ItemStack getItemBySlot(@NotNull EquipmentSlot p_21127_)
    {
        return super.getItemBySlot(p_21127_);
    }

    @Override
    public void setItemSlot(@NotNull EquipmentSlot p_21036_, @NotNull ItemStack p_21037_)
    {
        super.setItemSlot(p_21036_, p_21037_);
    }

    @Override
    public @NotNull HumanoidArm getMainArm()
    {
        return HumanoidArm.RIGHT;
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

    @Override
    public float getStepHeight()
    {
        return super.getStepHeight();
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
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void populateDefaultEquipmentSlots(@NotNull DifficultyInstance p_21383_)
    {
        super.populateDefaultEquipmentSlots(p_21383_);
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

    public static AttributeSupplier createAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 160.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0F)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0D)
                .add(Attributes.ATTACK_SPEED, 4.0F)
                .add(Attributes.ARMOR, 10.0F)
                .add(Attributes.ARMOR_TOUGHNESS, 5.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8F)
                .add(Attributes.FOLLOW_RANGE, 20.0F)
                .build();
    }

    @Override
    @SuppressWarnings("all")
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    @Override
    public int tickTimer()
    {
        return tickCount;
    }

    @Override
    public void tick()
    {
        super.tick();
        this.level.addParticle(ParticleTypes.EFFECT, this.getX(), this.getY(this.random.nextDouble() / 5.0D), this.getZ(), 0.0D, this.random.nextDouble() / 5.0D, 0.5D);
    }

    @Override
    public void knockback(double p_147241_, double p_147242_, double p_147243_)
    {
        super.knockback(0, 0 , 0);
    }

    @Override
    public void setGuaranteedDrop(@NotNull EquipmentSlot p_21509_)
    {
        super.setGuaranteedDrop(p_21509_);
    }

    @Override
    public void swing(@NotNull InteractionHand p_21007_)
    {
        super.swing(p_21007_);
    }

    @Override
    public void setItemInHand(@NotNull InteractionHand p_21009_, @NotNull ItemStack p_21010_)
    {
        super.setItemInHand(p_21009_, p_21010_);
    }
}
