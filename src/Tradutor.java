import compilador.analysis.DepthFirstAdapter;
import compilador.node.*;

import java.util.Hashtable;
import java.util.LinkedList;

import java.lang.System;

public class Tradutor extends DepthFirstAdapter {
  Hashtable symbol_table = new Hashtable();
  
  /**
  Programa
  */
  public void outACorpoPrograma(ACorpoPrograma node){}

  /**
  Declaracao
  */
  public void outADeclaracao(ADeclaracao node){}
  public void outAConstanteDeclaracao(AConstanteDeclaracao node){}
  /**
  Tipos_Dados
  */
  public void outAInteiroTiposDados(AInteiroTiposDados node) {}
  public void outARealTiposDados(ARealTiposDados node) {}
  public void outACaractereTiposDados(ACaractereTiposDados node) {}

  /**
  Variavel
  */
  public void outANomeVariavel(ANomeVariavel node) {
	    String parent = node.parent().getClass().getSimpleName();
	    String key = node.getId().getText();
	    if (parent.equals("ADeclaracao")) {
	      if (symbol_table.containsKey(key)) {
//	        System.out.println(
//	            "["
//	            + node.getId().getLine()
//	            + ","
//	            + node.getId().getPos()
//	            + "] Redeclaracao da variavel '"
//	            + key
//	            +"'."
//	            );
	      } else {
	        symbol_table.put(key, key);
	      }
	    }
	  }
  public void outAVetorVariavel(AVetorVariavel node) {}

  /**
  Valor
  */
  public void outAValCaractereValor(AValCaractereValor node) {}
  public void outAValInteiroValor(AValInteiroValor node) {}
  public void outAValRealValor(AValRealValor node) {}


  /**
   * Expressões
   * */
  public void outAIgualdadeExpressao(AIgualdadeExpressao node){}
  public void outADiferenteExpressao(ADiferenteExpressao node){}
  public void outAMenorIgExpressao(AMenorIgExpressao node){}
  public void outAMaiorIgExpressao(AMaiorIgExpressao node){}
  public void outAMenorExpressao(AMenorExpressao node){}
  public void outAMaiorExpressao(AMaiorExpressao node){}
  public void outAEExpressao(AEExpressao node){}
  public void outAOuExpressao(AOuExpressao node){}
  public void outAXorExpressao(AXorExpressao node){}
  public void outANotExpressao(ANotExpressao node){}
  public void outAInversaoExpressao(AInversaoExpressao node){}
  
  public void outASomarExpressao(ASomarExpressao node) {
	
	     try {
	    	 String resultado;
	    	 AValorExpressao noValor;
	    	 /*
	    	  * Verificar se é inteiro ou float
	    	  * */
	    	 if(node.getLeft().toString().indexOf(',')>=0) {
	    		 resultado = Integer.toString(
	    				 Integer.valueOf(node.getLeft().toString().trim()) +
	    				 Integer.valueOf(node.getRight().toString().trim())
	    				 );
	    		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
		    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
	    	 } else {
	    		 
	    		 resultado = Double.toString(
	    				 Double.valueOf(node.getLeft().toString().trim().replace(',', '.')) +
	    				 Double.valueOf(node.getRight().toString().trim().replace(',', '.'))
	    				 );
	    		 TNumReal tokenInteiro = new TNumReal(resultado);
		    	 AValRealValor valorInteiro = new AValRealValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
	    	 }
	    	 
	    	 node.replaceBy(noValor);
	    	 
	     }
	     catch (Exception e) {
	          System.out.println(e);
	     }
	}
  public void outASubtrairExpressao(ASubtrairExpressao node){
	  try {
	    	 String resultado;
	    	 AValorExpressao noValor;
	    	 /*
	    	  * Verificar se é inteiro ou float
	    	  * */
	    	 if(node.getLeft().toString().indexOf(',')>=0) {
	    		 resultado = Integer.toString(
	    				 Integer.valueOf(node.getLeft().toString().trim()) -
	    				 Integer.valueOf(node.getRight().toString().trim())
	    				 );
	    		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
		    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
	    	 } else {
	    		 
	    		 resultado = Double.toString(
	    				 Double.valueOf(node.getLeft().toString().trim().replace(',', '.')) +
	    				 Double.valueOf(node.getRight().toString().trim().replace(',', '.'))
	    				 );
	    		 TNumReal tokenInteiro = new TNumReal(resultado);
		    	 AValRealValor valorInteiro = new AValRealValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
	    	 }
	    	 
	    	 node.replaceBy(noValor);
	    	 
	     }
	     catch (Exception e) {
	          System.out.println(e);
	     }
  }
  public void outAMultiplicarExpressao(AMultiplicarExpressao node){
	  try {
	    	 String resultado;
	    	 AValorExpressao noValor;
	    	 /*
	    	  * Verificar se é inteiro ou float
	    	  * */
	    	 if(node.getLeft().toString().indexOf(',')>=0) {
	    		 resultado = Integer.toString(
	    				 Integer.valueOf(node.getLeft().toString().trim()) * 
	    				 Integer.valueOf(node.getRight().toString().trim())
	    				 );
	    		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
		    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
	    	 } else {
	    		 
	    		 resultado = Double.toString(
	    				 Double.valueOf(node.getLeft().toString().trim().replace(',', '.')) +
	    				 Double.valueOf(node.getRight().toString().trim().replace(',', '.'))
	    				 );
	    		 TNumReal tokenInteiro = new TNumReal(resultado);
		    	 AValRealValor valorInteiro = new AValRealValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
	    	 }
	    	 
	    	 node.replaceBy(noValor);
	    	 
	     }
	     catch (Exception e) {
	          System.out.println(e);
	     }
  }
  public void outADividirExpressao(ADividirExpressao node){try {
 	 String resultado;
 	 AValorExpressao noValor;
 	 /*
 	  * Verificar se é inteiro ou float
 	  * */
 	 if(node.getLeft().toString().indexOf(',')>=0) {
 		 resultado = Integer.toString(
 				 Integer.valueOf(node.getLeft().toString().trim()) /
 				 Integer.valueOf(node.getRight().toString().trim())
 				 );
 		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
	    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
	    	 noValor = new AValorExpressao(valorInteiro);
 	 } else {
 		 
 		 resultado = Double.toString(
 				 Double.valueOf(node.getLeft().toString().trim().replace(',', '.')) +
 				 Double.valueOf(node.getRight().toString().trim().replace(',', '.'))
 				 );
 		 TNumReal tokenInteiro = new TNumReal(resultado);
	    	 AValRealValor valorInteiro = new AValRealValor(tokenInteiro);
	    	 noValor = new AValorExpressao(valorInteiro);
 	 }
 	 
 	 node.replaceBy(noValor);
 	 
  }
  catch (Exception e) {
       System.out.println(e);
  }}
  public void outAValorExpressao(AValorExpressao node){}
  public void outAVariavelExpressao(AVariavelExpressao node){}

  /**
  Comando
  */
  public void outAAtribuicaoComando(AAtribuicaoComando node) {
	  String parent = node.parent().getClass().getSimpleName();
	    String key = node.getVariavel().toString();
	    String value = node.getExpressao().toString();
	      if (symbol_table.containsKey(value)) {
//		        System.out.println(
//			            "Atribuição da variavel '"
//			            + key
//			            +"' valor de "
//			            + value
//			            + "("
//			            + node.getExpressao().toString()
//			            +")"
//			            +"."
//			            );
		        symbol_table.put(key, value);
	      } else {
//	        System.out.println(
//		            "Atribuição da variavel '"
//		            + key
//		            +"' valor "
//		            + value
//		            +"."
//		            );
//	        symbol_table.put(key, value);
	        }

	}
  
  public void ALeituraComando(ALeituraComando node){}
  public void AEscritaComando(AEscritaComando node){
	  String conteudo = node.getExpressao().toString();
	  System.out.println(conteudo);
	  System.out.println("Teste:"+node.getExpressao());
  }
  public void ASeComando(ASeComando node){}
  public void AAvalieComando(AAvalieComando node){}
  public void AEnquantoComando(AEnquantoComando node){}
  public void ARepitaComando(ARepitaComando node){}
  public void AParaComando(AParaComando node){}
  public void AParaSegComando(AParaSegComando node){}

  /**
  Se_corpo
  */
  public void outASeSenaoSeCorpo (ASeSenaoSeCorpo node){}
  public void outASeUnicoSeCorpo(ASeUnicoSeCorpo node){}

  /**
  Caso_comandos
  */
  public void outACasoCasoComandos (ACasoCasoComandos node){}
  public void outACasosCasoComandos(ACasosCasoComandos node){}
  
  
}
