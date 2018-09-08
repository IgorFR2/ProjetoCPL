import compilador.analysis.DepthFirstAdapter;
import compilador.node.*;

import java.util.Hashtable;
import java.util.LinkedList;

import java.lang.System;

public class Tradutor extends DepthFirstAdapter {
  Hashtable symbol_table = new Hashtable();
  //Ao que parece a symbol table é onde fica a "memoria" das variaveis
  //O node ainda não entendi ao certo, mas deve ser as folhas da AST
  
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
	            + "] Redeclaracao da variavel '"
	            + key
	            +"'."
	            );
	      } else {
	        symbol_table.put(key, key);
	      }
	    }
	  }
  
  public void outASomarExpressao(ASomarExpressao node) {
	
	     try {
	    	 // Falta onde guardar
	    	 //String key = node.getId();
	    	 int esquerda = Integer.valueOf(node.getLeft().toString().trim());
	    	 int direita = Integer.valueOf(node.getRight().toString().trim());
	    	 int resultado = Integer.valueOf(esquerda + direita); 
	    	 System.out.println(esquerda+" + "+direita+" = "+resultado);
	    	 //return resultado;
	    	 //symbol_table.put(key, resultado);
	     }
	     catch (Exception e) {
	          System.out.println(e);
	     }
	}
  
  public void outAAtribuicaoComando(AAtribuicaoComando node) {
	  String parent = node.parent().getClass().getSimpleName();
	    String key = node.getVariavel().toString();
	    String value = node.getExpressao().toString();
	      if (symbol_table.containsKey(value)) {
		        System.out.println(
			            "Atribuição da variavel '"
			            + key
			            +"' valor de "
			            + value
			            + "("
			            + node.getExpressao().toString()
			            +")"
			            +"."
			            );
		        symbol_table.put(key, value);
	      } else {
	        System.out.println(
		            "Atribuição da variavel '"
		            + key
		            +"' valor "
		            + value
		            +"."
		            );
	        symbol_table.put(key, value);
	        }

	}
}
