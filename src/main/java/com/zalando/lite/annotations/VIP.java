package com.zalando.lite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a customer has VIP status in the ZalandoLite system.
 * <p>
 * This marker annotation is used to label specific fields or classes
 * related to privileged or high-priority customers.
 *
 * <p>It can be detected at runtime using Java Reflection to apply
 * special logic like discounts, priority handling, or access control.
 *
 * <p><b>Usage Example:</b></p>
 * <pre>{@code
 * public class Customer {
 *     @VIP
 *     private boolean isVip;
 * }
 * }</pre>
 *
 * @author Java Mentor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface VIP {
    // Marker annotation: no members or parameters defined.
    // Used only for runtime "marking" via reflection.
}
