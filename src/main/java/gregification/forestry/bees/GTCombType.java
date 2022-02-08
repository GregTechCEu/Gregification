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

import gregification.common.GFValues;
import gregtech.api.GTValues;

public enum GTCombType {

    //Organic Line
    COAL("coal", true, 0x525252, 0x666666),
    COKE("coke", true, 0x4B4B4B, 0x7D7D7D),
    STICKY("stickyresin", true, 0x2E8F5B, 0xDCC289),
    OIL("oil", true, 0x333333, 0x4C4C4C),
    APATITE("apatite", true, 0xc1c1f6, 0x676784),
    ASH("ash", true, 0x1e1a18, 0xc6c6c6),
    BIOMASS("biomass", true, 0x17AF0E, 0x21E118),

    //Industrial Line
    COOLANT("coolant", false, 0x144F5A, 0x2494A2),
    ENERGY("energy", true, 0xC11F1F, 0xEBB9B9),
    LAPOTRON("lapotron", true, 0x1414FF, 0x6478FF),
    PYROTHEUM("pyrotheum", false, 0xffebc4, 0xe36400),
    CRYOTHEUM("cryotheum", false, 0x2660ff, 0x5af7ff),

    //Alloy Line
    REDALLOY("redalloy", true, 0xE60000, 0xB80000),
    STAINLESSSTEEL("stainlesssteel", true, 0x778899, 0xC8C8DC),

    //Gem Line
    STONE("stone", true, 0x808080, 0x999999),
    CERTUS("certus", true, 0x57CFFB, 0xBBEEFF),
    FLUIX("fluix", true, 0xA375FF, 0xB591FF),
    REDSTONE("redstone", true, 0x7D0F0F, 0xD11919),
    RAREEARTH("rareearth", true, 0x555643, 0x343428),
    LAPIS("lapis", true, 0x1947D1, 0x476CDA),
    RUBY("ruby", true, 0xE6005C, 0xCC0052),
    REDGARNET("redgarnet", true, 0xBD4C4C, 0xECCECE),
    YELLOWGARNET("yellowgarnet", true, 0xA3A341, 0xEDEDCE),
    SAPPHIRE("sapphire", true, 0x0033CC, 0x00248F),
    DIAMOND("diamond", true, 0xCCFFFF, 0xA3CCCC),
    OLIVINE("olivine", true, 0x248F24, 0xCCFFCC),
    EMERALD("emerald", true, 0x248F24, 0x2EB82E),
    PYROPE("pyrope", true, 0x763162, 0x8B8B8B),
    GROSSULAR("grossular", true, 0x9B4E00, 0x8B8B8B),

    //Metals Line
    SLAG("slag", true, 0xD4D4D4, 0x58300B),
    COPPER("copper", true, 0xFF6600, 0xE65C00),
    TIN("tin", true, 0xD4D4D4, 0xDDDDDD),
    LEAD("lead", true, 0x666699, 0xA3A3CC),
    IRON("iron", true, 0xDA9147, 0xDE9C59),
    STEEL("steel", true, 0x808080, 0x999999),
    NICKEL("nickel", true, 0x8585AD, 0x9D9DBD),
    ZINC("zinc", true, 0xF0DEF0, 0xF2E1F2),
    SILVER("silver", true, 0xC2C2D6, 0xCECEDE),
    GOLD("gold", true, 0xE6B800, 0xCFA600),
    SULFUR("sulfur", true, 0x6F6F01, 0x8B8B8B),
    GALLIUM("gallium", true, 0x8B8B8B, 0xC5C5E4),
    ARSENIC("arsenic", true, 0x736C52, 0x292412),

    //Rare Metals Line
    BAUXITE("bauxite", true, 0x6B3600, 0x8B8B8B),
    ALUMINIUM("aluminium", true, 0x008AB8, 0xD6D6FF),
    MANGANESE("manganese", true, 0xD5D5D5, 0xAAAAAA),
    MAGNESIUM("magnesium", true, 0xF1D9D9, 0x8B8B8B),
    TITANIUM("titanium", true, 0xCC99FF, 0xDBB8FF),
    CHROME("chrome", true, 0xEBA1EB, 0xF2C3F2),
    TUNGSTEN("tungsten", true, 0x62626D, 0x161620),
    PLATINUM("platinum", true, 0xE6E6E6, 0xFFFFCC),
    IRIDIUM("iridium", true, 0xDADADA, 0xD1D1E0),
    MOLYBDENUM("molybdenum", true, 0xAEAED4, 0x8B8B8B),
    OSMIUM("osmium", true, 0x2B2BDA, 0x8B8B8B),
    LITHIUM("lithium", true, 0xF0328C, 0xE1DCFF),
    SALT("salt", true, 0xF0C8C8, 0xFAFAFA),
    ELECTROTINE("electrotine", true, 0x1E90FF, 0x3CB4C8),
    ALMANDINE("almandine", true, 0xC60000, 0x8B8B8B),

    //Radioactive Line
    URANIUM("uranium", true, 0x19AF19, 0x169E16),
    PLUTONIUM("plutonium", true, 0x240000, 0x570000),
    NAQUADAH("naquadah", true, 0x000000, 0x004400),
    NAQUADRIA("naquadria", true, 0x000000, 0x002400),
    THORIUM("thorium", true, 0x001E00, 0x005000),
    LUTETIUM("lutetium", true, 0x0059FF, 0x00AAFF),
    AMERICIUM("americium", true, 0x0C453A, 0x287869),
    NEUTRONIUM("neutronium", true, 0xFFF0F0, 0xFAFAFA),

    //Twilight
    NAGA("naga", GTBees.TWILIGHT_BEES, 0x0D5A0D, 0x28874B),
    LICH("lich", GTBees.TWILIGHT_BEES, 0x5C605E, 0xC5C5C5),
    HYDRA("hydra", GTBees.TWILIGHT_BEES, 0x872836, 0xB8132C),
    URGHAST("urghast", GTBees.TWILIGHT_BEES && GTBees.THAUMIC_BEES, 0x7C0618, 0xA7041C),
    SNOWQUEEN("snowqueen", GTBees.TWILIGHT_BEES && GTBees.THAUMIC_BEES, 0x9C0018, 0xD02001),

    //Thaumcraft Line TODO Most of these probably no longer fit
    THAUMIUM("thaumium", GTBees.THAUMIC_BEES, 0x7A007A, 0x5C005C),
    AMBER("amber", GTBees.THAUMIC_BEES, 0x774B15, 0xEE7700),
    QUICKSILVER("quicksilver", GTBees.THAUMIC_BEES, 0xc7c7ea, 0xb5b3df),
    SALISMUNDUS("salismundus", GTBees.THAUMIC_BEES, 0xF7ADDE, 0x592582),
    TAINTED("tainted", GTBees.THAUMIC_BEES, 0x904BB8, 0xE800FF),

    // Semi-stable nugget
    DIVIDED("divided", GTValues.isModLoaded(GFValues.MODID_XU2), 0xF0F0F0, 0xDCDCDC),

    // Nether Star
    SPARKLING("sparkling", GTValues.isModLoaded(GFValues.MODID_MB), 0x7A007A, 0xFFFFFF),

    //THAUMIUMSHARD("thaumiumshard", GTBees.THAUMIC_BEES, null, 85,0x9966FF, 0xAD85FF),
    //MITHRIL("mithril", GTBees.THAUMIC_BEES, null, 70,0xF0E68C, 0xFFFFD2),
    //ASTRALSILVER("astralsilver", GTBees.THAUMIC_BEES, null, 70,0xAFEEEE, 0xE6E6FF),
    //THAUMINITE("thauminite", GTBees.THAUMIC_BEES, null, 50,0x2E2D79, 0x7581E0),
    //SHADOWMETAL("shadowmetal", GTBees.THAUMIC_BEES, null, 50,0x100322, 0x100342),
/*
    //Space
    SPACE("space", true, null, 100,0x003366, 0xC0C0C0),
    METEORICIRON("meteoriciron",true, Materials.MeteoricIron, 100,0x321928, 0x643250),
    DESH("desh",true, Materials.Desh, 90,0x282828, 0x323232),
    LEDOX("ledox",true, Materials.Ledox, 75,0x0000CD, 0x0074FF),
    CALLISTOICE("callistoice",true, Materials.CallistoIce, 75,0x0074FF, 0x1EB1FF),
    MYTRYL("mytryl",true, Materials.Mytryl, 65,0xDAA520, 0xF26404),
    QUANTIUM("quantium",true, Materials.Quantium, 50,0x00FF00, 0x00D10B),
    ORIHARUKON("oriharukon",true, Materials.Oriharukon, 50,0x228B22, 0x677D68),
    MYSTERIOUSCRYSTAL("mysteriouscrystal",true, Materials.MysteriousCrystal, 45,0x3CB371, 0x16856C),
    BLACKPLUTONIUM("blackplutonium",true, Materials.Quantium, 25,0x000000, 0x323232),
    TRINIUM("trinium",true, Materials.Trinium, 25,0xB0E0E6, 0xC8C8D2),

    //Planet
    MERCURY("mercury", true, null, 65,0x4A4033, 0xB5A288),
    VENUS("venus",true, null, 65,0x120E07, 0x272010),
    MOON("moon",true, null, 90,0x373735, 0x7E7E78),
    MARS("mars",true, null, 80,0x220D05, 0x3A1505),
    JUPITER("jupiter",true, null, 75,0x734B2E, 0xD0CBC4),
    SATURN("saturn",true, null, 55,0xD2A472, 0xF8C37B),
    URANUS("uranus",true, null, 45,0x75C0C9, 0x84D8EC),
    NEPTUN("neptun",true, null, 35,0x334CFF, 0x576DFF),
    PLUTO("pluto",true, null, 25,0x34271E, 0x69503D),
    HAUMEA("haumea",true, null, 20,0x1C1413, 0x392B28),
    MAKEMAKE("makemake",true, null, 20,0x301811, 0x120A07),
    CENTAURI("centauri",true, null, 15,0x2F2A14, 0xB06B32),
    TCETI("tceti",true, null, 10,0x46241A, 0x7B412F),
    BARNARDA("barnarda",true, null, 10,0x0D5A0D, 0xE6C18D),
    VEGA("vega",true, null, 10,0x1A2036, 0xB5C0DE),

    //Infinity
    COSMICNEUTRONIUM("cosmicneutronium",true, null, 5,0x484848, 0x323232),
    INFINITYCATALYST("infinitycatalyst",true, null, 2,0xFFFFFF, 0xFFFFFF),
    INFINITY("infinity",true, null, 1,0xFFFFFF, 0xFFFFFF),

    //HEE
    ENDDUST("enddust", true, null, 50,0x003A7D, 0xCC00FA),
    ECTOPLASMA("ectoplasma", true, null, 35,0x381C40, 0xDCB0E5),
    ARCANESHARD("arcaneshard", true, null, 35,0x333D82, 0x9010AD),
    STARDUST("stardust", true, null, 60,0xDCBE13, 0xffff00),
    DRAGONESSENCE("dragonessence", true, null, 30,0x911ECE, 0xFFA12B),
    ENDERMAN("enderman", true, null, 25,0x6200e7, 0x161616),
    SILVERFISH("silverfish", true, null, 25,0x0000000, 0xEE053D),
    ENDIUM("endium", true, Materials.HeeEndium, 50,0x2F5A6C, 0xa0ffff),
    RUNEI("rune1", true, null, 10,0x0104D9, 0xE31010),
    RUNEII("rune2", true, null, 10,0xE31010, 0x0104D9),
    FIREESSENSE("fireessence", true, null, 30,0xFFA157, 0xD41238),
 */
    ;

    public static final GTCombType[] VALUES = values();

    public final boolean showInList;
    public final String name;
    public final int[] color;

    GTCombType(String name, boolean show, int primary, int secondary) {
        this.name = name;
        this.showInList = show;
        this.color = new int[]{primary, secondary};
    }

    public static GTCombType get(int meta) {
        if (meta >= VALUES.length) {
            meta = 0;
        }
        return VALUES[meta];
    }
}
