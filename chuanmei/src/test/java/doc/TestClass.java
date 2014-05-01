package doc;

import java.util.ArrayList;
import java.util.List;

public class TestClass {

  public static void main(String[] args) {
    boolean b = false;
    b = b || true;
    System.out.println(b);
    b = false;
    b = b && true;
    System.out.println(b);
    
    List<String> l = new ArrayList<String>();
    for (int i = 0; i < 100; i++ ) {
      l.add(String.valueOf(i));
    }
    List<String> r = new ArrayList<String>();
    for (String string : l) {
      if (string.indexOf("3") >= 0) {
        r.add(string);
      }
    }
    l.removeAll(r);
    for (String string : l) {
      System.out.print(string + ",");
    }
    
  }

}
