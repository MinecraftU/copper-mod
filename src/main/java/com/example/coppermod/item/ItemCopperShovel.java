package com.example.coppermod.item;

import com.example.coppermod.CopperMod;
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
        setTextureName(CopperMod.MODID + ":" + getUnlocalizedName().substring(5));
    }
}
