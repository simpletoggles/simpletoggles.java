package com.thoughtworks.simpletoggles;

import java.util.List;

public class SimpleToggles
{
    public static ToggleCheckerBuilder toggleCheckerBuilder = new EnvironmentVariablesToggleCheckerBuilder();
    private static ToggleChecker toggleChecker;

    protected SimpleToggles() {

    }

    public static void reset() {
        toggleChecker = null;
    }

    public static boolean isEnabled(String toggleName) {
        return getToggleChecker().isEnabled(toggleName);
    }

    public static List<String> enabledToggles() {
        return getToggleChecker().getEnabledToggles();
    }

    public static ToggleChecker getToggleChecker() {
        if (toggleChecker == null) {
            toggleChecker = toggleCheckerBuilder.build();
        }
        return toggleChecker;
    }
}
