package com.example.deepshah.followme;


/**
 * Generic container for storing a name and a value.
 *
 * @param <ValueType>    The data type of the pair's value.
 */
public class NameValuePair<ValueType extends Comparable<ValueType>> implements Comparable<NameValuePair<ValueType>> {
    /**
     * Fields
     */
    private String name;
    private ValueType value;

    /**
     * Constructs the NameValuePair instance.
     *
     * @param _name     The pair's name
     * @param _value    The pair's value
     */
    public NameValuePair(String _name, ValueType _value) {
        name = _name;
        value = _value;
    }

    /**
     * Gets the name stored in the pair.
     *
     * @return   The pair's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the value stored in the pair.
     *
     * @return   The pair's value.
     */
    public ValueType getValue() {
        return value;
    }

    /**
     * Compares this pair to another pair.
     *
     * @return   -1 if this pair is less than the passed-in pair
     *            0 if the two pairs are equal
     *            1 if this pair is greater than the passed-in pair
     */
    public int compareTo(NameValuePair<ValueType> other) {
        return this.value.compareTo(other.value);
    }
}
