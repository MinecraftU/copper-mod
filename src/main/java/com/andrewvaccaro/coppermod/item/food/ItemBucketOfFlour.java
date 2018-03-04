package com.andrewvaccaro.coppermod.item.food;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ItemBucketOfFlour extends Item {
    public ItemBucketOfFlour() {
        this.setUnlocalizedName("flour");
        this.setCreativeTab(CreativeTabs.MATERIALS);
        this.setContainerItem(Items.BUCKET);
    }
}
