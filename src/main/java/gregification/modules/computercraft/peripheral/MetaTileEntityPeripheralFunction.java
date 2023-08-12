package gregification.modules.computercraft.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;

interface MetaTileEntityPeripheralFunction {
    boolean isEnabled();

    String getName();

    Object[] call(IComputerAccess iComputerAccess, ILuaContext iLuaContext, int i, Object[] objects) throws LuaException, InterruptedException;
}
