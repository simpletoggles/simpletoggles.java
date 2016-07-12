package com.thoughtworks.simpletoggles.spring.sample;

import com.thoughtworks.simpletoggles.spring.ConditionalOnToggleDisabled;
import com.thoughtworks.simpletoggles.spring.ConditionalOnToggleEnabled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnToggleDisabled("FOO")
public class ToggledOffByFoo {

}
