package gregification.opencomputers.drivers.specific;

import gregification.opencomputers.drivers.EnvironmentMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.common.metatileentities.multi.electric.MetaTileEntityFusionReactor;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class DriverFusionReactor extends DriverSidedTileEntity {

    @Override
    public Class<?> getTileEntityClass() {
        return MetaTileEntityFusionReactor.class;
    }

    @Override
    public boolean worksWith(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return ((MetaTileEntityHolder) tileEntity).getMetaTileEntity() instanceof MetaTileEntityFusionReactor;
        }
        return false;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof MetaTileEntityHolder) {
            return new EnvironmentFusionReactor((MetaTileEntityHolder) tileEntity,
                    (MetaTileEntityFusionReactor) ((MetaTileEntityHolder) tileEntity).getMetaTileEntity()
            );
        }
        return null;
    }

    public final static class EnvironmentFusionReactor extends
            EnvironmentMetaTileEntity<MetaTileEntityFusionReactor> {

        public EnvironmentFusionReactor(MetaTileEntityHolder holder, MetaTileEntityFusionReactor tileEntity) {
            super(holder, tileEntity, "gtce_tileEntityFusionReactor");
        }

        @Callback(doc = "function():number --  Returns the heat of machine.")
        public Object[] getHeat(final Context context, final Arguments args) {
            return new Object[] {tileEntity.getHeat()};
        }
    }
}
