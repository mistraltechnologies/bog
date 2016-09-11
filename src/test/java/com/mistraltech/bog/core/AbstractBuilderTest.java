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
        IntegerBox box = new IntegerBoxBuilder().build();

        assertTrue(box.isSet());
    }

    @Test
    public void subsequentCallToBuildReturnsNewlyConstructedInstance() {
        IntegerBoxBuilder builder = new IntegerBoxBuilder();
        IntegerBox box1 = builder.build();
        IntegerBox box2 = builder.build();

        assertThat(box1, is(not(sameInstance(box2))));
    }

    @Test
    public void createReturnsConstructedButUnassignedInstance() {
        IntegerBox box = new IntegerBoxBuilder().create();

        assertFalse(box.isSet());
    }

    @Test(expected = IllegalStateException.class)
    public void cannotUpdateBeforeCreate() {
        IntegerBoxBuilder builder = new IntegerBoxBuilder();

        builder.update();
    }

    @Test
    public void updateReturnsAssignedInstance() {
        IntegerBoxBuilder builder = new IntegerBoxBuilder();
        IntegerBox box1 = builder.create();

        IntegerBox box2 = builder.update();

        assertTrue(box1.isSet());
        assertThat(box1, is(sameInstance(box2)));
    }

    @Test
    public void updateCallsPostUpdate() {
        IntegerBoxBuilder builder = new IntegerBoxBuilder();
        builder.create();

        int value = builder.update().getValue();

        assertThat(builder.count, is(value + 1));
    }


    private static class IntegerBox {
        private Integer value = null;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isSet() {
            return value != null;
        }
    }

    private static class IntegerBoxBuilder extends AbstractBuilder<IntegerBox> {
        private int count = 0;

        @Override
        protected IntegerBox construct() {
            return new IntegerBox();
        }

        @Override
        protected void assign(IntegerBox instance) {
            instance.setValue(count);
        }

        @Override
        protected void postUpdate() {
            count++;
        }
    }
}