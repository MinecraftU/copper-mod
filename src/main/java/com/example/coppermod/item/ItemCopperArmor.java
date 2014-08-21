package com.example.coppermod.item;

import com.example.coppermod.CopperMod;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * Created by atvaccaro on 8/20/14.
 */
public class ItemCopperArmor extends ItemArmor
{
    public ItemCopperArmor(ArmorMaterial material, int armorType, String name)
    {
        super(material, 0, armorType);
        setUnlocalizedName(name);
        setTextureName(CopperMod.MODID + ":" + getUnlocalizedName().substring(5));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(stack.getItem() == CopperMod.copperHelmet)
        {
            return CopperMod.MODID + ":models/armor/copper_layer_1.png"; //Pay attention to the folder path
        }
        else if(stack.getItem() == CopperMod.copperChestplate)
        {
            return CopperMod.MODID + ":models/armor/copper_layer_1.png";
        }
        else if (stack.getItem() == CopperMod.copperLegs)
        {
            return CopperMod.MODID + ":models/armor/copper_layer_2.png";
        }
        else if (stack.getItem() == CopperMod.copperBoots)
        {
            return CopperMod.MODID + ":models/armor/copper_layer_1.png";
        }
        else
        {
            System.out.println("Invalid Item ItemLiveArmor");
            return null;
        }
    }
}
