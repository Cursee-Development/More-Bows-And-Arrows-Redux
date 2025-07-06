package com.cursee.more_bows_and_arrows.core.util;

import com.cursee.more_bows_and_arrows.Constants;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ForgeRegistryHelper {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    @SuppressWarnings("unchecked")
    public static <T> DeferredRegister<T> deferredRegisterFor(Registry<T> objRegistry) {

        if (objRegistry.key().location() == ForgeRegistries.Keys.ITEMS.location()) return (DeferredRegister<T>) ITEMS;
        else if (objRegistry.key().location() == ForgeRegistries.Keys.BLOCKS.location()) return (DeferredRegister<T>) BLOCK;

        throw new IllegalArgumentException("No registry linked in Forge module to register type: " + objRegistry.key());
        // return null; // throws an error if registering to undefined/unlinked Forge registry
    }
}
