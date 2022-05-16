/*
    Copyright 2020, decal06, dan
    Gregicality

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package gregification.opencomputers.values;

import gregtech.api.cover.CoverBehavior;
import gregtech.common.covers.CoverPump;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class ValueCoverPump extends ValueCoverBehavior {

    public ValueCoverPump(CoverPump coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "gt_coverPump");
    }

    public ValueCoverPump() {
    }

    @Callback(doc = "function():number --  Returns tier.")
    public Object[] getTier(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverPump))
            return new Object[]{null, "Found no cover, this is an invalid object."};
        return new Object[]{((CoverPump) coverBehavior).tier};
    }

    @Callback(doc = "function():number --  Returns transfer rate.")
    public Object[] getTransferRate(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverPump))
            return new Object[]{null, "Found no cover, this is an invalid object."};
        int transferRate = ReflectionHelper
                .getPrivateValue(CoverPump.class, (CoverPump) coverBehavior, "transferRate");
        return new Object[]{transferRate};
    }

    @Callback(doc = "function(number) --  Sets transfer rate.")
    public Object[] setTransferRate(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverPump))
            return new Object[]{null, "Found no cover, this is an invalid object."};
        int transferRate = args.checkInteger(0);
        int maxRate = ((CoverPump) coverBehavior).maxFluidTransferRate;
        if (transferRate < 0 || transferRate > maxRate)
            throw new IllegalArgumentException(String.format("Expect a number between 0 and %d.", maxRate));
        Method setTransferRate = ReflectionHelper.findMethod(CoverPump.class, "setTransferRate", null, int.class);
        try {
            setTransferRate.invoke(coverBehavior, transferRate);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object[]{};
    }

    @Callback(doc = "function(mode:number) --  Sets pump mode. (0:IMPORT, 1:EXPORT)")
    public Object[] setPumpMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverPump))
            return new Object[]{null, "Found no cover, this is an invalid object."};
        int mode = args.checkInteger(0);
        if (mode < 0 || mode > 1) throw new IllegalArgumentException("Expect a number between 0 and 1.");
        ((CoverPump) coverBehavior).setPumpMode(CoverPump.PumpMode.values()[mode]);
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets pump mode. (0:IMPORT, 1:EXPORT)")
    public Object[] getPumpMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverPump))
            return new Object[]{null, "Found no cover, this is an invalid object."};
        CoverPump.PumpMode mode = ReflectionHelper.getPrivateValue(CoverPump.class, (CoverPump) coverBehavior, "pumpMode");
        return new Object[]{mode.ordinal()};
    }
}
