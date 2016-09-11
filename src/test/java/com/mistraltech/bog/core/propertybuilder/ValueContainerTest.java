package com.mistraltech.bog.core.propertybuilder;

import com.mistraltech.bog.core.Builder;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static com.mistraltech.bog.core.picker.ArrayValuePicker.arrayValuePicker;
import static com.mistraltech.bog.core.picker.RoundRobinValuePicker.roundRobinValuePicker;
import static com.mistraltech.bog.core.picker.SingleValuePicker.singleValuePicker;
import static com.mistraltech.bog.core.propertybuilder.ValueContainer.valueContainer;
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

        assertThat(valueContainer.take(), is(nullValue()));
    }

    @Test
    public void canCreateWithDefaultValue() {
        valueContainer = valueContainer(randomInt);

        assertThat(valueContainer.take(), is(randomInt));
    }

    @Test
    public void canCreateWithDefaultValuePicker() {
        valueContainer = valueContainer(singleValuePicker(randomInt));

        assertThat(valueContainer.take(), is(randomInt));
    }

    @Test
    public void canCreateWithBooleanType() {
        ValueContainer<Boolean> valueContainer = valueContainer(boolean.class);

        assertThat(valueContainer.take(), is(false));
    }

    @Test
    public void canCreateWithByteType() {
        ValueContainer<Byte> valueContainer = valueContainer(byte.class);

        assertThat(valueContainer.take(), is((byte) 0));
    }

    @Test
    public void canCreateWithCharType() {
        ValueContainer<Character> valueContainer = valueContainer(char.class);

        assertThat(valueContainer.take(), is((char) 0));
    }

    @Test
    public void canCreateWithDoubleType() {
        ValueContainer<Double> valueContainer = valueContainer(double.class);

        assertThat(valueContainer.take(), is(0D));
    }

    @Test
    public void canCreateWithFloatType() {
        ValueContainer<Float> valueContainer = valueContainer(float.class);

        assertThat(valueContainer.take(), is(0F));
    }

    @Test
    public void canCreateWithIntType() {
        ValueContainer<Integer> valueContainer = valueContainer(int.class);

        assertThat(valueContainer.take(), is(0));
    }

    @Test
    public void canCreateWithLongType() {
        ValueContainer<Long> valueContainer = valueContainer(long.class);

        assertThat(valueContainer.take(), is(0L));
    }

    @Test
    public void canCreateWithShortType() {
        ValueContainer<Short> valueContainer = valueContainer(short.class);

        assertThat(valueContainer.take(), is((short) 0));
    }

    @Test
    public void takeReturnsAssignedValue() {
        valueContainer.set(randomInt);

        assertThat(valueContainer.take(), is(randomInt));
    }

    @Test
    public void takeReturnsAssignedBuilderValue() {
        valueContainer.set(new IntBuilder(randomInt));

        assertThat(valueContainer.take(), is(randomInt));
    }

    @Test
    public void takeReturnsNewAssignedBuilderValueEachTime() {
        valueContainer.set(new IntBuilder(randomInt));

        valueContainer.take();

        assertThat(valueContainer.take(), is(randomInt + 1));
    }

    @Test
    public void takeReturnsNewDefaultPickerValueEachTime() {
        valueContainer.setDefault(roundRobinValuePicker(new Integer[]{1, 2}));

        assertThat(valueContainer.take(), is(1));
        assertThat(valueContainer.take(), is(2));
    }

    @Test
    public void previewReturnsNullWhenNoAssignedValueAndNoAssignedDefaultPicker() {
        assertThat(valueContainer.preview(), is(nullValue()));
    }

    @Test
    public void previewReturnsDefaultValueWhenNoAssignedValueAndAssignedDefaultValue() {
        valueContainer.setDefault(42);

        assertThat(valueContainer.preview(), is(42));
    }

    @Test
    public void previewReturnsDefaultPickerValueWhenNoAssignedValueAndAssignedDefaultValuePicker() {
        valueContainer.setDefault(roundRobinValuePicker(new Integer[]{1, 2}));

        assertThat(valueContainer.preview(), is(1));
    }

    @Test
    public void previewReturnsSameDefaultPickerValueEachTime() {
        valueContainer.setDefault(roundRobinValuePicker(new Integer[]{1, 2}));

        valueContainer.preview();

        assertThat(valueContainer.preview(), is(1));
    }

    @Test
    public void previewReturnsNewDefaultPickerValueAfterTake() {
        valueContainer.setDefault(roundRobinValuePicker(new Integer[]{1, 2}));

        valueContainer.preview();

        valueContainer.take();

        assertThat(valueContainer.preview(), is(2));
    }

    @Test
    public void takeReturnsSameValueAsPreview() {
        valueContainer.setDefault(arrayValuePicker(new Integer[]{1, 2, 3, 4, 5}));

        Integer previewValue = valueContainer.preview();

        assertThat(valueContainer.take(), is(previewValue));
    }

    @Test
    public void previewReturnsAssignedValueAfterSet() {
        valueContainer.setDefault(roundRobinValuePicker(new Integer[]{1, 2}));

        valueContainer.preview();

        valueContainer.set(3);

        assertThat(valueContainer.preview(), is(3));
    }

    @Test
    public void previewReturnsNewDefaultValueAfterSetDefault() {
        valueContainer.setDefault(1);

        valueContainer.preview();

        valueContainer.setDefault(2);

        assertThat(valueContainer.preview(), is(2));
    }

    @Test
    public void previewReturnsNewDefaultPickerValueAfterSetDefault() {
        valueContainer.setDefault(singleValuePicker(1));

        valueContainer.preview();

        valueContainer.setDefault(singleValuePicker(2));

        assertThat(valueContainer.preview(), is(2));
    }

    @Test
    public void setDefaultWithValueSetsDefaultToValue() {
        valueContainer.setDefault(42);

        assertThat(valueContainer.take(), is(42));
    }

    @Test
    public void setDefaultWithValueReturnsThis() {
        assertThat(valueContainer.setDefault(42), is(sameInstance(valueContainer)));
    }

    @Test
    public void setDefaultWithValuePickerSetsDefaultToValuePicker() {
        valueContainer.setDefault(singleValuePicker(42));

        assertThat(valueContainer.take(), is(42));
    }

    @Test
    public void setDefaultWithValuePickerReturnsThis() {
        assertThat(valueContainer.setDefault(singleValuePicker(42)), is(sameInstance(valueContainer)));
    }

    @Test
    public void setDefaultNullSetsDefaultToNull() {
        valueContainer.setDefault(42);
        valueContainer.setDefaultNull();

        assertThat(valueContainer.take(), is(nullValue()));
    }

    @Test
    public void setDefaultNullReturnsThis() {
        assertThat(valueContainer.setDefaultNull(), is(sameInstance(valueContainer)));
    }

    @Test
    public void hasValueReturnsTrueWhenValueAssigned() {
        valueContainer.set(42);

        assertTrue("before take()", valueContainer.hasValue());

        valueContainer.take();

        assertTrue("after take()", valueContainer.hasValue());
    }

    @Test
    public void hasValueReturnsTrueWhenValueBuilderAssigned() {
        valueContainer.set(new IntBuilder(randomInt));

        assertTrue("before take()", valueContainer.hasValue());

        valueContainer.take();

        assertTrue("after take()", valueContainer.hasValue());
    }

    @Test
    public void hasValueReturnsFalseWhenNoValueAssigned() {
        assertFalse("before take()", valueContainer.hasValue());

        valueContainer.take();

        assertFalse("after take()", valueContainer.hasValue());
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