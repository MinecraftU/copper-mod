package com.andrewvaccaro.coppermod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCopperBlock extends Block
{

    public BlockCopperBlock(Material mat)
    {
        super(mat);

        this.setHardness(5.0F);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setHarvestLevel("pickaxe", 2);
    }
}
