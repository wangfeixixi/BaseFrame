package com.wangfeixixi.base.debug.log;


 class InnerJSON {
  /**
   * Returns the input if it is a InnerJSON-permissible value; throws otherwise.
   */
  static double checkDouble(double d) throws InnerJSONException {
    if (Double.isInfinite(d) || Double.isNaN(d)) {
      throw new InnerJSONException("Forbidden numeric value: " + d);
    }
    return d;
  }

  static Boolean toBoolean(Object value) {
    if (value instanceof Boolean) {
      return (Boolean) value;
    } else if (value instanceof String) {
      String stringValue = (String) value;
      if ("true".equalsIgnoreCase(stringValue)) {
        return true;
      } else if ("false".equalsIgnoreCase(stringValue)) {
        return false;
      }
    }
    return null;
  }

  static Double toDouble(Object value) {
    if (value instanceof Double) {
      return (Double) value;
    } else if (value instanceof Number) {
      return ((Number) value).doubleValue();
    } else if (value instanceof String) {
      try {
        return Double.valueOf((String) value);
      } catch (NumberFormatException ignored) {
      }
    }
    return null;
  }

  static Integer toInteger(Object value) {
    if (value instanceof Integer) {
      return (Integer) value;
    } else if (value instanceof Number) {
      return ((Number) value).intValue();
    } else if (value instanceof String) {
      try {
        return (int) Double.parseDouble((String) value);
      } catch (NumberFormatException ignored) {
      }
    }
    return null;
  }

  static Long toLong(Object value) {
    if (value instanceof Long) {
      return (Long) value;
    } else if (value instanceof Number) {
      return ((Number) value).longValue();
    } else if (value instanceof String) {
      try {
        return (long) Double.parseDouble((String) value);
      } catch (NumberFormatException ignored) {
      }
    }
    return null;
  }

  static String toString(Object value) {
    if (value instanceof String) {
      return (String) value;
    } else if (value != null) {
      return String.valueOf(value);
    }
    return null;
  }

  public static InnerJSONException typeMismatch(Object indexOrName, Object actual,
                                                String requiredType) throws InnerJSONException {
    if (actual == null) {
      throw new InnerJSONException("Value at " + indexOrName + " is null.");
    } else {
      throw new InnerJSONException("Value " + actual + " at " + indexOrName
          + " of type " + actual.getClass().getName()
          + " cannot be converted to " + requiredType);
    }
  }

  public static InnerJSONException typeMismatch(Object actual, String requiredType)
      throws InnerJSONException {
    if (actual == null) {
      throw new InnerJSONException("Value is null.");
    } else {
      throw new InnerJSONException("Value " + actual
          + " of type " + actual.getClass().getName()
          + " cannot be converted to " + requiredType);
    }
  }
}
