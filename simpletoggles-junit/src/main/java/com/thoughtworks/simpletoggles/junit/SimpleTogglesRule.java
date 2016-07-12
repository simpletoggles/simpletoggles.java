package com.thoughtworks.simpletoggles.junit;

import com.thoughtworks.simpletoggles.SimpleToggles;
import com.thoughtworks.simpletoggles.ToggleChecker;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;

public class SimpleTogglesRule implements TestRule {
    public List<String> enabledToggles = new ArrayList<String>();

    public SimpleTogglesRule() {
        SimpleToggles.toggleCheckerBuilder = new SimpleTogglesRuleToggleCheckerBuilder(this);
    }

    public Statement apply(Statement base, Description description) {
        return new SimpleTogglesDecoratedStatement(base, enabledToggles);
    }

    protected class SimpleTogglesDecoratedStatement extends Statement {
        private final Statement base;

        public SimpleTogglesDecoratedStatement(Statement base, List<String> enabledToggles) {
            this.base = base;
        }

        @Override
        public void evaluate() throws Throwable {
            SimpleToggles.reset();
            base.evaluate();
        }
    }

    protected class SimpleTogglesRuleToggleCheckerBuilder implements com.thoughtworks.simpletoggles.ToggleCheckerBuilder {
        private final SimpleTogglesRule simpleTogglesRule;

        public SimpleTogglesRuleToggleCheckerBuilder(SimpleTogglesRule simpleTogglesRule) {
            this.simpleTogglesRule = simpleTogglesRule;
        }

        public ToggleChecker build() {
            return new ToggleChecker(simpleTogglesRule.enabledToggles);
        }
    }
}
