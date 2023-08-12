package gregification.modules.computercraft.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.metatileentity.MetaTileEntity;

class MetaTileEntityPeripheralSetEnabledFunction implements MetaTileEntityPeripheralFunction {

    private final IControllable controllable;

    public MetaTileEntityPeripheralSetEnabledFunction(MetaTileEntity metaTileEntity) {
        this.controllable = metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);
    }

    @Override
    public boolean isEnabled() {
        return this.controllable != null;
    }

    @Override
    public String getName() {
        return "setEnabled";
    }

    @Override
    public Object[] call(IComputerAccess iComputerAccess, ILuaContext iLuaContext, int i, Object[] objects) {
        boolean enabled = objects.length > 0 && objects[0] instanceof Boolean ? (Boolean) objects[0] : true;
        controllable.setWorkingEnabled(enabled);
        return new Object[]{};
    }
}
