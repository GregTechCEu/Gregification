package gregification.core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gregification.Gregification;
import net.minecraft.launchwrapper.Launch;
import org.lwjgl.Sys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MixinData {

    private final String pkg;
    private final String name;

    private String[] mixins;
    private String[] client;
    private String[] server;

    // Injector options
    private boolean hasInjectorOption = false;
    private Integer defaultRequire;
    private String defaultGroup;
    private String[] injectionPoints;
    private Integer maxShiftBy;

    private String target = "@env(DEFAULT)";
    private boolean required = false;

    // JSON keys
    private static final String K_PACKAGE = "package";
    private static final String K_REF_MAP = "refmap";
    private static final String K_TARGET = "target";
    private static final String K_MIN_VERSION = "minVersion";
    private static final String K_COMPATIBILITY_LEVEL = "compatibilityLevel";
    private static final String K_MIXINS = "mixins";
    private static final String K_CLIENT = "client";
    private static final String K_SERVER = "server";
    private static final String K_INJECTORS = "injectors";
    private static final String K_REQUIRED = "required";
    private static final String K_DEFAULT_REQUIRE = "defaultRequire";
    private static final String K_DEFAULT_GROUP = "defaultGroup";
    private static final String K_INJECTION_POINTS = "injectionPoints";
    private static final String K_MAX_SHIFT_BY = "maxShiftBy";

    private MixinData(String pkg, String name) {
        this.pkg = pkg;
        this.name = name;
    }

    /**
     * @return The path to the serialized JSON of this data.
     */
    public String serialize() {
        JsonObject json = new JsonObject();

        // Always needed
        json.addProperty(K_PACKAGE, pkg);
        json.addProperty(K_REF_MAP, "mixins.gregification.refmap.json");
        json.addProperty(K_TARGET, target);
        json.addProperty(K_MIN_VERSION, "0.8");
        json.addProperty(K_COMPATIBILITY_LEVEL, "JAVA_8");

        if (required) {
            json.addProperty(K_REQUIRED, true);
        }
        if (mixins != null && mixins.length != 0) {
            json.add(K_MIXINS, getJsonArray(mixins));
        }
        if (client != null && client.length != 0) {
            json.add(K_CLIENT, getJsonArray(client));
        }
        if (server != null && server.length != 0) {
            json.add(K_SERVER, getJsonArray(server));
        }

        if (hasInjectorOption) {
            JsonObject injectors = new JsonObject();
            if (defaultRequire != null) injectors.addProperty(K_DEFAULT_REQUIRE, defaultRequire);
            if (defaultGroup != null) injectors.addProperty(K_DEFAULT_GROUP, defaultGroup);
            if (injectionPoints != null && injectionPoints.length != 0) injectors.add(K_INJECTION_POINTS, getJsonArray(injectionPoints));
            if (maxShiftBy != null) injectors.addProperty(K_MAX_SHIFT_BY, maxShiftBy);
            json.add(K_INJECTORS, injectors);
        }
        return writeToResources(json);
    }

    private String writeToResources(JsonObject json) {
        String fileName = String.format("mixins.%s.json", name);
        try {
            URL url = this.getClass().getResource("/");
            File dir = new File(new URI(url.toString()));
            File f = new File(dir, fileName);
            FileWriter fw = new FileWriter(f);
            fw.write(json.toString());
            fw.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private static JsonArray getJsonArray(String[] array) {
        JsonArray jsonArray = new JsonArray();
        for (String value : array) {
            jsonArray.add(value);
        }
        return jsonArray;
    }

    public static Builder builder(String pkg, String name) {
        return new Builder(pkg, name);
    }

    public static class Builder {

        private final MixinData data;

        private Builder(String pkg, String name) {
            data = new MixinData(pkg, name);
        }

        public Builder setMixins(String... mixins) {
            data.mixins = mixins;
            return this;
        }

        public Builder setClientMixins(String... mixins) {
            data.client = mixins;
            return this;
        }

        public Builder setServerMixins(String... mixins) {
            data.server = mixins;
            return this;
        }

        public Builder setDefaultRequire(int value) {
            data.hasInjectorOption = true;
            data.defaultRequire = value;
            return this;
        }

        public Builder setDefaultGroup(String group) {
            data.hasInjectorOption = true;
            data.defaultGroup = group;
            return this;
        }

        public Builder setInjectionPoints(String... injectionPoints) {
            data.hasInjectorOption = true;
            data.injectionPoints = injectionPoints;
            return this;
        }

        public Builder setMaxShiftBy(int value) {
            data.hasInjectorOption = true;
            data.maxShiftBy = value;
            return this;
        }

        public Builder setTarget(String target) {
            data.target = target;
            return this;
        }

        public Builder setRequired() {
            data.required = true;
            return this;
        }

        public MixinData build() {
            if (data.mixins == null && data.client == null && data.server == null) {
                Gregification.logger.error("Could not create Mixin config from package {}, a mixin must be provided", data.pkg);
            }
            return data;
        }
    }
}
