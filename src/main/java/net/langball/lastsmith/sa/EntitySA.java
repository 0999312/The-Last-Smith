package net.langball.lastsmith.sa;

import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.ability.TeleportCanceller;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorDestructable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SlashDimension;
import mods.flammpfeil.slashblade.util.ReflectionAccessHelper;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Furia on 14/05/08.
 */
public class EntitySA extends Entity implements IThrowableEntity {

    protected Entity thrower;

    protected ItemStack blade = ItemStack.EMPTY;

    protected List<Entity> alreadyHitEntity = new ArrayList<Entity>();

    protected float AttackLevel = 0.0f;


    public EntitySA(World par1World)
    {
        super(par1World);
        ticksExisted = 0;

        getEntityData().setInteger("seed", rand.nextInt(50));
    }

    public EntitySA(World par1World, EntityLivingBase entityLiving, float AttackLevel, boolean multiHit){
        this(par1World, entityLiving, AttackLevel);
        this.setIsSingleHit(multiHit);
    }

    public EntitySA(World par1World, EntityLivingBase entityLiving, float AttackLevel)
    {
        this(par1World);

        this.AttackLevel = AttackLevel;

        thrower = entityLiving;

        blade = entityLiving.getHeldItemMainhand();
        if(!blade.isEmpty() && !(blade.getItem() instanceof ItemSlashBlade)){
            blade = ItemStack.EMPTY;
        }

        alreadyHitEntity.clear();
        alreadyHitEntity.add(thrower);
        alreadyHitEntity.add(thrower.getRidingEntity());
        alreadyHitEntity.addAll(thrower.getPassengers());

        ticksExisted = 0;

        setSize(4.0F, 4.0F);

    }




    private static final DataParameter<Integer> LIFETIME = EntityDataManager.<Integer>createKey(EntitySA.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> SINGLE_HIT = EntityDataManager.<Boolean>createKey(EntitySA.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IS_SLASH_DIMENSION = EntityDataManager.<Boolean>createKey(EntitySA.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> THROWER_ENTITY_ID = EntityDataManager.<Integer>createKey(EntitySA.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> INTERVAL = EntityDataManager.<Integer>createKey(EntitySA.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> COLOR = EntityDataManager.<Integer>createKey(EntitySA.class, DataSerializers.VARINT);

    @Override
    protected void entityInit() {
        this.getDataManager().register(LIFETIME, 20);

        this.getDataManager().register(SINGLE_HIT, false);

        this.getDataManager().register(IS_SLASH_DIMENSION, false);

        this.getDataManager().register(THROWER_ENTITY_ID, 0);

        this.getDataManager().register(INTERVAL, 7);

        this.getDataManager().register(COLOR, 0x3333FF);

    }

    public boolean getIsSingleHit(){
        return this.getDataManager().get(SINGLE_HIT);
    }
    public void setIsSingleHit(boolean isSingleHit){
        this.getDataManager().set(SINGLE_HIT,isSingleHit);
    }

    public int getLifeTime(){
        return this.getDataManager().get(LIFETIME);
    }
    public void setLifeTime(int lifetime){
        this.getDataManager().set(LIFETIME,lifetime);
    }

    public boolean getIsSlashDimension(){
        return this.getDataManager().get(IS_SLASH_DIMENSION);
    }
    public void setIsSlashDimension(boolean isSlashDimension){
        this.getDataManager().set(IS_SLASH_DIMENSION, isSlashDimension);
    }

    public int getInterval(){
        return this.getDataManager().get(INTERVAL);
    }
    public void setInterval(int value){
        this.getDataManager().set(INTERVAL,value);
    }

    public int getColor(){
        return this.getDataManager().get(COLOR);
    }
    public void setColor(int value){
        this.getDataManager().set(COLOR,value);
    }

    public int getThrowerEntityId(){
        return this.getDataManager().get(THROWER_ENTITY_ID);
    }
    public void setThrowerEntityId(int entityid){
        this.getDataManager().set(THROWER_ENTITY_ID, entityid);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;

        if(!world.isRemote)
        {
            if(ticksExisted < 8 && ticksExisted % 2 == 0) {
                this.playSound(SoundEvents.ENTITY_WITHER_HURT, 0.2F, 0.5F + 0.25f * this.rand.nextFloat());
            }

            if(this.getThrower() != null){
                AxisAlignedBB bb = this.getEntityBoundingBox();

                if(this.getThrower() instanceof EntityLivingBase){
                    EntityLivingBase entityLiving = (EntityLivingBase)this.getThrower();
                    List<Entity> list = this.world.getEntitiesInAABBexcluding(this.getThrower(), bb, EntitySelectorDestructable.getInstance());

                    StylishRankManager.setNextAttackType(this.thrower ,StylishRankManager.AttackTypes.DestructObject);

                    list.removeAll(alreadyHitEntity);
                    alreadyHitEntity.addAll(list);
                    for(Entity curEntity : list){
                        boolean isDestruction = true;

                        if(curEntity instanceof EntityFireball){
                            if((((EntityFireball)curEntity).shootingEntity != null && ((EntityFireball)curEntity).shootingEntity.getEntityId() == entityLiving.getEntityId())){
                                isDestruction = false;
                            }else{
                                isDestruction = !curEntity.attackEntityFrom(DamageSource.causeMobDamage(entityLiving), this.AttackLevel);
                            }
                        }else if(curEntity instanceof EntityArrow){
                            if((((EntityArrow)curEntity).shootingEntity != null && ((EntityArrow)curEntity).shootingEntity.getEntityId() == entityLiving.getEntityId())){
                                isDestruction = false;
                            }
                        }else if(curEntity instanceof IThrowableEntity){
                            if((((IThrowableEntity)curEntity).getThrower() != null && ((IThrowableEntity)curEntity).getThrower().getEntityId() == entityLiving.getEntityId())){
                                isDestruction = false;
                            }
                        }else if(curEntity instanceof EntityThrowable){
                            if((((EntityThrowable)curEntity).getThrower() != null && ((EntityThrowable)curEntity).getThrower().getEntityId() == entityLiving.getEntityId())){
                                isDestruction = false;
                            }
                        }

                        if(!isDestruction)
                            continue;
                        else{
                            ReflectionAccessHelper.setVelocity(curEntity, 0, 0, 0);
                            curEntity.setDead();

                            for (int var1 = 0; var1 < 10; ++var1)
                            {
                                Random rand = this.getRand();
                                double var2 = rand.nextGaussian() * 0.02D;
                                double var4 = rand.nextGaussian() * 0.02D;
                                double var6 = rand.nextGaussian() * 0.02D;
                                double var8 = 10.0D;
                                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL
                                        , curEntity.posX + (double)(rand.nextFloat() * curEntity.width * 2.0F) - (double)curEntity.width - var2 * var8
                                        , curEntity.posY + (double)(rand.nextFloat() * curEntity.height) - var4 * var8
                                        , curEntity.posZ + (double)(rand.nextFloat() * curEntity.width * 2.0F) - (double)curEntity.width - var6 * var8
                                        , var2, var4, var6);
                            }
                        }

                        StylishRankManager.doAttack(this.thrower);
                    }
                }

                if(getIsSingleHit() || this.ticksExisted % 2 == 0){
                    List<Entity> list = this.world.getEntitiesInAABBexcluding(this.getThrower(), bb, EntitySelectorAttackable.getInstance());
                    list.removeAll(alreadyHitEntity);

                    if(getIsSingleHit())
                        alreadyHitEntity.addAll(list);

                    float magicDamage = Math.max(1.0f, AttackLevel);

                    StylishRankManager.setNextAttackType(this.thrower ,StylishRankManager.AttackTypes.SlashDimMagic);

                    for(Entity curEntity : list){

                        if(getIsSlashDimension()){
                            if(curEntity instanceof EntityLivingBase){
                                float health = ((EntityLivingBase) curEntity).getHealth();
                                if(0 < health){
                                    health = Math.max(1,health - magicDamage);
                                    ((EntityLivingBase) curEntity).setHealth(health);
                                }
                            }
                        }

                        Vec3d pos = curEntity.getPositionVector();

                        TeleportCanceller.setCancel(curEntity);

                        curEntity.hurtResistantTime = 0;
                        DamageSource ds = new EntityDamageSource("directMagic",this.getThrower()).setDamageBypassesArmor().setMagicDamage().setProjectile();


                        if(!blade.isEmpty() && curEntity instanceof EntityLivingBase)
                            ((ItemSlashBlade)blade.getItem()).hitEntity(blade,(EntityLivingBase)curEntity,(EntityLivingBase)thrower);

                        if(!curEntity.getPositionVector().equals(pos))
                            curEntity.setPositionAndUpdate(pos.x,pos.y,pos.z);

                        curEntity.motionX = 0;
                        curEntity.motionY = 0;
                        curEntity.motionZ = 0;

                        if(3 < this.ticksExisted){
                            if(!blade.isEmpty() && curEntity instanceof EntityLivingBase) {
                                if(getIsSlashDimension()){
                                    curEntity.addVelocity(
                                            0,
                                            0.5D,
                                            0);

                                }else{
                                    int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, blade);
                                    if(0 < level){
                                        curEntity.addVelocity(
                                                (double) (Math.sin(getThrower().rotationYaw * (float) Math.PI / 180.0F) * (float) level * 0.5F),
                                                0.2D,
                                                (double) (-Math.cos(getThrower().rotationYaw * (float) Math.PI / 180.0F) * (float) level * 0.5F));
                                    }else{
                                        curEntity.addVelocity(
                                                (double) (-Math.sin(getThrower().rotationYaw * (float) Math.PI / 180.0F) * 0.5),
                                                0.2D,
                                                (double) (Math.cos(getThrower().rotationYaw * (float) Math.PI / 180.0F)) * 0.5);

                                    }
                                }
                            }
                        }

                    }
                }
            }


        }

        if(ticksExisted >= getLifeTime()) {
            alreadyHitEntity.clear();
            alreadyHitEntity = null;
            setDead();
        }
    }

    public Random getRand()
    {
        return this.rand;
    }

    @Override
    public boolean isOffsetPositionInLiquid(double par1, double par3, double par5)
    {
        return false;
    }

    @Override
    public void move(MoverType moverType, double par1, double par3, double par5) {}

    @Override
    protected void dealFireDamage(int par1) {}

    @Override
    public boolean handleWaterMovement()
    {
        return false;
    }

    @Override
    public boolean isInsideOfMaterial(Material par1Material)
    {
        return false;
    }

    @Override
    public boolean isInLava() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBrightnessForRender()
    {
        float f1 = 0.5F;

        if (f1 < 0.0F)
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        int i = super.getBrightnessForRender();
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(f1 * 15.0F * 16.0F);

        if (j > 240)
        {
            j = 240;
        }

        return j | k << 16;
    }

    @Override
    public float getBrightness()
    {
        float f1 = super.getBrightness();
        float f2 = 0.9F;
        f2 = f2 * f2 * f2 * f2;
        return f1 * (1.0F - f2) + f2;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {}

    @Override
    public void setPortal(BlockPos pos) {
    }

    @Override
    public boolean isBurning()
    {
        return false;
    }

    @Override
    public boolean shouldRenderInPass(int pass)
    {
        return pass == 1;
    }

    /**
     * ■Sets the Entity inside a web block.
     */
    @Override
    public void setInWeb() {}

    @Override
    public Entity getThrower() {
        if(this.thrower == null){
            int id = getThrowerEntityId();
            if(id != 0){
                this.thrower = this.getEntityWorld().getEntityByID(id);
            }
        }

        return this.thrower;
    }

    @Override
    public void setThrower(Entity entity) {
        if(entity != null)
            setThrowerEntityId(entity.getEntityId());
        this.thrower = entity;
    }
}
