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
package gregification.opencomputers.drivers;

import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.integration.ManagedTileEntityEnvironment;

public abstract class EnvironmentMetaTileEntity<T> extends ManagedTileEntityEnvironment<T> implements NamedBlock {

    private final String preferredName;

    public EnvironmentMetaTileEntity(IGregTechTileEntity holder, T capability, String name) {
        super(capability, name);
        preferredName = holder.getMetaTileEntity().metaTileEntityId.getPath();
    }

    @Override
    public String preferredName() {
        return preferredName;
    }

    @Override
    public int priority() {
        return 0;
    }
}
