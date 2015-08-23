package utd.atvaccaro.coppermod.item;

import utd.atvaccaro.coppermod.CopperMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

/**
 * Created by atvaccaro on 6/4/15.
 */
public class ItemBucketOfFlour extends Item {
    public ItemBucketOfFlour() {
        this.setUnlocalizedName("flour");
        this.setTextureName(CopperMod.MODID + ":flour");
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setContainerItem(Items.bucket);
    }
}
