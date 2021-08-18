import net.minecraftforge.gradle.user.UserBaseExtension
import java.util.*

buildscript {
    repositories {
        mavenCentral()
        maven {
            name = "Jitpack"
            setUrl("https://jitpack.io")
        }
        maven {
            name = "Forge"
            setUrl("https://maven.minecraftforge.net")
        }
    }
    dependencies {
        classpath("com.github.GregTechCE:ForgeGradle:FG_2.3-SNAPSHOT")
    }
}

apply {
    plugin("net.minecraftforge.gradle.forge")
}

val Project.minecraft: UserBaseExtension
    get() = extensions.getByName<UserBaseExtension>("minecraft")

val config: Properties = file("build.properties").inputStream().let {
    val prop = Properties()
    prop.load(it)
    return@let prop
}

val modVersion = config["gregification.version"] as String
val mcVersion = config["mc.version"] as String
val forgeVersion = "$mcVersion-${config["forge.version"]}"
val shortVersion = mcVersion.substring(0, mcVersion.lastIndexOf("."))
val strippedVersion = shortVersion.replace(".", "") + "0";

version = "$mcVersion-$modVersion"
group = "gregification"

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

configure<BasePluginConvention> {
    archivesBaseName = "Gregification"
}

configure<UserBaseExtension> {
    version = forgeVersion
    mappings = config["mcp.version"] as String
    runDir = "run"
    isUseDepAts = true
    replace("@VERSION@", modVersion)
    replaceIn("GFValues.java")
}

repositories {
    maven {
        name = "JEI"
        setUrl("http://dvs1.progwml6.com/files/maven/")
    }
    maven {
        name = "CraftTweaker"
        setUrl("https://maven.blamejared.com/")
    }
    maven {
        name = "Forestry"
        setUrl("http://maven.ic2.player.to")
    }
    maven {
        name = "CurseForge"
        setUrl("https://minecraft.curseforge.com/api/maven")
    }
    maven {
        name = "CCL, CASM"
        setUrl("http://chickenbones.net/maven/")
    }
    maven {
        name = "CTM"
        setUrl("https://maven.tterrag.com/")
    }
    maven {
        name = "OpenComputers"
        setUrl("https://maven.cil.li/")
    }
    maven {
        name = "McJtyLib, XNet"
        setUrl("http://maven.k-4u.nl")
    }
    // for forgelin
    maven {
        name = "Forgelin"
        setUrl("http://maven.shadowfacts.net/")
    }
}

dependencies {
    "deobfCompile"("codechicken-lib-1-8:CodeChickenLib-$mcVersion:${config["ccl.version"]}:universal")
    "deobfCompile"("codechicken:ChickenASM:$shortVersion-${config["chickenasm.version"]}")
    "deobfCompile"("mezz.jei:jei_$mcVersion:${config["jei.version"]}")
    "deobfCompile"("mcjty.theoneprobe:TheOneProbe-$shortVersion:$shortVersion-${config["top.version"]}")
    "deobfCompile"("CraftTweaker2:CraftTweaker2-MC$strippedVersion-Main:${config["crafttweaker.version"]}")
    "deobfCompile"("team.chisel.ctm:CTM:MC$mcVersion-${config["ctm.version"]}")

    "deobfCompile"("net.sengir.forestry:forestry_$mcVersion:${config["forestry.version"]}")
    "deobfCompile"("slimeknights.mantle:Mantle:$shortVersion-${config["mantle.version"]}")
    "deobfCompile"("slimeknights:TConstruct:$mcVersion-${config["ticon.version"]}")
    "deobfCompile"("com.github.mcjty:xnet:$shortVersion-${config["xnet.version"]}")
    "deobfCompile"("com.github.mcjty:mcjtylib:$shortVersion-${config["mcjtylib.version"]}")
    "deobfCompile"("li.cil.oc:OpenComputers:MC$mcVersion-${config["oc.version"]}")
    "deobfCompile"("binnie:binnie-mods-$mcVersion:${config["binnie.version"]}")
    "deobfCompile"("exnihilocreatio:exnihilocreatio:$mcVersion-${config["exnihilo.version"]}") {
        isTransitive = false
    }

    "compile"(files("gregtech-1.12.2-2.0.0.1182-alpha-dev.jar"))
    "compile"(files("libs/Cucumber-1.12.2-1.1.3.jar"))
    "compile"(files("libs/MysticalAgriculture-1.12.2-1.7.5.jar"))
    "compile"(files("libs/MysticalAgradditions-1.12.2-1.3.2.jar"))
    "compile"("net.shadowfacts:Forgelin:${config["forgelin.version"]}")
}

val processResources: ProcessResources by tasks
val sourceSets: SourceSetContainer = the<JavaPluginConvention>().sourceSets

processResources.apply {
    inputs.property("version", modVersion)
    inputs.property("mcversion", forgeVersion)

    from(sourceSets["main"].resources.srcDirs) {
        include("mcmod.info")
        expand(mapOf("version" to modVersion, "mcversion" to forgeVersion))
    }

    from(sourceSets["main"].resources.srcDirs) {
        exclude("mcmod.info")
    }
}