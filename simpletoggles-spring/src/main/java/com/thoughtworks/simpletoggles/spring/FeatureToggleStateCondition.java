package com.thoughtworks.simpletoggles.spring;

import com.thoughtworks.simpletoggles.SimpleToggles;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class FeatureToggleStateCondition implements ConfigurationCondition {

    public static final String ENABLED_ANNOTATION = ConditionalOnToggleEnabled.class.getName();
    public static final String DISABLED_ANNOTATION = ConditionalOnToggleDisabled.class.getName();

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // TODO: Check for illegal state of being conditional on the same toggle being ON & OFF
        if (metadata.isAnnotated(ENABLED_ANNOTATION) && metadata.isAnnotated(DISABLED_ANNOTATION)) {
            String conditionalOnEnabledValue = (String) metadata.getAnnotationAttributes(ENABLED_ANNOTATION).get("value");
            String conditionalOnDisabledValue = (String) metadata.getAnnotationAttributes(DISABLED_ANNOTATION).get("value");
            if (conditionalOnEnabledValue.equals(conditionalOnDisabledValue)) {
                throw new IllegalStateException("You cannot be conditional on the feature " + conditionalOnEnabledValue + " being both enabled and disabled");
            }
        }

        if (metadata.isAnnotated(ENABLED_ANNOTATION)) {
            return toggleStateMatches(true, context, metadata, ENABLED_ANNOTATION);
        } else if (metadata.isAnnotated(DISABLED_ANNOTATION)) {
            return toggleStateMatches(false, context, metadata, DISABLED_ANNOTATION);
        } else {
            throw new IllegalStateException("FeatureToggleStateCondition called without proper annotation");
        }
    }

    private boolean toggleStateMatches(boolean stateToMatch, ConditionContext context, AnnotatedTypeMetadata metadata, String annotationClassName) {
        String toggleName = (String) metadata.getAnnotationAttributes(annotationClassName).get("value");
        return stateToMatch == SimpleToggles.isEnabled(toggleName);
    }

    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.PARSE_CONFIGURATION;
    }
}
