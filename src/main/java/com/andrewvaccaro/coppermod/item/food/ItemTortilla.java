package com.andrewvaccaro.coppermod.item.food;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemTortilla extends ItemFood {

    public ItemTortilla(int foodvalue, float satmodifier, boolean isWolfsFavoriteMeat) {
        super(foodvalue, satmodifier, isWolfsFavoriteMeat);

        this.setCreativeTab(CreativeTabs.FOOD);
        this.setUnlocalizedName("itemTortilla");
    }
}
