package mod.loader;

import com.google.common.collect.Sets;
import mod.BaseMod;
import toolbox.Globals;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ModManager extends AbstractClassLoader<BaseMod> implements Globals {
    //ensures a single instance
    private static final ModManager INSTANCE = new ModManager();

    public static ModManager getInstance() {
        return INSTANCE;
    }

    private final Set<Class<? extends BaseMod>> classes = Sets.newHashSet();
    private final Set<BaseMod> active = Sets.newTreeSet(Comparator.comparing(BaseMod::getModName, String.CASE_INSENSITIVE_ORDER));

    private Stream<Class<? extends BaseMod>> unloadedClasses() {
        return classes.stream()
                .filter(clazz -> active.stream().noneMatch(mod -> mod.getClass().equals(clazz)));
    }

    private Stream<Class<? extends BaseMod>> loadedClasses() {
        return classes.stream()
                .filter(clazz -> active.stream().anyMatch(mod -> mod.getClass().equals(clazz)));
    }

    public Collection<BaseMod> getMods() {
        return Collections.unmodifiableCollection(active);
    }

    public Optional<? extends BaseMod> get(final String modName) {
        return active.stream().filter(mod -> mod.getModName().equalsIgnoreCase(modName)).findFirst();
    }

    public void load(Class<? extends BaseMod> clazz) {
        unloadedClasses().filter(clazz::equals).findFirst().ifPresent(this::_load);
    }

    private void _load(Class<? extends BaseMod> clazz) {
        if (active.add(loadClass(clazz))) {
            LOGGER.info("Loaded mod " + clazz.getSimpleName());
        }
    }

    public void loadAll() {
        unloadedClasses().forEach(this::_load);
    }

    public void unload(BaseMod mod) {
        if (active.remove(mod)) {
            mod.unload();
        }
    }

    public void unloadAll() {
        active.forEach(this::unload);
    }

    public void refresh() {
        forEach(BaseMod::unload);
        forEach(BaseMod::load);
    }

    public void forEach(final Consumer<BaseMod> consumer) {
        active.forEach(consumer);
    }

    @Nullable
    @Override
    public Class<BaseMod> getInheritedClass() {
        return BaseMod.class;
    }

    @Nullable
    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return RegisterMod.class;
    }
}
