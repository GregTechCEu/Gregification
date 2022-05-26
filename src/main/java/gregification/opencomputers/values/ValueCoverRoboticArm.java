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
import gregtech.common.covers.CoverRoboticArm;
import gregtech.common.covers.TransferMode;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

public class ValueCoverRoboticArm extends ValueCoverConveyor {

    public ValueCoverRoboticArm(CoverRoboticArm coverBehavior, EnumFacing side) {
        super(coverBehavior, side, "gt_coverRoboticArm");
    }

    @Override
    protected CoverBehavior getCoverBehavior() {
        CoverBehavior cover = super.getCoverBehavior();
        return cover instanceof CoverRoboticArm ? cover : null;
    }

    @Callback(doc = "function(mode:number) --  Sets transfer mode. (0:TRANSFER_ANY, 1:TRANSFER_EXACT, 2:KEEP_EXACT)")
    public Object[] setTransferMode(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        int mode = InputValidator.getInteger(args, 0, 0, 2);
        ((CoverRoboticArm) cover).setTransferMode(TransferMode.values()[mode]);
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets transfer mode. (0:TRANSFER_ANY, 1:TRANSFER_EXACT, 2:KEEP_EXACT)")
    public Object[] getTransferMode(final Context context, final Arguments args) {
        CoverBehavior cover = getCoverBehavior();
        if (cover == null) {
            return NULL_COVER;
        }

        return new Object[]{((CoverRoboticArm) cover).getTransferMode().ordinal()};
    }
}
