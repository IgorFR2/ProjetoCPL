import compilador.analysis.DepthFirstAdapter;
import compilador.node.*;

import java.util.Hashtable;
import java.util.LinkedList;

public class Tradutor extends DepthFirstAdapter {
  Hashtable symbol_table = new Hashtable();

  public void outANomeVariavel(ANomeVariavel node) {
    String parent = node.parent().getClass().getSimpleName();
    String key = node.getId().getText();
    if (parent.equals("ADeclaracao")) {
      if (symbol_table.containsKey(key)) {
        System.out.println(
            "["
                + node.getId().getLine()
                + ","
                + node.getId().getPos()
                + "] Redeclaração da variável "
                + key);
      } else {
        symbol_table.put(key, key);
      }
    }
  }
}
