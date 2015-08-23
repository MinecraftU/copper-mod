package utd.atvaccaro.coppermod.crafting;

import net.minecraft.item.crafting.IRecipe;

import java.util.Comparator;

/**
 * Created by atvaccaro on 8/21/14.
 */
public class MetalworkingBenchRecipeSorter implements Comparator
{
    final MetalworkingBenchCraftingManager metalworkingBenchManager;

    public MetalworkingBenchRecipeSorter(MetalworkingBenchCraftingManager manager)
    {
        this.metalworkingBenchManager = manager;
    }

    public int compareRecipes(IRecipe ir1, IRecipe ir2)
    {
        //wtf is this horrifying mess
        return ir1 instanceof MetalworkingBenchShapelessRecipes && ir2 instanceof MetalworkingBenchShapedRecipes
                ? 1 : (ir1 instanceof  MetalworkingBenchShapedRecipes && ir2 instanceof MetalworkingBenchShapelessRecipes
                ? -1 : (ir2.getRecipeSize() < ir1.getRecipeSize() ? -1 : (ir2.getRecipeSize() > ir1.getRecipeSize() ? 1 : 0)));
    }

    @Override
    public int compare(Object o1, Object o2)
    {
        return this.compareRecipes((IRecipe)o1, (IRecipe)o2);
    }
}
