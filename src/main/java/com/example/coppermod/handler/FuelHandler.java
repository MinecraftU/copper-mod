package com.example.coppermod.handler;

import com.example.coppermod.CopperMod;
import cpw.mods.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

/**
 * Created by atvaccaro on 1/8/15.
 */
public class FuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {

        if (fuel.getItem() == CopperMod.greenApple) return 200;

        return 0;
    }
}
