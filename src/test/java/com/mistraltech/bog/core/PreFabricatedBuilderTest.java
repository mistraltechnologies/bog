package com.mistraltech.bog.core;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.*;

public class PreFabricatedBuilderTest {

    @Test
    public void buildReturnsObjectPassedToFactoryMethod() {
        Object obj = new Object();

        PreFabricatedBuilder<Object> builder = PreFabricatedBuilder.preFabricated(obj);

        assertThat(builder.build(), is(sameInstance(obj)));
    }
}