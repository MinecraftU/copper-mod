package com.example.coppermod.block;

import com.example.coppermod.CopperMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by atvaccaro on 8/21/14.
 */
public class BlockMetalworkingBench extends Block
{

    @SideOnly(Side.CLIENT)
    private IIcon metalworkingBenchTop;

    public BlockMetalworkingBench() {
        super(Material.wood);

        this.setHardness(3.5F);
        this.setResistance(5.0F);
        this.setBlockName("metalworkingBench");
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int metadata) {
        return side == 1 ? this.metalworkingBenchTop : this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons (IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(CopperMod.MODID + ":" + "metalworking_bench_side");
        this.metalworkingBenchTop = iconRegister.registerIcon(CopperMod.MODID + ":" + "metalworking_bench_top");
    }

    public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        if (!player.isSneaking()) {
            player.openGui(CopperMod.instance, CopperMod.guiIDMetalworkingBench, world, x, y, z);
            return true;
        }else{
            return false;
        }
    }
}
