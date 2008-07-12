package edu.nyu.cs.javagit.utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class provides utilities methods that perform various checks for validity.
 */
public class CheckUtilities {

  /**
   * Checks that the specified filename exists. This assumes that the above check for string
   * validity has already been run and the path/filename is neither null or of size 0.
   * 
   * @param filename
   *          File or directory path
   */
  public static void checkFileValidity(String filename) throws IOException {
    File file = new File(filename);
    if (!file.exists()) {
      throw new IOException(ExceptionMessageMap.getMessage("020001") + "  { filename=[" + filename
          + "] }");
    }
  }

  /**
   * Checks that the int to check is greater than <code>lowerBound</code>. If the int to check is
   * not greater than <code>lowerBound</code>, an <code>IllegalArgumentException</code> is
   * thrown.
   * 
   * @param toCheck
   *          The int to check.
   * @param lowerBound
   *          The lower bound to check against.
   * @param variableName
   *          The name of the variable being checked; for use in exception messages.
   */
  public static void checkIntArgumentGreaterThan(int toCheck, int lowerBound, String variableName) {
    if (lowerBound >= toCheck) {
      throw new IllegalArgumentException(ExceptionMessageMap.getMessage("000004") + "  { toCheck=["
          + toCheck + "], lowerBound=[" + lowerBound + "], variableName=[" + variableName + "] }");
    }
  }

  /**
   * Performs a null check on the specified object. If the object is null, a
   * <code>NullPointerException</code> is thrown.
   * 
   * @param obj
   *          The object to check.
   * @param variableName
   *          The name of the variable being checked; for use in exception messages.
   */
  public static void checkNullArgument(Object obj, String variableName) {
    if (null == obj) {
      throw new NullPointerException(ExceptionMessageMap.getMessage("000003")
          + "  { variableName=[" + variableName + "] }");
    }
  }

  /**
   * Checks a <code>List&lt;?&gt;</code> argument to make sure it is not null, has length > 0, and
   * none of its elements are null. If the <code>List&lt;?&gt;</code> or any contained instance is
   * null, a <code>NullPointerException</code> is thrown. If the <code>List&lt;?&gt;</code> or
   * any contained instance has length zero, an <code>IllegalArgumentException</code> is thrown.
   * 
   * @param list
   *          The list to check.
   * @param variableName
   *          The name of the variable being checked; for use in exception messages.
   */
  public static void checkNullListArgument(List<?> list, String variableName) {
    // TODO (jhl388): Write a unit test for this method.
    if (null == list) {
      throw new NullPointerException(ExceptionMessageMap.getMessage("000005")
          + "  { variableName=[" + variableName + "] }");
    }
    if (list.size() == 0) {
      throw new IllegalArgumentException(ExceptionMessageMap.getMessage("000005")
          + "  { variableName=[" + variableName + "] }");
    }
    for (int i = 0; i < list.size(); i++) {
      checkNullArgument(list.get(i), variableName);
    }
  }

  /**
   * Checks to see if two objects are equal. The Object.equal() method is used to check for
   * equality.
   * 
   * @param o1
   *          The first object to check.
   * @param o2
   *          The second object to check.
   * @return True if the two objects are equal. False if the objects are not equal.
   */
  public static boolean checkObjectsEqual(Object o1, Object o2) {
    if (null != o1 && !o1.equals(o2)) {
      return false;
    }

    if (null == o1 && null != o2) {
      return false;
    }

    return true;
  }

  /**
   * Checks a <code>String</code> argument to make sure it is not null and contains one or more
   * characters. If the <code>String</code> is null, a <code>NullPointerException</code> is
   * thrown. If the <code>String</code> has length zero, an <code>IllegalArgumentException</code>
   * is thrown.
   * 
   * @param str
   *          The string to check.
   * @param variableName
   *          The name of the variable being checked; for use in exception messages.
   */
  public static void checkStringArgument(String str, String variableName) {
    if (null == str) {
      throw new NullPointerException(ExceptionMessageMap.getMessage("000001")
          + "  { variableName=[" + variableName + "] }");
    }
    if (str.length() == 0) {
      throw new IllegalArgumentException(ExceptionMessageMap.getMessage("000001")
          + "  { variableName=[" + variableName + "] }");
    }
  }

  /**
   * Checks a <code>List&lt;String&gt;</code> argument to make sure it is not null, none of its
   * elements are null, and all its elements contain one or more characters. If the
   * <code>List&lt;String&gt;</code> or a contained <code>String</code> is null, a
   * <code>NullPointerException</code> is thrown. If the <code>List&lt;String&gt;</code> or a
   * contained <code>String</code> has length zero, an <code>IllegalArgumentException</code> is
   * thrown.
   * 
   * @param str
   *          The <code>List&lt;String&gt;</code> to check.
   * @param variableName
   *          The name of the variable being checked; for use in exception messages.
   */
  public static void checkStringListArgument(List<String> str, String variableName) {
    if (null == str) {
      throw new NullPointerException(ExceptionMessageMap.getMessage("000002")
          + "  { variableName=[" + variableName + "] }");
    }
    if (str.size() == 0) {
      throw new IllegalArgumentException(ExceptionMessageMap.getMessage("000002")
          + "  { variableName=[" + variableName + "] }");
    }
    for (int i = 0; i < str.size(); i++) {
      checkStringArgument(str.get(i), variableName);
    }
  }

  /**
   * Checks if two unordered lists are equal.
   * 
   * @param l1
   *          The first list to test.
   * @param l2
   *          The second list to test.
   * @return True if:
   *         <ul>
   *         <li>both lists are null or</li>
   *         <li>both lists are the same length, there exists an equivalent object in l2 for all
   *         objects in l1, and there exists an equivalent object in l1 for all objects in l2</li>
   *         </ul>
   *         False otherwise.
   */
  public static boolean checkUnorderedListsEqual(List<?> l1, List<?> l2) {
    if (null == l1 && null != l2) {
      return false;
    }

    if (null != l1 && null == l2) {
      return false;
    }

    if (l1.size() != l2.size()) {
      return false;
    }

    for (Object o : l1) {
      if (!l2.contains(o)) {
        return false;
      }
    }

    for (Object o : l2) {
      if (!l1.contains(o)) {
        return false;
      }
    }

    return true;
  }
  
  /**
   * Check if the index provided to list is within the range i.e. positive 
   * and less than the size of the <List>. If the index is less than 0 or
   * greater than equal to the size of the list then <code>IndexOutOfBoundsException</code>
   * is thrown.
   * @param list <List> for which the index is being verified.
   * @param index Index in the list.
   */
  public static void checkIntIndexInListRange(List<?> list, int index ) {
    checkIntInRange( index, 0, list.size());
  }
  
  /**
   * A general range check utility for checking whether a given &lt;integer&gt; value
   * is between a given start and end indexes. This is a helper method for
   * other methods such as checkIntIndexInListRange or can also be
   * used independently by external objects.
   * @param index Given index that is being checked for validity between
   *    start and end.
   * @param start index should be greater than or equal to start.
   * @param end index should be less than end.
   */
  public static void checkIntInRange(int index, int start, int end) {
    if (index < start) {
      throw new IndexOutOfBoundsException(ExceptionMessageMap.getMessage("000006") + "  { index=["
          + index + "], start=[" + start + "], end=[" + end + "] }");
    }
    if (index >= end) {
      throw new IndexOutOfBoundsException(ExceptionMessageMap.getMessage("000006") + "  { index=["
          + index + "], start=[" + start + "], end=[" + end + "] }");
    }
  }

}
