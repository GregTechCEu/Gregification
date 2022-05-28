package gregification.base;

import gregification.Gregification;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class AnnotationProcessor {

    public static void loadModules(ASMDataTable table) {

        // Root annotations
        for (ASMData data : table.getAll(Module.Root.class.getName())) {
            String className = data.getClassName();
            Class<?> clazz;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                Gregification.logger.error("Failed to load {} module, malformed annotation data", className);
                continue;
            }
            String moduleName = (String) data.getAnnotationInfo().get("name");

            Constructor<?> constructor = null;

            if (!Module.class.isAssignableFrom(clazz)) {
                Gregification.logger.error("class {} does not extend Module", className);
                continue;
            } else if (Modifier.isAbstract(clazz.getModifiers())) {
                Gregification.logger.error("class {} is abstract and cannot be initialized", className);
            }
            try {
                constructor = clazz.getConstructor();
            } catch (NoSuchMethodException e) {
                Gregification.logger.error("no no-arg constructor available for initialization on class {}", className);
            }
            if (constructor == null) {
                Gregification.logger.error("no no-arg constructor available for initialization on class {}", className);
                continue;
            }

            try {
                Module module = (Module) constructor.newInstance();
                if (module.isModuleActive()) {
                    Gregification.registerModule(module);
                    loadFieldAnnotations(clazz, moduleName);
                    Gregification.logger.info("Registered {} Module", moduleName);
                }
            } catch (ReflectiveOperationException e) {
                Gregification.logger.error("Could not initialize module from class {}", className);
            }
        }
    }

    private static void loadFieldAnnotations(Class<?> clazz, String moduleName) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Module.Log.class)) {
                if (!Logger.class.equals(field.getType())) {
                    Gregification.logger.error("Log annotation in module {} is not on field of type Logger", moduleName);
                    continue;
                }
                if (!Modifier.isStatic(field.getModifiers())) {
                    Gregification.logger.error("Log annotation in module {} is on non-static field", moduleName);
                    continue;
                }
                field.setAccessible(true);
                try {
                    field.set(null, LogManager.getLogger(moduleName));
                } catch (IllegalAccessException e) {
                    Gregification.logger.error("Could not initialize Logger in module {}", moduleName);
                }
            }
        }
    }
}
