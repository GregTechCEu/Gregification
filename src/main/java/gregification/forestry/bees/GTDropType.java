/*
    Copyright 2022, GregoriusT, dan
    GregTech5

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
package gregification.forestry.bees;

public enum GTDropType {

    OIL("oil", true, 0x19191B, 0x303032),
    LAPIS("lapis", true, 0x1727B1, 0x008CE3),
    //OXYGEN("oxygen", true, 0x003366, 0x0066BB),
    //MUTAGEN("mutagen", true, 0xFFC100, 0x00FF11),
    //COOLANT("coolant", true, 0x144F5A, 0x2494A2),
    //HOT_COOLANT("hotcoolant", true, 0xC11F1F, 0xEBB9B9),
    //HYDRA("hydrablood", true, 0x872836, 0xB8132C),
    //SNOW_QUEEN("snowqueenblood", true, 0xD02001, 0x9C0018),
    //ENDERGOO("endergoo", true, 0xA005E7, 0x161616),
    ;

    public static final GTDropType[] VALUES = values();

    public boolean showInList;
    private final String name;
    private final int[] color;

    GTDropType(String name, boolean show, int primary, int secondary) {
        this.name = name;
        this.showInList = show;
        this.color = new int[]{primary, secondary};
    }

    public void setHidden() {
        this.showInList = false;
    }

    public String getName() {
        return name;
    }

    public int[] getColors() {
        return color;
    }

    public static GTDropType getDrop(int meta) {
        return meta < 0 || meta >= VALUES.length ? VALUES[0] : VALUES[meta];
    }
}
