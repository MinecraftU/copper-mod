package com.andrewvaccaro.coppermod.item.weapons;

import com.andrewvaccaro.coppermod.CopperMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by atvaccaro on 6/11/15.
 */
public class ItemExplodingArrow extends Item
{
    public ItemExplodingArrow()
    {
        this.setUnlocalizedName("exploding_arrow");
        this.setCreativeTab(CreativeTabs.tabCombat);
        this.setTextureName(CopperMod.MOD_ID + ":exploding_arrow");
    }
}
