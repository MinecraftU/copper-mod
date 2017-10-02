package com.andrewvaccaro.coppermod.tileentity;

import com.andrewvaccaro.coppermod.CopperMod;
import com.andrewvaccaro.coppermod.block.BlockAlabasterOven;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by atvaccaro on 8/22/14.
 */
public class TileEntityAlabasterOven extends TileEntity implements ISidedInventory
{
    private String localizedName;

    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{2, 1};
    private static final int[] slots_side = new int[]{1};

    private ItemStack[] slots = new ItemStack[3];

    public int furnaceSpeed = 150;
    public int burnTime;    //number of ticks the furnace wil keep burning
    public int currentItemBurnTime; //number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
    public int cookTime;    //number of ticks the current item has been cooking for

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

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        //if TileEntity in this location does not match the TileEntity that this is in, return false
        //otherwise, check the distance between the player and the TileEntity
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this
                ? false
                : entityplayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return i == 2 ? false : (i == 1 ? isItemFuel(itemstack) : true);
    }

    public static boolean isItemFuel(ItemStack itemstack) {
        return getItemBurnTime(itemstack) > 0;
    }

    public static int getItemBurnTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        } else {
            Item item = itemstack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
                Block block = Block.getBlockFromItem(item);

                //fuel values
                if (item == CopperMod.copperIngot) return 800;
                if (item == Items.coal) return 1600;
                if (item == Items.stick) return 100;
                if (block == Blocks.log) return 800;
                if (block == Blocks.coal_block) return 14400;

                return GameRegistry.getFuelValue(itemstack);
            }
        }
        return 0;   //TODO: fix this; this is only a temporary workaround

    }   //end getItemBurnTime

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if (this.isBurning()) {
            this.burnTime--;
        }

        if (!this.worldObj.isRemote) {  //if on a server world
            if (this.burnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]);  //slot 1 (fuel slot)

                if (this.isBurning()) {
                    flag1 = true;

                    if (this.slots[1] != null) {    //slot 1 has something in it
                        this.slots[1].stackSize--;

                        if (this.slots[1].stackSize == 0) {  //slot 1 is now empty
                            this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);    //will return bucket if bucket of lava is burned
                        }
                    }
                }
            }


            if (this.isBurning() && this.canSmelt()) {
                this.cookTime++;

                if (this.cookTime == this.furnaceSpeed) {   //if thing is done cooking
                    this.cookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.cookTime = 0;
            }

            if (flag != this.isBurning()) { //what does this do?
                flag1 = true;
                BlockAlabasterOven.updateAlabasterOvenBlockState(this.burnTime > 0, this.worldObj,
                        this.xCoord, this.yCoord, this.zCoord);
            }

        }

        if (flag1) {
            this.markDirty();   //ensures the chunk containing the tile entity it saved to disk later
        }

    }

    //checks slot 0 to see if item is smeltable; slot 0 is the slot that holds the item to be smelted (obviously)
    public boolean canSmelt() {
       if (this.slots[0] == null) { //if empty
           return false;

       } else { //if not empty
           ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);    //get registered output (if any) for input

           if (itemstack == null) { //if input has no smelting output
               return false;
           }

           if (this.slots[2] == null) { //if output is currently empty
               return true;
           }

           if (!this.slots[2].isItemEqual(itemstack)) { //if current output item doesn't match smelting output of the input stack
               return false;
           }

           int result = this.slots[2].stackSize + itemstack.stackSize;

           return result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize();  //returns false if smelting would exceed output stack size limit
       }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);    //get registered output (if any) for input

            if (this.slots[2] == null) { //output is empty
                this.slots[2] = itemstack.copy();
            } else if (this.slots[2].isItemEqual(itemstack)) {   //if smelt item has same output as current output
                this.slots[2].stackSize += itemstack.stackSize;
            }

            this.slots[0].stackSize--;   //remove an item that has just been smelted

            if (this.slots[0].stackSize <= 0) {  //remove if stack size goes to 0
                this.slots[0] = null;
            }
        }
    }


    public int getSizeInventory() {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotNum) {
        return this.slots[slotNum];
    }

    @Override
    public ItemStack decrStackSize(int slotNum, int decreaseAmount) {
        if (this.slots[slotNum] != null) {  //if slot contains something
            ItemStack itemstack;

            if (this.slots[slotNum].stackSize <= decreaseAmount) {    //item stack goes to hand if all of it should be removed
                itemstack = this.slots[slotNum];
                this.slots[slotNum] = null;
                return itemstack;
            } else {
                itemstack = this.slots[slotNum].splitStack(decreaseAmount);

                //if only 1 in stack?
                if (this.slots[slotNum].stackSize == 0) {
                    this.slots[slotNum] = null;
                }
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotNum) {
        if (this.slots[slotNum] != null) {   //if slot is not empty
            ItemStack itemstack = this.slots[slotNum];
            this.slots[slotNum] = null;
            return itemstack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int slotNum, ItemStack itemstack) {
        this.slots[slotNum] = itemstack;
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) { //spread out stack if too large for slot
            itemstack.stackSize = this.getInventoryStackLimit();
        }

    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_side);
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return this.isItemValidForSlot(i, itemstack);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return j != 0 || i != 1 || itemstack.getItem() == Items.bucket; //limit what/where things can be extracted
    }
}
