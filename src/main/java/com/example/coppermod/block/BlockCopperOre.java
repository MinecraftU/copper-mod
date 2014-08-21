package com.example.coppermod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Created by atvaccaro on 8/8/14.
 */
public class BlockCopperOre extends Block
{

    public BlockCopperOre(Material p_i45394_1_) {
        super(p_i45394_1_);

        this.setBlockName("copper_ore");
        this.setHardness(10.0F);
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockTextureName("coppermod:copper_ore");
        this.setHarvestLevel("pickaxe", 2);
    }
}
