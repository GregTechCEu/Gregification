package gregification.tfc.food;

import gregtech.api.util.RandomPotionEffect;
import gregtechfoodoption.item.GTFOFoodStats;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;

import static gregtechfoodoption.item.GTFOMetaItem.*;

public class GTFOTFCCompatibilityAdder {
    public static void init() {
        POPCORN_BAG.addComponents(TFCFoodComponent.createFromFoodStats(POPCORN_BAG)
                .setFoodData(0, 4, 0, 2, 0, 0));
        MINERAL_WATER.addComponents(TFCFoodComponent.createFromFoodStats(MINERAL_WATER)
                .setFoodData(20, 0, 0, 1, 0, 1));
        APPLE_HARD_CANDY.addComponents(TFCFoodComponent.createFromFoodStats(APPLE_HARD_CANDY)
                .setFoodData(0, 0, 5, 0, 0, 0));
        SPARKLING_WATER.addComponents(TFCFoodComponent.createFromFoodStats(SPARKLING_WATER)
                .setFoodData(10, 0, 0, 0, 0, 0));
        LEMON.addComponents(TFCFoodComponent.createFromFoodStats(LEMON)
                .setFoodData(2, 0, 5, 0, 0, 0));
        LIME.addComponents(TFCFoodComponent.createFromFoodStats(LIME)
                .setFoodData(2, 0, 5, 0, 0, 0));
        ETIRPS.addComponents(TFCFoodComponent.createFromFoodStats(ETIRPS)
                .setFoodData(15, 0, 3, 0, 0, 0));
        BACON.addComponents(TFCFoodComponent.createFromFoodStats(BACON)
                .setFoodData(0, 0, 0, 0, 2, 0));
        FRENCH_FRIES.addComponents(TFCFoodComponent.createFromFoodStats(FRENCH_FRIES));
        SYALS.addComponents(TFCFoodComponent.createFromFoodStats(SYALS));
        BAG_OF_CHIPS.addComponents(TFCFoodComponent.createFromFoodStats(BAG_OF_CHIPS));
        KETTLE_FRIED_CHIPS.addComponents(TFCFoodComponent.createFromFoodStats(KETTLE_FRIED_CHIPS));
        REDUCED_FAT_CHIPS.addComponents(TFCFoodComponent.createFromFoodStats(REDUCED_FAT_CHIPS)
                .setFoodData(0, 0, 0, 5, 0, 0));
        POTATO_ON_A_STICK.addComponents(TFCFoodComponent.createFromFoodStats(POTATO_ON_A_STICK)
                .setFoodData(0, 0, 0, 1.5f, 0, 0));
        BAGUETTE.addComponents(TFCFoodComponent.createFromFoodStats(BAGUETTE)
                .setFoodData(0, 1.8f, 0, 0, 0, 0));
        TUNGSTENSTEEL_APPLE.addComponents(TFCFoodComponent.createFromFoodStats(TUNGSTENSTEEL_APPLE));
        CAKE_BOTTOM.addComponents(TFCFoodComponent.createFromFoodStats(CAKE_BOTTOM));
        BAKED_CAKE_BOTTOM.addComponents(TFCFoodComponent.createFromFoodStats(BAKED_CAKE_BOTTOM));
        PIZZA_CHEESE.addComponents(TFCFoodComponent.createFromFoodStats(PIZZA_CHEESE)
                .setFoodData(0, 3, 0, 3, 0, 6));
        PIZZA_MINCE_MEAT.addComponents(TFCFoodComponent.createFromFoodStats(PIZZA_MINCE_MEAT)
                .setFoodData(0, 3, 0, 3, 5, 5));
        PIZZA_VEGGIE.addComponents(TFCFoodComponent.createFromFoodStats(PIZZA_VEGGIE)
                .setFoodData(0, 3, 0, 8, 0, 5));
        SANDWICH_BACON.addComponents(TFCFoodComponent.createFromFoodStats(SANDWICH_BACON)
                .setFoodData(0, 2, 0, 0, 5, 0));
        SANDWICH_CHEESE.addComponents(TFCFoodComponent.createFromFoodStats(SANDWICH_CHEESE)
                .setFoodData(0, 2, 0, 0, 0, 5));
        SANDWICH_STEAK.addComponents(TFCFoodComponent.createFromFoodStats(SANDWICH_STEAK)
                .setFoodData(0, 2, 0, 0, 6, 0));
        SANDWICH_VEGGIE.addComponents(TFCFoodComponent.createFromFoodStats(SANDWICH_VEGGIE)
                .setFoodData(0, 2, 0, 5, 0, 0));
        SANDWICH_LARGE_BACON.addComponents(TFCFoodComponent.createFromFoodStats(SANDWICH_LARGE_BACON)
                .setFoodData(0, 3, 0, 0, 8, 0));
        SANDWICH_LARGE_CHEESE.addComponents(TFCFoodComponent.createFromFoodStats(SANDWICH_LARGE_CHEESE)
                .setFoodData(0, 3, 0, 0, 0, 8));
        SANDWICH_LARGE_STEAK.addComponents(TFCFoodComponent.createFromFoodStats(SANDWICH_LARGE_STEAK)
                .setFoodData(0, 3, 0, 0, 10, 0));
        SANDWICH_LARGE_VEGGIE.addComponents(TFCFoodComponent.createFromFoodStats(SANDWICH_LARGE_VEGGIE)
                .setFoodData(0, 3, 0, 8, 0, 0));
        BUN.addComponents(TFCFoodComponent.createFromFoodStats(BUN)
                .setFoodData(0, 1, 0, 0, 0, 0));
        BURGER_MEAT.addComponents(TFCFoodComponent.createFromFoodStats(BURGER_MEAT)
                .setFoodData(0, 1.5f, 0, 0, 4, 0));
        BURGER_CHEESE.addComponents(TFCFoodComponent.createFromFoodStats(BURGER_CHEESE)
                .setFoodData(0, 1.5f, 0, 0, 0, 4));
        BURGER_VEGGIE.addComponents(TFCFoodComponent.createFromFoodStats(BURGER_VEGGIE)
                .setFoodData(0, 1.5f, 0, 4, 0, 0));
        CHEDDAR_SLICE.addComponents(TFCFoodComponent.createFromFoodStats(CHEDDAR_SLICE)
                .setFoodData(0, 0, 0, 0, 0, 2));
        MOZZARELLA_BALL.addComponents(TFCFoodComponent.createFromFoodStats(MOZZARELLA_BALL)
                .setFoodData(0, 0, 0, 0, 0, 2));
        GORGONZOLA_TRIANGULAR_SLICE.addComponents(TFCFoodComponent.createFromFoodStats(GORGONZOLA_TRIANGULAR_SLICE)
                .setFoodData(0, 0, 0, 0, 0, 2));
        ROTTEN_FISH.addComponents(TFCFoodComponent.createFromFoodStats(ROTTEN_FISH)
                .setFoodData(2, 0, 0, 0, 1, 0));
        ROTTEN_MEAT.addComponents(TFCFoodComponent.createFromFoodStats(ROTTEN_MEAT)
                .setFoodData(1, 0, 0, 0, 1, 0));
        CHUM.addComponents(TFCFoodComponent.createFromFoodStats(CHUM)
                .setFoodData(1, 1, 1, 1, 1, 1));
        CHUM_ON_A_STICK.addComponents(TFCFoodComponent.createFromFoodStats(CHUM_ON_A_STICK)
                .setFoodData(1, 1, 1, 1, 1, 1));
        BURGER_CHUM.addComponents(TFCFoodComponent.createFromFoodStats(BURGER_CHUM)
                .setFoodData(1, 2.5f, 1, 1, 1, 1));
        BANANA.addComponents(TFCFoodComponent.createFromFoodStats(BANANA)
                .setFoodData(0, 0, 3, 0, 0, 0));
        ORANGE.addComponents(TFCFoodComponent.createFromFoodStats(ORANGE)
                .setFoodData(0, 0, 3, 0, 0, 0));
        GRAPES.addComponents(TFCFoodComponent.createFromFoodStats(GRAPES)
                .setFoodData(0, 0, 3, 0, 0, 0));
        MANGO.addComponents(TFCFoodComponent.createFromFoodStats(MANGO)
                .setFoodData(0, 0, 3, 0, 0, 0));
        APRICOT.addComponents(TFCFoodComponent.createFromFoodStats(APRICOT)
                .setFoodData(0, 0, 3, 0, 0, 0));
        PEELED_BANANA.addComponents(TFCFoodComponent.createFromFoodStats(PEELED_BANANA)
                .setFoodData(0, 0, 3, 0, 0, 0));
        PEELED_BANANA.addComponents(TFCFoodComponent.createFromFoodStats(PEELED_BANANA)
                .setFoodData(0, 0, 3, 0, 0, 0));
        VODKA.addComponents(TFCFoodComponent.createFromFoodStats(VODKA)
                .setFoodData(4, 2, 0, 2, 0, 0));
        LENINADE.addComponents(TFCFoodComponent.createFromFoodStats(LENINADE)
                .setFoodData(5, 3, 1, 3, 0, 0));
        HOT_MUSHROOM_STEW.addComponents(TFCFoodComponent.createFromFoodStats(HOT_MUSHROOM_STEW)
                .setFoodData(2, 2, 0, 5, 0, 0));
        HOT_BEETROOT_SOUP.addComponents(TFCFoodComponent.createFromFoodStats(HOT_MUSHROOM_STEW)
                .setFoodData(2, 2, 0, 5, 0, 0));
        HOT_RABBIT_STEW.addComponents(TFCFoodComponent.createFromFoodStats(HOT_MUSHROOM_STEW)
                .setFoodData(2, 2, 0, 2, 3, 0));
        KEBAB_KUBIDEH_COOKED.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_KUBIDEH_COOKED)
                .setFoodData(0, 0, 0, 4, 6, 0));
        KEBAB_BARG_COOKED.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_BARG_COOKED)
                .setFoodData(0, 0, 2, 2, 6, 0));
        KEBAB_SOLTANI.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_SOLTANI)
                .setFoodData(0, 0, 6, 10, 25, 0));
        KEBAB_ONION_COOKED.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_ONION_COOKED)
                .setFoodData(0, 0, 0, 8, 0, 0));
        KEBAB_TOMATO_COOKED.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_TOMATO_COOKED)
                .setFoodData(0, 0, 0, 8, 0, 0));
        KEBAB_CARROT_COOKED.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_CARROT_COOKED)
                .setFoodData(2, 0, 0, 8, 0, 0));
        KEBAB_CHUM_COOKED.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_CHUM_COOKED)
                .setFoodData(4, 5, 5, 8, 5, 5));
        KEBAB_CHUM_BUCKET.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_CHUM_BUCKET)
                .setFoodData(10, 10, 5, 20, 20, 8));
        KEBAB_FAT_COOKED.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_FAT_COOKED));
        KEBAB_MEAT_COOKED.addComponents(TFCFoodComponent.createFromFoodStats(KEBAB_MEAT_COOKED)
                .setFoodData(0, 0, 0, 0, 8, 0));
        APPLE_JUICE.addComponents(TFCFoodComponent.createFromFoodStats(APPLE_JUICE)
                .setFoodData(4, 0, 2, 0, 0, 0));
        ORANGE_JUICE.addComponents(TFCFoodComponent.createFromFoodStats(ORANGE_JUICE)
                .setFoodData(4, 0, 2, 0, 0, 0));
        TOMATO_SLICE.addComponents(TFCFoodComponent.createFromFoodStats(TOMATO_SLICE)
                .setFoodData(0, 0, 0, 1, 0, 0));
        ONION_SLICE.addComponents(TFCFoodComponent.createFromFoodStats(ONION_SLICE)
                .setFoodData(0, 0, 0, 1, 0, 0));
        CUCUMBER_SLICE.addComponents(TFCFoodComponent.createFromFoodStats(CUCUMBER_SLICE)
                .setFoodData(0, 0, 0, 1, 0, 0));
        CARROT_SLICE.addComponents(TFCFoodComponent.createFromFoodStats(CARROT_SLICE)
                .setFoodData(0, 0, 0, 1, 0, 0));
        APPLE_SLICE.addComponents(TFCFoodComponent.createFromFoodStats(APPLE_SLICE)
                .setFoodData(0, 0, 1, 0, 0, 0));
    }
}
