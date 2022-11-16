package gregification.modules.tinkers.material;

import com.google.common.base.CaseFormat;
import gregification.modules.tinkers.TinkersConfig;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.ITrait;

import java.util.*;

import static slimeknights.tconstruct.library.materials.Material.UNKNOWN;

public class MaterialStats {

    private final Material gtMaterial;

    private final List<IMaterialStats> allStats = new ArrayList<>();
    private final Map<ITrait, List<String>> allTraits = new HashMap<>(); // todo add some cool traits

    private MaterialStats(Material gtMaterial) {
        this.gtMaterial = gtMaterial;
    }

    private void addStat(IMaterialStats stat) {
        allStats.add(stat);
    }

    public void register() {
        TMaterial tconMaterial = new TMaterial(gtMaterial.toString(), gtMaterial.getMaterialRGB());
        String formattedName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, gtMaterial.toCamelCaseString());

        if (gtMaterial.hasProperty(PropertyKey.FLUID)) {
            tconMaterial.setFluid(gtMaterial.getFluid());
        }

        if (gtMaterial.hasProperty(PropertyKey.INGOT)) {
            tconMaterial.addCommonItems(formattedName);
            tconMaterial.addItemIngot(new UnificationEntry(OrePrefix.ingot, gtMaterial).toString());
            tconMaterial.setRepresentativeItem(OreDictUnifier.get(OrePrefix.ingot, gtMaterial));
        }

        // TODO Some way to modify materials that already exist in tinkers?
        if (TinkerRegistry.getMaterial(tconMaterial.identifier) == UNKNOWN) {
            TinkerRegistry.addMaterial(tconMaterial);
            for (IMaterialStats stat : allStats) {
                tconMaterial.addStats(stat);
            }
            for (Map.Entry<ITrait, List<String>> entry : allTraits.entrySet()) {
                ITrait trait = entry.getKey();
                if (entry.getValue() == null) {
                    tconMaterial.addTrait(trait);
                } else for (String part : entry.getValue()) {
                    tconMaterial.addTrait(trait, part);
                }
            }
            TinkerRegistry.integrate(tconMaterial, tconMaterial.getFluid(), formattedName);
        }
    }

    public static Builder builder(Material gtMaterial) {
        return new Builder(gtMaterial);
    }

    /** Requires the GT Material provided to have a Tool Property */
    public static Builder createIngotTemplate(Material gtMaterial) {
        ToolProperty prop = gtMaterial.getProperty(PropertyKey.TOOL);
        if (prop == null) {
            throw new IllegalArgumentException("Cannot auto-initialize tinkers tool stats from material with no tool property!");
        }

        int durability = prop.getToolDurability();
        float speed = prop.getToolSpeed();
        float damage = prop.getToolAttackDamage();
        int harvestLevel = gtMaterial.getToolHarvestLevel();

        return builder(gtMaterial)
                .setHead((int) (durability * 0.8), speed, damage, harvestLevel)
                .setHandle((harvestLevel - 0.5f) / 2, durability / 3)
                .setExtra(durability / 4);
    }

    /** Requires the GT Material provided to have a Tool Property */
    public static Builder createGemTemplate(Material gtMaterial) {
        ToolProperty prop = gtMaterial.getProperty(PropertyKey.TOOL);
        if (prop == null) {
            throw new IllegalArgumentException("Cannot auto-initialize tinkers tool stats from material with no tool property!");
        }

        int durability = prop.getToolDurability();
        float speed = prop.getToolSpeed();
        float damage = prop.getToolAttackDamage();
        int harvestLevel = gtMaterial.getToolHarvestLevel();

        return builder(gtMaterial)
                .setHead(durability, speed, damage, harvestLevel)
                .setHandle(harvestLevel - 0.5f, durability / 4)
                .setExtra(durability / 100);
    }

    public static class Builder {

        private final MaterialStats stats;

        private Builder (Material gtMaterial) {
            stats = new MaterialStats(gtMaterial);
        }

        /**
         * @param durability   Base value for durability calculations
         * @param miningSpeed  How fast a tool head of this material can break blocks
         * @param attackDamage Base value for attack calculations
         * @param harvestLevel What range of blocks at tool with a tool head of this material can mine
         */
        public Builder setHead(int durability, float miningSpeed, float attackDamage, int harvestLevel) {
            stats.addStat(new HeadMaterialStats(
                    (int) (durability * TinkersConfig.toolStats.durabilityModifier),
                    miningSpeed * (float) TinkersConfig.toolStats.miningSpeedModifier,
                    attackDamage * (float) TinkersConfig.toolStats.attackDamageModifier,
                    harvestLevel));
            return this;
        }

        /**
         * @param modifier   The total durability of the tool will be multiplied by this
         * @param durability Tool durability will be changed by this amount
         */
        public Builder setHandle(float modifier, int durability) {
            stats.addStat(new HandleMaterialStats(
                    modifier * (float) TinkersConfig.toolStats.handleModifier,
                    durability));
            return this;
        }

        /**
         * @param extraDurability How much durability this part contributes when used as an accessory
         */
        public Builder setExtra(int extraDurability) {
            stats.addStat(new ExtraMaterialStats(extraDurability));
            return this;
        }

        /**
         * @param drawSpeed   How fast you can draw the bow
         * @param range       How far the projectile can be propelled (multiplier)
         * @param bonusDamage Bonus damage dealt on hit. The force of the arrow
         */
        public Builder setBow(float drawSpeed, float range, float bonusDamage) {
            stats.addStat(new BowMaterialStats(
                    drawSpeed * (float) TinkersConfig.toolStats.bowDrawSpeedModifier,
                    range * (float) TinkersConfig.toolStats.bowFlightSpeedModifier,
                    bonusDamage * (float) TinkersConfig.toolStats.arrowMassModifier));
            return this;
        }

        /**
         * @param modifier The tool durability will be multiplied by this
         */
        public Builder setBowString(float modifier) {
            stats.addStat(new BowStringMaterialStats(modifier));
            return this;
        }

        /**
         * @param accuracy How stable the flight path will be using this fletching
         * @param modifier How many arrows you can craft with this. Projectile ammo will be multiplied by this
         */
        public Builder setFletching(float accuracy, float modifier) {
            stats.addStat(new FletchingMaterialStats(accuracy, modifier));
            return this;
        }

        // TODO is this useful?
        public Builder setProjectile() {
            stats.addStat(new ProjectileMaterialStats());
            return this;
        }

        /**
         * @param modifier  The total ammo count of the tool will be multiplied by this
         * @param bonusAmmo This much flat ammo will be added
         */
        public Builder setArrowShaft(float modifier, int bonusAmmo) {
            stats.addStat(new ArrowShaftMaterialStats(
                    modifier * (float) TinkersConfig.toolStats.arrowAmmoModifier,
                    bonusAmmo));
            return this;
        }

        public Builder addTrait(ITrait trait, String... parts) {
            List<String> traitParts = stats.allTraits.computeIfAbsent(trait, k -> new ArrayList<>());
            traitParts.addAll(Arrays.asList(parts));
            return this;
        }

        public void build() {
            stats.register();
        }
    }
}
