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
import gregtech.common.covers.CoverConveyor;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

public class ValueCoverConveyor extends ValueCoverBehavior {

    protected ValueCoverConveyor(CoverConveyor coverBehavior, EnumFacing side, String name) {
        super(coverBehavior, side, name);
    }

    public ValueCoverConveyor(CoverConveyor coverBehavior, EnumFacing side) {
        this(coverBehavior, side, "gt_coverConveyor");
    }

    @Override
    protected CoverBehavior getCoverBehavior() {
        CoverBehavior cover = super.getCoverBehavior();
        return cover instanceof CoverConveyor ? cover : null;
    }

    @Callback(doc = "function():number --  Returns tier.")
    public Object[] getTier(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        return new Object[]{((CoverConveyor) cover).tier};
    }

    @Callback(doc = "function():number --  Returns transfer rate.")
    public Object[] getTransferRate(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        return new Object[]{((CoverConveyor) cover).getTransferRate()};
    }

    @Callback(doc = "function(number) --  Sets transfer rate.")
    public Object[] setTransferRate(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        int transferRate = InputValidator.getInteger(args, 0, 0, ((CoverConveyor) cover).maxItemTransferRate);
        ((CoverConveyor) cover).setTransferRate(transferRate);
        return new Object[]{};
    }

    @Callback(doc = "function(mode:number) --  Sets conveyor mode. (0:IMPORT, 1:EXPORT)")
    public Object[] setConveyorMode(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        int mode = InputValidator.getInteger(args, 0, 0, 1);
        ((CoverConveyor) cover).setConveyorMode(CoverConveyor.ConveyorMode.values()[mode]);
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets conveyor mode. (0:IMPORT, 1:EXPORT)")
    public Object[] getConveyorMode(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        return new Object[]{((CoverConveyor) cover).getConveyorMode().ordinal()};
    }
}
