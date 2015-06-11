package com.example.coppermod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by atvaccaro on 6/4/15.
 */
public class ItemSteakTaco extends ItemFood {
    public ItemSteakTaco(int foodvalue, float satmodifier, boolean isWolfsFavoriteMeat) {
        super(foodvalue, satmodifier, isWolfsFavoriteMeat); //could also hard-code in values here

        this.setCreativeTab(CreativeTabs.tabFood);
        this.setUnlocalizedName("steak_taco");
        this.setTextureName("coppermod:steak_taco");
    }

    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
    {
        player.addPotionEffect(new PotionEffect(1, 20, 1));  //potion id, duration, amplifier
    }
}
