package jakarta.enterprise.lang.model.declarations;

import jakarta.enterprise.lang.model.AnnotationTarget;
import jakarta.enterprise.lang.model.types.Type;

/**
 * Declarations directly correspond to an element of a program source code.
 * Declarations are:
 *
 * <ul>
 * <li>{@linkplain PackageInfo packages}</li>
 * <li>{@linkplain ClassInfo classes}, including interfaces, enums, and annotations</li>
 * <li>{@linkplain FieldInfo fields}</li>
 * <li>{@linkplain MethodInfo methods}, including constructors</li>
 * <li>{@linkplain ParameterInfo method parameters}, including constructor parameters</li>
 * </ul>
 *
 * @since 4.0
 */
public interface DeclarationInfo extends AnnotationTarget {
    // TODO reevaluate the is*/as*/kind() approach (everywhere!); maybe type checks and casts are better, maybe
    //  something completely different is even better

    @Override
    default boolean isDeclaration() {
        return true;
    }

    @Override
    default boolean isType() {
        return false;
    }

    @Override
    default DeclarationInfo asDeclaration() {
        return this;
    }

    @Override
    default Type asType() {
        throw new IllegalStateException("Not a type");
    }

    enum Kind {
        PACKAGE,
        CLASS,
        METHOD,
        PARAMETER,
        FIELD,
    }

    /**
     * Returns the {@linkplain Kind kind} of this declaration.
     *
     * @return the kind of this declaration
     */
    Kind kind();

    /**
     * Returns whether this declaration is a {@linkplain PackageInfo package}.
     *
     * @return {@code true} if this is a package, {@code false} otherwise
     */
    default boolean isPackage() {
        return kind() == Kind.PACKAGE;
    }

    /**
     * Returns whether this declaration is a {@linkplain ClassInfo class}.
     *
     * @return {@code true} if this is a class, {@code false} otherwise
     */
    default boolean isClass() {
        return kind() == Kind.CLASS;
    }

    /**
     * Returns whether this declaration is a {@linkplain MethodInfo method}.
     *
     * @return {@code true} if this is a method, {@code false} otherwise
     */
    default boolean isMethod() {
        return kind() == Kind.METHOD;
    }

    /**
     * Returns whether this declaration is a {@linkplain ParameterInfo method parameter}.
     *
     * @return {@code true} if this is a parameter, {@code false} otherwise
     */
    default boolean isParameter() {
        return kind() == Kind.PARAMETER;
    }

    /**
     * Returns whether this declaration is a {@linkplain FieldInfo field}.
     *
     * @return {@code true} if this is a field, {@code false} otherwise
     */
    default boolean isField() {
        return kind() == Kind.FIELD;
    }

    /**
     * Returns this declaration as a {@linkplain PackageInfo package}.
     *
     * @return this package, never {@code null}
     * @throws IllegalStateException if {@link #isPackage()} returns {@code false}
     */
    default PackageInfo asPackage() {
        throw new IllegalStateException("Not a package");
    }

    /**
     * Returns this declaration as a {@linkplain ClassInfo class}.
     *
     * @return this class, never {@code null}
     * @throws IllegalStateException if {@link #isClass()} returns {@code false}
     */
    default ClassInfo asClass() {
        throw new IllegalStateException("Not a class");
    }

    /**
     * Returns this declaration as a {@linkplain MethodInfo method}.
     *
     * @return this method, never {@code null}
     * @throws IllegalStateException if {@link #isMethod()} returns {@code false}
     */
    default MethodInfo asMethod() {
        throw new IllegalStateException("Not a method");
    }

    /**
     * Returns this declaration as a {@linkplain ParameterInfo method parameter}.
     *
     * @return this parameter, never {@code null}
     * @throws IllegalStateException if {@link #isParameter()} returns {@code false}
     */
    default ParameterInfo asParameter() {
        throw new IllegalStateException("Not a parameter");
    }

    /**
     * Returns this declaration as a {@linkplain FieldInfo field}.
     *
     * @return this field, never {@code null}
     * @throws IllegalStateException if {@link #isField()} returns {@code false}
     */
    default FieldInfo asField() {
        throw new IllegalStateException("Not a field");
    }
}