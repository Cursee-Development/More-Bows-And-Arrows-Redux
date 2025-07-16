package com.cursee.more_bows_and_arrows.platform;

import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrowsForge;
import com.cursee.more_bows_and_arrows.core.util.DeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.util.ForgeDeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.util.ForgeRegistryHelper;
import com.cursee.more_bows_and_arrows.platform.services.IPlatformHelper;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegisterEvent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public String getGameDirectory() {

        return FMLPaths.GAMEDIR.get().toString();
    }

    @Override
    public boolean isClientSide() {

        return FMLEnvironment.dist == Dist.CLIENT;
    }

    @Override
    public CreativeModeTab creativeModeTab(Supplier<ItemStack> icon, Component title, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
        return CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.SPAWN_EGGS).icon(icon).title(title).displayItems(displayItemsGenerator).build();
    }

    @Override
    public <T extends Entity> EntityType.Builder<T> createEntityType(BiFunction<EntityType<T>, Level, T> constructor, MobCategory category) {
        return EntityType.Builder.<T>of(constructor::apply, category);
    }

    @Override @SuppressWarnings("unchecked")
    public <T extends EnchantmentEntityEffect> MapCodec<T> createEnchantmentEffect(String name, MapCodec<T> codec) {

        AtomicReference<MapCodec<? extends EnchantmentEntityEffect>> reference = new AtomicReference<>();

        MoreBowsAndArrowsForge.EVENT_BUS.register((Consumer<RegisterEvent>) event -> {
            if (event.getRegistryKey() != Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE) return;
            event.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MoreBowsAndArrows.identifier(name), () -> codec);
            reference.set(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE.get(MoreBowsAndArrows.identifier(name)));
        });

        return (MapCodec<T>) reference.get();
    }

//    @Override
//    public MapCodec<? extends EnchantmentEntityEffect> getEnchantmentReference(ModEnchantment enchantment) {
//        return switch (enchantment) {
//            case ANTI_GRAVITY -> ModEnchantmentEntityEffects.ANTI_GRAVITY;
//            case BONUS_SHOT -> ModEnchantmentEntityEffects.BONUS_SHOT;
//            case DEFENSIVE_SHOT -> ModEnchantmentEntityEffects.DEFENSIVE_SHOT;
//            case FLUID_MOVEMENT -> ModEnchantmentEntityEffects.FLUID_MOVEMENT;
//            case MONSTER_HUNTER -> ModEnchantmentEntityEffects.MONSTER_HUNTER;
//            case QUICK_PULL -> ModEnchantmentEntityEffects.QUICK_PULL;
//            case TEMPO_THIEF -> ModEnchantmentEntityEffects.TEMPO_THIEF;
//        };
//    }


    @Override
    public <T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier) {
        DeferredRegister<T> registry = ForgeRegistryHelper.deferredRegisterFor(objRegistry);
        return new ForgeDeferredRegistryObject<>(registry.register(objName, objSupplier));
    }

    @Override
    public CreativeModeTab.Builder tabBuilder() {
        return CreativeModeTab.builder();
    }
}