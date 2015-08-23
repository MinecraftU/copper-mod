package utd.atvaccaro.coppermod.block;

import utd.atvaccaro.coppermod.CopperMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by atvaccaro on 8/19/14.
 */
public class BlockCopperBlock extends Block
{
    public BlockCopperBlock(Material mat)
    {
        super(mat);

        this.setBlockName("copper_block");
        this.setHardness(5.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setBlockTextureName("coppermod:copper_block");
        this.setHarvestLevel("pickaxe", 2);

        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        //world.setBlock(x, y, z, CopperMod.copperOre);
        if (this.getTextureName().contains("ore"))
            this.setBlockTextureName("coppermod:copper_block");
        else
            this.setBlockTextureName("coppermod:copper_ore");
        //Minecraft.getMinecraft().renderGlobal.markBlockForRenderUpdate(x, y, z);
    }

    public int tickRate(World world)
    {
        return 40;
    }


    //Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
    /*public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        return MathHelper.clamp_int(this.quantityDropped(p_149679_2_) + p_149679_2_.nextInt(p_149679_1_ + 1), 1, 4);
    }*/


    //Returns the quantity of items to drop on block destruction.
    public int quantityDropped(Random rand)
    {
        return 9;
        //return 2 + rand.nextInt(3); //random between 2 and 5
    }

    //Returns item dropped when the block is destroyed
    public Item getItemDropped(int p_149650_1_, Random rand, int p_149650_3_)
    {
        return CopperMod.copperIngot;
    }
}
