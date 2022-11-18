package gregification.modules.tinkers.effects;

import gregification.base.misc.BasePotion;

public class PotionUnhealing extends BasePotion {

    public PotionUnhealing() {
        // color derived from Materials.Salt
        super("gt_unhealing", true, 0xFAFAFA, 0);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    protected boolean canRender() {
        return true;
    }
}
