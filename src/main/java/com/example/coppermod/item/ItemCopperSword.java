package com.example.coppermod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

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

    //How much damage dealt when hitting an entity
    public int getDamageVsEntity(Entity par1Entity)
    {
        return (int)this.material.getDamageVsEntity();
    }

    //When entity is hit with item
    public boolean hitEntity(ItemStack itemHitting, EntityLivingBase entityBeingHit, EntityLivingBase entityHitting)
    {
        //Create explosion on hit
        entityBeingHit.worldObj.createExplosion(null, entityBeingHit.posX, entityBeingHit.posY, entityBeingHit.posZ, 10.0f, true);

        //Set hit entity on fire
        //entityBeingHit.setFire(4);

        //Attempt at creating lightning strike on hit
        //EntityLightningBolt lightning = new EntityLightningBolt(entityBeingHit.worldObj, entityBeingHit.posX, entityBeingHit.posY, entityBeingHit.posZ);

        //Give potion effect to hit entity
        //entityBeingHit.addPotionEffect(new PotionEffect(1, 1, 1, false));

        itemHitting.damageItem(1, entityHitting);
        return true;
    }
}
