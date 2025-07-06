package com.cursee.more_bows_and_arrows.platform;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrowsNeoForge;
import com.cursee.more_bows_and_arrows.core.registry.ModEnchantmentEntityEffects;
import com.cursee.more_bows_and_arrows.core.registry.ModEnchantmentEntityEffectsNeoForge;
import com.cursee.more_bows_and_arrows.core.util.DeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.util.NeoForgeDeferredRegistryObject;
import com.cursee.more_bows_and_arrows.core.util.NeoForgeRegistryHelper;
import com.cursee.more_bows_and_arrows.core.world.item.enchantment.ModEnchantment;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
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

    @Override
    public <T extends EnchantmentEntityEffect> MapCodec<T> createEnchantmentEffect(String name, MapCodec<T> codec) {
        return null;
    }

//    @Override
//    public MapCodec<? extends EnchantmentEntityEffect> getEnchantmentReference(ModEnchantment enchantment) {
//        return switch (enchantment) {
//            case ANTI_GRAVITY -> ModEnchantmentEntityEffectsNeoForge.ANTI_GRAVITY.get();
//            case BONUS_SHOT -> ModEnchantmentEntityEffectsNeoForge.BONUS_SHOT.get();
//            case DEFENSIVE_SHOT -> ModEnchantmentEntityEffectsNeoForge.DEFENSIVE_SHOT.get();
//            case FLUID_MOVEMENT -> ModEnchantmentEntityEffectsNeoForge.FLUID_MOVEMENT.get();
//            case MONSTER_HUNTER -> ModEnchantmentEntityEffectsNeoForge.MONSTER_HUNTER.get();
//            case QUICK_PULL -> ModEnchantmentEntityEffectsNeoForge.QUICK_PULL.get();
//            case TEMPO_THIEF -> ModEnchantmentEntityEffectsNeoForge.TEMPO_THIEF.get();
//        };
//    }

    @Override
    public <T, U extends T> DeferredRegistryObject<U> register(Registry<T> objRegistry, String objName, Supplier<U> objSupplier) {
        DeferredRegister<T> registry = NeoForgeRegistryHelper.deferredRegisterFor(objRegistry);
        return new NeoForgeDeferredRegistryObject<>(registry.register(objName, objSupplier));
    }

    //    public static boolean assigned = false;
//    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
//            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Constants.MOD_ID);
//
//    @Override
//    public <T extends EnchantmentEntityEffect> MapCodec<T> createEnchantmentEffect(String name, MapCodec<T> codec) {
//        if (!assigned) {
//            ENTITY_ENCHANTMENT_EFFECTS.register(MoreBowsAndArrowsNeoForge.EVENT_BUS);
//            assigned = true;
//        }
//
//        return ENTITY_ENCHANTMENT_EFFECTS.register(name, () -> codec).get();
//    }

    @Override
    public CreativeModeTab.Builder tabBuilder() {
        return CreativeModeTab.builder();
    }
}