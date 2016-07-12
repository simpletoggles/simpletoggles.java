package com.thoughtworks.simpletoggles;

import java.util.List;

/**
 * Created by Thoughtworker on 7/11/16.
 */
public class ToggleChecker {
    private final List<String> enabledToggles;

    public ToggleChecker(List<String> enabledToggles) {
        this.enabledToggles = enabledToggles;
    }

    public boolean isEnabled(String toggleName) {
        return enabledToggles.contains(toggleName);
    }

    public List<String> getEnabledToggles() {
        return enabledToggles;
    }
}
