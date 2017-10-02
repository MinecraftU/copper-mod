package com.andrewvaccaro.coppermod.item.food;

import com.andrewvaccaro.coppermod.CopperMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by atvaccaro on 6/4/15.
 */
public class ItemBucketOfFlour extends Item {
    public ItemBucketOfFlour() {
        this.setUnlocalizedName("flour");
        this.setCreativeTab(CreativeTabs.MATERIALS);
        this.setContainerItem(Items.BUCKET);
    }
}
