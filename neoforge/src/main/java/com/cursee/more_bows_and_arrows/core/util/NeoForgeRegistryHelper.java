package com.cursee.more_bows_and_arrows.core.util;

import com.cursee.more_bows_and_arrows.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NeoForgeRegistryHelper {

    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(BuiltInRegistries.BLOCK, Constants.MOD_ID);
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(BuiltInRegistries.ITEM, Constants.MOD_ID);

    @SuppressWarnings("unchecked")
    public static <T> DeferredRegister<T> deferredRegisterFor(Registry<T> objRegistry) {

        if (objRegistry.key().location() == BuiltInRegistries.ITEM.key().location()) return (DeferredRegister<T>) ITEM;
        else if (objRegistry.key().location() == BuiltInRegistries.BLOCK.key().location()) return (DeferredRegister<T>) BLOCK;

        throw new IllegalArgumentException("No registry linked in NeoForge module to register type: " + objRegistry.key());
        // return null; // throws an error if registering to undefined/unlinked NeoForge registry
    }
}
