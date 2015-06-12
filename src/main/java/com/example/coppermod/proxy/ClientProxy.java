package com.example.coppermod.proxy;

import com.example.coppermod.entity.EntityExplodingArrow;
import com.example.coppermod.renderer.RenderExplodingArrow;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by atvaccaro on 6/11/15.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderThing()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityExplodingArrow.class, new RenderExplodingArrow());
    }

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }

}