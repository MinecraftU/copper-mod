package com.andrewvaccaro.coppermod.item.tools;

import com.andrewvaccaro.coppermod.CopperMod;
import com.google.common.collect.Sets;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;

import java.util.Set;

public class ItemCopperAxe extends ItemAxe {
    private static Set effectiveAgainst = Sets.newHashSet(
            Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2,
            Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN);

    public ItemCopperAxe() {
        super(CopperMod.COPPER, 2.0F, 5.0F); // damage and speed
        this.setUnlocalizedName("copper_axe");
    }
}
