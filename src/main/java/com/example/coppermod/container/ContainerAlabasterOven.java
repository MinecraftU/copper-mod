package com.example.coppermod.container;

import com.example.coppermod.CopperMod;
import com.example.coppermod.tileentity.TileEntityAlabasterOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

/**
 * Created by atvaccaro on 8/22/14.
 */
public class ContainerAlabasterOven extends Container
{


    private TileEntityAlabasterOven alabasterOven;

    public ContainerAlabasterOven(InventoryPlayer inventory, TileEntityAlabasterOven tileEntity)
    {
        this.alabasterOven = tileEntity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return false;
    }
}
