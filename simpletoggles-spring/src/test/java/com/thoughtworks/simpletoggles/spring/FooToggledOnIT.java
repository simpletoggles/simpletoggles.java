package com.thoughtworks.simpletoggles.spring;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ToggledOffByFoo.class, ToggledOnByFoo.class})
public class FooToggledOnIT {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testComponentConditionalOnFooBeingOnIsAvailable() {
        assertNotNull(applicationContext.getBean("toggledOnByFoo"));
    }

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void testComponentConditionalOnFooBeingOffIsNotAvailable() {
        applicationContext.getBean("toggledOffByFoo");
    }
}
