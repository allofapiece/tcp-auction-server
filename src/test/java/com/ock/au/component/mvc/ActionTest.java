package com.ock.au.component.mvc;

import com.ock.au.component.Action;
import com.ock.au.component.Parameter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ActionTest {

    @Test
    void getParameterNames() {
        Parameter component1 = new Parameter("name");
        Parameter component2 = new Parameter("anotherName");

        Action action = new Action();
        action.addParameter(component1);
        action.addParameter(component2);

        assertNotNull(action.getParameterNames());
        assertEquals(2, action.getParameterNames().size());
        assertEquals("name", action.getParameterNames().get(0));
        assertEquals("anotherName", action.getParameterNames().get(1));
    }
}