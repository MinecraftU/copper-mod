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

    /**
     * Main constructor for ItemSteakTaco
     * @param foodvalue Amount healed by the food in halves (between 1 and 10 usually)
     * @param satmodifier   How long until hunger starts again (between 0 and 1 usually)
     * @param isWolfsFavoriteMeat   Do wolves like this?
     */
    public ItemSteakTaco(int foodvalue, float satmodifier, boolean isWolfsFavoriteMeat) {
        super(foodvalue, satmodifier, isWolfsFavoriteMeat); //could also hard-code in values here

        this.setCreativeTab(CreativeTabs.FOOD);
        this.setUnlocalizedName("steak_taco");
    }

    /**
     * Called when a food is finished being eaten by a player.
     * @param   itemstack   the item stack of the food being eaten
     * @param   world       the current world object
     * @param   player      the player eating the food
     */
    protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 15 * 20, 0));  //potion id, duration (in ticks so x20), amplifier (0 is base)
        }

        player.inventory.addItemStackToInventory(new ItemStack(Blocks.OBSIDIAN));
    }
}
