package com.andrewvaccaro.coppermod.handler;

import com.andrewvaccaro.coppermod.init.CopperModItems;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraft.item.ItemStack;

public class FuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {

        if (fuel.getItem() == CopperModItems.greenApple) return 200;

        return 0;
    }
}
