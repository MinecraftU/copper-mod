package com.andrewvaccaro.coppermod.init;

import com.andrewvaccaro.coppermod.CopperMod;
import com.andrewvaccaro.coppermod.item.food.*;
import com.andrewvaccaro.coppermod.item.material.ItemCopperIngot;
import com.andrewvaccaro.coppermod.item.tools.*;
import com.andrewvaccaro.coppermod.item.weapons.ItemCopperArmor;
import com.andrewvaccaro.coppermod.item.weapons.ItemCopperSword;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CopperModItems {
    public static final ItemArmor.ArmorMaterial COPPER_ARMOR = EnumHelper.addArmorMaterial(
            "copper_armor",
            "copper_armor",
            20,
            new int[]{2, 6, 5, 2},
            20,
            null,
            1.00f);

    public static Item copperIngot;
    public static Item blueberry;
    public static Item copperSword;
    public static Item copperPickaxe;
    public static Item copperAxe;
    public static Item copperShovel;
    public static Item copperHoe;
    public static Item mysteryToolSeed;
    public static ItemArmor copperHelmet;
    public static ItemArmor copperChestplate;
    public static ItemArmor copperLegs;
    public static ItemArmor copperBoots;
    public static Item greenApple;
    public static Item itemFlour;
    public static Item itemTortillaDough;
    public static Item itemTortilla;
    public static Item itemSteakTaco;
    public static Item chocolateCoveredBlueberry;
    public static Item steakSandwich;

    public static void init() {
        copperIngot = new ItemCopperIngot();
        itemTortillaDough = new ItemTortillaDough();
        blueberry = new ItemBlueberry();
        copperSword = new ItemCopperSword();
        copperPickaxe = new ItemCopperPickaxe();
        copperAxe = new ItemCopperAxe();
        copperShovel = new ItemCopperShovel();
        copperHoe = new ItemCopperHoe();
        mysteryToolSeed = new ItemMysteryToolSeed();
        copperHelmet = new ItemCopperArmor(EntityEquipmentSlot.HEAD, "copper_helmet");
        copperChestplate = new ItemCopperArmor(EntityEquipmentSlot.CHEST, "copper_chestplate");
        copperLegs = new ItemCopperArmor(EntityEquipmentSlot.LEGS, "copper_legs");
        copperBoots = new ItemCopperArmor(EntityEquipmentSlot.FEET, "copper_boots");
        greenApple = new ItemFood(6, 0.2F, false).setUnlocalizedName("green_apple");
        itemSteakTaco = new ItemSteakTaco();
        itemTortilla = new ItemTortilla();
        chocolateCoveredBlueberry = new ItemChocolateCoveredBlueberry();
        steakSandwich = new ItemSteakSandwich();
    }

    public static void register() {
        GameRegistry.registerItem(blueberry, blueberry.getUnlocalizedName());
        GameRegistry.registerItem(itemTortillaDough, CopperMod.MOD_ID + ":" + itemTortillaDough.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(copperSword, copperSword.getUnlocalizedName());
        GameRegistry.registerItem(copperPickaxe, copperPickaxe.getUnlocalizedName());
        GameRegistry.registerItem(copperAxe, copperAxe.getUnlocalizedName());
        GameRegistry.registerItem(copperShovel, copperShovel.getUnlocalizedName());
        GameRegistry.registerItem(copperHoe, copperHoe.getUnlocalizedName());
        GameRegistry.registerItem(mysteryToolSeed, mysteryToolSeed.getUnlocalizedName());
        GameRegistry.registerItem(copperHelmet, copperHelmet.getUnlocalizedName());
        GameRegistry.registerItem(copperChestplate, copperChestplate.getUnlocalizedName());
        GameRegistry.registerItem(copperLegs, copperLegs.getUnlocalizedName());
        GameRegistry.registerItem(copperBoots, CopperMod.MOD_ID + "_" + copperBoots.getUnlocalizedName());
        GameRegistry.registerItem(greenApple, greenApple.getUnlocalizedName());
        GameRegistry.registerItem(itemSteakTaco, itemSteakTaco.getUnlocalizedName());
        GameRegistry.registerItem(itemTortilla, itemTortilla.getUnlocalizedName());
        GameRegistry.registerItem(chocolateCoveredBlueberry, chocolateCoveredBlueberry.getUnlocalizedName());
        GameRegistry.registerItem(steakSandwich, steakSandwich.getUnlocalizedName());
    }

    public static void registerRenders() {
        registerRender(itemTortillaDough);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft()
                .getRenderItem()
                .getItemModelMesher()
                .register(item, 0, new ModelResourceLocation(
                        CopperMod.MOD_ID + ":" + item.getUnlocalizedName().substring(5),
                        "inventory"
                ));
    }

}
