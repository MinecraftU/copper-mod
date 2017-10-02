package com.andrewvaccaro.coppermod.block;

import com.andrewvaccaro.coppermod.CopperMod;
import com.andrewvaccaro.coppermod.init.CopperModBlocks;
import com.andrewvaccaro.coppermod.tileentity.TileEntityAlabasterOven;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAlabasterOven extends BlockContainer
{
    public boolean isActive;

    private static boolean keepInventory;

    public BlockAlabasterOven(boolean isActive)
    {
        super(Material.IRON);
        this.isActive = isActive;
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            FMLNetworkHandler.openGui(playerIn, CopperMod.instance, CopperModBlocks.guiIDAlabasterOven, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
