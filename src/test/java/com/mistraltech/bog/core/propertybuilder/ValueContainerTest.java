package com.mistraltech.bog.core.propertybuilder;

import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static com.mistraltech.bog.core.picker.ArrayRoundRobinValuePicker.arrayRoundRobinValuePicker;
import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ValueContainerTest {

    private static Random random = new Random();

    private ValueContainer<Integer> valueContainer;

    private Integer randomInt;

    @Before
    public void setUp() {
        valueContainer = new ValueContainer<>();
        randomInt = random.nextInt();
    }

    @Test
    public void canCreateWithoutDefault() {
        valueContainer = new ValueContainer<>();

        assertThat(valueContainer.value(), is(nullValue()));
    }

    @Test
    public void canCreateWithDefaultValue() {
        valueContainer = new ValueContainer<>(randomInt);

        assertThat(valueContainer.value(), is(randomInt));
    }

    @Test
    public void canCreateWithDefaultValuePicker() {
        valueContainer = new ValueContainer<>(singleValuePicker(randomInt));

        assertThat(valueContainer.value(), is(randomInt));
    }

    @Test
    public void valueReturnsFalseForPrimitiveBooleanTypeWithoutDefault() {
        ValueContainer<Boolean> valueContainer = new ValueContainer<>(boolean.class);

        assertThat(valueContainer.value(), is(false));
    }

    @Test
    public void valueReturnsFalseForPrimitiveByteTypeWithoutDefault() {
        ValueContainer<Byte> valueContainer = new ValueContainer<>(byte.class);

        assertThat(valueContainer.value(), is((byte) 0));
    }

    @Test
    public void valueReturnsFalseForPrimitiveCharTypeWithoutDefault() {
        ValueContainer<Character> valueContainer = new ValueContainer<>(char.class);

        assertThat(valueContainer.value(), is((char) 0));
    }

    @Test
    public void valueReturnsFalseForPrimitiveDoubleTypeWithoutDefault() {
        ValueContainer<Double> valueContainer = new ValueContainer<>(double.class);

        assertThat(valueContainer.value(), is(0D));
    }

    @Test
    public void valueReturnsFalseForPrimitiveFloatTypeWithoutDefault() {
        ValueContainer<Float> valueContainer = new ValueContainer<>(float.class);

        assertThat(valueContainer.value(), is(0F));
    }

    @Test
    public void valueReturnsFalseForPrimitiveIntTypeWithoutDefault() {
        ValueContainer<Integer> valueContainer = new ValueContainer<>(int.class);

        assertThat(valueContainer.value(), is(0));
    }

    @Test
    public void valueReturnsFalseForPrimitiveLongTypeWithoutDefault() {
        ValueContainer<Long> valueContainer = new ValueContainer<>(long.class);

        assertThat(valueContainer.value(), is(0L));
    }

    @Test
    public void valueReturnsFalseForPrimitiveShortTypeWithoutDefault() {
        ValueContainer<Short> valueContainer = new ValueContainer<>(short.class);

        assertThat(valueContainer.value(), is((short) 0));
    }

    @Test
    public void valueReturnsAssignedValue() {
        valueContainer.set(randomInt);

        assertThat(valueContainer.value(), is(randomInt));
    }

    @Test
    public void valueReturnsAssignedBuilderValue() {
        valueContainer.set(new IntBuilder(randomInt));

        assertThat(valueContainer.value(), is(randomInt));
    }

    @Test
    public void valueReturnsSameAssignedBuilderValueEachTime() {
        valueContainer.set(new IntBuilder(randomInt));

        valueContainer.value();

        assertThat(valueContainer.value(), is(randomInt));
    }

    @Test
    public void valueReturnsNewAssignedBuilderValueAfterReset() {
        valueContainer.set(new IntBuilder(randomInt));

        valueContainer.value();

        valueContainer.reset();

        assertThat(valueContainer.value(), is(randomInt + 1));
    }

    @Test
    public void valueReturnsSameDefaultPickerValueEachTime() {
        valueContainer.setDefault(arrayRoundRobinValuePicker(new Integer[]{1, 2}));

        assertThat(valueContainer.value(), is(1));
        assertThat(valueContainer.value(), is(1));
    }

    @Test
    public void valueReturnsNewDefaultPickerValueAfterReset() {
        valueContainer.setDefault(arrayRoundRobinValuePicker(new Integer[]{1, 2}));

        valueContainer.value(); // 1

        valueContainer.reset();

        assertThat(valueContainer.value(), is(2));
    }

    @Test
    public void valueReturnsDefaultValueWhenNoAssignedValueAndAssignedDefaultValue() {
        valueContainer.setDefault(42);

        assertThat(valueContainer.value(), is(42));
    }

    @Test
    public void valueReturnsDefaultPickerValueWhenNoAssignedValueAndAssignedDefaultValuePicker() {
        valueContainer.setDefault(arrayRoundRobinValuePicker(new Integer[]{1, 2}));

        assertThat(valueContainer.value(), is(1));
    }

    @Test
    public void valueReturnsAssignedValueAfterSet() {
        valueContainer.setDefault(arrayRoundRobinValuePicker(new Integer[]{1, 2}));

        valueContainer.value();

        valueContainer.set(3);

        assertThat(valueContainer.value(), is(3));
    }

    @Test
    public void valueReturnsNewDefaultValueAfterSetDefault() {
        valueContainer.setDefault(1);

        valueContainer.value();

        valueContainer.setDefault(2);

        assertThat(valueContainer.value(), is(2));
    }

    @Test
    public void valueReturnsNewDefaultPickerValueAfterSetDefault() {
        valueContainer.setDefault(singleValuePicker(1));

        valueContainer.value();

        valueContainer.setDefault(singleValuePicker(2));

        assertThat(valueContainer.value(), is(2));
    }

    @Test
    public void hasValueReturnsTrueWhenValueAssigned() {
        valueContainer.set(42);

        assertTrue("before value()", valueContainer.isAssigned());

        valueContainer.value();

        assertTrue("after value()", valueContainer.isAssigned());
    }

    @Test
    public void hasValueReturnsTrueWhenValueBuilderAssigned() {
        valueContainer.set(new IntBuilder(randomInt));

        assertTrue("before value()", valueContainer.isAssigned());

        valueContainer.value();

        assertTrue("after value()", valueContainer.isAssigned());
    }

    @Test
    public void resetDoesNotUnassignAssignedValue() {
        valueContainer.set(42);

        valueContainer.reset();

        assertThat(valueContainer.value(), is(42));
        assertTrue(valueContainer.isAssigned());
    }

    @Test
    public void hasValueReturnsFalseWhenNoValueAssigned() {
        assertFalse(valueContainer.isAssigned());
    }

    @Test
    public void gettingValueDoesNotSetHasValue() {
        valueContainer.value();

        assertFalse(valueContainer.isAssigned());
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