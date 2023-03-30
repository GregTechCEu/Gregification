package gregification.modules.computercraft.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;

import java.util.HashMap;
import java.util.Map;

class MetaTileEntityPeripheralStatusFunction implements MetaTileEntityPeripheralFunction {

    private final TieredMetaTileEntity tieredMetaTileEntity;

    private final IControllable controllable;
    private final AbstractRecipeLogic recipeLogic;

    public MetaTileEntityPeripheralStatusFunction(MetaTileEntity metaTileEntity) {
        this.tieredMetaTileEntity = metaTileEntity instanceof TieredMetaTileEntity ? (TieredMetaTileEntity) metaTileEntity : null;
        this.controllable = metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);
        this.recipeLogic = metaTileEntity.getCapability(GregtechTileCapabilities.CAPABILITY_RECIPE_LOGIC, null);
    }

    @Override
    public boolean isEnabled() {
        return this.tieredMetaTileEntity != null || this.controllable != null || this.recipeLogic != null;
    }

    @Override
    public String getName() {
        return "getStatus";
    }

    @Override
    public Object[] call(IComputerAccess iComputerAccess, ILuaContext iLuaContext, int i, Object[] objects) {
        Map<String, Object> data = new HashMap<>();
        if (controllable != null) {
            data.put("enabled", controllable.isWorkingEnabled());
        }
        if (tieredMetaTileEntity != null) {
            data.put("tier", tieredMetaTileEntity.getTier());
        }
        if (recipeLogic != null) {
            data.put("active", recipeLogic.isActive());
            data.put("progress", recipeLogic.getProgress());
            data.put("maxProgress", recipeLogic.getMaxProgress());
        }
        return new Object[]{data};
    }
}
