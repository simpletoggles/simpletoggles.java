package com.thoughtworks.simpletoggles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnvironmentVariablesToggleCheckerBuilder implements ToggleCheckerBuilder {
    public ToggleChecker build() {
        List<String> enabledToggles = new ArrayList<String>();
        String enabledPropertiesCommaSeparatedString = System.getenv("ENABLED_TOGGLES");

        if (enabledPropertiesCommaSeparatedString != null) {
            Collections.addAll(enabledToggles, enabledPropertiesCommaSeparatedString.split(","));
        }

        return new ToggleChecker(enabledToggles);
    }
}
