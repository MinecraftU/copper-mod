package com.andrewvaccaro.coppermod.item.food;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemSteakSandwich extends ItemFood {
    public ItemSteakSandwich(int heal, float saturation, boolean isWolfsFavorite) {
        super(heal, saturation, isWolfsFavorite);

        this.setUnlocalizedName("steak_sandwich");
        this.setCreativeTab(CreativeTabs.FOOD);
    }

    @Override
    protected void onFoodEaten(ItemStack item, World world, EntityPlayer player)
    {
        if(!world.isRemote)
            player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 5 * 20, 0)); // Potion ID, duration in ticks, amplifier
    }
}
