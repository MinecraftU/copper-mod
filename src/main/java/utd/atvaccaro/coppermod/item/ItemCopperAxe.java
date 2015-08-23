package utd.atvaccaro.coppermod.item;

import utd.atvaccaro.coppermod.CopperMod;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

import java.util.Set;

/**
 * Created by atvaccaro on 8/20/14.
 */
public class ItemCopperAxe extends ItemAxe
{
    private static Set effectiveAgainst = Sets.newHashSet(new Block[]{
            Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2,
            Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});

    public ItemCopperAxe(ToolMaterial tm, String name)
    {
        super(tm);

        setUnlocalizedName(name);
        setTextureName(CopperMod.MODID + ":" + getUnlocalizedName().substring(5));
    }

    @Override
    public boolean func_150897_b(Block block)   //essentially "canHarvestBlocK"
    {
        return effectiveAgainst.contains(block) ? true : super.func_150897_b(block);
    }

    @Override
    public float func_150893_a(ItemStack stack, Block block)    //essentially "getEfficiencyVersusBlock"
    {
        if (block.getMaterial() == Material.wood || block.getMaterial() == Material.vine || block.getMaterial() == Material.plants)
            return this.efficiencyOnProperMaterial; //necessary for axe class

        return effectiveAgainst.contains(block) ? this.efficiencyOnProperMaterial : super.func_150893_a(stack, block);
    }
}
