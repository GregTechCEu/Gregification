package gregification;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;

@MixinLoader
public class GregificationMixinLoader {

    public GregificationMixinLoader() {
        // Insert Mixins.addConfiguration(...) here
        Mixins.addConfiguration("mixins.tfc.json");
    }

}
