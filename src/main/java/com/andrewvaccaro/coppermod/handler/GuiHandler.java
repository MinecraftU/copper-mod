package com.andrewvaccaro.coppermod.handler;

import com.andrewvaccaro.coppermod.CopperMod;
import com.andrewvaccaro.coppermod.gui.GuiMetalworkingBench;
import com.andrewvaccaro.coppermod.container.ContainerAlabasterOven;
import com.andrewvaccaro.coppermod.container.ContainerMetalworkingBench;
import com.andrewvaccaro.coppermod.gui.GuiAlabasterOven;
import com.andrewvaccaro.coppermod.init.CopperModBlocks;
import com.andrewvaccaro.coppermod.init.CopperModItems;
import com.andrewvaccaro.coppermod.tileentity.TileEntityAlabasterOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    //Provides containers when request by a server
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
        TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));


        //If the block has a tile entity
        if(entity != null) {
            switch(ID) {
                case CopperModBlocks.guiIDAlabasterOven:
                    if (entity instanceof TileEntityAlabasterOven) {
                        return new ContainerAlabasterOven(player.inventory, (TileEntityAlabasterOven) entity);
                    }
                    return null;

                /*case Nealecraft.guiIDIngotMasher:
                    if (entity instanceof TileEntityIngotMasher) {
                        return new ContainerIngotMasher(player.inventory, (TileEntityIngotMasher) entity);
                    }
                    return null;*/
            }
        }


        //No tile entity
        if(ID == CopperModBlocks.guiIDMetalworkingBench) {
            return world.getBlockState(new BlockPos(x, y, z)) == CopperModBlocks.metalworkingBench
                    ? new ContainerMetalworkingBench(player.inventory, world, x, y, z) : null;
        }

        return null;
    } //end getServerGuiElement

    //Provides GUIs when requested by a client
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
        TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));


        //If the block has a tile entity
        if(entity != null) {
            switch(ID)
            {
                case CopperModBlocks.guiIDAlabasterOven:
                    if (entity instanceof TileEntityAlabasterOven) {
                        return new GuiAlabasterOven(player.inventory, (TileEntityAlabasterOven) entity);
                    }
                    return null;

                /*case Nealecraft.guiIDIngotMasher:
                    if (entity instanceof TileEntityIngotMasher) {
                        return new GuiIngotMasher(player.inventory, (TileEntityIngotMasher) entity);
                    }
                    return null;*/
            }
        }


        //No tile entity
        if(ID == CopperModBlocks.guiIDMetalworkingBench) {
            return ID == CopperModBlocks.guiIDMetalworkingBench && world.getBlockState(new BlockPos(x, y, z)) == CopperModBlocks.metalworkingBench
                    ? new GuiMetalworkingBench(player.inventory, world, x, y, z) : null;
        }

        return null;
    } //end get ClientGuiElement
}

