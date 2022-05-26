package gregification.opencomputers.values;

import gregification.opencomputers.InputValidator;
import gregtech.api.cover.CoverBehavior;
import gregtech.common.covers.CoverEnderFluidLink;
import gregtech.common.covers.CoverPump;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

public class ValueCoverEnderFluidLink extends ValueCoverBehavior {

    public ValueCoverEnderFluidLink(CoverEnderFluidLink coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "gt_coverEnderFluidLink");
    }

    @Override
    protected CoverBehavior getCoverBehavior() {
        CoverBehavior cover = super.getCoverBehavior();
        return cover instanceof CoverEnderFluidLink ? cover : null;
    }

    @Callback(doc = "function(mode:string) --  Sets the color channel. Must be RGBA hexcode string (like 0xAF5614BB).")
    public Object[] setColorChannel(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        String colorString = InputValidator.getColorString(args, 0);
        ((CoverEnderFluidLink) cover).updateColor(colorString);
        return new Object[]{};
    }

    @Callback(doc = "function():string --  Gets the color channel.")
    public Object[] getColorChannel(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        return new Object[]{((CoverEnderFluidLink) cover).getColorStr()};
    }

    @Callback(doc = "function(mode:number) --  Sets pump mode. (0:IMPORT, 1:EXPORT)")
    public Object[] setPumpMode(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        int mode = InputValidator.getInteger(args, 0, 0, 1);
        ((CoverEnderFluidLink) cover).setPumpMode(CoverPump.PumpMode.values()[mode]);
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets pump mode. (0:IMPORT, 1:EXPORT)")
    public Object[] getPumpMode(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        return new Object[]{((CoverEnderFluidLink) cover).getPumpMode().ordinal()};
    }
}
