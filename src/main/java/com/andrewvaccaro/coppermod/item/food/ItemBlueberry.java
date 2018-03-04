package com.andrewvaccaro.coppermod.item.food;

import com.andrewvaccaro.coppermod.init.CopperModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;

public class ItemBlueberry extends ItemSeedFood
{

    public ItemBlueberry()
    {
        super(1, 0.3F, CopperModBlocks.blueberryBlock, Blocks.FARMLAND);
        setUnlocalizedName("blueberry");
        setCreativeTab(CreativeTabs.FOOD);
    }
}