package com.andrewvaccaro.coppermod.block;

import com.andrewvaccaro.coppermod.CopperMod;
import com.andrewvaccaro.coppermod.init.CopperModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockMetalworkingBench extends Block {
    public BlockMetalworkingBench() {
        super(Material.WOOD);

        this.setHardness(3.5F);
        this.setResistance(5.0F);
        this.setUnlocalizedName("metalworkingBench");
        this.setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (playerIn.isSneaking()) {
            return false;
        }

        playerIn.openGui(CopperMod.instance, CopperModBlocks.guiIDMetalworkingBench, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
