package com.cursee.more_bows_and_arrows.core.world.item.util;

import com.cursee.more_bows_and_arrows.Constants;
import com.cursee.more_bows_and_arrows.MoreBowsAndArrows;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public enum BowType implements Tier {

    NORMAL(1, 0.0f, 0.0f, 0, 0, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    NOCTURNAL(369, 2.0f, 4.0f, 3, 20, () -> Ingredient.of(Items.PHANTOM_MEMBRANE)),

    AMETHYST(400, 0.0f, 2.0f, 0, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    BLAZE(750, 0.0f, 3.0f, 0, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    BONE(400, 0.0f, 2.0f, 0, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    COAL(400, 0.0f, 2.0f, 0, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    COPPER(500, 0.0f, 2.0f, 0, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    DIAMOND(1000, 0.0f, 5.0f, 3, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    EMERALD(750, 0.0f, 3.0f, 2, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    GOLD(400, 0.0f, 1.5f, 2, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    IRON(500, 0.0f, 2.5f, 2, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    LAPIS(400, 0.0f, 2.0f, 1, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    MOSS(300, 0.0f, 0.0f, 0, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    NETHERITE(2000, 0.0f, 6.0f, 4, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    OBSIDIAN(750, 0.0f, 4.0f, 3, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    PAPER(300, 0.0f, 0.0f, 0, 20, () -> Ingredient.of(Blocks.OAK_PLANKS)),

    ACACIA(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    BAMBOO(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    BIRCH(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    CHERRY(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    CRIMSON_STEM(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    DARK_OAK(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    JUNGLE(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    MANGROVE(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    OAK(350, 0.2f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    SPRUCE(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    WARPED_STEM(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),

    STRIPPED_ACACIA(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_BAMBOO(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_BIRCH(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_CHERRY(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_CRIMSON_STEM(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_DARK_OAK(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_JUNGLE(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_MANGROVE(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_OAK(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_SPRUCE(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS)),
    STRIPPED_WARPED_STEM(350, 0.0f, 2.0f, 0, 15, () -> Ingredient.of(Blocks.OAK_PLANKS));

    private final int uses;
    private final float speed;
    private final float damageBonus;
    private final int level;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> ingredient;

    BowType(int uses, float speed, float damageBonus, int level, int enchantmentValue, Supplier<Ingredient> ingredientSupplier) {
        this.uses = uses;
        this.speed = speed;
        this.damageBonus = damageBonus;
        this.level = level;
        this.enchantmentValue = enchantmentValue;
        this.ingredient = new LazyLoadedValue<Ingredient>(ingredientSupplier);

        // ResourceKey.create(BuiltInRegistries.ITEM.key(), MoreBowsAndArrows.identifier(this.name().toLowerCase() + "_bow"));
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damageBonus;
    }

//    @Override
//    public int getLevel() {
//        return this.level;
//    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.ingredient.get();
    }

    public static void appendHoverText(BowType type, List<Component> tooltipComponents) {

        // wooden bows and their stripped variants should use the same lore text,
        // so we replace the "stripped_" prefix
        switch (type) {
            // case ACACIA, BAMBOO, BIRCH, CHERRY, CRIMSON_STEM, DARK_OAK, JUNGLE, MANGROVE, OAK, SPRUCE, WARPED_STEM,
            case STRIPPED_ACACIA, STRIPPED_BAMBOO, STRIPPED_BIRCH, STRIPPED_CHERRY, STRIPPED_CRIMSON_STEM, STRIPPED_DARK_OAK, STRIPPED_JUNGLE, STRIPPED_MANGROVE, STRIPPED_OAK, STRIPPED_SPRUCE, STRIPPED_WARPED_STEM -> {
                addLore(type.name().toLowerCase().replace("stripped_", ""), tooltipComponents);
                return;
            }
        }
        addLore(type.name().toLowerCase(), tooltipComponents);
    }

    private static void addLore(String loreID, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.translatable(Constants.MOD_ID + ".text." + loreID + "_bow_lore1"));
        tooltipComponents.add(Component.translatable(Constants.MOD_ID + ".text." + loreID + "_bow_lore2"));
        tooltipComponents.add(Component.translatable(Constants.MOD_ID + ".text." + loreID + "_bow_damage"));
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return BlockTags.INCORRECT_FOR_WOODEN_TOOL;
    }
}
