package com.andrewvaccaro.coppermod.util;

import com.andrewvaccaro.coppermod.CopperMod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RecipeUtils {
    public static void removeRecipesWithResult(ItemStack resultItem)
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
                System.out.println("[" + sdf.format(cal.getTime()) + "] [" + CopperMod.MOD_ID + "] Removing Recipe: " + recipes.get(scan).toString() + " -> " + recipeResult);
                recipes.remove(scan);
            }
        }
    }
}
