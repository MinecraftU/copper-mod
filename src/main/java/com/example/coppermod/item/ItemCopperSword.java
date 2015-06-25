package com.example.coppermod.item;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * Created by atvaccaro on 8/8/14.
 */
public class ItemCopperSword extends ItemSword
{
    private final ToolMaterial material;
    private int weaponDamage;

    public ItemCopperSword(ToolMaterial tm) {
        super(tm);

        material = tm;

        this.setTextureName("coppermod:copper_sword");
        this.setUnlocalizedName("copper_sword");

        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabCombat);

        this.setMaxDamage(751);
        this.weaponDamage = 10;

    }

    /**
     * Returns the damage vs entities (sorta)
     * @param par1Entity
     * @return
     */
    public int getDamageVsEntity(Entity par1Entity)
    {
        return 3;//(int)this.material.getDamageVsEntity();
    }

    /**
     * Called when the item is used to hit an entity
     * @param itemHitting
     * @param entityBeingHit
     * @param entityHitting
     * @return
     */
    public boolean hitEntity(ItemStack itemHitting, EntityLivingBase entityBeingHit, EntityLivingBase entityHitting)
    {
        //Create explosion on hit
        //4.0f is standard TNT strength
        //entityBeingHit.worldObj.createExplosion(null, entityBeingHit.posX, entityBeingHit.posY, entityBeingHit.posZ, 4.0f, true);

        //Set hit entity on fire
        //entityBeingHit.setFire(4);

        entityBeingHit.addVelocity(0, 1, 0);

        //Attempt at creating lightning strike on hit
        //EntityLightningBolt lightning = new EntityLightningBolt(entityBeingHit.worldObj, entityBeingHit.posX, entityBeingHit.posY, entityBeingHit.posZ);
        //entityHitting.worldObj.addWeatherEffect(lightning);

        //Give potion effect to hit entity
        entityBeingHit.addPotionEffect(new PotionEffect(1, 1, 0));

        //itemHitting.damageItem(1, entityHitting);


        return true;
    }   //end hitEntity
}
