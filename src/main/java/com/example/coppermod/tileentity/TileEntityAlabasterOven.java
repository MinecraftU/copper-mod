package com.example.coppermod.tileentity;

import com.example.coppermod.container.ContainerAlabasterOven;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by atvaccaro on 8/22/14.
 */
public class TileEntityAlabasterOven extends TileEntity
{
    private String localizedName;

    public void setGuiDisplayName(String displayName)
    {
        localizedName = displayName;
    }

    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.localizedName : "container.alabaster_oven";
    }

    public boolean hasCustomInventoryName()
    {
        return this.localizedName != null && this.localizedName.length() > 0;
    }
}
