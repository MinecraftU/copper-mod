package com.andrewvaccaro.coppermod.item.food;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemChocolateCoveredBlueberry extends ItemFood {
    public ItemChocolateCoveredBlueberry() {
        super(6, 0.1f, false);

        this.setUnlocalizedName("chocolate_covered_blueberry");
        this.setCreativeTab(CreativeTabs.FOOD);
    }
}
