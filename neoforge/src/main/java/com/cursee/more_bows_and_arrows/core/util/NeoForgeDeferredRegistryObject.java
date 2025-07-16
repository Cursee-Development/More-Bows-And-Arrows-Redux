package com.cursee.more_bows_and_arrows.core.util;

import net.neoforged.neoforge.registries.DeferredHolder;

public class NeoForgeDeferredRegistryObject<T, U extends T> implements DeferredRegistryObject<U> {

    private final DeferredHolder<T, U> objHolder;

    public NeoForgeDeferredRegistryObject(DeferredHolder<T, U> objHolder) {
        this.objHolder = objHolder;
    }

    public U get() {
        return this.objHolder.get();
    }
}
