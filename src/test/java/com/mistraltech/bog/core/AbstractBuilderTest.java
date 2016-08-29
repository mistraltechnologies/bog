package com.mistraltech.bog.core;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class AbstractBuilderTest {

    @Test
    public void buildReturnsFullyConstructedInstance() {
        BooleanBox bb = new BooleanBoxBuilder().build();

        assertTrue(bb.isSet());
    }

    @Test
    public void subsequentCallToBuildReturnsDifferentInstance() {
        BooleanBoxBuilder bbBuilder = new BooleanBoxBuilder();
        BooleanBox bb1 = bbBuilder.build();
        BooleanBox bb2 = bbBuilder.build();

        assertThat(bb1, is(not(sameInstance(bb2))));
    }

    @Test
    public void createReturnsConstructedButUnassignedInstance() {
        BooleanBox bb = new BooleanBoxBuilder().create();

        assertFalse(bb.isSet());
    }

    @Test(expected = IllegalStateException.class)
    public void cannotUpdateBeforeCreate() {
        BooleanBoxBuilder bbBuilder = new BooleanBoxBuilder();

        bbBuilder.update();
    }

    @Test
    public void updateReturnsAssignedInstance() {
        BooleanBoxBuilder bbBuilder = new BooleanBoxBuilder();
        BooleanBox bb1 = bbBuilder.create();

        BooleanBox bb2 = bbBuilder.update();

        assertTrue(bb1.isSet());
        assertThat(bb1, is(sameInstance(bb2)));
    }

    private static class BooleanBox {
        private boolean value = false;

        public void set() {
            value = true;
        }

        public boolean isSet() {
            return value;
        }
    }

    private static class BooleanBoxBuilder extends AbstractBuilder<BooleanBox> {

        @Override
        protected BooleanBox construct() {
            return new BooleanBox();
        }

        @Override
        protected void assign(BooleanBox instance) {
            instance.set();
        }
    }
}