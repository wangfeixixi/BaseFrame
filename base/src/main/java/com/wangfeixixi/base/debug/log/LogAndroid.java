package com.wangfeixixi.base.debug.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;

import static com.wangfeixixi.base.debug.log.Utils.checkNotNull;

/**
 * <pre>
 *  ┌────────────────────────────────────────────
 *  │ LOGGER
 *  ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 *  │ Standard logging mechanism
 *  ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 *  │ But more pretty, simple and powerful
 *  └────────────────────────────────────────────
 * </pre>
 *
 * <h3>How to use it</h3>
 * Initialize it first
 * <pre><code>
 *   LogAndroid.addLogAdapter(new AndroidLogAdapter());
 * </code></pre>
 * <p>
 * And use the appropriate static LogAndroid methods.
 *
 * <pre><code>
 *   LogAndroid.d("debug");
 *   LogAndroid.e("error");
 *   LogAndroid.w("warning");
 *   LogAndroid.v("verbose");
 *   LogAndroid.i("information");
 *   LogAndroid.wtf("What a Terrible Failure");
 * </code></pre>
 *
 * <h3>String format arguments are supported</h3>
 * <pre><code>
 *   LogAndroid.d("hello %s", "world");
 * </code></pre>
 *
 * <h3>Collections are support ed(only available for debug logs)</h3>
 * <pre><code>
 *   LogAndroid.d(MAP);
 *   LogAndroid.d(SET);
 *   LogAndroid.d(LIST);
 *   LogAndroid.d(ARRAY);
 * </code></pre>
 *
 * <h3>Json and Xml support (output will be in debug level)</h3>
 * <pre><code>
 *   LogAndroid.json(JSON_CONTENT);
 *   LogAndroid.xml(XML_CONTENT);
 * </code></pre>
 *
 * <h3>Customize LogAndroid</h3>
 * Based on your needs, you can change the following settings:
 * <ul>
 * <li>Different {@link LogAdapter}</li>
 * <li>Different {@link FormatStrategy}</li>
 * <li>Different {@link LogStrategy}</li>
 * </ul>
 *
 * @see LogAdapter
 * @see FormatStrategy
 * @see LogStrategy
 */
public final class LogAndroid {

  public static final int VERBOSE = 2;
  public static final int DEBUG = 3;
  public static final int INFO = 4;
  public static final int WARN = 5;
  public static final int ERROR = 6;
  public static final int ASSERT = 7;

  @NonNull private static Printer printer = new LoggerPrinter();

  private LogAndroid() {
    //no instance
  }

  public static void printer(@NonNull Printer printer) {
    LogAndroid.printer = checkNotNull(printer);
  }

  public static void addLogAdapter(@NonNull LogAdapter adapter) {
    printer.clearLogAdapters();
    printer.addAdapter(checkNotNull(adapter));
  }

  public static void clearLogAdapters() {
    printer.clearLogAdapters();
  }

  /**
   * Given tag will be used as tag only once for this method call regardless of the tag that's been
   * set during initialization. After this invocation, the general tag that's been set will
   * be used for the subsequent d calls
   */
  public static Printer t(@Nullable String tag) {
    return printer.t(tag);
  }

  /**
   * General d function that accepts all configurations as parameter
   */
  public static void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
    printer.log(priority, tag, message, throwable);
  }

  public static void d(@NonNull String message, @Nullable Object... args) {
    printer.d(message, args);
  }

  public static void d(@Nullable Object object) {
    printer.d(object);
  }

  public static void bean(@Nullable Object object) {
    printer.json(JSON.toJSONString(object));
  }

  public static void e(@NonNull String message, @Nullable Object... args) {
    printer.e(null, message, args);
  }

  public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
    printer.e(throwable, message, args);
  }

  public static void i(@NonNull String message, @Nullable Object... args) {
    printer.i(message, args);
  }

  public static void v(@NonNull String message, @Nullable Object... args) {
    printer.v(message, args);
  }

  public static void w(@NonNull String message, @Nullable Object... args) {
    printer.w(message, args);
  }

  /**
   * Tip: Use this for exceptional situations to d
   * ie: Unexpected errors etc
   */
  public static void wtf(@NonNull String message, @Nullable Object... args) {
    printer.wtf(message, args);
  }

  /**
   * Formats the given json content and print it
   */
  public static void json(@Nullable String json) {
    printer.json(json);
  }

  /**
   * Formats the given xml content and print it
   */
  public static void xml(@Nullable String xml) {
    printer.xml(xml);
  }

}
