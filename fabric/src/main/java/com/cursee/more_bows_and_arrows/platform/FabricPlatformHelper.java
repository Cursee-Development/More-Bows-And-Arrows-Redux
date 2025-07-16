package com.cursee.more_bows_and_arrows.platform;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.core.util.DeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.util.FabricDeferredRegistryObject;
import com.cursee.more_bows_and_arrows.platform.services.IPlatformHelper;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.Level;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public String getGameDirectory() {

        return FabricLoader.getInstance().getGameDir().toString();
    }

    @Override
    public boolean isClientSide() {

        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    @Override
    public CreativeModeTab creativeModeTab(Supplier<ItemStack> icon, Component title, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
        return FabricItemGroup.builder().icon(icon).title(title).displayItems(displayItemsGenerator).build();
    }

    @Override
    public <T extends Entity> EntityType.Builder<T> createEntityType(BiFunction<EntityType<T>, Level, T> constructor, MobCategory category) {
        return EntityType.Builder.of(constructor::apply, category);
    }

    @Override
    public <T extends EnchantmentEntityEffect> MapCodec<T> createEnchantmentEffect(String name, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MoreBowsAndArrows.identifier(name), codec);
    }

    public <T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier) {
        return new FabricDeferredRegistryObject<>(Registry.register(objRegistry, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, objName), objSupplier.get()));
    }

    @Override
    public CreativeModeTab.Builder tabBuilder() {
        return FabricItemGroup.builder();
    }
}
