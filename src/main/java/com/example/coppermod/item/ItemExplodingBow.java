package com.example.coppermod.item;

import com.example.coppermod.CopperMod;
import com.example.coppermod.entity.EntityExplodingArrow;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemExplodingBow extends Item
{
    public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;
    private static final String __OBFID = "CL_00001777";

    public ItemExplodingBow()
    {
        this.setUnlocalizedName("exploding_bow");
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack bowItemStack, World world, EntityPlayer player, int itemInUseCount)
    {
        int j = this.getMaxItemUseDuration(bowItemStack) - itemInUseCount;

        ArrowLooseEvent event = new ArrowLooseEvent(player, bowItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, bowItemStack) > 0;

        if (flag || player.inventory.hasItem(CopperMod.explodingArrow))
        {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityExplodingArrow entityexplodingarrow = new EntityExplodingArrow(world, player, f * 2.0F);

            if (f == 1.0F)
            {
                entityexplodingarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, bowItemStack);

            if (k > 0)
            {
                entityexplodingarrow.setDamage(entityexplodingarrow.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, bowItemStack);

            if (l > 0)
            {
                entityexplodingarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, bowItemStack) > 0)
            {
                entityexplodingarrow.setFire(100);
            }

            bowItemStack.damageItem(1, player);
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityexplodingarrow.canBePickedUp = 2;
            }
            else
            {
                player.inventory.consumeInventoryItem(CopperMod.explodingArrow);
            }

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entityexplodingarrow);
            }
        }
    }

    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_)
    {
        return p_77654_1_;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
        ArrowNockEvent event = new ArrowNockEvent(p_77659_3_, p_77659_1_);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }

        if (p_77659_3_.capabilities.isCreativeMode || p_77659_3_.inventory.hasItem(CopperMod.explodingArrow))
        {
            p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        }

        return p_77659_1_;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.itemIcon = p_94581_1_.registerIcon(CopperMod.MODID + ":" + this.getUnlocalizedName().substring(5) + "_standby");
                                            //this.getIconString() + "_standby"); //the "standby" texture
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = p_94581_1_.registerIcon(CopperMod.MODID + ":" + this.getUnlocalizedName().substring(5) + "_" + bowPullIconNameArray[i]);
                                //this.getIconString() + "_" + bowPullIconNameArray[i]);
        }
    }


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        if (usingItem == null)
            return itemIcon;

        int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;

        //three stages of bow pull
        if (ticksInUse > 17)
            return iconArray[2];
        else if (ticksInUse > 13)
            return iconArray[1];
        else if (ticksInUse > 0)
            return iconArray[0];
        else
            return itemIcon;
    }

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */
    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int p_94599_1_)
    {
        return this.iconArray[p_94599_1_];
    }
}