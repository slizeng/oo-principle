package cc.oobootcamp.length;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthTest {

    @Test
    void should_return_expected_values_when_longer_length_compares_to_the_shorter_one() {
        Length theLongerLength = new Length(3);
        Length theShorterLength = new Length(2);

        assertTrue(theLongerLength.isLongerThan(theShorterLength));
        assertFalse(theLongerLength.isEqualTo(theShorterLength));
        assertFalse(theLongerLength.isShorterThan(theShorterLength));
    }

    @Test
    void should_return_expected_values_when_the_shorter_length_compares_to_the_longer_one() {
        Length theLongerLength = new Length(3);
        Length theShorterLength = new Length(2);

        assertTrue(theShorterLength.isShorterThan(theLongerLength));
        assertFalse(theShorterLength.isLongerThan(theLongerLength));
        assertFalse(theShorterLength.isEqualTo(theLongerLength));
    }

    @Test
    void should_return_expected_values_when_compare_two_lengths_with_same_value() {
        Length length = new Length(2);

        assertTrue(length.isEqualTo(length));
        assertFalse(length.isShorterThan(length));
        assertFalse(length.isLongerThan(length));

        Length secondLength = new Length(2);

        assertTrue(length.isEqualTo(secondLength));
    }
}