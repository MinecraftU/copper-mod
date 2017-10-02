package com.andrewvaccaro.coppermod;

import com.andrewvaccaro.coppermod.block.*;
import com.andrewvaccaro.coppermod.handler.EntityHandler;
import com.andrewvaccaro.coppermod.init.CopperModBlocks;
import com.andrewvaccaro.coppermod.item.*;
import com.andrewvaccaro.coppermod.util.RecipeUtils;
import net.minecraft.init.Enchantments;
import com.andrewvaccaro.coppermod.entity.EntityCyclops;
import com.andrewvaccaro.coppermod.entity.EntityExplodingArrow;
import com.andrewvaccaro.coppermod.handler.FuelHandler;
import com.andrewvaccaro.coppermod.handler.GuiHandler;
import com.andrewvaccaro.coppermod.init.CopperModItems;
import com.andrewvaccaro.coppermod.proxy.CommonProxy;
import com.andrewvaccaro.coppermod.renderer.RenderExplodingArrow;
import com.andrewvaccaro.coppermod.tileentity.TileEntityAlabasterOven;
import com.andrewvaccaro.coppermod.worldgen.OreManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@Mod(modid = CopperMod.MOD_ID, version = CopperMod.VERSION)
public class CopperMod
{
    //Mod info variables
    public static final String MOD_ID = "coppermod";
    public static final String MOD_NAME = "Copper Mod";
    public static final String VERSION = "1.0";
    public static final String CLIENT_PROXY_CLASS = "com.andrewvaccaro.coppermod.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.andrewvaccaro.coppermod.proxy.ServerProxy";

    @Mod.Instance(MOD_ID)
    public static CopperMod instance;

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
    public static CommonProxy proxy;


    //Block variables


    public static CreativeTabs tabCopper = new CreativeTabs("CopperTab") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return CopperModItems.copperIngot;
        }
    };

    //Misc variables
    private static OreManager oreManager;


    //Create tool and armor materials
    public static final Item.ToolMaterial COPPER = EnumHelper.addToolMaterial("copper_tool", 2,
            150, 5.0F, 2.0F, 21); //Harvest level, durability, block damage, entity damage, enchantability


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        CopperModItems.init();
        CopperModItems.register();
        CopperModBlocks.init();
        CopperModBlocks.register();

        //bow rendering
        RenderingRegistry.registerEntityRenderingHandler(EntityExplodingArrow.class, new RenderExplodingArrow());

//        EntityRegistry.registerGlobalEntityID(EntityExplodingArrow.class, "exploding_arrow",
//                EntityRegistry.findGlobalUniqueEntityId());

        EntityRegistry.registerModEntity(EntityExplodingArrow.class, "exploding_arrow", 1, CopperMod.MOD_ID,
                128, 1, true);
        //MinecraftForgeClient.registerItemRenderer(CopperMod.explodingBow, new RenderExplodingBow());


        //the order matters apparently?
        //RenderingRegistry.registerEntityRenderingHandler(EntityExplodingArrow.class, new RenderExplodingArrow());
        //EntityRegistry.registerModEntity(EntityExplodingArrow.class, "mystery_arrow", 1, this, 128, 1, true);

        GameRegistry.registerFuelHandler(new FuelHandler());

        RecipeUtils.removeRecipesWithResult(new ItemStack(Items.STICK, 4));

        //RECIPES
        GameRegistry.addShapedRecipe(new ItemStack(CopperModItems.copperSword), " x ", " x ", " y ", 'x', CopperModItems.copperIngot, 'y', Items.STICK);
        GameRegistry.addShapedRecipe(new ItemStack(CopperModBlocks.copperBlock), "xxx", "xxx", "xxx", 'x', CopperModItems.copperIngot);
        GameRegistry.addShapelessRecipe(new ItemStack(CopperModItems.itemSteakTaco), CopperModItems.itemTortilla, Items.COOKED_BEEF);
        GameRegistry.addShapedRecipe(new ItemStack(CopperModItems.itemTortilla), "xx ", "xx ", "   ", 'x', Items.WHEAT);
        GameRegistry.addShapedRecipe(new ItemStack(CopperModItems.chocolateCoveredBlueberry),
                "ccc",
                "cbc",
                "ccc",
                'b', new ItemStack(CopperModItems.blueberry),
                'c', new ItemStack(Items.DYE, 1, 3)
        );
        ItemStack aaSword = new ItemStack(CopperModItems.copperSword);
        aaSword.addEnchantment(Enchantments.AQUA_AFFINITY, 1);
        GameRegistry.addShapelessRecipe(aaSword, new ItemStack(Items.STICK));
        GameRegistry.addShapelessRecipe(new ItemStack(CopperModItems.itemFlour), new ItemStack(Items.WHEAT),
                new ItemStack(Items.BUCKET));


        GameRegistry.addSmelting(CopperModBlocks.copperOre, new ItemStack(CopperModItems.copperIngot), 0.5F);

        MinecraftForge.addGrassSeed(new ItemStack(CopperModItems.copperIngot), 100);   //10 weight is standard for wheat seeds

        DungeonHooks.addDungeonMob("Ghast", 100);

        oreManager = new OreManager();
        GameRegistry.registerWorldGenerator(oreManager, 0);

        //ENTITIES
        EntityHandler.registerEntities(EntityCyclops.class, "Cyclops");

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerRenders();

        //FMLCommonHandler.instance().bus().register(new CraftingHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        RenderingRegistry.registerEntityRenderingHandler(EntityExplodingArrow.class, new RenderExplodingArrow());

    } //end init


}
