package gregification.forestry.recipes;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import forestry.api.recipes.RecipeManagers;
import forestry.core.fluids.Fluids;
import gregification.common.GFLog;
import gregification.common.GFUtility;
import gregification.common.GFValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.ConfigHolder;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

import static gregification.common.GFValues.FORESTRY;
import static gregification.forestry.recipes.ForestryRecipeUtil.removeCarpenterRecipe;
import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.common.metatileentities.MetaTileEntities.BRONZE_DRUM;
import static gregtech.common.metatileentities.MetaTileEntities.HULL;

// TODO Read into GT configs for some "basic" forestry things
public class ForestryOverrideRecipes {

    public static void init() {

        // Sturdy Casing
        ModHandler.removeRecipeByName("forestry:sturdy_casing");
        ModHandler.addShapedRecipe("forestry:sturdy_casing", getForestryItem("sturdy_machine", 0),
                "BSB", "SHS", " d ",
                'B', new UnificationEntry(plate, Bronze),
                'S', new UnificationEntry(screw, Steel),
                'H', HULL[LV].getStackForm());
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(HULL[LV])
                .input(plate, Bronze, 2)
                .outputs(getForestryItem("sturdy_machine", 0))
                .duration(400).EUt(VA[LV]).buildAndRegister();

        // Hardened Casing
        removeCarpenterRecipe(getForestryItem("hardened_machine", 0));
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(getForestryItem("sturdy_machine", 0))
                .input(plate, Diamond, 4)
                .outputs(getForestryItem("hardened_machine", 0))
                .duration(400).EUt(VA[MV]).buildAndRegister();

        // Impregnated Casing
        ASSEMBLER_RECIPES.recipeBuilder()
                .input("logWood", 8)
                .circuitMeta(8)
                .fluidInputs(SeedOil.getFluid(250))
                .outputs(getForestryItem("impregnated_casing", 0))
                .duration(64).EUt(16).buildAndRegister();

        // TODO Flexible Casing?

        // Basic Circuit Chipset
        removeCarpenterRecipe(getForestryItem("chipsets", 1, 0, "{T:0s}"));
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(BASIC_CIRCUIT_BOARD)
                .input(circuit, Tier.Primitive, 2)
                .input(foil, Iron, 2)
                .input(screw, Iron, 4)
                .input(wireFine, Iron)
                .notConsumable(new IntCircuitIngredient(1))
                .outputs(getForestryItem("chipsets", 1, 0, "{T:0s}"))
                .duration(200).EUt(VA[LV]).buildAndRegister();
        RecipeManagers.carpenterManager.addRecipe(20, Redstone.getFluid(1152), BASIC_CIRCUIT_BOARD.getStackForm(), getForestryItem("chipsets", 1, 0, "{T:0s}"),
                "SFS", "CWC", "SFS",
                'S', "screwIron",
                'F', "foilIron",
                'W', "wireFineIron",
                'C', "circuitPrimitive");

        // Enhanced Circuit Chipset
        removeCarpenterRecipe(getForestryItem("chipsets", 1, 1, "{T:1s}"));
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(BASIC_CIRCUIT_BOARD)
                .input(circuit, Tier.Basic, 2)
                .input(foil, Bronze, 2)
                .input(screw, Bronze, 4)
                .input(wireFine, Bronze)
                .notConsumable(new IntCircuitIngredient(1))
                .outputs(getForestryItem("chipsets", 1, 1, "{T:1s}"))
                .duration(200).EUt(VA[LV]).buildAndRegister();
        RecipeManagers.carpenterManager.addRecipe(20, Redstone.getFluid(1152), BASIC_CIRCUIT_BOARD.getStackForm(), getForestryItem("chipsets", 1, 1, "{T:1s}"),
                "SFS", "CWC", "SFS",
                'S', "screwBronze",
                'F', "foilBronze",
                'W', "wireFineBronze",
                'C', "circuitBasic");

        // Refined Circuit Chipset
        removeCarpenterRecipe(getForestryItem("chipsets", 1, 2, "{T:2s}"));
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(GOOD_CIRCUIT_BOARD)
                .input(circuit, Tier.Good, 2)
                .input(foil, Steel, 2)
                .input(screw, Steel, 4)
                .input(wireFine, Steel)
                .notConsumable(new IntCircuitIngredient(1))
                .outputs(getForestryItem("chipsets", 1, 2, "{T:2s}"))
                .duration(200).EUt(VA[LV]).buildAndRegister();
        RecipeManagers.carpenterManager.addRecipe(20, Redstone.getFluid(1152), GOOD_CIRCUIT_BOARD.getStackForm(), getForestryItem("chipsets", 1, 2, "{T:2s}"),
                "SFS", "CWC", "SFS",
                'S', "screwSteel",
                'F', "foilSteel",
                'W', "wireFineSteel",
                'C', "circuitGood");

        // Intricate Circuit Chipset
        removeCarpenterRecipe(getForestryItem("chipsets", 1, 3, "{T:3s}"));
        CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder()
                .input(GOOD_CIRCUIT_BOARD)
                .input(circuit, Tier.Advanced, 2)
                .input(foil, Electrum, 2)
                .input(screw, Electrum, 4)
                .input(wireFine, Electrum)
                .notConsumable(new IntCircuitIngredient(1))
                .outputs(getForestryItem("chipsets", 1, 3, "{T:3s}"))
                .duration(200).EUt(VA[LV]).buildAndRegister();
        RecipeManagers.carpenterManager.addRecipe(20, Redstone.getFluid(1152), GOOD_CIRCUIT_BOARD.getStackForm(), getForestryItem("chipsets", 1, 3, "{T:3s}"),
                "SFS", "CWC", "SFS",
                'S', "screwElectrum",
                'F', "foilElectrum",
                'W', "wireFineElectrum",
                'C', "circuitAdvanced");

        // Analyzer
        ModHandler.removeRecipeByName("forestry:analyzer");
        ModHandler.addShapedRecipe("forestry:analyzer", getForestryItem("analyzer", 0),
                " A ", "TST", "CMC",
                'A', getForestryItem("portable_alyzer", 0),
                'T', BRONZE_DRUM.getStackForm(),
                'S', getForestryItem("sturdy_machine", 0),
                'C', new UnificationEntry(circuit, Tier.Basic),
                'M', ELECTRIC_MOTOR_LV.getStackForm());

        // Bottler
        ModHandler.removeRecipeByName("forestry:bottler");
        ModHandler.addShapedRecipe("forestry:bottler", getForestryItem("bottler", 0),
                "PTP", "RCR", "GMG",
                'P', new UnificationEntry(plate, Cupronickel),
                'T', BRONZE_DRUM.getStackForm(),
                'R', new UnificationEntry(ring, Rubber),
                'C', getForestryItem("sturdy_machine", 0),
                'G', new UnificationEntry(gearSmall, Steel),
                'M', ELECTRIC_MOTOR_LV.getStackForm());

        // Carpenter
        ModHandler.removeRecipeByName("forestry:carpenter");
        ModHandler.addShapedRecipe("forestry:carpenter", getForestryItem("carpenter", 0),
                "PTP", "RCR", "GMG",
                'P', new UnificationEntry(plate, Cupronickel),
                'T', BRONZE_DRUM.getStackForm(),
                'R', new UnificationEntry(ring, Rubber),
                'C', getForestryItem("sturdy_machine", 0),
                'G', new UnificationEntry(gearSmall, Steel),
                'M', ELECTRIC_MOTOR_LV.getStackForm());

        // Centrifuge
        ModHandler.removeRecipeByName("forestry:centrifuge");
        ModHandler.addShapedRecipe("forestry:centrifuge", getForestryItem("centrifuge", 0),
                "PMP", "RCR", "GMG",
                'P', new UnificationEntry(plate, Cupronickel),
                'R', new ItemStack(Blocks.IRON_BARS),
                'C', getForestryItem("sturdy_machine", 0),
                'G', new UnificationEntry(gearSmall, Steel),
                'M', ELECTRIC_MOTOR_LV.getStackForm());

        // Fermenter
        ModHandler.removeRecipeByName("forestry:fermenter");
        ModHandler.addShapedRecipe("forestry:fermenter", getForestryItem("fermenter", 0),
                "PRP", "TCT", "GMG",
                'P', new UnificationEntry(plate, Cupronickel),
                'T', BRONZE_DRUM.getStackForm(),
                'R', new UnificationEntry(rotor, Tin),
                'C', getForestryItem("sturdy_machine", 0),
                'G', new UnificationEntry(gearSmall, Steel),
                'M', ELECTRIC_MOTOR_LV.getStackForm());

        // Moistener
        ModHandler.removeRecipeByName("forestry:moistener");
        ModHandler.addShapedRecipe("forestry:moistener", getForestryItem("moistener", 0),
                "PTP", "RCR", "GMG",
                'P', new UnificationEntry(plate, Cupronickel),
                'T', BRONZE_DRUM.getStackForm(),
                'R', new UnificationEntry(rotor, Tin),
                'C', getForestryItem("sturdy_machine", 0),
                'G', new UnificationEntry(gearSmall, Steel),
                'M', ELECTRIC_MOTOR_LV.getStackForm());

        // Squeezer
        ModHandler.removeRecipeByName("forestry:squeezer");
        ModHandler.addShapedRecipe("forestry:squeezer", getForestryItem("squeezer", 0),
                "PTP", "RCR", "GMG",
                'P', new UnificationEntry(plate, Cupronickel),
                'T', BRONZE_DRUM.getStackForm(),
                'R', ELECTRIC_PISTON_LV.getStackForm(),
                'C', getForestryItem("sturdy_machine", 0),
                'G', new UnificationEntry(gearSmall, Steel),
                'M', ELECTRIC_MOTOR_LV.getStackForm());

        // Still
        ModHandler.removeRecipeByName("forestry:still");
        ModHandler.addShapedRecipe("forestry:still", getForestryItem("still", 0),
                "PRP", "TCT", "GMG",
                'P', new UnificationEntry(plate, Cupronickel),
                'T', BRONZE_DRUM.getStackForm(),
                'R', VOLTAGE_COIL_LV.getStackForm(),
                'C', getForestryItem("sturdy_machine", 0),
                'G', new UnificationEntry(gearSmall, Steel),
                'M', ELECTRIC_MOTOR_LV.getStackForm());

        // Thermionic Fabricator
        ModHandler.removeRecipeByName("forestry:fabricator");
        RecipeManagers.carpenterManager.addRecipe(20, Redstone.getFluid(1296), getForestryItem("worktable", 0), getForestryItem("fabricator", 0),
                "STS", "CFC", "SMS",
                'S', "screwSteel",
                'T', BRONZE_DRUM.getStackForm(),
                'C', VOLTAGE_COIL_ULV.getStackForm(),
                'F', getForestryItem("sturdy_machine", 0),
                'M', ELECTRIC_MOTOR_LV.getStackForm());

        // Rainmaker
        ModHandler.removeRecipeByName("forestry:rainmaker");
        ModHandler.addShapedRecipe("forestry:rainmaker", getForestryItem("rainmaker", 0),
                "PSP", "ICI", "GEG",
                'P', new UnificationEntry(plate, Cupronickel),
                'S', SENSOR_LV.getStackForm(),
                'I', ELECTRIC_PISTON_LV.getStackForm(),
                'C', getForestryItem("hardened_machine", 0),
                'G', new UnificationEntry(gearSmall, Aluminium),
                'E', EMITTER_LV.getStackForm());

        // Analyzer
        removeCarpenterRecipe(getForestryItem("portable_alyzer", 0));
        RecipeManagers.carpenterManager.addRecipe(20, Redstone.getFluid(576), COVER_SCREEN.getStackForm(), getForestryItem("portable_alyzer", 0),
                "SPS", "PCP", "SPS",
                'S', "screwDiamond",
                'P', "plateTin",
                'C', "circuitBasic");

        // Peat-Fired Engine
        ModHandler.removeRecipeByName("forestry:peat_engine");
        ModHandler.addShapedRecipe("forestry:peat_engine", getForestryItem("engine_peat", 0),
                "PLP", "SMS", "GCG",
                'P', new UnificationEntry(plate, Iron),
                'L', new UnificationEntry(plate, Lapis),
                'S', new UnificationEntry(spring, Iron),
                'M', "craftingPiston",
                'G', new UnificationEntry(gear, Iron),
                'C', getForestryItem("sturdy_machine", 0));

        // Biogas Engine
        ModHandler.removeRecipeByName("forestry:biogas_engine");
        ModHandler.addShapedRecipe("forestry:biogas_engine", getForestryItem("engine_biogas", 0),
                "PLP", "SMS", "GCG",
                'P', new UnificationEntry(plate, Bronze),
                'L', new UnificationEntry(plate, Lapis),
                'S', new UnificationEntry(spring, Bronze),
                'M', "craftingPiston",
                'G', new UnificationEntry(gear, Bronze),
                'C', getForestryItem("sturdy_machine", 0));

        // Clockwork Engine
        ModHandler.removeRecipeByName("forestry:clockwork_engine");
        ModHandler.addShapedRecipe("forestry:clockwork_engine", getForestryItem("engine_clockwork", 0),
                "PLP", "SMS", "GCG",
                'P', new UnificationEntry(plate, Gold),
                'L', new UnificationEntry(plate, Lapis),
                'S', new UnificationEntry(spring, WroughtIron),
                'M', "craftingPiston",
                'G', new UnificationEntry(gear, WroughtIron),
                'C', getForestryItem("sturdy_machine", 0));

        // Proven Frame
        RecipeManagers.carpenterManager.addRecipe(getForestryItem("frame_impregnated", 0), getForestryItem("frame_proven", 0),
                "SHS", "PSI", "SJS",
                'S', "screwSteel",
                'H', getForestryItem("honeydew", 0),
                'P', getForestryItem("propolis", 0),
                'I', getForestryItem("propolis", 3),
                'J', getForestryItem("royal_jelly", 0));

        // Farm Multiblocks
        String[] recipeNames = new String[]{
                "brick_stone", "brick_mossy", "brick_cracked", "brick", "sandstone_smooth",
                "sandstone_chiseled", "brick_nether", "brick_chiseled",
                "quartz", "quartz_chiseled", "quartz_lines"
        };
        for (String s : recipeNames) {
            ModHandler.removeRecipeByName("forestry:farm_basic_" + s);
            ModHandler.removeRecipeByName("forestry:farm_gearbox_" + s);
            ModHandler.removeRecipeByName("forestry:farm_hatch_" + s);
            ModHandler.removeRecipeByName("forestry:farm_valve_" + s);
            ModHandler.removeRecipeByName("forestry:farm_control_" + s);
        }

        Map<ItemStack, String> farmBlockMap = ImmutableMap.<ItemStack, String>builder()
                .put(new ItemStack(Blocks.STONEBRICK, 1, 0), "{FarmBlock:0}")
                .put(new ItemStack(Blocks.STONEBRICK, 1, 1), "{FarmBlock:1}")
                .put(new ItemStack(Blocks.STONEBRICK, 1, 2), "{FarmBlock:2}")
                .put(new ItemStack(Blocks.BRICK_BLOCK), "{FarmBlock:3}")
                .put(new ItemStack(Blocks.SANDSTONE, 1, 2), "{FarmBlock:4}")
                .put(new ItemStack(Blocks.SANDSTONE, 1, 1), "{FarmBlock:5}")
                .put(new ItemStack(Blocks.NETHER_BRICK), "{FarmBlock:6}")
                .put(new ItemStack(Blocks.STONEBRICK, 1, 3), "{FarmBlock:7}")
                .put(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 0), "{FarmBlock:8}")
                .put(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 1), "{FarmBlock:9}")
                .put(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 2), "{FarmBlock:10}")
                .build();

        for (Map.Entry<ItemStack, String> entry : farmBlockMap.entrySet()) {
            // Farm Block
            RecipeManagers.carpenterManager.addRecipe(20, Creosote.getFluid(500), entry.getKey(), getForestryItem("ffarm", 1, 0, entry.getValue()),
                    "SPS", " E ", "SPS",
                    'S', "gearSmallSteel",
                    'P', "plateBronze",
                    'E', getForestryItem("thermionic_tubes", 10));

            // Farm Gearbox
            RecipeManagers.carpenterManager.addRecipe(20, Creosote.getFluid(500), getForestryItem("ffarm", 1, 0, entry.getValue()), getForestryItem("ffarm", 1, 2, entry.getValue()),
                    "EGE", "GMG", "EGE",
                    'E', getForestryItem("thermionic_tubes", 2),
                    'G', "gearSmallSteel",
                    'M', ELECTRIC_MOTOR_LV.getStackForm());

            // Farm Hatch
            RecipeManagers.carpenterManager.addRecipe(20, Creosote.getFluid(500), getForestryItem("ffarm", 1, 0, entry.getValue()), getForestryItem("ffarm", 1, 3, entry.getValue()),
                    "EGE", "CMC", "EHE",
                    'E', getForestryItem("thermionic_tubes", 1),
                    'G', "gearSmallSteel",
                    'M', ELECTRIC_MOTOR_LV.getStackForm(),
                    'C', CONVEYOR_MODULE_LV.getStackForm(),
                    'H', new ItemStack(Blocks.HOPPER));

            // Farm Valve
            RecipeManagers.carpenterManager.addRecipe(20, Creosote.getFluid(500), getForestryItem("ffarm", 1, 0, entry.getValue()), getForestryItem("ffarm", 1, 4, entry.getValue()),
                    "EGE", "PMP", "ERE",
                    'E', getForestryItem("thermionic_tubes", 11),
                    'G', "gearSmallSteel",
                    'P', ELECTRIC_PUMP_LV.getStackForm(),
                    'M', ELECTRIC_MOTOR_LV.getStackForm(),
                    'R', "ringRubber");

            // Farm Control
            RecipeManagers.carpenterManager.addRecipe(20, Creosote.getFluid(500), getForestryItem("ffarm", 1, 0, entry.getValue()), getForestryItem("ffarm", 1, 5, entry.getValue()),
                    "EGE", "CMC", "EWE",
                    'E', getForestryItem("thermionic_tubes", 4),
                    'G', "gearSmallSteel",
                    'C', "circuitBasic",
                    'M', ELECTRIC_MOTOR_LV.getStackForm(),
                    'W', "cableGtSingleTin");
        }

        // Apiary/Bee House Minecarts
        ASSEMBLER_RECIPES.recipeBuilder().inputs(getForestryItem("bee_house", 0)).input(Items.MINECART).outputs(getForestryItem("cart.beehouse", 0)).duration(400).EUt(4).buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().inputs(getForestryItem("apiary", 0)).input(Items.MINECART).outputs(getForestryItem("cart.beehouse", 1)).duration(400).EUt(4).buildAndRegister();
        if (ConfigHolder.recipes.hardMiscRecipes) {
            ModHandler.removeRecipeByName("forestry:bee_house_minecart");
            ModHandler.addShapedRecipe("forestry:bee_house_minecart", getForestryItem("cart.beehouse", 0), "hIw", " M ", " d ", 'I', getForestryItem("bee_house", 0), 'M', new ItemStack(Items.MINECART));
            ModHandler.removeRecipeByName("forestry:apiary_minecart");
            ModHandler.addShapedRecipe("forestry:apiary_minecart", getForestryItem("cart.beehouse", 1), "hIw", " M ", " d ", 'I', getForestryItem("apiary", 0), 'M', new ItemStack(Items.MINECART));

            // Pipette
            ModHandler.removeRecipeByName("forestry:pipette");
            ModHandler.addShapedRecipe("forestry:pipette", getForestryItem("pipette", 0),
                    " RR", " CR", "B  ",
                    'R', new UnificationEntry(plate, Rubber),
                    'C', FLUID_CELL.getStackForm(),
                    'B', new UnificationEntry(bolt, Glass));

            // Scented Paneling
            removeCarpenterRecipe(getForestryItem("crafting_material", 6));
            RecipeManagers.carpenterManager.addRecipe(10, Fluids.FOR_HONEY.getFluid(500), ItemStack.EMPTY, getForestryItem("crafting_material", 6),
                    "WJW", "SSS", "APA",
                    'W', "wireFineElectrum",
                    'J', "dropRoyalJelly",
                    'S', getForestryItem("oak_stick", 0),
                    'A', "itemBeeswax",
                    'P', "itemPollen");

            ModHandler.removeRecipeByName("forestry:spectables");
            ModHandler.addShapedRecipe("forestry:spectacles", getForestryItem("naturalist_helmet", 0),
                    "SRS", "L L", "   ",
                    'S', new UnificationEntry(screw, Iron),
                    'R', new UnificationEntry(ring, Iron),
                    'L', new UnificationEntry(craftingLens, Glass));
        }

        // Bituminous Peat
        ModHandler.removeRecipeByName("bituminous_peat");
        RecipeManagers.carpenterManager.addRecipe(10, ItemStack.EMPTY, getForestryItem("bituminous_peat", 0),
                " A ", "IPI", " A ",
                'A', "dustAsh",
                'I', "brickPeat",
                'P', GFUtility.getModItem(FORESTRY, "propolis", W));

        // Pulsating Mesh
        ModHandler.removeRecipeByName("forestry:pulsating_mesh");
        ASSEMBLER_RECIPES.recipeBuilder().inputs(getForestryItem("propolis", 5, 2)).circuitMeta(5).outputs(getForestryItem("crafting_material", 1)).duration(16).EUt(7).buildAndRegister();

        // Woven Silk
        ASSEMBLER_RECIPES.recipeBuilder().inputs(getForestryItem("crafting_material", 9, 2)).fluidInputs(Water.getFluid(500)).circuitMeta(9).outputs(getForestryItem("crafting_material", 3)).duration(64).EUt(7).buildAndRegister();

        // Soldering Iron
        removeCarpenterRecipe(getForestryItem("soldering_iron", 0));
        RecipeManagers.carpenterManager.addRecipe(20, Redstone.getFluid(576), ItemStack.EMPTY, getForestryItem("soldering_iron", 0),
                "  R", " RB", "O  ",
                'R', "stickSteel",
                'B', "boltSteel",
                'O', "stickBronze");

        // Impregnated Stick
        ASSEMBLER_RECIPES.recipeBuilder().input("logWood", 1).fluidInputs(SeedOil.getFluid(50)).circuitMeta(2).outputs(getForestryItem("oak_stick", 0)).duration(16).EUt(7).buildAndRegister();

        // Wood Pulp TODO Always remove?
        removeCarpenterRecipe(getForestryItem("wood_pulp", 0));

        if (ConfigHolder.recipes.hardWoodRecipes) {
            // Escritoire
            removeCarpenterRecipe(getForestryItem("escritoire", 0));
            RecipeManagers.carpenterManager.addRecipe(20, SeedOil.getFluid(500), ItemStack.EMPTY, getForestryItem("escritoire", 0),
                    "PSS", "HHH", "F F",
                    'P', "plankWood",
                    'S', "screwSteel",
                    'H', "slabWood",
                    'F', "fenceWood");
        }

        if (isModLoaded(GFValues.MODID_GTFO)) {
            // Honeyed Slice
            ModHandler.removeRecipeByName("forestry:honeyed_slice");
            RecipeManagers.carpenterManager.addRecipe(10, Fluids.FOR_HONEY.getFluid(800), ItemStack.EMPTY, getForestryItem("honeyed_slice", 0),
                    "   ", "   ", " B ", 'B', GTFOMetaItem.PRESLICED_BREAD.getStackForm());
        }

        // Wax Capsule
        ModHandler.removeRecipeByName("forestry:wax_capsule");
        EXTRUDER_RECIPES.recipeBuilder().inputs(getForestryItem("beeswax", 0)).notConsumable(SHAPE_EXTRUDER_CELL).outputs(getForestryItem("capsule", 0)).duration(64).EUt(16).buildAndRegister();

        // Refractory Capsule
        ModHandler.removeRecipeByName("forestry:refractory_capsule");
        EXTRUDER_RECIPES.recipeBuilder().inputs(getForestryItem("refractory_wax", 0)).notConsumable(SHAPE_EXTRUDER_CELL).outputs(getForestryItem("refractory", 0)).duration(128).EUt(16).buildAndRegister();

        // Compost
        ModHandler.removeRecipeByName("forestry:wheat_to_compost");
        ModHandler.removeRecipeByName("forestry:ash_to_compost");
        RecipeManagers.carpenterManager.addRecipe(5, Water.getFluid(100), ItemStack.EMPTY, getForestryItem("fertilizer_bio", 4, 0),
                " W ", "WDW", " W ", 'W', new ItemStack(Items.WHEAT), 'D', new ItemStack(Blocks.DIRT, 1, W));
        RecipeManagers.carpenterManager.addRecipe(5, Water.getFluid(100), ItemStack.EMPTY, getForestryItem("fertilizer_bio", 0),
                " W ", "WDW", " W ", 'W', "dustAsh", 'D', new ItemStack(Blocks.DIRT, 1, W));
        MIXER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.DIRT, 1, W))
                .inputs(new ItemStack(Items.WHEAT))
                .notConsumable(new IntCircuitIngredient(4))
                .fluidInputs(Water.getFluid(100))
                .outputs(getForestryItem("fertilizer_bio", 4, 0))
                .duration(200).EUt(16).buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Blocks.DIRT, 1, W))
                .input(dust, Ash, 4)
                .notConsumable(new IntCircuitIngredient(4))
                .fluidInputs(Water.getFluid(100))
                .outputs(getForestryItem("fertilizer_bio", 4, 0))
                .duration(200).EUt(16).buildAndRegister();

        // TODO Remove gear recipes??
        // TODO Arc furnace recycling all of this stuff

        // Clear the map since we don't need it anymore
        cachedStackMap = null;
    }


    // Little caching strategy to try and avoid looking up the game registry quite as often
    private static Map<Pair<String, Integer>, ItemStack> cachedStackMap = new HashMap<>();

    private static ItemStack getForestryItem(String name, int meta) {
        return getForestryItem(name, meta, 1, null);
    }

    private static ItemStack getForestryItem(String name, int amount, int meta) {
        return getForestryItem(name, amount, meta, null);
    }

    private static ItemStack getForestryItem(String name, int amount, int meta, String nbt) {
        Pair<String, Integer> itemKey = Pair.of(name, meta);
        ItemStack stack = cachedStackMap.get(itemKey);
        if (stack == null) {
            stack = GFUtility.getModItem(FORESTRY, name, meta, 1);
            cachedStackMap.put(itemKey, stack);
        }
        if (stack == ItemStack.EMPTY) {
            GFLog.forestryLogger.error("Could not find Forestry item with name {} and meta {}", name, meta);
            return stack;
        }
        stack = stack.copy();
        if (!Strings.isNullOrEmpty(nbt)) {
            try {
                stack.setTagCompound(JsonToNBT.getTagFromJson(nbt));
            } catch (NBTException e) {
                GFLog.forestryLogger.error("Invalid NBT string passed for item with name {} and meta {}. NBT string: {}", name, meta, nbt);
            }
        }
        stack.setCount(amount);
        return stack;
    }
}
