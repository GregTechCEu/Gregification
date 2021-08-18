package gregification.opencomputers.drivers;

import gregtech.api.metatileentity.MetaTileEntityHolder;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.integration.ManagedTileEntityEnvironment;

@SuppressWarnings("unused")
public abstract class EnvironmentMetaTileEntity<T> extends ManagedTileEntityEnvironment<T> implements NamedBlock {

    private final String preferredName;

    public EnvironmentMetaTileEntity(MetaTileEntityHolder holder, T capability, String name) {
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
