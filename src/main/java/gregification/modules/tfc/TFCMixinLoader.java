package gregification.modules.tfc;

import gregification.core.IMixinRegistrator;
import gregification.core.MixinData;

@SuppressWarnings("unused")
public class TFCMixinLoader implements IMixinRegistrator {

    @Override
    public MixinData getMixinData() {
        return MixinData.builder("gregification.modules.tfc.mixins", "tfc")
                .setMixins("FluidsTFCMixin", "TerraFirmaCraftMixin")
                .setDefaultRequire(1)
                .build();
    }
}
