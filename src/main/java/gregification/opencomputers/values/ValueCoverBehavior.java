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

import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.ICoverable;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.prefab.AbstractValue;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Objects;

@SuppressWarnings("unused")
public class ValueCoverBehavior extends AbstractValue {

    private BlockPos pos;
    private EnumFacing side;
    private int dim;
    private String coverName;

    protected ValueCoverBehavior(CoverBehavior coverBehavior, EnumFacing side, String coverName) {
        pos = coverBehavior.coverHolder.getPos();
        dim = coverBehavior.coverHolder.getWorld().provider.getDimension();
        this.side = side;
        this.coverName = coverName;
    }

    public ValueCoverBehavior(CoverBehavior coverBehavior, EnumFacing side) {
        this(coverBehavior, side, "gt_coverBehavior");
    }

    public ValueCoverBehavior() {
    }

    protected CoverBehavior getCoverBehavior() {
        World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(dim);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof IGregTechTileEntity) {
            ICoverable coverable = te.getCapability(GregtechTileCapabilities.CAPABILITY_COVERABLE, null);
            if (coverable != null) {
                return coverable.getCoverAtSide(side);
            }
        }
        return null;
    }

    @Callback(doc = "function():number --  Returns the side of the cover.")
    public Object[] getSide(final Context context, final Arguments args) {
        return new Object[]{side.ordinal()};
    }

    @Callback(doc = "function():string --  Returns the type name of the cover.")
    public Object[] getTypeName(final Context context, final Arguments args) {
        return new Object[]{coverName};
    }

    @Callback(doc = "function(signal:number) --  Sets redstone signal output.")
    public Object[] setRedstoneSignalOutput(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (coverBehavior == null) return new Object[]{null, "Found no cover, this is an invalid object."};
        int signal = args.checkInteger(0);
        if (signal > 15 || signal < 0) throw new IllegalArgumentException("Expect a number between 0 and 15.");
        coverBehavior.setRedstoneSignalOutput(signal);
        return new Object[]{};
    }

    @Callback(doc = "function():number --  Gets redstone signal output.")
    public final Object[] getRedstoneSignalOutput(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (coverBehavior == null) return new Object[]{null, "Found no cover, this is an invalid object."};
        return new Object[]{coverBehavior.getRedstoneSignalOutput()};
    }

    @Callback(doc = "function():number --  Gets redstone signal input.")
    public final Object[] getRedstoneSignalInput(final Context context, final Arguments args) {
        CoverBehavior coverBehavior = getCoverBehavior();
        if (coverBehavior == null) return new Object[]{null, "Found no cover, this is an invalid object."};
        return new Object[]{coverBehavior.getRedstoneSignalInput()};
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, dim);
    }

    @Override
    public Object[] call(Context context, Arguments arguments) {
        return new Object[0];
    }

    @Override
    public void load(NBTTagCompound nbt) {
        dim = nbt.getInteger("dim");
        pos = NBTUtil.getPosFromTag(nbt.getCompoundTag("pos"));
        side = EnumFacing.values()[nbt.getInteger("side")];
        coverName = nbt.getString("name");
    }

    @Override
    public void save(NBTTagCompound nbt) {
        nbt.setTag("pos", NBTUtil.createPosTag(pos));
        nbt.setInteger("dim", dim);
        nbt.setInteger("side", side.ordinal());
        nbt.setString("name", coverName);
    }
}
