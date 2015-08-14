package com.example.coppermod.item;

import net.minecraftforge.common.ISpecialArmor;
import com.example.coppermod.CopperMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

/**
 * Created by atvaccaro on 8/20/14.
 */
public class ItemCopperArmor extends ItemArmor
{
    public ItemCopperArmor(ArmorMaterial material, int armorType, String name)
    {
        super(material, 0, armorType);
        this.setUnlocalizedName(name);
        this.setTextureName(CopperMod.MODID + ":" + getUnlocalizedName().substring(5));
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if (stack.getItem() == CopperMod.copperLegs)
            return CopperMod.MODID + ":models/armor/copper_layer_2.png";

        else
            return CopperMod.MODID + ":models/armor/copper_layer_1.png";
    }   //end getArmorTexture


    //called on every armor tick
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        //player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 500, 4));   //will refresh duration, not stack multiple

        //note that indices for getCurrentArmor are BACKWARDS (eg. 0 = boots, 3 = helm)
        if (player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == CopperMod.copperBoots) {
            player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 2, 10));
        }
    }

    //can put in multiple creative tabs
    @Override
    public CreativeTabs[] getCreativeTabs() {
        return new CreativeTabs[] {CreativeTabs.tabCombat, CreativeTabs.tabTools}; //This lets me put my armor in as many create tabs as I want, pretty cool right?
    }

    //can repair in anvil or not
    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack stack) {
        return stack.getItem() == CopperMod.copperIngot; //Allows certain items to repair this armor.
    }


}
