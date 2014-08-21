package com.example.coppermod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by atvaccaro on 8/8/14.
 */
public class ItemCopperIngot extends Item
{
    public ItemCopperIngot()
    {
        this.setUnlocalizedName("copper_ingot");
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setTextureName("coppermod:copper_ingot");
    }
}
