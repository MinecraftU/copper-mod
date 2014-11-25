package com.example.coppermod;

import com.example.coppermod.block.BlockAlabasterOven;
import com.example.coppermod.block.BlockCopperBlock;
import com.example.coppermod.block.BlockCopperOre;
import com.example.coppermod.block.BlockMetalworkingBench;
import com.example.coppermod.entity.EntityCyclops;
import com.example.coppermod.handler.EntityHandler;
import com.example.coppermod.handler.GuiHandler;
import com.example.coppermod.tileentity.TileEntityAlabasterOven;
import com.example.coppermod.worldgen.OreManager;
import com.example.coppermod.item.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = CopperMod.MODID, version = CopperMod.VERSION)
public class CopperMod
{
    //Mod info variables
    public static final String MODID = "coppermod";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    public static CopperMod instance;

    //Block variables
    public static Block copperOre;
    public static Block copperBlock;

    public static Block alabasterOvenIdle;
    public static Block alabasterOvenActive;
    public static final int guiIDAlabasterOven = 0;

    public static Block metalworkingBench;
    public static final int guiIDMetalworkingBench = 1;


    //Item variables
    public static Item copperIngot;

    public static Item copperSword;
    public static Item copperPickaxe;
    public static Item copperAxe;
    public static Item copperShovel;
    public static Item copperHoe;

    public static Item copperHelmet;
    public static Item copperChestplate;
    public static Item copperLegs;
    public static Item copperBoots;

    public static Item greenApple;

    //Misc variables
    private static OreManager oreManager;


    //Create tool and armor materials
    public static final Item.ToolMaterial COPPER = EnumHelper.addToolMaterial("copperTool", 2,
            150, 5.0F, 7.0F, 21); //Harvest level, durability, block damage, entity damage, enchantability

    public static final ItemArmor.ArmorMaterial COPPER_ARMOR = EnumHelper.addArmorMaterial("copperArmor", 20,
            new int[]{2, 6, 5, 2}, 20); //durability (diamond = 33), damage done to pieces (helmet down to boots), enchantability

    @EventHandler
    public void preInit(FMLInitializationEvent event)
    {
        //BLOCKS
        copperOre = new BlockCopperOre(Material.rock);
        GameRegistry.registerBlock(copperOre, MODID + "_" + copperOre.getUnlocalizedName());

        copperBlock = new BlockCopperBlock(Material.iron);
        GameRegistry.registerBlock(copperBlock, MODID + "_" + copperBlock.getUnlocalizedName());

        metalworkingBench = new BlockMetalworkingBench();
        GameRegistry.registerBlock(metalworkingBench, metalworkingBench.getUnlocalizedName());

        alabasterOvenIdle = new BlockAlabasterOven(false).setBlockName("alabasterOvenIdle");
        alabasterOvenActive = new BlockAlabasterOven(true).setBlockName("alabasterOvenActive").setLightLevel(0.625F);

        GameRegistry.registerBlock(alabasterOvenIdle, MODID + "_" + alabasterOvenIdle.getUnlocalizedName());
        GameRegistry.registerBlock(alabasterOvenActive, MODID + "_" + alabasterOvenActive.getUnlocalizedName());

        //TILE ENTITIES
        GameRegistry.registerTileEntity(TileEntityAlabasterOven.class, "alabaster_oven");


        //ITEMS
        copperIngot = new ItemCopperIngot();
        GameRegistry.registerItem(copperIngot, MODID + "_" + copperIngot.getUnlocalizedName());


        //TOOLS
        copperSword = new ItemCopperSword(CopperMod.COPPER);
        GameRegistry.registerItem(copperSword, MODID + "_" + copperSword.getUnlocalizedName());

        copperPickaxe = new ItemCopperPickaxe(CopperMod.COPPER, "copper_pickaxe");
        GameRegistry.registerItem(copperPickaxe, MODID + "_" + copperPickaxe.getUnlocalizedName());

        copperAxe = new ItemCopperAxe(CopperMod.COPPER, "copper_axe");
        GameRegistry.registerItem(copperAxe, MODID + "_" + copperAxe.getUnlocalizedName());

        copperShovel = new ItemCopperShovel(CopperMod.COPPER, "copper_shovel");
        GameRegistry.registerItem(copperShovel, MODID + "_" + copperShovel.getUnlocalizedName());

        copperHoe = new ItemCopperHoe(CopperMod.COPPER, "copper_hoe");
        GameRegistry.registerItem(copperHoe, MODID + "_" + copperHoe.getUnlocalizedName());



        //ARMOR
        copperHelmet = new ItemCopperArmor(COPPER_ARMOR, 0, "copper_helmet");
        GameRegistry.registerItem(copperHelmet, MODID + "_" + copperHelmet.getUnlocalizedName());

        copperChestplate = new ItemCopperArmor(COPPER_ARMOR, 1, "copper_chestplate");
        GameRegistry.registerItem(copperChestplate, MODID + "_" + copperChestplate.getUnlocalizedName());

        copperLegs = new ItemCopperArmor(COPPER_ARMOR, 2, "copper_legs");
        GameRegistry.registerItem(copperLegs, MODID + "_" + copperLegs.getUnlocalizedName());

        copperBoots = new ItemCopperArmor(COPPER_ARMOR, 3, "copper_boots");
        GameRegistry.registerItem(copperBoots, MODID + "_" + copperBoots.getUnlocalizedName());

        //FOOD
        greenApple = new ItemFood(6, 0.2F, false).setUnlocalizedName("green_apple").setTextureName(MODID + ":"
                + "green_apple");
        GameRegistry.registerItem(greenApple, MODID + "_" + greenApple.getUnlocalizedName());


        //RECIPES

        //shapeless

        //shaped
        GameRegistry.addShapedRecipe(new ItemStack(copperSword), " x ", " x ", " y ", 'x', copperIngot, 'y', Items.stick);
        GameRegistry.addShapedRecipe(new ItemStack(copperBlock), "xxx", "xxx", "xxx", 'x', copperIngot);

        //smelting
        GameRegistry.addSmelting(copperOre, new ItemStack(copperIngot), 0.5F);

        //world generators
        oreManager = new OreManager();
        GameRegistry.registerWorldGenerator(oreManager, 0); //Integer determines when generation code runes (0 = normal)
                                                            //Use higher numbers to run later
        //entities
        EntityHandler.registerEntities(EntityCyclops.class, "Cyclops");

    } //end preInit

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        //FMLCommonHandler.instance().bus().register(new CraftingHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    } //end init
}
