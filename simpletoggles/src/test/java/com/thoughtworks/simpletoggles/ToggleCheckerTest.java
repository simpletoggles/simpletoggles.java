package com.thoughtworks.simpletoggles;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ToggleCheckerTest {

    @Rule
    public final EnvironmentVariables environmentVariables
            = new EnvironmentVariables();

    @Test
    public void testIsEnabledReturnsFalseWhenNoTogglesAreEnabled() {
        assertFalse(SimpleToggles.isEnabled("FOO"));
    }

    @Before
    public void resetSimpleToggles() {
        SimpleToggles.reset();
    }

    @Test
    public void isEnabledReturnsTrueForASingelToggleThatIsEnabled() {
        environmentVariables.set("ENABLED_TOGGLES", "FOO");
        assertTrue(SimpleToggles.isEnabled("FOO"));
        assertFalse(SimpleToggles.isEnabled("BAR"));
    }

    @Test
    public void isEnabledReturnsTrueForAnyEnabledToggle() {
        environmentVariables.set("ENABLED_TOGGLES", "FOO,BAR");
        assertTrue(SimpleToggles.isEnabled("FOO"));
        assertTrue(SimpleToggles.isEnabled("BAR"));
        assertFalse(SimpleToggles.isEnabled("BAZ"));
        // Partial match
        assertFalse(SimpleToggles.isEnabled("BA"));
    }

    @Test
    public void enabledTogglesReturnsAnEmptyListIfNoTogglesAreEnabled() {
        assertThat(SimpleToggles.enabledToggles(), is(empty()));
    }

    @Test
    public void enabledTogglesReturnsTheListOfEnabledToggles() {
        environmentVariables.set("ENABLED_TOGGLES", "FOO,BAR,MAX");
        assertThat(SimpleToggles.enabledToggles(), containsInAnyOrder("FOO", "BAR", "MAX"));
    }
}