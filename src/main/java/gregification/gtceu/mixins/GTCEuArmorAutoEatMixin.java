package gregification.gtceu.mixins;

import gregtech.api.capability.IElectricItem;
import gregtech.common.items.armor.QuarkTechSuite;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.FoodStatsTFC;
import net.dries007.tfc.api.capability.food.IFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = QuarkTechSuite.class, remap = false)
public class GTCEuArmorAutoEatMixin {

    /*@Inject(method = "onArmorTick", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/util/NonNullList;get(I)Ljava/lang/Object;", ordinal = 0, shift = At.Shift.AFTER), locals = LocalCapture.PRINT)
    public void tryConsumeWater(World world, EntityPlayer player, ItemStack stack, CallbackInfo ci, IElectricItem item, NBTTagCompound data, byte toggleTimer, boolean ret, int air, int slotId) {
        System.out.println("Test");
        IFood foodCap = stack.getCapability(CapabilityFood.CAPABILITY, null);
        if(foodCap != null && player.getFoodStats() instanceof FoodStatsTFC) {
            float waterContent = foodCap.getData().getWater();
            if(waterContent != 0) {
                if(((FoodStatsTFC) player.getFoodStats()).attemptDrink(waterContent, true)) {
                    ((FoodStatsTFC) player.getFoodStats()).addThirst(waterContent);
                }
            }
        }
    } */

    // Fixing TFC food being consumed but not restoring saturation/hunger. Also don't consume rotten foods
    @Redirect(method = "onArmorTick", at = @At(value = "INVOKE", target = "Lgregtech/api/items/armor/ArmorUtils;canEat(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/util/ActionResult;"))
    private ActionResult<ItemStack> adjustCanEat(EntityPlayer player, ItemStack food) {

        if (!(food.getItem() instanceof ItemFood)) {
            return new ActionResult<>(EnumActionResult.FAIL, food);
        }

        // Check for TFC Rotten Foods
        if(food.hasCapability(CapabilityFood.CAPABILITY, null)) {
            if(food.getCapability(CapabilityFood.CAPABILITY, null).isRotten()) {
                return new ActionResult<>(EnumActionResult.FAIL, food);
            }
        }

        ItemFood foodItem = (ItemFood) food.getItem();
        if (player.getFoodStats().needFood()) {
            if(!player.isCreative()) {
                food.setCount(food.getCount() - 1);
            }

            // Find the saturation of the food
            float saturation = foodItem.getSaturationModifier(food);

            // The amount of empty food haunches of the player
            int hunger = 20 - player.getFoodStats().getFoodLevel();

            // Increase the saturation of the food if the food replenishes more than the amount of missing haunches
            saturation += (hunger - foodItem.getHealAmount(food)) < 0 ? foodItem.getHealAmount(food) - hunger : 1.0F;

            // Use this method to add stats for compat with TFC, who overrides addStats(int amount, float saturation) for their food and does nothing
            player.getFoodStats().addStats(new ItemFood(foodItem.getHealAmount(food), saturation, foodItem.isWolfsFavoriteMeat()), food);

            return new ActionResult<>(EnumActionResult.SUCCESS, food);
        } else {
            return new ActionResult<>(EnumActionResult.FAIL, food);
        }
    }
}
