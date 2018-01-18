package android.text;

/**
 * Created by Leo on 2018/1/12.
 */

   
public class TextUtils {
  public static boolean isEmpty(CharSequence str){
    if(str == null || str.equals("")){
      return true;
    }
    return false;
  }
}