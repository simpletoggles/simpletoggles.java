package com.thoughtworks.simpletoggles.junit;

import com.thoughtworks.simpletoggles.SimpleToggles;
import org.junit.Rule;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class SimpleTogglesRuleTest {
    @Rule
    public SimpleTogglesRule simpleTogglesRule = new SimpleTogglesRule();

    @Test
    public void usesToggleStatesFromTheRule() {
        Collections.addAll(simpleTogglesRule.enabledToggles, "FOO", "BAR");
        assertTrue(SimpleToggles.isEnabled("FOO"));
        assertTrue(SimpleToggles.isEnabled("BAR"));
    }
}