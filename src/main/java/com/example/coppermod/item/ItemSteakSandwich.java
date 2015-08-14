package com.example.coppermod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by John on 7/15/2015.
 */
public class ItemSteakSandwich extends ItemFood {
    public ItemSteakSandwich(int heal, float saturation,
                             boolean isWolfsFavorite) {
        super(heal, saturation, isWolfsFavorite);

        this.setUnlocalizedName("steak_sandwich");
        this.setTextureName("examplemod:steak_sandwich");
        this.setCreativeTab(CreativeTabs.tabFood);
    }

    protected void onFoodEaten(ItemStack item, World world,
                               EntityPlayer player)
    {
        if(!world.isRemote)
            player.addPotionEffect(new PotionEffect(
                    Potion.invisibility.getId(), 5 * 20, 0));
                    // Potion ID, duration in ticks, amplifier
    }
}
