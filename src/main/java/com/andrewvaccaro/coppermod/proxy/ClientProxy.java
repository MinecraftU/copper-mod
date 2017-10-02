package com.andrewvaccaro.coppermod.proxy;

import com.andrewvaccaro.coppermod.init.CopperModItems;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by atvaccaro on 6/11/15.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenders()
    {
        CopperModItems.registerRenders();
    }
}