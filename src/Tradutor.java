import compilador.analysis.DepthFirstAdapter;
import compilador.node.*;

import java.util.Hashtable;
import java.util.LinkedList;

import java.lang.System;
import java.util.Scanner;
/**
 * OBSERVAÇÃO IMPORTANTE: Nem todas as classes serão UTEIS! Algumas não possuem alterações necessárias para o desenvolvimento.
 * 						  A própria definição nas outras classes já geram completas (Ex: AValorExpressao, removido no comit de
 * 						  09/09 às 00:32) 
 * 
 * */
public class Tradutor extends DepthFirstAdapter {
  Hashtable symbol_table = new Hashtable();
  
  /**
   *	Programa
   **/
  public void outACorpoPrograma(ACorpoPrograma node){}

  /**
   *	Declaracao
   **/
  public void outADeclaracao(ADeclaracao node){}
  public void outAConstanteDeclaracao(AConstanteDeclaracao node){}
  
  /**
   *	Tipos_Dados
   **/
  public void outAInteiroTiposDados(AInteiroTiposDados node) {}
  public void outARealTiposDados(ARealTiposDados node) {}
  public void outACaractereTiposDados(ACaractereTiposDados node) {}

  /**
  *	Variavel
  **/
  //	Não acabado, precisa direcionar segunda parte para quando for uma VARIAVEL em uma EXPRESSAO
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
	        System.out.println("Declarada a variavel "+key+".");
	        symbol_table.put(key, key);
	      }
	    }
	  }
  //	Como fazer o Vetor?
  public void outAVetorVariavel(AVetorVariavel node) {}

  /**
   *	Valor
   **/
  public void outAValCaractereValor(AValCaractereValor node) {}
  public void outAValInteiroValor(AValInteiroValor node) {}
  public void outAValRealValor(AValRealValor node) {}


  /**
   * Expressões
   **/
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
  
  // 	Bloco OK
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
	    				 ).replace('.', ',');
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
	    				 ).replace('.', ',');
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
	    				 ).replace('.', ',');
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
  public void outADividirExpressao(ADividirExpressao node){
	 try {
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
	 				 ).replace('.', ',');
	 		 TNumReal tokenInteiro = new TNumReal(resultado);
		    	 AValRealValor valorInteiro = new AValRealValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
 	 }
 	 
 	 node.replaceBy(noValor);
 	 
  }
  catch (Exception e) {
       System.out.println(e);
  }}
  // 	Fim Bloco
 
  //	Pendente
  public void outAVariavelExpressao(AVariavelExpressao node){
	  String key = node.getVariavel().toString().trim();
	  AValorExpressao noValor;
  	if (symbol_table.containsKey(key)) {
  		// Retornar um tipo de nó, mas qual?
  		// Se houver letras, é uma string. Se não, se houver virgula é real, se não é inteiro.
  		String conteudo = symbol_table.get(key).toString().trim();
  			// Tipo numérico
  		if(conteudo.matches("^[0-9]+[,]{0,1}[0,9]+")){
	   	    	if(conteudo.indexOf(',')>=0) {
	   	    		 TNumInteiro tokenInteiro = new TNumInteiro(conteudo);
	   		    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
	   		    	 noValor = new AValorExpressao(valorInteiro);
	   		    	 node.replaceBy(noValor);
	   	    	 } else {
	   	    		 TNumReal tokenInteiro = new TNumReal(conteudo);
	   		    	 AValRealValor valorInteiro = new AValRealValor(tokenInteiro);
	   		    	 noValor = new AValorExpressao(valorInteiro);
	   		    	 node.replaceBy(noValor);
	   	    	 }
  		} 
  		// Tipo String
  		else {
  			TVetorString tokenChar = new TVetorString(conteudo);
		        AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
		        noValor = new AValorExpressao(valorChar);
		      	node.replaceBy(noValor);
  		}
  	} 
  	// Caso não haja a variavel na tabela
  	else {
  		System.out.println("Erro: Variavel '"+key+"' não existe na tabela.");
  	}
  }

  /**
  *	Comando
  **/
  //	Corrigir atribuição com variaveis, provavel que seja dependencia
  public void outAAtribuicaoComando(AAtribuicaoComando node) {
	  String key = node.getVariavel().toString().trim();
	  String value = node.getExpressao().toString();
      if (symbol_table.containsKey(key)) {
        symbol_table.replace(key, value);
    	  System.out.println(
            "Atribuir "
            + key
            + " com valor "
            + value
            +"."
            );
      } else {
        System.out.println("Variavel '"+key+"' não declarada.");
      }
	}
  //	Pendente: Arrumar checagem de tipo
  //	Inicialmente lê uma string, mas mudar para o tipo de dado ou dar erro.
  public void outALeituraComando(ALeituraComando node){
	  String value = null;
	  Scanner leitor = new Scanner(System.in);
	  //Como escolherei?
	  leitor.close();
	  if (symbol_table.containsKey(node.getVariavel().toString())) {
		  symbol_table.put(node.getVariavel().toString(), value);
	  } 
	  else {
		  // Como pegar o tipo da variavel?!
		  System.out.println("(!) Comando Leitura não implementado totalmente.");
	  }
  }
  
  //	Corrigir impressão de valor das variaveis (função dependente)
  public void outAEscritaComando(AEscritaComando node){
	  if(node.getExpressao().getClass().getSimpleName().equals("AVariavelExpressao")) {
		  if (symbol_table.containsKey(node.getExpressao().toString())) {
			  System.out.println(symbol_table.get(node.getExpressao().toString()));
		  }
	  }
	  else {
		  String conteudo = node.getExpressao().toString();
		  System.out.println(">>> "+conteudo);
	  }
  }
  public void outASeComando(ASeComando node){}
  public void outAAvalieComando(AAvalieComando node){}
  public void outAEnquantoComando(AEnquantoComando node){}
  public void outARepitaComando(ARepitaComando node){}
  public void outAParaComando(AParaComando node){}
  public void outAParaSegComando(AParaSegComando node){}

  /**
   * Se_corpo
   **/
  public void outASeSenaoSeCorpo (ASeSenaoSeCorpo node){}
  public void outASeUnicoSeCorpo(ASeUnicoSeCorpo node){}

  /**
   *Caso_comandos
   **/
  public void outACasoCasoComandos (ACasoCasoComandos node){}
  public void outACasosCasoComandos(ACasosCasoComandos node){}
  
  
}
