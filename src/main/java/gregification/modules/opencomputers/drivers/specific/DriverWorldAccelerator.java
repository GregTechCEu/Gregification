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
package gregification.modules.opencomputers.drivers.specific;

import gregification.modules.opencomputers.drivers.EnvironmentMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.common.metatileentities.electric.MetaTileEntityWorldAccelerator;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DriverWorldAccelerator extends DriverSidedTileEntity {

    @Override
    public Class<?> getTileEntityClass() {
        return MetaTileEntityWorldAccelerator.class;
    }

    @Override
    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof IGregTechTileEntity) {
            return ((IGregTechTileEntity) tileEntity).getMetaTileEntity() instanceof MetaTileEntityWorldAccelerator;
        }
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof IGregTechTileEntity) {
            return new EnvironmentTileEntityWorldAccelerator((IGregTechTileEntity) tileEntity,
                    (MetaTileEntityWorldAccelerator) ((IGregTechTileEntity) tileEntity).getMetaTileEntity()
            );
        }
        return null;
    }

    public final static class EnvironmentTileEntityWorldAccelerator extends
            EnvironmentMetaTileEntity<MetaTileEntityWorldAccelerator> {

        public EnvironmentTileEntityWorldAccelerator(IGregTechTileEntity holder, MetaTileEntityWorldAccelerator tileEntity) {
            super(holder, tileEntity, "gt_worldAccelerator");
        }

        @Callback(doc = "function():boolean --  Returns the mode of machine.")
        public Object[] isTileMode(final Context context, final Arguments args) {
            return new Object[]{tileEntity.isTEMode()};
        }

        @Callback(doc = "function(isTile:boolean) --  Sets the mode of machine.")
        public Object[] setTileMode(final Context context, final Arguments args) {
            tileEntity.setTEMode(args.checkBoolean(0));
            return new Object[]{};
        }

        @Callback(doc = "function():boolean --  Returns is working enabled.")
        public Object[] isWorkingEnabled(final Context context, final Arguments args) {
            return new Object[]{tileEntity.isWorkingEnabled()};
        }

        @Callback(doc = "function(workingEnabled:boolean):boolean -- "
                + "Sets working enabled, return last working enabled.")
        public Object[] setWorkingEnabled(final Context context, final Arguments args) {
            boolean lsatState = tileEntity.isWorkingEnabled();
            tileEntity.setWorkingEnabled(args.checkBoolean(0));
            return new Object[]{lsatState};
        }

    }
}
