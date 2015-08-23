package utd.atvaccaro.coppermod.item;

import utd.atvaccaro.coppermod.CopperMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

/**
 * Created by atvaccaro on 7/17/15.
 */
public class ItemChocolateCoveredBlueberry extends ItemFood {
    public ItemChocolateCoveredBlueberry() {
        super(6, 0.1f, false);

        this.setUnlocalizedName("chocolate_covered_blueberry");
        this.setTextureName(CopperMod.MODID + ":" + getUnlocalizedName().substring(5));
        this.setCreativeTab(CreativeTabs.tabFood);
    }
}
