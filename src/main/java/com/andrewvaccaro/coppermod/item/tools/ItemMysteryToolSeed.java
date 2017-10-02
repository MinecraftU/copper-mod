package com.andrewvaccaro.coppermod.item.tools;

import com.andrewvaccaro.coppermod.CopperMod;
import com.andrewvaccaro.coppermod.init.CopperModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.Random;

public class ItemMysteryToolSeed extends Item {
    public ItemMysteryToolSeed() {
        this.setUnlocalizedName("mystery_tool_seed");
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
    {
        Random rand = new Random();
        ItemStack returnStack = itemStack;
        final int tool = rand.nextInt(5);
        switch (tool)
        {
            case 0:
                returnStack = new ItemStack(CopperModItems.copperPickaxe);
            case 1:
                returnStack = new ItemStack(CopperModItems.copperAxe);
            case 2:
                returnStack = new ItemStack(CopperModItems.copperShovel);
            case 3:
                returnStack = new ItemStack(CopperModItems.copperHoe);
            case 4:
                returnStack = new ItemStack(CopperModItems.copperSword);
        }

        return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStack);
    }
}
