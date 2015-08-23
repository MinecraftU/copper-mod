package utd.atvaccaro.coppermod.item;

import utd.atvaccaro.coppermod.CopperMod;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Set;

/**
 * Created by atvaccaro on 8/20/14.
 */
public class ItemCopperPickaxe extends ItemPickaxe
{
    //as if it was a shovel
    private static Set effectiveAgainst = Sets.newHashSet(new Block[]{
            Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel,
            Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland,
            Blocks.soul_sand, Blocks.mycelium});

    public ItemCopperPickaxe(ToolMaterial tm, String name)
    {
        super(tm);

        setUnlocalizedName(name);
        setTextureName(CopperMod.MODID + ":" + getUnlocalizedName().substring(5));
    }

    //unsure if these are the correct params or not
    @Override
    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block blockBroken,
                                    int x, int y, int z, EntityLivingBase player)
    {
        System.out.println("Broke block " + blockBroken.getUnlocalizedName());
        //isRemote is TRUE FOR CLIENT AND FALSE FOR SERVER
        if (!world.isRemote) {
            world.setBlock(x, y, z, Blocks.air);
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Blocks.obsidian, 1)));
        }
        return false;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("pickaxe", "spade"); //is both a pickaxe and spade
    }
}
