package com.andrewvaccaro.coppermod.item.tools;

import com.andrewvaccaro.coppermod.CopperMod;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class ItemCopperPickaxe extends ItemPickaxe {
    //as if it was a shovel
    private static Set effectiveAgainst = Sets.newHashSet(
            Blocks.GRASS, Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL,
            Blocks.SNOW_LAYER, Blocks.SNOW, Blocks.CLAY, Blocks.FARMLAND,
            Blocks.SOUL_SAND, Blocks.MYCELIUM);

    public ItemCopperPickaxe() {
        super(CopperMod.COPPER);
        this.setUnlocalizedName("copper_pickaxe");
    }
}
