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
import gregtech.common.covers.CoverFluidFilter;
import gregtech.common.covers.FluidFilterMode;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SuppressWarnings("unused")
public class ValueCoverFluidFilter extends ValueCoverBehavior {

    public ValueCoverFluidFilter(CoverFluidFilter coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "CoverFluidFilter");
    }

    public ValueCoverFluidFilter() {
    }

    @Callback(doc = "function(mode:number) --  Sets filter mode. (0:FILTER_FILL, 1:FILTER_DRAIN, 2:FILTER_BOTH)")
    public Object[] setFilterMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverFluidFilter)) return new Object[] {null, "Found no cover, this is an invalid object."};
        int mode = args.checkInteger(0);
        if (mode < 0 || mode > 2) throw new IllegalArgumentException("Expect a number between 0 and 2.");
        Method setFilterMode = ReflectionHelper.findMethod(CoverFluidFilter.class, "setFilterMode", null, FluidFilterMode.class);
        try {
            setFilterMode.invoke(coverBehavior, FluidFilterMode.values()[mode]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets filter mode. (0:FILTER_FILL, 1:FILTER_DRAIN, 2:FILTER_BOTH)")
    public Object[] getFilterMode(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (!(coverBehavior instanceof CoverFluidFilter)) return new Object[] {null, "Found no cover, this is an invalid object."};
        return new Object[]{((CoverFluidFilter) coverBehavior).getFilterMode().ordinal()};
    }
}
