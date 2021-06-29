/*
    Copyright 2019, TheLimePixel, dan
    GregBlock Utilities

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
package gregification.exnihilo.metatileentities;

import gregification.config.GFConfig;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;

import static gregification.util.GFUtility.gregificationId;

public class ExNihiloMetaTileEntities {

    public static SimpleMachineMetaTileEntity[] SIEVES = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];

    public static MetaTileEntitySteamSieve STEAM_SIEVE;
    // high pressure steam sieve?

    // TODO Rework this with GTCEu
    public static void register() {

        // Sieve, IDs 4000-4014
        SIEVES[0] = GregTechAPI.registerMetaTileEntity(4000, new MetaTileEntitySieve(gregificationId("sieve.lv"), 1));
        SIEVES[1] = GregTechAPI.registerMetaTileEntity(4001, new MetaTileEntitySieve(gregificationId("sieve.mv"), 2));
        SIEVES[2] = GregTechAPI.registerMetaTileEntity(4002, new MetaTileEntitySieve(gregificationId("sieve.hv"), 3));
        SIEVES[3] = GregTechAPI.registerMetaTileEntity(4003, new MetaTileEntitySieve(gregificationId("sieve.ev"), 4));
        if (GFConfig.exNihilo.highTierSieve) {
            SIEVES[4] = GregTechAPI.registerMetaTileEntity(4004, new MetaTileEntitySieve(gregificationId("sieve.iv"), 5));
            SIEVES[5] = GregTechAPI.registerMetaTileEntity(4005, new MetaTileEntitySieve(gregificationId("sieve.luv"), 6));
            SIEVES[6] = GregTechAPI.registerMetaTileEntity(4006, new MetaTileEntitySieve(gregificationId("sieve.zpm"), 7));
            SIEVES[7] = GregTechAPI.registerMetaTileEntity(4007, new MetaTileEntitySieve(gregificationId("sieve.uv"), 8));
        }

        STEAM_SIEVE = GregTechAPI.registerMetaTileEntity(2749, new MetaTileEntitySteamSieve(gregificationId("sieve.steam"), false));
    }
}
