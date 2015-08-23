package utd.atvaccaro.coppermod.handler;

/**
 * Created by atvaccaro on 8/21/14.
 */

import utd.atvaccaro.coppermod.CopperMod;
import utd.atvaccaro.coppermod.container.ContainerAlabasterOven;
import utd.atvaccaro.coppermod.container.ContainerMetalworkingBench;
import utd.atvaccaro.coppermod.gui.GuiAlabasterOven;
import utd.atvaccaro.coppermod.gui.GuiMetalworkingBench;
import utd.atvaccaro.coppermod.tileentity.TileEntityAlabasterOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    //Provides containers when request by a server
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);


        //If the block has a tile entity
        if(entity != null) {
            switch(ID) {
                case CopperMod.guiIDAlabasterOven:
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
        if(ID == CopperMod.guiIDMetalworkingBench) {
            return ID == CopperMod.guiIDMetalworkingBench && world.getBlock(x, y, z) == CopperMod.metalworkingBench
                    ? new ContainerMetalworkingBench(player.inventory, world, x, y, z) : null;
        }

        return null;
    } //end getServerGuiElement

    //Provides GUIs when requested by a client
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);


        //If the block has a tile entity
        if(entity != null) {
            switch(ID)
            {
                case CopperMod.guiIDAlabasterOven:
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
        if(ID == CopperMod.guiIDMetalworkingBench) {
            return ID == CopperMod.guiIDMetalworkingBench && world.getBlock(x, y, z) == CopperMod.metalworkingBench
                    ? new GuiMetalworkingBench(player.inventory, world, x, y, z) : null;
        }

        return null;
    } //end get ClientGuiElement
}

