package com.andrewvaccaro.coppermod.item.tools;

import com.andrewvaccaro.coppermod.CopperMod;
import net.minecraft.item.ItemSpade;

/**
 * Created by atvaccaro on 8/20/14.
 */
public class ItemCopperShovel extends ItemSpade
{
    public ItemCopperShovel(ToolMaterial tm, String name)
    {
        super(tm);
        setUnlocalizedName(name);
        setTextureName(CopperMod.MOD_ID + ":" + getUnlocalizedName().substring(5));
    }
}
