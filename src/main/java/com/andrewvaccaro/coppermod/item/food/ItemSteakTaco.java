package com.andrewvaccaro.coppermod.item.food;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemSteakTaco extends ItemFood {
    public ItemSteakTaco() {
        super(12, 0.8F, false);
        this.setCreativeTab(CreativeTabs.FOOD);
        this.setUnlocalizedName("steak_taco");
    }

    @Override
    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 15 * 20, 0));  //potion id, duration (in ticks so x20), amplifier (0 is base)
        }

        player.inventory.addItemStackToInventory(new ItemStack(Blocks.OBSIDIAN));
    }
}
