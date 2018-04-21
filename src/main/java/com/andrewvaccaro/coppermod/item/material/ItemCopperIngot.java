package com.andrewvaccaro.coppermod.item.material;

import com.andrewvaccaro.coppermod.CopperMod;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class ItemCopperIngot extends Item
{
    public ItemCopperIngot()
    {
        this.setUnlocalizedName("copper_ingot");
        this.setCreativeTab(CreativeTabs.MATERIALS);
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(CopperMod.MOD_ID + ":" + this.getUnlocalizedName(), "inventory"));
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        /*System.out.println("Attempting to make explosion");
        //MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
        MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
        if(mop != null)
        {
            int blockHitSide = mop.sideHit;
            Block blockLookingAt = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
            world.createExplosion(null, mop.blockX, mop.blockY, mop.blockZ, 3.0f, true);
        }*/
        return EnumActionResult.SUCCESS;
    }   //end onItemUse

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
//        MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
//        //MovingObjectPosition mop = Minecraft.getMinecraft().objectMouseOver;
//        if(mop != null)
//        {
//            int blockHitSide = mop.sideHit;
//            Block blockLookingAt = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
//            world.createExplosion(null, mop.blockX, mop.blockY, mop.blockZ, 3.0f, true);
//        }
//        return itemstack;
        return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);

    }
}
