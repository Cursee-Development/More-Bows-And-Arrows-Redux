package com.cursee.more_bows_and_arrows.core.util;

import net.minecraftforge.registries.RegistryObject;

public class ForgeDeferredRegistryObject<T> implements DeferredRegistryObject<T> {

    private final RegistryObject<T> objHolder;

    public ForgeDeferredRegistryObject(RegistryObject<T> objHolder) {
        this.objHolder = objHolder;
    }

    public T get() {
        return this.objHolder.get();
    }
}
