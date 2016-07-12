package com.thoughtworks.simpletoggles.spring.sample;

import com.thoughtworks.simpletoggles.spring.ConditionalOnToggleEnabled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnToggleEnabled("FOO")
public class ToggledOnByFoo {
}
