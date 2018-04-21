package com.andrewvaccaro.coppermod.init;

import com.andrewvaccaro.coppermod.block.BlockBlueberry;
import com.andrewvaccaro.coppermod.block.BlockCopperBlock;
import com.andrewvaccaro.coppermod.block.BlockCopperOre;
import com.andrewvaccaro.coppermod.block.BlockMetalworkingBench;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CopperModBlocks {
    public static Block copperOre;
    public static Block copperBlock;
    public static Block blueberryBlock;

    public static Block metalworkingBench;
    public static final int guiIDMetalworkingBench = 1;

    public static void init() {
        copperOre = new BlockCopperOre();
        copperBlock = new BlockCopperBlock();
        blueberryBlock = new BlockBlueberry();
        metalworkingBench = new BlockMetalworkingBench();
    }

    public static void register() {
//        GameRegistry.registerBlock(copperOre, copperOre.getUnlocalizedName());
//        GameRegistry.registerBlock(copperBlock, copperBlock.getUnlocalizedName());
//        GameRegistry.registerBlock(blueberryBlock, blueberryBlock.getUnlocalizedName());
    }

    public static void registerRenders() {

    }

    public static void registerRender(Item item) {

    }

}
