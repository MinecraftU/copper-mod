package com.example.coppermod.item;

import com.example.coppermod.CopperMod;
import net.minecraft.item.ItemHoe;

/**
 * Created by atvaccaro on 8/20/14.
 */
public class ItemCopperHoe extends ItemHoe
{
    public ItemCopperHoe(ToolMaterial tm, String name)
    {
        super(tm);

        setUnlocalizedName(name);
        setTextureName(CopperMod.MODID + ":" + getUnlocalizedName().substring(5));
    }
}
