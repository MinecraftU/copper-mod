package utd.atvaccaro.coppermod.item;

import utd.atvaccaro.coppermod.CopperMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by atvaccaro on 6/16/15.
 */
public class ItemMysteryToolSeed extends Item {
    public ItemMysteryToolSeed() {
        this.setUnlocalizedName("mystery_tool_seed");
        this.setTextureName("coppermod:mystery_tool_seed");
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setMaxStackSize(1);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        Random rand = new Random();
        int tool = rand.nextInt(5);
        switch (tool)
        {
            case 0:
                return new ItemStack(CopperMod.copperPickaxe);
            case 1:
                return new ItemStack(CopperMod.copperAxe);
            case 2:
                return new ItemStack(CopperMod.copperShovel);
            case 3:
                return new ItemStack(CopperMod.copperHoe);
            case 4:
                return new ItemStack(CopperMod.copperSword);
            default:
                return itemStack;
        }
    }   //end onItemRightClick
}
