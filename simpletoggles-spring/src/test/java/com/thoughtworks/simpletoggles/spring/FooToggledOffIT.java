package com.thoughtworks.simpletoggles.spring;

import com.thoughtworks.simpletoggles.SimpleToggles;
import com.thoughtworks.simpletoggles.spring.sample.ToggledOffByFoo;
import com.thoughtworks.simpletoggles.spring.sample.ToggledOnByFoo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ToggledOffByFoo.class, ToggledOnByFoo.class})
public class FooToggledOffIT {

    @Autowired
    private ApplicationContext applicationContext;

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void testComponentConditionalOnFooBeingOnIsNotAvailable() {
        applicationContext.getBean("toggledOnByFoo");
    }

    @Test
    public void testComponentConditionalOnFooBeingOffIsNotAvailable() {
        assertNotNull(applicationContext.getBean("toggledOffByFoo"));
    }
}
