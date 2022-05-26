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

import gregification.opencomputers.InputValidator;
import gregtech.api.cover.CoverBehavior;
import gregtech.common.covers.CoverItemFilter;
import gregtech.common.covers.ItemFilterMode;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

public class ValueCoverItemFilter extends ValueCoverBehavior {

    public ValueCoverItemFilter(CoverItemFilter coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "gt_coverItemFilter");
    }

    @Override
    protected CoverBehavior getCoverBehavior() {
        CoverBehavior cover = super.getCoverBehavior();
        return cover instanceof CoverItemFilter ? cover : null;
    }

    @Callback(doc = "function(mode:number) --  Sets filter mode. (0:FILTER_INSERT, 1:FILTER_EXTRACT, 2:FILTER_BOTH)")
    public Object[] setFilterMode(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        int mode = InputValidator.getInteger(args, 0, 0, 2);
        ((CoverItemFilter) cover).setFilterMode(ItemFilterMode.values()[mode]);
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets filter mode. (0:FILTER_INSERT, 1:FILTER_EXTRACT, 2:FILTER_BOTH)")
    public Object[] getFilterMode(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        return new Object[]{((CoverItemFilter) cover).getFilterMode().ordinal()};
    }
}
