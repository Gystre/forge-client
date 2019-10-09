package mod.loader;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import toolbox.Globals;

public abstract class AbstractClassLoader<E> implements Globals {

    protected AbstractClassLoader() {
    }

    /**
     * The class that must be extended
     *
     * @return class
     */
    @Nullable
    public abstract Class<E> getInheritedClass();

    /**
     * The optional annotation class that must be on top of every class
     *
     * @return null if no annotation is required
     */
    @Nullable
    public abstract Class<? extends Annotation> getAnnotationClass();

    /**
     * Initializes all the classes from ::create and returns a list of non-null instances created from
     * the provided classes
     */
    public Collection<? extends E> loadClasses(Collection<Class<? extends E>> classes) {
        return classes.stream().map(this::create).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public E loadClass(Class<? extends E> clazz) {
        return loadClasses(Collections.singleton(clazz)).stream().findFirst().orElse(null);
    }

    protected boolean valid(Class<? extends E> clazz) {
        try {
            return clazz.getDeclaredConstructor() != null;
        } catch (NoSuchMethodException e) {
            LOGGER.error("Class has no default constructor");
            return false;
        }
    }

    protected E create(Class<? extends E> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            LOGGER.error("Failed to initialize class "
                        + clazz.getSimpleName()
                        + ": "
                        + e.getClass().getSimpleName()
                        + " - "
                        + e.getMessage()
                        + " - caused by: "
                        + e.getCause());
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private Class<? extends E> wildCast(Class<?> clazz) {
        return (Class<? extends E>) clazz;
    }

    private boolean checkAnnotation(Class<?> clazz) {
        return getAnnotationClass() == null || clazz.isAnnotationPresent(getAnnotationClass());
    }

    private boolean checkInheritedClass(Class<?> clazz) {
        return getInheritedClass() == null || getInheritedClass().isAssignableFrom(clazz);
    }

    //
    //
    //

    public static LaunchClassLoader getFMLClassLoader() {
        return Launch.classLoader;
    }
}
