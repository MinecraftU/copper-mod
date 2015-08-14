package com.example.coppermod;

import com.example.coppermod.block.*;
import com.example.coppermod.entity.EntityCyclops;
import com.example.coppermod.entity.EntityExplodingArrow;
import com.example.coppermod.handler.EntityHandler;
import com.example.coppermod.handler.FuelHandler;
import com.example.coppermod.handler.GuiHandler;
import com.example.coppermod.proxy.ClientProxy;
import com.example.coppermod.proxy.CommonProxy;
import com.example.coppermod.renderer.RenderExplodingArrow;
import com.example.coppermod.renderer.RenderExplodingBow;
import com.example.coppermod.tileentity.TileEntityAlabasterOven;
import com.example.coppermod.worldgen.OreManager;
import com.example.coppermod.item.*;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@Mod(modid = CopperMod.MODID, version = CopperMod.VERSION)
public class CopperMod
{
    //Mod info variables
    public static final String MODID = "coppermod";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    public static CopperMod instance;

    @SidedProxy(clientSide="com.example.coppermod.proxy.ClientProxy", serverSide="com.example.coppermod.proxy.ServerProxy")
    public static ClientProxy clientProxy;
    public static CommonProxy commonProxy;


    //Block variables
    public static Block copperOre;
    public static Block copperBlock;

    public static Block alabasterOvenIdle;
    public static Block alabasterOvenActive;
    public static final int guiIDAlabasterOven = 0;

    public static Block metalworkingBench;
    public static final int guiIDMetalworkingBench = 1;

    public static CreativeTabs tabCopper = new CreativeTabs("CopperTab") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return CopperMod.copperIngot;
        }
    };


    //Item variables
    public static Item copperIngot;

    //Crops
    public static Block blueberryBlock;
    public static Item blueberry;

    //Tools
    public static Item copperSword;
    public static Item copperPickaxe;
    public static Item copperAxe;
    public static Item copperShovel;
    public static Item copperHoe;
    public static Item mysteryToolSeed;

    //Armor
    public static Item copperHelmet;
    public static Item copperChestplate;
    public static Item copperLegs;
    public static Item copperBoots;

    //Bow and arrow
    public static Item explodingBow;
    public static Item explodingArrow;

    //Food variables
    public static Item greenApple;
    public static ItemBucketOfFlour itemFlour;
    public static ItemTortillaDough itemTortillaDough;
    public static ItemTortilla itemTortilla;
    public static ItemShreddedSteak itemShreddedSteak;
    public static ItemSteakTaco itemSteakTaco;
    public static Item chocolateCoveredBlueberry;
    public static ItemSteakSandwich steakSandwich;

    //Misc variables
    private static OreManager oreManager;


    //Create tool and armor materials
    public static final Item.ToolMaterial COPPER = EnumHelper.addToolMaterial("copper_tool", 2,
            150, 5.0F, 2.0F, 21); //Harvest level, durability, block damage, entity damage, enchantability

    public static final ItemArmor.ArmorMaterial COPPER_ARMOR = EnumHelper.addArmorMaterial("copper_armor", 20,
            new int[]{2, 6, 5, 2}, 20); //durability (diamond = 33), damage done to pieces (helmet down to boots), enchantability

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
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

        //BOWS AND ARROWS
        explodingBow = new ItemExplodingBow();
        GameRegistry.registerItem(explodingBow, MODID + "_" + explodingBow.getUnlocalizedName());
        explodingArrow = new ItemExplodingArrow();
        GameRegistry.registerItem(explodingArrow, MODID + "_" + explodingArrow.getUnlocalizedName());
        clientProxy.registerRenderThing(); //could put arrow render registrations in client proxy

        //bow rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityExplodingArrow.class, new RenderExplodingArrow());

        EntityRegistry.registerGlobalEntityID(EntityExplodingArrow.class, "exploding_arrow",
                EntityRegistry.findGlobalUniqueEntityId());

        EntityRegistry.registerModEntity(EntityExplodingArrow.class, "exploding_arrow", 1, CopperMod.MODID,
                128, 1, true);
        //MinecraftForgeClient.registerItemRenderer(CopperMod.explodingBow, new RenderExplodingBow());


        //the order matters apparently?
        //RenderingRegistry.registerEntityRenderingHandler(EntityExplodingArrow.class, new RenderExplodingArrow());
        //EntityRegistry.registerModEntity(EntityExplodingArrow.class, "mystery_arrow", 1, this, 128, 1, true);

        //CROPS
        blueberryBlock = new BlockBlueberry();
        GameRegistry.registerBlock(blueberryBlock, blueberryBlock.getUnlocalizedName());
        blueberry = new ItemBlueberry();
        GameRegistry.registerItem(blueberry, blueberry.getUnlocalizedName());



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

        mysteryToolSeed = new ItemMysteryToolSeed();
        GameRegistry.registerItem(mysteryToolSeed, MODID + "_" + mysteryToolSeed.getUnlocalizedName());



        //ARMOR
        copperHelmet = new ItemCopperArmor(COPPER_ARMOR, 0, "copper_helmet");
        GameRegistry.registerItem(copperHelmet, MODID + "_" + copperHelmet.getUnlocalizedName());

        copperChestplate = new ItemCopperArmor(COPPER_ARMOR, 1, "copper_chestplate");
        GameRegistry.registerItem(copperChestplate, MODID + "_" + copperChestplate.getUnlocalizedName());

        copperLegs = new ItemCopperArmor(COPPER_ARMOR, 2, "copper_legs");
        GameRegistry.registerItem(copperLegs, MODID + "_" + copperLegs.getUnlocalizedName());

        copperBoots = new ItemCopperArmor(COPPER_ARMOR, 3, "copper_boots");
        GameRegistry.registerItem(copperBoots, MODID + "_" + copperBoots.getUnlocalizedName());

        //FOODS
        greenApple = new ItemFood(6, 0.2F, false).setUnlocalizedName("green_apple").setTextureName(MODID + ":"
                + "green_apple");   //just creating a new standard food
        GameRegistry.registerItem(greenApple, MODID + "_" + greenApple.getUnlocalizedName());

        itemSteakTaco = new ItemSteakTaco(12, 0.8F, false); //with entirely new class
        GameRegistry.registerItem(itemSteakTaco, MODID + "_" + itemSteakTaco.getUnlocalizedName());

        itemTortilla = new ItemTortilla(1, 0.1F, false);
        GameRegistry.registerItem(itemTortilla, MODID + "_" + itemTortilla.getUnlocalizedName());

        chocolateCoveredBlueberry = new ItemChocolateCoveredBlueberry();
        GameRegistry.registerItem(chocolateCoveredBlueberry, chocolateCoveredBlueberry.getUnlocalizedName());

        steakSandwich = new ItemSteakSandwich(8, 0.5f, false);
        GameRegistry.registerItem(steakSandwich, steakSandwich.getUnlocalizedName());


        //HANDLERS
        GameRegistry.registerFuelHandler(new FuelHandler());


        //REMOVE RECIPES
        CopperMod.removeRecipesWithResult(new ItemStack(Items.stick, 4));

        //RECIPES
        GameRegistry.addShapedRecipe(new ItemStack(copperSword), " x ", " x ", " y ", 'x', copperIngot, 'y', Items.stick);
        GameRegistry.addShapedRecipe(new ItemStack(copperBlock), "xxx", "xxx", "xxx", 'x', copperIngot);
        GameRegistry.addShapelessRecipe(new ItemStack(itemSteakTaco), itemTortilla, Items.cooked_beef);
        GameRegistry.addShapedRecipe(new ItemStack(itemTortilla), "xx ", "xx ", "   ", 'x', Items.wheat);
        GameRegistry.addShapedRecipe(new ItemStack(chocolateCoveredBlueberry),
                "ccc",
                "cbc",
                "ccc",
                'b', new ItemStack(blueberry),
                'c', new ItemStack(Items.dye, 1, 3)
        );
        ItemStack aaSword = new ItemStack(copperSword);
        aaSword.addEnchantment(Enchantment.aquaAffinity, 1);
        GameRegistry.addShapelessRecipe(aaSword, new ItemStack(Items.stick));
        GameRegistry.addShapelessRecipe(new ItemStack(itemFlour), new ItemStack(Items.wheat),
                new ItemStack(Items.bucket));


        //SMELTING
        GameRegistry.addSmelting(copperOre, new ItemStack(copperIngot), 0.5F);

        //GRASS SEEDS
        MinecraftForge.addGrassSeed(new ItemStack(copperIngot), 100);   //10 weight is standard for wheat seeds

        //DUNGEONS
        DungeonHooks.addDungeonMob("Ghast", 100);

        //WORLDGEN
        oreManager = new OreManager();
        GameRegistry.registerWorldGenerator(oreManager, 0); //Integer determines when generation code runes (0 = normal)
                                                            //Use higher numbers to run later

        //ENTITIES
        EntityHandler.registerEntities(EntityCyclops.class, "Cyclops");

    } //end preInit

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        //FMLCommonHandler.instance().bus().register(new CraftingHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        RenderingRegistry.registerEntityRenderingHandler(EntityExplodingArrow.class, new RenderExplodingArrow());

    } //end init

    private static void removeRecipesWithResult(ItemStack resultItem)
    {
        ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();

        for (int scan = 0; scan < recipes.size(); scan++)
        {
            IRecipe tmpRecipe = (IRecipe) recipes.get(scan);
            ItemStack recipeResult = tmpRecipe.getRecipeOutput();

            if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
            {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                System.out.println("[" + sdf.format(cal.getTime()) + "] [" + CopperMod.MODID + "] Removing Recipe: " + recipes.get(scan).toString() + " -> " + recipeResult);
                recipes.remove(scan);
            }
        }
    }   //end removeRecipesWithResult
}
