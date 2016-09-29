package com.mistraltech.bog.core.propertybuilder;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static com.mistraltech.bog.core.picker.RoundRobinValuePicker.roundRobinValuePicker;
import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static com.mistraltech.bog.core.ValueContainer.valueContainer;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ValueContainerTest {

    private static Random random = new Random();

    private ValueContainer<Integer> valueContainer;

    private Integer randomInt;

    @Before
    public void setUp() {
        valueContainer = valueContainer();
        randomInt = random.nextInt();
    }

    @Test
    public void canCreateWithoutDefault() {
        valueContainer = valueContainer();

        assertThat(valueContainer.get(), is(nullValue()));
    }

    @Test
    public void canCreateWithDefaultValue() {
        valueContainer = valueContainer(randomInt);

        assertThat(valueContainer.get(), is(randomInt));
    }

    @Test
    public void canCreateWithDefaultValuePicker() {
        valueContainer = valueContainer(singleValuePicker(randomInt));

        assertThat(valueContainer.get(), is(randomInt));
    }

    @Test
    public void getReturnsFalseForPrimitiveBooleanTypeWithoutDefault() {
        ValueContainer<Boolean> valueContainer = valueContainer(boolean.class);

        assertThat(valueContainer.get(), is(false));
    }

    @Test
    public void getReturnsFalseForPrimitiveByteTypeWithoutDefault() {
        ValueContainer<Byte> valueContainer = valueContainer(byte.class);

        assertThat(valueContainer.get(), is((byte) 0));
    }

    @Test
    public void getReturnsFalseForPrimitiveCharTypeWithoutDefault() {
        ValueContainer<Character> valueContainer = valueContainer(char.class);

        assertThat(valueContainer.get(), is((char) 0));
    }

    @Test
    public void getReturnsFalseForPrimitiveDoubleTypeWithoutDefault() {
        ValueContainer<Double> valueContainer = valueContainer(double.class);

        assertThat(valueContainer.get(), is(0D));
    }

    @Test
    public void getReturnsFalseForPrimitiveFloatTypeWithoutDefault() {
        ValueContainer<Float> valueContainer = valueContainer(float.class);

        assertThat(valueContainer.get(), is(0F));
    }

    @Test
    public void getReturnsFalseForPrimitiveIntTypeWithoutDefault() {
        ValueContainer<Integer> valueContainer = valueContainer(int.class);

        assertThat(valueContainer.get(), is(0));
    }

    @Test
    public void getReturnsFalseForPrimitiveLongTypeWithoutDefault() {
        ValueContainer<Long> valueContainer = valueContainer(long.class);

        assertThat(valueContainer.get(), is(0L));
    }

    @Test
    public void getReturnsFalseForPrimitiveShortTypeWithoutDefault() {
        ValueContainer<Short> valueContainer = valueContainer(short.class);

        assertThat(valueContainer.get(), is((short) 0));
    }

    @Test
    public void getReturnsAssignedValue() {
        valueContainer.set(randomInt);

        assertThat(valueContainer.get(), is(randomInt));
    }

    @Test
    public void getReturnsAssignedBuilderValue() {
        valueContainer.set(new IntBuilder(randomInt));

        assertThat(valueContainer.get(), is(randomInt));
    }

    @Test
    public void getReturnsSameAssignedBuilderValueEachTime() {
        valueContainer.set(new IntBuilder(randomInt));

        valueContainer.get();

        assertThat(valueContainer.get(), is(randomInt));
    }

    @Test
    public void getReturnsNewAssignedBuilderValueAfterReset() {
        valueContainer.set(new IntBuilder(randomInt));

        valueContainer.get();

        valueContainer.reset();

        assertThat(valueContainer.get(), is(randomInt + 1));
    }

    @Test
    public void getReturnsSameDefaultPickerValueEachTime() {
        valueContainer.setDefault(roundRobinValuePicker(new Integer[]{1, 2}));

        assertThat(valueContainer.get(), is(1));
        assertThat(valueContainer.get(), is(1));
    }

    @Test
    public void getReturnsNewDefaultPickerValueAfterReset() {
        valueContainer.setDefault(roundRobinValuePicker(new Integer[]{1, 2}));

        valueContainer.get(); // 1

        valueContainer.reset();

        assertThat(valueContainer.get(), is(2));
    }

    @Test
    public void getReturnsDefaultValueWhenNoAssignedValueAndAssignedDefaultValue() {
        valueContainer.setDefault(42);

        assertThat(valueContainer.get(), is(42));
    }

    @Test
    public void getReturnsDefaultPickerValueWhenNoAssignedValueAndAssignedDefaultValuePicker() {
        valueContainer.setDefault(roundRobinValuePicker(new Integer[]{1, 2}));

        assertThat(valueContainer.get(), is(1));
    }

    @Test
    public void getReturnsAssignedValueAfterSet() {
        valueContainer.setDefault(roundRobinValuePicker(new Integer[]{1, 2}));

        valueContainer.get();

        valueContainer.set(3);

        assertThat(valueContainer.get(), is(3));
    }

    @Test
    public void getReturnsNewDefaultValueAfterSetDefault() {
        valueContainer.setDefault(1);

        valueContainer.get();

        valueContainer.setDefault(2);

        assertThat(valueContainer.get(), is(2));
    }

    @Test
    public void getReturnsNewDefaultPickerValueAfterSetDefault() {
        valueContainer.setDefault(singleValuePicker(1));

        valueContainer.get();

        valueContainer.setDefault(singleValuePicker(2));

        assertThat(valueContainer.get(), is(2));
    }

    @Test
    public void setDefaultWithValueReturnsThis() {
        assertThat(valueContainer.setDefault(42), is(sameInstance(valueContainer)));
    }

    @Test
    public void setDefaultWithValuePickerReturnsThis() {
        assertThat(valueContainer.setDefault(singleValuePicker(42)), is(sameInstance(valueContainer)));
    }

    @Test
    public void hasValueReturnsTrueWhenValueAssigned() {
        valueContainer.set(42);

        assertTrue("before get()", valueContainer.hasValue());

        valueContainer.get();

        assertTrue("after get()", valueContainer.hasValue());
    }

    @Test
    public void hasValueReturnsTrueWhenValueBuilderAssigned() {
        valueContainer.set(new IntBuilder(randomInt));

        assertTrue("before get()", valueContainer.hasValue());

        valueContainer.get();

        assertTrue("after get()", valueContainer.hasValue());
    }

    @Test
    public void resetDoesNotUnassignAssignedValue() {
        valueContainer.set(42);

        valueContainer.reset();

        assertThat(valueContainer.get(), is(42));
        assertTrue(valueContainer.hasValue());
    }

    @Test
    public void hasValueReturnsFalseWhenNoValueAssigned() {
        assertFalse(valueContainer.hasValue());
    }

    @Test
    public void gettingValueDoesNotSetHasValue() {
        valueContainer.get();

        assertFalse(valueContainer.hasValue());
    }

    private static class IntBuilder implements Builder<Integer> {
        private int nextValue;

        IntBuilder(Integer start) {
            nextValue = start;
        }

        @Override
        public Integer build() {
            return nextValue++;
        }
    }
}