package utd.atvaccaro.coppermod.block;

import utd.atvaccaro.coppermod.CopperMod;
import utd.atvaccaro.coppermod.tileentity.TileEntityAlabasterOven;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by atvaccaro on 8/22/14.
 */
public class BlockAlabasterOven extends BlockContainer
{
    public boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;

    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    private static boolean keepInventory;

    public BlockAlabasterOven(boolean isActive)
    {
        super(Material.iron);
        this.isActive = isActive;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(CopperMod.MODID + ":" + "alabaster_oven_side");
        this.iconFront = iconRegister.registerIcon(CopperMod.MODID + ":"
                + (this.isActive ? "alabaster_oven_front_on" : "alabaster_oven_front_off"));
        this.iconTop = iconRegister.registerIcon(CopperMod.MODID + ":" + "alabaster_oven_top");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
                                    float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            FMLNetworkHandler.openGui(player, CopperMod.instance, CopperMod.guiIDAlabasterOven, world, x, y, z);
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return side == 1 || side == 0 ? this.iconTop : (side != metadata ? this.iconFront : this.blockIcon);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityAlabasterOven();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityPlayer, ItemStack itemStack)
    {
        int l = MathHelper.floor_double((double) (entityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemStack.hasDisplayName())
        {
            ((TileEntityAlabasterOven)world.getTileEntity(x, y, z)).setGuiDisplayName(itemStack.getDisplayName());
        }
    } //end onBlockPlacedBy

    public static void updateAlabasterOvenBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord) {
        int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);

        keepInventory = true;

        if (active) {
            worldObj.setBlock(xCoord, yCoord, zCoord, CopperMod.alabasterOvenActive);
        } else {
            worldObj.setBlock(xCoord, yCoord, zCoord, CopperMod.alabasterOvenIdle);
        }

        keepInventory = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);

        if (tileentity != null) {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }

    }   //end updateAlabasterOvenBlockState
}
