package hive.udfs;

import java.util.Arrays;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

/*i
* FROM (
*   SELECT *
*   FROM table
*   DISTRIBUTE BY user
*   SORT BY user, time
* ) A
*
* DISTRIBUTE by is needed so that all the rows with the same userid will be
* seen by the same machine; otherwise each machine operating in parallel will
* number its own rows starting from one.  The SORT BY ensures that the
* numbering proceeds in the desired order.  Using A.user as the key of the 
* operation ensures that the numbering restarts when a new user is seen.
*/
@Description(name = "NUMBER_ROWS",
            value = "_FUNC_(key1, key2, ...) - Number rows starting at 1.  Whenever the value of any key changes the numbering is reset to 1.")
public class UDFNumberRows extends UDF {
 Object[] previous_keys = null;
 int previous_index;

 public int evaluate(Object... keys) {
   if (previous_keys == null || !Arrays.equals(previous_keys, keys)) {
     previous_index = 0;
     previous_keys = keys.clone();
   }
   previous_index++;
   return previous_index;
 }
}