package utd.atvaccaro.coppermod.gui;

import utd.atvaccaro.coppermod.CopperMod;
import utd.atvaccaro.coppermod.container.ContainerAlabasterOven;
import utd.atvaccaro.coppermod.tileentity.TileEntityAlabasterOven;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by atvaccaro on 8/22/14.
 */
public class GuiAlabasterOven extends GuiContainer
{
    public static final ResourceLocation background = new ResourceLocation(CopperMod.MODID, "/textures/gui/alabaster_oven.png");

    public TileEntityAlabasterOven alabasterOven;


    public GuiAlabasterOven(InventoryPlayer inventory, TileEntityAlabasterOven entity)
    {
        super(new ContainerAlabasterOven(inventory, entity));

        this.alabasterOven = entity;

        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String name = this.alabasterOven.hasCustomInventoryName() ? this.alabasterOven.getInventoryName()
                : I18n.format(this.alabasterOven.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[8]), 128, this.ySize - 96 + 2, 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

    }
}
