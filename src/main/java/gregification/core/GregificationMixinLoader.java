package gregification.core;

import com.google.common.reflect.ClassPath;
import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;

import java.io.IOException;

// TODO update to new annotations?
@MixinLoader
@SuppressWarnings("UnstableApiUsage")
public class GregificationMixinLoader {

    public GregificationMixinLoader() {
        try {
            ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
            for (ClassPath.ClassInfo info : classPath.getTopLevelClassesRecursive("gregification.modules")) {
                String className = info.getName();
                if (className.matches("(.*?)MixinLoader") && IMixinRegistrator.class.isAssignableFrom(info.load())) {
                    Class<?> clazz = info.load();
                    IMixinRegistrator registrator = (IMixinRegistrator) clazz.newInstance();
                    Mixins.addConfiguration(registrator.getMixinData().serialize());
                }
            }
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
