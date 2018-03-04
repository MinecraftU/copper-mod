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
    public static Item explodingBow;
    public static Item explodingArrow;
    public static Item greenApple;
    public static Item itemFlour;
    public static Item itemTortillaDough;
    public static Item itemTortilla;
    public static Item itemShreddedSteak;
    public static Item itemSteakTaco;
    public static Item chocolateCoveredBlueberry;
    public static Item steakSandwich;;

    public static void init() {
        copperIngot = new ItemCopperIngot();
        itemTortillaDough = new ItemTortillaDough();
        blueberry = new ItemBlueberry();
        copperSword = new ItemCopperSword(CopperMod.COPPER);
        copperPickaxe = new ItemCopperPickaxe(CopperMod.COPPER, "copper_pickaxe");
        copperAxe = new ItemCopperAxe(CopperMod.COPPER, "copper_axe");
        copperShovel = new ItemCopperShovel(CopperMod.COPPER, "copper_shovel");
        copperHoe = new ItemCopperHoe(CopperMod.COPPER, "copper_hoe");
        mysteryToolSeed = new ItemMysteryToolSeed();
        copperHelmet = new ItemCopperArmor(COPPER_ARMOR, EntityEquipmentSlot.HEAD, "copper_helmet");
        copperChestplate = new ItemCopperArmor(COPPER_ARMOR, EntityEquipmentSlot.CHEST, "copper_chestplate");
        copperLegs = new ItemCopperArmor(COPPER_ARMOR, EntityEquipmentSlot.LEGS, "copper_legs");
        copperBoots = new ItemCopperArmor(COPPER_ARMOR, EntityEquipmentSlot.FEET, "copper_boots");
        greenApple = new ItemFood(6, 0.2F, false).setUnlocalizedName("green_apple");
        itemSteakTaco = new ItemSteakTaco(12, 0.8F, false); //with entirely new class
        itemTortilla = new ItemTortilla(1, 0.1F, false);
        chocolateCoveredBlueberry = new ItemChocolateCoveredBlueberry();
        steakSandwich = new ItemSteakSandwich(8, 0.5f, false);
    }

    public static void register() {
        GameRegistry.registerItem(blueberry, blueberry.getUnlocalizedName());
        GameRegistry.registerItem(itemTortillaDough, itemTortillaDough.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(copperSword, CopperMod.MOD_ID + "_" + copperSword.getUnlocalizedName());
        GameRegistry.registerItem(copperPickaxe, CopperMod.MOD_ID + "_" + copperPickaxe.getUnlocalizedName());
        GameRegistry.registerItem(copperAxe, CopperMod.MOD_ID + "_" + copperAxe.getUnlocalizedName());
        GameRegistry.registerItem(copperShovel, CopperMod.MOD_ID + "_" + copperShovel.getUnlocalizedName());
        GameRegistry.registerItem(copperHoe, CopperMod.MOD_ID + "_" + copperHoe.getUnlocalizedName());
        GameRegistry.registerItem(mysteryToolSeed, CopperMod.MOD_ID + "_" + mysteryToolSeed.getUnlocalizedName());
        GameRegistry.registerItem(copperHelmet, CopperMod.MOD_ID + "_" + copperHelmet.getUnlocalizedName());
        GameRegistry.registerItem(copperChestplate, CopperMod.MOD_ID + "_" + copperChestplate.getUnlocalizedName());
        GameRegistry.registerItem(copperLegs, CopperMod.MOD_ID + "_" + copperLegs.getUnlocalizedName());
        GameRegistry.registerItem(copperBoots, CopperMod.MOD_ID + "_" + copperBoots.getUnlocalizedName());
        GameRegistry.registerItem(greenApple, CopperMod.MOD_ID + "_" + greenApple.getUnlocalizedName());
        GameRegistry.registerItem(itemSteakTaco, CopperMod.MOD_ID + "_" + itemSteakTaco.getUnlocalizedName());
        GameRegistry.registerItem(itemTortilla, CopperMod.MOD_ID + "_" + itemTortilla.getUnlocalizedName());
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
