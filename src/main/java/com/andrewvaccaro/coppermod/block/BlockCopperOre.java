package com.andrewvaccaro.coppermod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCopperOre extends Block {
    public BlockCopperOre() {
        super(Material.ROCK);

        this.setUnlocalizedName("copper_ore");
        this.setHardness(10.0F);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setHarvestLevel("pickaxe", 2);
    }
}
