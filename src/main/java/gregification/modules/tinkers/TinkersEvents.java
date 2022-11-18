package gregification.modules.tinkers;

import gregification.modules.tinkers.effects.PotionEffects;
import gregtech.api.unification.material.Material;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent.*;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;

import static gregtech.api.unification.ore.OrePrefix.*;

public class TinkersEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onMeltingRegisterEvent(MeltingRegisterEvent event) {
        MeltingRecipe recipe = event.getRecipe();
        Material m = TinkersUtil.getBlastMaterials().get(recipe.getResult().getFluid());
        if (m != null && (TinkersUtil.matches(recipe.input, ore, m) || TinkersUtil.matches(recipe.input, dust, m)
                || TinkersUtil.matches(recipe.input, dustTiny, m) || TinkersUtil.matches(recipe.input, dustSmall, m))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingHealEvent(LivingHealEvent event) {
        if (event.getEntityLiving().getActivePotionEffect(PotionEffects.POTION_UNHEALING) != null) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void registerPotionEffects(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(PotionEffects.POTION_UNHEALING);
    }

    // Some weird bullshit for tinkers to be satisfied with modifier additions

}
