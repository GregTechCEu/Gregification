package gregification.core;

import com.google.common.reflect.ClassPath;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Name("Gregification")
@MCVersion(ForgeVersion.mcVersion)
@SortingIndex(1002)
@SuppressWarnings("UnstableApiUsage")
public class GregificationLoadingPlugin implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        List<String> transformers = new ArrayList<>();
        try {
            ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
            for (ClassPath.ClassInfo info : classPath.getTopLevelClassesRecursive("gregification.modules")) {
                String className = info.getName();
                if (className.matches("(.*?)Transformer") && IClassTransformer.class.isAssignableFrom(info.load())) {
                    transformers.add(className);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load Gregification ASM Transformers!! Bad things may be about to happen");
        }
        return transformers.toArray(new String[0]);
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> map) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
