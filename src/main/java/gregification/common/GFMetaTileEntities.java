package gregification.common;

import gregification.config.GFConfig;
import gregification.exnihilo.metatileentities.MetaTileEntitySieve;
import gregification.exnihilo.metatileentities.MetaTileEntitySteamSieve;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.util.ResourceLocation;

public class GFMetaTileEntities {

    public static MetaTileEntitySieve[] SIEVES = new MetaTileEntitySieve[GTValues.V.length - 1];

    public static MetaTileEntitySteamSieve STEAM_SIEVE;
    // high pressure steam sieve?

    public static void registerExNihilo() {

        STEAM_SIEVE = MetaTileEntities.registerMetaTileEntity(4000, new MetaTileEntitySteamSieve(gregificationId("sieve.steam"), false));
        // 4001 reserved for HP Steam Sieve if desired

        SIEVES[0] = MetaTileEntities.registerMetaTileEntity(4002, new MetaTileEntitySieve(gregificationId("sieve.lv"), 1));
        SIEVES[1] = MetaTileEntities.registerMetaTileEntity(4003, new MetaTileEntitySieve(gregificationId("sieve.mv"), 2));
        SIEVES[2] = MetaTileEntities.registerMetaTileEntity(4004, new MetaTileEntitySieve(gregificationId("sieve.hv"), 3));
        SIEVES[3] = MetaTileEntities.registerMetaTileEntity(4005, new MetaTileEntitySieve(gregificationId("sieve.ev"), 4));
        SIEVES[4] = MetaTileEntities.registerMetaTileEntity(4006, new MetaTileEntitySieve(gregificationId("sieve.iv"), 5));
        if (!GTValues.isModLoaded(GFValues.MODID_GCY) || (GTValues.isModLoaded(GFValues.MODID_GCY) && GFConfig.exNihilo.highTierSieve)) {
            SIEVES[5] = MetaTileEntities.registerMetaTileEntity(4007, new MetaTileEntitySieve(gregificationId("sieve.luv"), 6));
            SIEVES[6] = MetaTileEntities.registerMetaTileEntity(4008, new MetaTileEntitySieve(gregificationId("sieve.zpm"), 7));
            SIEVES[7] = MetaTileEntities.registerMetaTileEntity(4009, new MetaTileEntitySieve(gregificationId("sieve.uv"), 8));
        }
        if (GTValues.isModLoaded(GFValues.MODID_GCY) && GFConfig.exNihilo.highTierSieve) {
            SIEVES[8] = MetaTileEntities.registerMetaTileEntity(4010, new MetaTileEntitySieve(gregificationId("sieve.uhv"), 9));
            SIEVES[9] = MetaTileEntities.registerMetaTileEntity(4011, new MetaTileEntitySieve(gregificationId("sieve.uev"), 10));
            SIEVES[10] = MetaTileEntities.registerMetaTileEntity(4012, new MetaTileEntitySieve(gregificationId("sieve.uiv"), 11));
            SIEVES[11] = MetaTileEntities.registerMetaTileEntity(4013, new MetaTileEntitySieve(gregificationId("sieve.umv"), 12));
            SIEVES[12] = MetaTileEntities.registerMetaTileEntity(4014, new MetaTileEntitySieve(gregificationId("sieve.uxv"), 13));
        }
    }

    private static ResourceLocation gregificationId(String name) {
        return new ResourceLocation(GFValues.MODID, name);
    }
}
