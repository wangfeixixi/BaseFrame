package com.wangfeixixi.base.debug.log;
/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Note: this class was written without inspecting the non-free org.json sourcecode.

/**
 * A dense indexed sequence of values. Values may be any mix of
 * { InnerJSONObject JSONObjects}, other { InnerJSONArray JSONArrays}, Strings,
 * Booleans, Integers, Longs, Doubles, {@code null} or { InnerJSONObject#NULL}.
 * Values may not be Double#isNaN() NaNs}, { Double#isInfinite()
 * infinities}, or of any type not listed here.
 *
 * <p>{@code InnerJSONArray} has the same type coercion behavior and
 * optional/mandatory accessors as { InnerJSONObject}. See that class'
 * documentation for details.
 *
 * <p><strong>Warning:</strong> this class represents null in two incompatible
 * ways: the standard Java {@code null} reference, and the sentinel value {
 * InnerJSONObject#NULL}. In particular, {@code get} fails if the requested index
 * holds the null reference, but succeeds if it holds {@code InnerJSONObject.NULL}.
 *
 * <p>Instances of this class are not thread safe. Although this class is
 * nonfinal, it was not designed for inheritance and should not be subclassed.
 * In particular, self-use by overridable methods is not specified. See
 * <i>Effective Java</i> Item 17, "Design and Document or inheritance or else
 * prohibit it" for further information.
 */
 class InnerJSONArray {

  private final List<Object> values;

  /**
   * Creates a {@code InnerJSONArray} with no values.
   */
  public InnerJSONArray() {
    values = new ArrayList<Object>();
  }

  /**
   * Creates a new {@code InnerJSONArray} by copying all values from the given
   * collection.
   *
   * @param copyFrom a collection whose values are of supported types.
   *     Unsupported values are not permitted and will yield an array in an
   *     inconsistent state.
   */
  /* Accept a raw type for API compatibility */
  public InnerJSONArray(Collection copyFrom) {
    this();
    if (copyFrom != null) {
      for (Iterator it = copyFrom.iterator(); it.hasNext();) {
        put(InnerJSONObject.wrap(it.next()));
      }
    }
  }

  /**
   * Creates a new {@code InnerJSONArray} with values from the next array in the
   * tokener.
   *
   * @param readFrom a tokener whose nextValue() method will yield a
   *     {@code InnerJSONArray}.
   * @throws InnerJSONException if the parse fails or doesn't yield a
   *     {@code InnerJSONArray}.
   */
  public InnerJSONArray(InnerJSONTokener readFrom) throws InnerJSONException {
    /*
     * Getting the parser to populate this could get tricky. Instead, just
     * parse to temporary InnerJSONArray and then steal the data from that.
     */
    Object object = readFrom.nextValue();
    if (object instanceof InnerJSONArray) {
      values = ((InnerJSONArray) object).values;
    } else {
      throw InnerJSON.typeMismatch(object, "InnerJSONArray");
    }
  }

  /**
   * Creates a new {@code InnerJSONArray} with values from the InnerJSON string.
   *
   * @param json a InnerJSON-encoded string containing an array.
   * @throws InnerJSONException if the parse fails or doesn't yield a {@code
   *     InnerJSONArray}.
   */
  public InnerJSONArray(String json) throws InnerJSONException {
    this(new InnerJSONTokener(json));
  }

  /**
   * Creates a new {@code InnerJSONArray} with values from the given primitive array.
   */
  public InnerJSONArray(Object array) throws InnerJSONException {
    if (!array.getClass().isArray()) {
      throw new InnerJSONException("Not a primitive array: " + array.getClass());
    }
    final int length = Array.getLength(array);
    values = new ArrayList<Object>(length);
    for (int i = 0; i < length; ++i) {
      put(InnerJSONObject.wrap(Array.get(array, i)));
    }
  }

  /**
   * Returns the number of values in this array.
   */
  public int length() {
    return values.size();
  }

  /**
   * Appends {@code value} to the end of this array.
   *
   * @return this array.
   */
  public InnerJSONArray put(boolean value) {
    values.add(value);
    return this;
  }

  /**
   * Appends {@code value} to the end of this array.
   *
   * @param value a finite value. May not be { Double#isNaN() NaNs} or
   *     { Double#isInfinite() infinities}.
   * @return this array.
   */
  public InnerJSONArray put(double value) throws InnerJSONException {
    values.add(InnerJSON.checkDouble(value));
    return this;
  }

  /**
   * Appends {@code value} to the end of this array.
   *
   * @return this array.
   */
  public InnerJSONArray put(int value) {
    values.add(value);
    return this;
  }

  /**
   * Appends {@code value} to the end of this array.
   *
   * @return this array.
   */
  public InnerJSONArray put(long value) {
    values.add(value);
    return this;
  }

  /**
   * Appends {@code value} to the end of this array.
   *
   * @param value a { InnerJSONObject}, { InnerJSONArray}, String, Boolean,
   *     Integer, Long, Double, { InnerJSONObject#NULL}, or {@code null}. May
   *     not be { Double#isNaN() NaNs} or { Double#isInfinite()
   *     infinities}. Unsupported values are not permitted and will cause the
   *     array to be in an inconsistent state.
   * @return this array.
   */
  public InnerJSONArray put(Object value) {
    values.add(value);
    return this;
  }

  /**
   * Same as { #put}, with added validity checks.
   */
  void checkedPut(Object value) throws InnerJSONException {
    if (value instanceof Number) {
      InnerJSON.checkDouble(((Number) value).doubleValue());
    }

    put(value);
  }

  /**
   * Sets the value at {@code index} to {@code value}, null padding this array
   * to the required length if necessary. If a value already exists at {@code
   * index}, it will be replaced.
   *
   * @return this array.
   */
  public InnerJSONArray put(int index, boolean value) throws InnerJSONException {
    return put(index, (Boolean) value);
  }

  /**
   * Sets the value at {@code index} to {@code value}, null padding this array
   * to the required length if necessary. If a value already exists at {@code
   * index}, it will be replaced.
   *
   * @param value a finite value. May not be { Double#isNaN() NaNs} or
   *     { Double#isInfinite() infinities}.
   * @return this array.
   */
  public InnerJSONArray put(int index, double value) throws InnerJSONException {
    return put(index, (Double) value);
  }

  /**
   * Sets the value at {@code index} to {@code value}, null padding this array
   * to the required length if necessary. If a value already exists at {@code
   * index}, it will be replaced.
   *
   * @return this array.
   */
  public InnerJSONArray put(int index, int value) throws InnerJSONException {
    return put(index, (Integer) value);
  }

  /**
   * Sets the value at {@code index} to {@code value}, null padding this array
   * to the required length if necessary. If a value already exists at {@code
   * index}, it will be replaced.
   *
   * @return this array.
   */
  public InnerJSONArray put(int index, long value) throws InnerJSONException {
    return put(index, (Long) value);
  }

  /**
   * Sets the value at {@code index} to {@code value}, null padding this array
   * to the required length if necessary. If a value already exists at {@code
   * index}, it will be replaced.
   *
   * @param value a { InnerJSONObject}, { InnerJSONArray}, String, Boolean,
   *     Integer, Long, Double, { InnerJSONObject#NULL}, or {@code null}. May
   *     not be { Double#isNaN() NaNs} or { Double#isInfinite()
   *     infinities}.
   * @return this array.
   */
  public InnerJSONArray put(int index, Object value) throws InnerJSONException {
    if (value instanceof Number) {
      // deviate from the original by checking all Numbers, not just floats & doubles
      InnerJSON.checkDouble(((Number) value).doubleValue());
    }
    while (values.size() <= index) {
      values.add(null);
    }
    values.set(index, value);
    return this;
  }

  /**
   * Returns true if this array has no value at {@code index}, or if its value
   * is the {@code null} reference or  InnerJSONObject#NULL}.
   */
  public boolean isNull(int index) {
    Object value = opt(index);
    return value == null || value == InnerJSONObject.NULL;
  }

  /**
   * Returns the value at {@code index}.
   *
   * @throws InnerJSONException if this array has no value at {@code index}, or if
   *     that value is the {@code null} reference. This method returns
   *     normally if the value is {@code InnerJSONObject#NULL}.
   */
  public Object get(int index) throws InnerJSONException {
    try {
      Object value = values.get(index);
      if (value == null) {
        throw new InnerJSONException("Value at " + index + " is null.");
      }
      return value;
    } catch (IndexOutOfBoundsException e) {
      throw new InnerJSONException("Index " + index + " out of range [0.." + values.size() + ")", e);
    }
  }

  /**
   * Returns the value at {@code index}, or null if the array has no value
   * at {@code index}.
   */
  public Object opt(int index) {
    if (index < 0 || index >= values.size()) {
      return null;
    }
    return values.get(index);
  }

  /**
   * Removes and returns the value at {@code index}, or null if the array has no value
   * at {@code index}.
   */
  public Object remove(int index) {
    if (index < 0 || index >= values.size()) {
      return null;
    }
    return values.remove(index);
  }

  /**
   * Returns the value at {@code index} if it exists and is a boolean or can
   * be coerced to a boolean.
   *
   * @throws InnerJSONException if the value at {@code index} doesn't exist or
   *     cannot be coerced to a boolean.
   */
  public boolean getBoolean(int index) throws InnerJSONException {
    Object object = get(index);
    Boolean result = InnerJSON.toBoolean(object);
    if (result == null) {
      throw InnerJSON.typeMismatch(index, object, "boolean");
    }
    return result;
  }

  /**
   * Returns the value at {@code index} if it exists and is a boolean or can
   * be coerced to a boolean. Returns false otherwise.
   */
  public boolean optBoolean(int index) {
    return optBoolean(index, false);
  }

  /**
   * Returns the value at {@code index} if it exists and is a boolean or can
   * be coerced to a boolean. Returns {@code fallback} otherwise.
   */
  public boolean optBoolean(int index, boolean fallback) {
    Object object = opt(index);
    Boolean result = InnerJSON.toBoolean(object);
    return result != null ? result : fallback;
  }

  /**
   * Returns the value at {@code index} if it exists and is a double or can
   * be coerced to a double.
   *
   * @throws InnerJSONException if the value at {@code index} doesn't exist or
   *     cannot be coerced to a double.
   */
  public double getDouble(int index) throws InnerJSONException {
    Object object = get(index);
    Double result = InnerJSON.toDouble(object);
    if (result == null) {
      throw InnerJSON.typeMismatch(index, object, "double");
    }
    return result;
  }

  /**
   * Returns the value at {@code index} if it exists and is a double or can
   * be coerced to a double. Returns {@code NaN} otherwise.
   */
  public double optDouble(int index) {
    return optDouble(index, Double.NaN);
  }

  /**
   * Returns the value at {@code index} if it exists and is a double or can
   * be coerced to a double. Returns {@code fallback} otherwise.
   */
  public double optDouble(int index, double fallback) {
    Object object = opt(index);
    Double result = InnerJSON.toDouble(object);
    return result != null ? result : fallback;
  }

  /**
   * Returns the value at {@code index} if it exists and is an int or
   * can be coerced to an int.
   *
   * @throws InnerJSONException if the value at {@code index} doesn't exist or
   *     cannot be coerced to a int.
   */
  public int getInt(int index) throws InnerJSONException {
    Object object = get(index);
    Integer result = InnerJSON.toInteger(object);
    if (result == null) {
      throw InnerJSON.typeMismatch(index, object, "int");
    }
    return result;
  }

  /**
   * Returns the value at {@code index} if it exists and is an int or
   * can be coerced to an int. Returns 0 otherwise.
   */
  public int optInt(int index) {
    return optInt(index, 0);
  }

  /**
   * Returns the value at {@code index} if it exists and is an int or
   * can be coerced to an int. Returns {@code fallback} otherwise.
   */
  public int optInt(int index, int fallback) {
    Object object = opt(index);
    Integer result = InnerJSON.toInteger(object);
    return result != null ? result : fallback;
  }

  /**
   * Returns the value at {@code index} if it exists and is a long or
   * can be coerced to a long.
   *
   * @throws InnerJSONException if the value at {@code index} doesn't exist or
   *     cannot be coerced to a long.
   */
  public long getLong(int index) throws InnerJSONException {
    Object object = get(index);
    Long result = InnerJSON.toLong(object);
    if (result == null) {
      throw InnerJSON.typeMismatch(index, object, "long");
    }
    return result;
  }

  /**
   * Returns the value at {@code index} if it exists and is a long or
   * can be coerced to a long. Returns 0 otherwise.
   */
  public long optLong(int index) {
    return optLong(index, 0L);
  }

  /**
   * Returns the value at {@code index} if it exists and is a long or
   * can be coerced to a long. Returns {@code fallback} otherwise.
   */
  public long optLong(int index, long fallback) {
    Object object = opt(index);
    Long result = InnerJSON.toLong(object);
    return result != null ? result : fallback;
  }

  /**
   * Returns the value at {@code index} if it exists, coercing it if
   * necessary.
   *
   * @throws InnerJSONException if no such value exists.
   */
  public String getString(int index) throws InnerJSONException {
    Object object = get(index);
    String result = InnerJSON.toString(object);
    if (result == null) {
      throw InnerJSON.typeMismatch(index, object, "String");
    }
    return result;
  }

  /**
   * Returns the value at {@code index} if it exists, coercing it if
   * necessary. Returns the empty string if no such value exists.
   */
  public String optString(int index) {
    return optString(index, "");
  }

  /**
   * Returns the value at {@code index} if it exists, coercing it if
   * necessary. Returns {@code fallback} if no such value exists.
   */
  public String optString(int index, String fallback) {
    Object object = opt(index);
    String result = InnerJSON.toString(object);
    return result != null ? result : fallback;
  }

  /**
   * Returns the value at {@code index} if it exists and is a {@code
   * InnerJSONArray}.
   *
   * @throws InnerJSONException if the value doesn't exist or is not a {@code
   *     InnerJSONArray}.
   */
  public InnerJSONArray getJSONArray(int index) throws InnerJSONException {
    Object object = get(index);
    if (object instanceof InnerJSONArray) {
      return (InnerJSONArray) object;
    } else {
      throw InnerJSON.typeMismatch(index, object, "InnerJSONArray");
    }
  }

  /**
   * Returns the value at {@code index} if it exists and is a {@code
   * InnerJSONArray}. Returns null otherwise.
   */
  public InnerJSONArray optJSONArray(int index) {
    Object object = opt(index);
    return object instanceof InnerJSONArray ? (InnerJSONArray) object : null;
  }

  /**
   * Returns the value at {@code index} if it exists and is a {@code
   * InnerJSONObject}.
   *
   * @throws InnerJSONException if the value doesn't exist or is not a {@code
   *     InnerJSONObject}.
   */
  public InnerJSONObject getJSONObject(int index) throws InnerJSONException {
    Object object = get(index);
    if (object instanceof InnerJSONObject) {
      return (InnerJSONObject) object;
    } else {
      throw InnerJSON.typeMismatch(index, object, "InnerJSONObject");
    }
  }

  /**
   * Returns the value at {@code index} if it exists and is a {@code
   * InnerJSONObject}. Returns null otherwise.
   */
  public InnerJSONObject optJSONObject(int index) {
    Object object = opt(index);
    return object instanceof InnerJSONObject ? (InnerJSONObject) object : null;
  }

  /**
   * Returns a new object whose values are the values in this array, and whose
   * names are the values in {@code names}. Names and values are paired up by
   * index from 0 through to the shorter array's length. Names that are not
   * strings will be coerced to strings. This method returns null if either
   * array is empty.
   */
  public InnerJSONObject toJSONObject(InnerJSONArray names) throws InnerJSONException {
    InnerJSONObject result = new InnerJSONObject();
    int length = Math.min(names.length(), values.size());
    if (length == 0) {
      return null;
    }
    for (int i = 0; i < length; i++) {
      String name = InnerJSON.toString(names.opt(i));
      result.put(name, opt(i));
    }
    return result;
  }

  /**
   * Returns a new string by alternating this array's values with {@code
   * separator}. This array's string values are quoted and have their special
   * characters escaped. For example, the array containing the strings '12"
   * pizza', 'taco' and 'soda' joined on '+' returns this:
   * <pre>"12\" pizza"+"taco"+"soda"</pre>
   */
  public String join(String separator) throws InnerJSONException {
    InnerJSONStringer stringer = new InnerJSONStringer();
    stringer.open(InnerJSONStringer.Scope.NULL, "");
    for (int i = 0, size = values.size(); i < size; i++) {
      if (i > 0) {
        stringer.out.append(separator);
      }
      stringer.value(values.get(i));
    }
    stringer.close(InnerJSONStringer.Scope.NULL, InnerJSONStringer.Scope.NULL, "");
    return stringer.out.toString();
  }

  /**
   * Encodes this array as a compact InnerJSON string, such as:
   * <pre>[94043,90210]</pre>
   */
  @Override public String toString() {
    try {
      InnerJSONStringer stringer = new InnerJSONStringer();
      writeTo(stringer);
      return stringer.toString();
    } catch (InnerJSONException e) {
      return null;
    }
  }

  /**
   * Encodes this array as a human readable InnerJSON string for debugging, such
   * as:
   * <pre>
   * [
   *     94043,
   *     90210
   * ]</pre>
   *
   * @param indentSpaces the number of spaces to indent for each level of
   *     nesting.
   */
  public String toString(int indentSpaces) throws InnerJSONException {
    InnerJSONStringer stringer = new InnerJSONStringer(indentSpaces);
    writeTo(stringer);
    return stringer.toString();
  }

  void writeTo(InnerJSONStringer stringer) throws InnerJSONException {
    stringer.array();
    for (Object value : values) {
      stringer.value(value);
    }
    stringer.endArray();
  }

  @Override public boolean equals(Object o) {
    return o instanceof InnerJSONArray && ((InnerJSONArray) o).values.equals(values);
  }

  @Override public int hashCode() {
    // diverge from the original, which doesn't implement hashCode
    return values.hashCode();
  }
}
