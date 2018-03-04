package com.andrewvaccaro.coppermod.handler;

import com.andrewvaccaro.coppermod.gui.GuiMetalworkingBench;
import com.andrewvaccaro.coppermod.container.ContainerMetalworkingBench;
import com.andrewvaccaro.coppermod.init.CopperModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
        if(ID == CopperModBlocks.guiIDMetalworkingBench) {
            return world.getBlockState(new BlockPos(x, y, z)) == CopperModBlocks.metalworkingBench
                    ? new ContainerMetalworkingBench(player.inventory, world, x, y, z) : null;
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
        if (ID == CopperModBlocks.guiIDMetalworkingBench) {
            return world.getBlockState(new BlockPos(x, y, z)) == CopperModBlocks.metalworkingBench
                    ? new GuiMetalworkingBench(player.inventory, world, x, y, z) : null;
        }

        return null;
    }
}

