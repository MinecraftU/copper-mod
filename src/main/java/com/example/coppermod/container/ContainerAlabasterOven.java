package com.example.coppermod.container;

import com.example.coppermod.tileentity.TileEntityAlabasterOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

/**
 * Created by atvaccaro on 8/22/14.
 */
public class ContainerAlabasterOven extends Container
{


    private TileEntityAlabasterOven alabasterOven;

    public ContainerAlabasterOven(InventoryPlayer inventory, TileEntityAlabasterOven tileentity)
    {
        this.alabasterOven = tileentity;

        this.addSlotToContainer(new Slot(tileentity, 0, 56, 35));   //input
        this.addSlotToContainer(new Slot(tileentity, 1, 8, 62));    //fuel
        this.addSlotToContainer(new SlotFurnace(inventory.player, tileentity, 2, 116, 35));  //output

        //add player inventory slots
        for (int i = 0; i < 3; i++) {   //increment row
            for (int j = 0; j < 9; j++) {   //increment column
                this.addSlotToContainer(new Slot(inventory, j + i*9 + 9, 8 + j*18, 94 + i*18)); //slot #, x pos, y pos
            }
        }
    }



    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
}
