package jakarta.enterprise.inject.build.compatible.spi;

import jakarta.enterprise.lang.model.AnnotationInfo;
import jakarta.enterprise.lang.model.declarations.ClassInfo;
import jakarta.enterprise.lang.model.declarations.FieldInfo;
import jakarta.enterprise.lang.model.declarations.MethodInfo;
import jakarta.enterprise.lang.model.types.Type;
import java.util.Collection;

/**
 * Beans are:
 *
 * <ul>
 * <li>managed beans</li>
 * <li>beans defined by producer methods</li>
 * <li>beans defined by producer fields</li>
 * <li>synthetic beans</li>
 * </ul>
 *
 * Managed beans are also known as class-based beans, while beans defined by producer methods
 * and producer fields are together also known as producer-based beans.
 * <p>
 * Class-based and producer-based beans directly correspond to a declaration in program source code.
 * Synthetic beans don't and are instead defined through other mechanisms, such as
 * {@linkplain BuildCompatibleExtension extensions}.
 *
 * @since 4.0
 */
public interface BeanInfo {
    /**
     * Returns the {@linkplain ScopeInfo scope} of this bean.
     *
     * @return the {@linkplain ScopeInfo scope} of this bean, never {@code null}
     */
    ScopeInfo scope();

    /**
     * Returns a collection of all {@linkplain Type types} of this bean.
     *
     * @return immutable collection of bean types, never {@code null}
     */
    Collection<Type> types();

    /**
     * Returns a collection of this bean's qualifiers, represented as {@link AnnotationInfo}.
     *
     * @return immutable collection of qualifiers, never {@code null}
     */
    // TODO method(s) for getting AnnotationInfo for given qualifier class?
    Collection<AnnotationInfo> qualifiers();

    /**
     * Returns the class that declares this bean.
     * In case of a managed bean, also known as class-based bean, that is the bean class directly.
     * In case of a producer method or field, that is the class that declares the producer method or field.
     * Returns {@code null} if this bean is synthetic.
     *
     * @return {@link ClassInfo} for the class that declares this bean, or {@code null} if this bean is synthetic
     */
    ClassInfo declaringClass();

    /**
     * Returns whether this bean is a managed bean, also known as class-based bean.
     *
     * @return whether this bean is a managed bean
     */
    boolean isClassBean();

    /**
     * Returns whether this bean is defined by a producer method.
     *
     * @return whether this bean is defined by a producer method
     */
    boolean isProducerMethod();

    /**
     * Returns whether this bean is defined by a producer field.
     *
     * @return whether this bean is defined by a producer field
     */
    boolean isProducerField();

    /**
     * Returns whether this bean is synthetic. In other words, whether this bean
     * doesn't correspond to a declaration in program source code and was created
     * through other means (e.g. using an extension).
     *
     * @return whether this bean is synthetic
     */
    boolean isSynthetic();

    /**
     * Returns the producer {@linkplain MethodInfo method} that defines this bean.
     * Returns {@code null} if this bean isn't defined by a producer method.
     *
     * @return producer method that defines this bean, or {@code null} if this bean isn't defined by a producer method
     */
    MethodInfo producerMethod();

    /**
     * Returns the producer {@linkplain FieldInfo field} that defines this bean.
     * Returns {@code null} if this bean isn't defined by a producer field.
     *
     * @return producer field that defines this bean, or {@code null} if this bean isn't defined by a producer field
     */
    FieldInfo producerField();

    /**
     * Returns whether this bean is an {@linkplain jakarta.enterprise.inject.Alternative alternative}.
     *
     * @return whether this bean is an {@linkplain jakarta.enterprise.inject.Alternative alternative}
     */
    boolean isAlternative();

    /**
     * Returns the {@linkplain jakarta.annotation.Priority priority} of this alternative bean.
     * If this bean is not an alternative, the return value is undefined.
     *
     * @return the priority of this alternative bean
     * @see #isAlternative()
     */
    // TODO maybe specify that if this bean is not an alternative, this returns 0?
    int priority();

    /**
     * Returns the bean name of this bean. A bean name is usually defined
     * using the {@link jakarta.inject.Named @Named} annotation.
     * Returns {@code null} if the bean doesn't have a name.
     *
     * @return the bean name, or {@code null} if the bean doesn't have a name
     */
    String getName();

    /**
     * Returns the {@linkplain DisposerInfo disposer} method of this producer-based bean.
     * Returns {@code null} if this bean is not a defined by a producer method or a producer field,
     * or if this producer-based bean doesn't have a corresponding disposer method.
     *
     * @return the {@linkplain DisposerInfo disposer}, or {@code null} if this bean doesn't have a disposer
     */
    DisposerInfo disposer();

    /**
     * Returns a collection of this bean's {@linkplain StereotypeInfo stereotypes}.
     *
     * @return immutable collection of stereotypes, never {@code null}
     */
    Collection<StereotypeInfo> stereotypes();

    // TODO interceptors?

    /**
     * Returns a collection of this bean's {@linkplain InjectionPointInfo injection points}.
     *
     * @return immutable collection of injection points, never {@code null}
     */
    Collection<InjectionPointInfo> injectionPoints();
}