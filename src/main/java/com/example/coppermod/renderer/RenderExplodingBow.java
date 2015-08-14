package com.example.coppermod.renderer;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderExplodingBow implements IItemRenderer {

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return false;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

    }
}