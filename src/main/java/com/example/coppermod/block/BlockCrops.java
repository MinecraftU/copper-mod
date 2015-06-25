package com.example.coppermod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCrops extends BlockBush implements IGrowable
{
    protected int maxGrowthStage = 7;


    @SideOnly(Side.CLIENT)
    protected IIcon[] iIcon;

    public BlockCrops()
    {
        // Basic block setup
        setTickRandomly(true);
        float f = 0.5F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        setCreativeTab((CreativeTabs)null);
        setHardness(0.0F);
        setStepSound(soundTypeGrass);
        disableStats();
    }

    /**
     * is the block grass, dirt or farmland
     */
    @Override
    protected boolean canPlaceBlockOn(Block parBlock)
    {
        return parBlock == Blocks.farmland;
    }

    public void incrementGrowStage(World parWorld, Random parRand, int parX, int parY, int parZ)
    {
        int growStage = parWorld.getBlockMetadata(parX, parY, parZ) +
                MathHelper.getRandomIntegerInRange(parRand, 2, 5);

        if (growStage > maxGrowthStage)
        {
            growStage = maxGrowthStage;
        }

        parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random parRand, int parFortune)
    {
        return Item.getItemFromBlock(this);
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType()
    {
        return 1; // Cross like flowers
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int parSide, int parGrowthStage)
    {
        return iIcon[parGrowthStage];
    }
   
    /*
     * Need to implement the IGrowable interface methods
     */

    /*
     * (non-Javadoc)
     * @see net.minecraft.block.IGrowable#func_149851_a(net.minecraft.world.World, 
     * int, int, int, boolean)
     */
    @Override
    // checks if finished growing (a grow stage of 7 is final stage)
    public boolean func_149851_a(World parWorld, int parX, int parY, int parZ,
                                 boolean p_149851_5_)
    {
        return parWorld.getBlockMetadata(parX, parY, parZ) != 7;
    }

    /*
     * (non-Javadoc)
     * @see net.minecraft.block.IGrowable#func_149852_a(net.minecraft.world.World, 
     * java.util.Random, int, int, int)
     */
    @Override
    public boolean func_149852_a(World p_149852_1_, Random parRand, int p_149852_3_,
                                 int p_149852_4_, int p_149852_5_)
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see net.minecraft.block.IGrowable#func_149853_b(net.minecraft.world.World, 
     * java.util.Random, int, int, int)
     */
    @Override
    public void func_149853_b(World parWorld, Random parRand, int parX, int parY,
                              int parZ)
    {
        incrementGrowStage(parWorld, parRand, parX, parY, parZ);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World parWorld, int parX, int parY, int parZ, Random parRand)
    {
        super.updateTick(parWorld, parX, parY, parZ, parRand);
        int growStage = parWorld.getBlockMetadata(parX, parY, parZ) + 1;

        if (growStage > 7)
        {
            growStage = 7;
        }

        parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
    }
}