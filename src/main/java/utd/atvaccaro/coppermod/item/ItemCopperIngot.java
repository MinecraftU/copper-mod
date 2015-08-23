package utd.atvaccaro.coppermod.item;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by atvaccaro on 8/8/14.
 */
public class ItemCopperIngot extends Item
{
    public ItemCopperIngot()
    {
        this.setUnlocalizedName("copper_ingot");
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setTextureName("coppermod:copper_ingot");
    }

    /*@Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        System.out.println("Attempting to make explosion");
        //MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
        MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if(mop != null)
        {
            int blockHitSide = mop.sideHit;
            Block blockLookingAt = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
            world.createExplosion(null, mop.blockX, mop.blockY, mop.blockZ, 3.0f, true);
        }
        return false;
    }   //end onItemUse */

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
        //MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if(mop != null)
        {
            int blockHitSide = mop.sideHit;
            Block blockLookingAt = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
            world.createExplosion(null, mop.blockX, mop.blockY, mop.blockZ, 3.0f, true);
        }
        return itemstack;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

    }
}
