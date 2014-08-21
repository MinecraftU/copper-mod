package com.example.coppermod.item;

import com.example.coppermod.CopperMod;
import net.minecraft.item.ItemPickaxe;

/**
 * Created by atvaccaro on 8/20/14.
 */
public class ItemCopperPickaxe extends ItemPickaxe
{
    public ItemCopperPickaxe(ToolMaterial tm, String name)
    {
        super(tm);

        setUnlocalizedName(name);
        setTextureName(CopperMod.MODID + ":" + getUnlocalizedName().substring(5));
    }
}
