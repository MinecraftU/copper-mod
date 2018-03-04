package com.andrewvaccaro.coppermod.item.weapons;

import com.andrewvaccaro.coppermod.CopperMod;
import com.andrewvaccaro.coppermod.init.CopperModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCopperArmor extends ItemArmor
{
    public ItemCopperArmor(ArmorMaterial material, EntityEquipmentSlot armorType, String name)
    {
        super(material, 0, armorType);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabs.COMBAT);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
        //player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 500, 4));   //will refresh duration, not stack multiple

        //note that indices for getCurrentArmor are BACKWARDS (eg. 0 = boots, 3 = helm)
        if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == CopperModItems.copperBoots) {
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 2, 10));
        }
    }

    @Override
    public CreativeTabs[] getCreativeTabs() {
        return new CreativeTabs[] {CreativeTabs.COMBAT, CreativeTabs.TOOLS};
    }

    @Override
    public boolean getIsRepairable(ItemStack armor, ItemStack stack) {
        return stack.getItem() == CopperModItems.copperIngot;
    }


}
