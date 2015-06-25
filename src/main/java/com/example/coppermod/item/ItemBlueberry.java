package com.example.coppermod.item;

import com.example.coppermod.CopperMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;

public class ItemBlueberry extends ItemSeedFood
{

    public ItemBlueberry()
    {
        super(1, 0.3F, CopperMod.blueberryBlock, Blocks.farmland);
        setUnlocalizedName("blueberry");
        setTextureName("coppermod:blueberry");
        setCreativeTab(CreativeTabs.tabFood);
    }
}