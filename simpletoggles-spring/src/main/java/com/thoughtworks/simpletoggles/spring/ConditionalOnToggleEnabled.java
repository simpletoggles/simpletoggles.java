package com.thoughtworks.simpletoggles.spring;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(FeatureToggleStateCondition.class)
public @interface ConditionalOnToggleEnabled {
    String value();
}
