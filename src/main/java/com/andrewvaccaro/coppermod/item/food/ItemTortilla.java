package com.andrewvaccaro.coppermod.item.food;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemTortilla extends ItemFood {
    public ItemTortilla() {
        super(1, 0.1F, false);
        this.setCreativeTab(CreativeTabs.FOOD);
        this.setUnlocalizedName("itemTortilla");
    }
}
