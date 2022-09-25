package gregification.modules.tfc.mixins;

import gregification.modules.tfc.ITFCFluidRegistrationStatus;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(TerraFirmaCraft.class)
public class TerraFirmaCraftMixin implements ITFCFluidRegistrationStatus {

    @Unique
    private boolean isEarly = true;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        FluidsTFC.registerFluids();
        isEarly = false;
    }

    @Override
    public boolean isEarly() {
        return isEarly;
    }

}
