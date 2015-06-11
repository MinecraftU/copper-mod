package com.example.coppermod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

/**
 * Created by atvaccaro on 6/4/15.
 */
public class ItemTortilla extends ItemFood {

    public ItemTortilla(int foodvalue, float satmodifier, boolean isWolfsFavoriteMeat) {
        super(foodvalue, satmodifier, isWolfsFavoriteMeat);

        this.setCreativeTab(CreativeTabs.tabFood);
        this.setUnlocalizedName("itemTortilla");
        this.setTextureName(("coppermod:tortilla"));
    }
}
