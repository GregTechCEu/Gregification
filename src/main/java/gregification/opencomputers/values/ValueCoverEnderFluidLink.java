package gregification.opencomputers.values;

import gregtech.api.cover.CoverBehavior;
import gregtech.common.covers.CoverEnderFluidLink;
import gregtech.common.covers.CoverPump;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class ValueCoverEnderFluidLink extends ValueCoverBehavior {

    public ValueCoverEnderFluidLink(CoverEnderFluidLink coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "gt_coverEnderFluidLink");
    }

    public ValueCoverEnderFluidLink() {
    }

    @Callback(doc = "function(mode:string) --  Sets the color channel. Must be RGBA hexcode string (like 0xAF5614BB).")
    public Object[] setColorChannel(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverEnderFluidLink)) return new Object[] {null, "Found no cover, this is an invalid object."};
        String colorString = args.checkString(0);
        if (colorString == null) {
            return new Object[] {null, "Must pass a color string."};
        }
        if (colorString.startsWith("0x")) {
            colorString = colorString.substring(2);
        }
        if (colorString.length() != 8) {
            return new Object[] {null, "String " + colorString + " is not valid, must be 8 characters long beyond \"0x\"."};
        }
        try {
            long colorLong = Long.parseLong(colorString, 16);
        } catch (NumberFormatException e) {
            return new Object[] {null, "String " + colorString + " is not a valid code, must be only numbers (0-9) and letters (A-F)."};
        }
        Method updateColor = ReflectionHelper.findMethod(CoverEnderFluidLink.class, "updateColor", null, String.class);
        try {
            updateColor.invoke(coverBehavior, colorString);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object[]{};
    }

    @Callback(doc = "function():string --  Gets the color channel.")
    public Object[] getColorChannel(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverEnderFluidLink)) return new Object[] {null, "Found no cover, this is an invalid object"};
        String colorStr = "";
        Method getColorStr = ReflectionHelper.findMethod(CoverEnderFluidLink.class, "getCoverStr", null);
        try {
            colorStr = (String) getColorStr.invoke(coverBehavior);
        } catch (IllegalAccessException | InvocationTargetException | ClassCastException e) {
            e.printStackTrace();
        }
        return new Object[]{colorStr};
    }

    @Callback(doc = "function(mode:number) --  Sets pump mode. (0:IMPORT, 1:EXPORT)")
    public Object[] setPumpMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverEnderFluidLink)) return new Object[] {null, "Found no cover, this is an invalid object."};
        int mode = args.checkInteger(0);
        if (mode < 0 || mode > 1) throw new IllegalArgumentException("Expect a number between 0 and 1.");

        ((CoverEnderFluidLink) coverBehavior).setPumpMode(CoverPump.PumpMode.values()[mode]);
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets pump mode. (0:IMPORT, 1:EXPORT)")
    public Object[] getPumpMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverEnderFluidLink)) return new Object[] {null, "Found no cover, this is an invalid object."};
        return new Object[]{((CoverEnderFluidLink) coverBehavior).getPumpMode().ordinal()};
    }
}
