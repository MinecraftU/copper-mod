package com.andrewvaccaro.coppermod.gui;

import com.andrewvaccaro.coppermod.CopperMod;
import com.andrewvaccaro.coppermod.container.ContainerMetalworkingBench;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiMetalworkingBench extends GuiContainer {

    private ResourceLocation texture = new ResourceLocation(CopperMod.MOD_ID + ":" + "textures/gui/metalworking_bench.png");

    public GuiMetalworkingBench(InventoryPlayer invPlayer, World world, int x, int y, int z) {
        super(new ContainerMetalworkingBench(invPlayer, world, x, y, z));

        this.xSize = 176;
        this.ySize = 188;

    }

    public void onGuiClosed() {
        super.onGuiClosed();
    }

    protected void drawGuiContainerForegroundLayer(int i, int j) {

        this.fontRendererObj.drawString(I18n.translateToLocal("Metalworking Bench"), 100, 5, 0xFFFFFF);
        //x, y, color

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

        GL11.glColor4f(1F, 1F, 1F, 1F);

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);

        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

    }
}
