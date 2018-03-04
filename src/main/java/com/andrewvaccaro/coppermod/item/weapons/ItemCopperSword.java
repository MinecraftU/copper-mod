package com.andrewvaccaro.coppermod.item.weapons;

import com.andrewvaccaro.coppermod.CopperMod;
import com.andrewvaccaro.coppermod.init.CopperModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import java.util.Random;

public class ItemCopperSword extends ItemSword {
    public static int timeSinceLastParticleSpawn = 0;

    public ItemCopperSword() {
        super(CopperMod.COPPER);
        this.setUnlocalizedName("copper_sword");
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.COMBAT);
        this.setMaxDamage(751); // durability
    }

    /**
     * Returns the damage vs entities (sorta)
     *
     * @param par1Entity
     * @return
     */
    public int getDamageVsEntity(Entity par1Entity) {
        return 3;//(int)this.material.getDamageVsEntity();
    }

    /**
     * Called when the item is used to hit an entity
     *
     * @param item
     * @param target
     * @param player
     * @return
     */
    public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase player) {
        item.damageItem(1, player);    //damage item
        //Create explosion on hit
        //4.0f is standard TNT strength
        //target.worldObj.createExplosion(null, target.posX, target.posY, target.posZ, 4.0f, true);

        //Set hit entity on fire
        //target.setFire(4);

        //Make target fly up
        target.addVelocity(0, 1, 0);

        //Attempt at creating lightning strike on hit
        //EntityLightningBolt lightning = new EntityLightningBolt(target.worldObj, target.posX, target.posY, target.posZ);
        //entityHitting.worldObj.addWeatherEffect(lightning);

        //Give potion effect to hit entity
        //target.addPotionEffect(new PotionEffect(1, 1, 0));

        //item.damageItem(1, player);


        if (true) //!world.isRemote && timeSinceLastParticleSpawn % 40 == 0)
        {
            timeSinceLastParticleSpawn++;
            //System.out.println("spawn particles? " + target.worldObj.isRemote); //debug print
            Random rand = new Random();
            int width = 1;
            int height = 1;
            for (int particles = 0; particles < 10; particles++) {
                double motionX = rand.nextGaussian() * 0.02D;
                double motionY = rand.nextGaussian() * 0.02D;
                double motionZ = rand.nextGaussian() * 0.02D;
                target.worldObj.spawnParticle(
                        EnumParticleTypes.EXPLOSION_NORMAL,
                        player.posX, //+ rand.nextFloat() * width * 2.0F - width,
                        player.posY + 3, //+ 0.5D + rand.nextFloat() * height,
                        player.posZ, //+ rand.nextFloat() * width * 2.0F - width,
                        motionX,
                        motionY,
                        motionZ);
            }
        }
        return true;
    }   //end hitEntity

    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity player, int i, boolean flag) {
        EntityPlayer p = (EntityPlayer) player;


        if (p.getHeldItemMainhand() != null && p.getHeldItemMainhand().getItem() == CopperModItems.copperSword) {
            p.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.SPEED, 1, 0)));
        }

        timeSinceLastParticleSpawn++;
        if (timeSinceLastParticleSpawn == 20) //!world.isRemote && timeSinceLastParticleSpawn % 40 == 0)
        {
            timeSinceLastParticleSpawn = 0;
            //System.out.println("spawn particles?"); //debug print
            Random rand = new Random();
            int width = 1;
            int height = 1;
            for (int particles = 0; particles < 10; particles++) {
                double motionX = rand.nextGaussian() * 0.02D;
                double motionY = rand.nextGaussian() * 0.02D;
                double motionZ = rand.nextGaussian() * 0.02D;
                world.spawnParticle(
                        EnumParticleTypes.EXPLOSION_NORMAL,
                        player.posX, //+ rand.nextFloat() * width * 2.0F - width,
                        player.posY + 1, //+ 0.5D + rand.nextFloat() * height,
                        player.posZ, //+ rand.nextFloat() * width * 2.0F - width,
                        motionX,
                        motionY,
                        motionZ);
            }
        }
    }
}
