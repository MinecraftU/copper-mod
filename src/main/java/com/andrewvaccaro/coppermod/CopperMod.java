package com.andrewvaccaro.coppermod;

import com.andrewvaccaro.coppermod.init.CopperModBlocks;
import com.andrewvaccaro.coppermod.util.RecipeUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.init.Enchantments;
import com.andrewvaccaro.coppermod.handler.FuelHandler;
import com.andrewvaccaro.coppermod.init.CopperModItems;
import com.andrewvaccaro.coppermod.proxy.CommonProxy;
import com.andrewvaccaro.coppermod.worldgen.OreManager;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = CopperMod.MOD_ID, version = CopperMod.VERSION)
public class CopperMod {
    public static final String MOD_ID = "coppermod";
    public static final String MOD_NAME = "Copper Mod";
    public static final String VERSION = "1.0";
    public static final String CLIENT_PROXY_CLASS = "com.andrewvaccaro.coppermod.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.andrewvaccaro.coppermod.proxy.ServerProxy";

    @Mod.Instance(MOD_ID)
    public static CopperMod instance;

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    public static CreativeTabs tabCopper = new CreativeTabs("CopperTab") {
        @Override
        @MethodsReturnNonnullByDefault
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return CopperModItems.copperIngot;
        }
    };

    private static OreManager oreManager;

    public static final Item.ToolMaterial COPPER = EnumHelper.addToolMaterial("copper_tool", 2,
            150, 5.0F, 2.0F, 21); //Harvest level, durability, block damage, entity damage, enchantability


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CopperModItems.init();
        CopperModItems.register();
        CopperModBlocks.init();
        CopperModBlocks.register();
        proxy.registerRenders();

        GameRegistry.registerFuelHandler(new FuelHandler());

        RecipeUtils.removeRecipesWithResult(new ItemStack(Items.STICK, 4));

        //RECIPES
        GameRegistry.addShapedRecipe(new ItemStack(CopperModItems.copperSword), "x", "x", "y", 'x', CopperModItems.copperIngot, 'y', Items.STICK);
        GameRegistry.addShapedRecipe(new ItemStack(CopperModBlocks.copperBlock), "xxx", "xxx", "xxx", 'x', CopperModItems.copperIngot);
        GameRegistry.addShapelessRecipe(new ItemStack(CopperModItems.itemSteakTaco), CopperModItems.itemTortilla, Items.COOKED_BEEF);
        GameRegistry.addShapedRecipe(new ItemStack(CopperModItems.itemTortilla), "xx", "xx", 'x', Items.WHEAT);
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
    }
}
