package com.example.coppermod.block;

import com.example.coppermod.CopperMod;
import com.example.coppermod.item.ItemCopperPickaxe;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.Random;

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

        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        world.setBlock(x, y, z, CopperMod.copperBlock);
        //this.setBlockTextureName("coppermod:copper_block");
        //world.markBlockForUpdate(x, y, z);
    }

    public int tickRate(World world)
    {
        return 40;
    }

    @SubscribeEvent
    public void onBlockBroken(BlockEvent.BreakEvent event)
    {
        if(event.block instanceof BlockCopperOre && !(event.getPlayer().inventory.getCurrentItem().getItem() instanceof ItemCopperPickaxe))
        {
            event.setCanceled(true);
        }
    }   //end onBlockBroken

}
