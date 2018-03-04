package com.andrewvaccaro.coppermod.proxy;

import com.andrewvaccaro.coppermod.init.CopperModItems;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenders() {
        CopperModItems.registerRenders();
    }
}
