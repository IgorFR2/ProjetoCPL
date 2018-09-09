import compilador.analysis.DepthFirstAdapter;
import compilador.node.*;

import java.util.Hashtable;
import java.util.LinkedList;

import java.lang.System;
import java.util.Scanner;
/**
 * OBSERVAÇÃO IMPORTANTE: Nem todas as classes serão UTEIS! Algumas não possuem alterações necessárias para o desenvolvimento.
 * 						  A própria definição nas outras etapas já geram completas 
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
  // Parece OK
  // Se a Variavel for chamada numa declaração e ela já existir, responda com o erro, se não declare-a.
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
/*  public void outAValCaractereValor(AValCaractereValor node) {}
  public void outAValInteiroValor(AValInteiroValor node) {}
  public void outAValRealValor(AValRealValor node) {}*/
  
  /**
   * Expressões
   **/
  
  // Bloco OK
  public void outAIgualdadeExpressao(AIgualdadeExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  if (
			  node.getLeft().toString().trim().equals(node.getRight().toString().trim())
		)
		  tokenChar = new TVetorString("verdadeiro");
	  else	
		  tokenChar = new TVetorString("falso");
	  AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
	  noValor = new AValorExpressao(valorChar);
	  node.replaceBy(noValor);

  }
  public void outADiferenteExpressao(ADiferenteExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  if (
			  node.getLeft().toString().trim().equals(node.getRight().toString().trim())
		)
		  tokenChar = new TVetorString("falso");
	  else	
		  tokenChar = new TVetorString("verdadeiro");
	  AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
	  noValor = new AValorExpressao(valorChar);
	  node.replaceBy(noValor);
  }
  public void outAEExpressao(AEExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  if (
			  node.getLeft().toString().trim().equals("verdadeiro") & node.getRight().toString().trim().equals("verdadeiro")
		)
		  tokenChar = new TVetorString("verdadeiro");
	  else	
		  tokenChar = new TVetorString("falso");
	  AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
	  noValor = new AValorExpressao(valorChar);
	  node.replaceBy(noValor);
  }
  public void outAOuExpressao(AOuExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  if (
			  node.getLeft().toString().trim().equals("verdadeiro") | node.getRight().toString().trim().equals("verdadeiro")
		)
		  tokenChar = new TVetorString("verdadeiro");
	  else	
		  tokenChar = new TVetorString("falso");
	  AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
	  noValor = new AValorExpressao(valorChar);
	  node.replaceBy(noValor);
  }
  public void outAXorExpressao(AXorExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  if (
			  node.getLeft().toString().trim().equals(node.getRight().toString().trim())
		)
		  tokenChar = new TVetorString("falso");
	  else	
		  tokenChar = new TVetorString("verdadeiro");
	  AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
	  noValor = new AValorExpressao(valorChar);
	  node.replaceBy(noValor);
  }
  public void outANotExpressao(ANotExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  if (
			  node.getExpressao().toString().trim().equals("falso")
		)
		  tokenChar = new TVetorString("verdadeiro");
	  else	
		  tokenChar = new TVetorString("falso");
	  AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
	  noValor = new AValorExpressao(valorChar);
	  node.replaceBy(noValor);
  }
  public void outAInversaoExpressao(AInversaoExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  if (
			  node.getExpressao().toString().trim().equals("falso")
		)
		  tokenChar = new TVetorString("verdadeiro");
	  else	
		  tokenChar = new TVetorString("falso");
	  AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
	  noValor = new AValorExpressao(valorChar);
	  node.replaceBy(noValor);
  }
  //Fim Bloco
  
  // Bloco OK
  public void outAMenorIgExpressao(AMenorIgExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  String esquerda = node.getLeft().toString().trim();
	  String direita = node.getRight().toString().trim();
	  double floatE, floatD;
	  int intE, intD;
//	  Se ambos forem números (Int Real | Real Real | Int Int)
	  if  (esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		&& esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		){
 	    	if(esquerda.indexOf(',')>=0 | direita.indexOf(',')>=0  ) {
 	    		floatE = Double.valueOf(esquerda.replace(',', '.'));
 	    		floatD = Double.valueOf(direita.replace(',', '.'));
 	    		tokenChar = new TVetorString(floatE<=floatD?"verdadeiro":"falso");
 	    	} else {
 	    		intE = Integer.valueOf(esquerda);
 	    		intD = Integer.valueOf(direita);
 	    		tokenChar = new TVetorString(intE<=intD?"verdadeiro":"falso");
 	    	}
	  }
// 	    	 Caso haja String ( Num Str | Str Str)
	  else {
 	    		if (
 	    				  node.getLeft().toString().trim().compareTo(node.getRight().toString().trim()) <= 0 
 	    			)
 	    			  tokenChar = new TVetorString("verdadeiro");
 	    		  else	
 	    			  tokenChar = new TVetorString("falso");
 	    
 	    		AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
 	    		noValor = new AValorExpressao(valorChar);
 	    		node.replaceBy(noValor);
 	    	}
}
  public void outAMaiorIgExpressao(AMaiorIgExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  String esquerda = node.getLeft().toString().trim();
	  String direita = node.getRight().toString().trim();
	  double floatE, floatD;
	  int intE, intD;
//	  Se ambos forem números (Int Real | Real Real | Int Int)
	  if  (esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		&& esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		){
 	    	if(esquerda.indexOf(',')>=0 | direita.indexOf(',')>=0  ) {
 	    		floatE = Double.valueOf(esquerda.replace(',', '.'));
 	    		floatD = Double.valueOf(direita.replace(',', '.'));
 	    		tokenChar = new TVetorString(floatE>=floatD?"verdadeiro":"falso");
 	    	} else {
 	    		intE = Integer.valueOf(esquerda);
 	    		intD = Integer.valueOf(direita);
 	    		tokenChar = new TVetorString(intE>=intD?"verdadeiro":"falso");
 	    	}
	  }
// 	    	 Caso haja String ( Num Str | Str Str)
	  else {
 	    		if (
 	    				  node.getLeft().toString().trim().compareTo(node.getRight().toString().trim()) >= 0 
 	    			)
 	    			  tokenChar = new TVetorString("verdadeiro");
 	    		  else	
 	    			  tokenChar = new TVetorString("falso");
 	    
 	    		AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
 	    		noValor = new AValorExpressao(valorChar);
 	    		node.replaceBy(noValor);
 	    	}
  }
  public void outAMenorExpressao(AMenorExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  String esquerda = node.getLeft().toString().trim();
	  String direita = node.getRight().toString().trim();
	  double floatE, floatD;
	  int intE, intD;
//	  Se ambos forem números (Int Real | Real Real | Int Int)
	  if  (esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		&& esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		){
 	    	if(esquerda.indexOf(',')>=0 | direita.indexOf(',')>=0  ) {
 	    		floatE = Double.valueOf(esquerda.replace(',', '.'));
 	    		floatD = Double.valueOf(direita.replace(',', '.'));
 	    		tokenChar = new TVetorString(floatE<floatD?"verdadeiro":"falso");
 	    	} else {
 	    		intE = Integer.valueOf(esquerda);
 	    		intD = Integer.valueOf(direita);
 	    		tokenChar = new TVetorString(intE<intD?"verdadeiro":"falso");
 	    	}
	  }
// 	    	 Caso haja String ( Num Str | Str Str)
	  else {
 	    		if (
 	    				  node.getLeft().toString().trim().compareTo(node.getRight().toString().trim()) < 0 
 	    			)
 	    			  tokenChar = new TVetorString("verdadeiro");
 	    		  else	
 	    			  tokenChar = new TVetorString("falso");
 	    
 	    		AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
 	    		noValor = new AValorExpressao(valorChar);
 	    		node.replaceBy(noValor);
 	    	}
  }
  public void outAMaiorExpressao(AMaiorExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  String esquerda = node.getLeft().toString().trim();
	  String direita = node.getRight().toString().trim();
	  double floatE, floatD;
	  int intE, intD;
//	  Se ambos forem números (Int Real | Real Real | Int Int)
	  if  (esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		&& esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		){
 	    	if(esquerda.indexOf(',')>=0 | direita.indexOf(',')>=0  ) {
 	    		floatE = Double.valueOf(esquerda.replace(',', '.'));
 	    		floatD = Double.valueOf(direita.replace(',', '.'));
 	    		tokenChar = new TVetorString(floatE>floatD?"verdadeiro":"falso");
 	    	} else {
 	    		intE = Integer.valueOf(esquerda);
 	    		intD = Integer.valueOf(direita);
 	    		tokenChar = new TVetorString(intE>intD?"verdadeiro":"falso");
 	    	}
	  }
// 	    	 Caso haja String ( Num Str | Str Str)
	  else {
 	    		if (
 	    				  node.getLeft().toString().trim().compareTo(node.getRight().toString().trim()) > 0 
 	    			)
 	    			  tokenChar = new TVetorString("verdadeiro");
 	    		  else	
 	    			  tokenChar = new TVetorString("falso");
 	    
 	    		AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
 	    		noValor = new AValorExpressao(valorChar);
 	    		node.replaceBy(noValor);
 	    	}
  }
  // Fim Bloco
  
  // 	Bloco OK: Operações básicas matemáticas ( + - x / )
  public void outASomarExpressao(ASomarExpressao node) {
	
	     try {
	    	 String resultado;
	    	 AValorExpressao noValor;
	    	 /*
	    	  * Verificar se é inteiro ou float
	    	  * */
	    	 if(node.getLeft().toString().indexOf(',')<0 &&
	    		node.getRight().toString().indexOf(',')<0 ) 
	    	 {
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
	    	 if(node.getLeft().toString().indexOf(',')<0 &&
	 	    		node.getLeft().toString().indexOf(',')<0 ) 
	 	    	 {
	    		 resultado = Integer.toString(
	    				 Integer.valueOf(node.getLeft().toString().trim()) -
	    				 Integer.valueOf(node.getRight().toString().trim())
	    				 );
	    		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
		    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
	    	 } else {
	    		 
	    		 resultado = Double.toString(
	    				 Double.valueOf(node.getLeft().toString().trim().replace(',', '.')) -
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
	    	 if(node.getLeft().toString().indexOf(',')<0 &&
	 	    		node.getLeft().toString().indexOf(',')<0 ) 
	 	    	 {
	    		 resultado = Integer.toString(
	    				 Integer.valueOf(node.getLeft().toString().trim()) * 
	    				 Integer.valueOf(node.getRight().toString().trim())
	    				 );
	    		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
		    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
	    	 } else {
	    		 
	    		 resultado = Double.toString(
	    				 Double.valueOf(node.getLeft().toString().trim().replace(',', '.')) *
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
	 	if(node.getLeft().toString().indexOf(',')<0 &&
	    		node.getLeft().toString().indexOf(',')<0 ) 
	    	 {
	 		 resultado = Integer.toString(
	 				 Integer.valueOf(node.getLeft().toString().trim()) /
	 				 Integer.valueOf(node.getRight().toString().trim())
	 				 );
	 		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
		    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
		    	 noValor = new AValorExpressao(valorInteiro);
	 	 } else {
	 		 
	 		 resultado = Double.toString(
	 				 Double.valueOf(node.getLeft().toString().trim().replace(',', '.')) /
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
 
  // Aparentemente OK.
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
  // Substituição necessaria apenas apra operações boleanas
  public void outAValorExpressao(AValorExpressao node) {
	  if(node.parent().toString().contentEquals(
			  "AIgualdadeExpressao"
					  +"ADiferenteExpressao"
					  +"AEExpressao"
					  +"AOuExpressao"
					  +"AXorExpressao"
					  +"ANotExpressao"
					  +"AInversaoExpressao")) {
		  String conteudo = node.getValor().toString().trim();
		  AValorExpressao noValor;
		  TVetorString tokenChar;
		  if (conteudo.equals("0"))
			  tokenChar = new TVetorString("falso");
		  else	
			  tokenChar = new TVetorString("verdadeiro");
		  AValCaractereValor valorChar= new AValCaractereValor(tokenChar);
		  noValor = new AValorExpressao(valorChar);
		  node.replaceBy(noValor);
	  }
  }

  /**
  *	Comando
  **/
  //	Aparentemente OK.
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
  //	Ok: Lê e armazena no lugar certo.
  public void outALeituraComando(ALeituraComando node){
	  Scanner leitor = new Scanner(System.in);
	  String conteudo = "9";
//	  String conteudo = leitor.nextLine();
	  
	  leitor.close();
//	  System.out.println("#$ "+symbol_table.containsKey(node.getVariavel().toString().trim()));
	  if (symbol_table.containsKey(node.getVariavel().toString().trim())) {
		  symbol_table.replace(node.getVariavel().toString().trim() , conteudo);
		  System.out.println("Atribuido valor lido '"+conteudo+"' à variavel '"+node.getVariavel().toString().trim()+"'.");
	  } 
	  else {
		  System.out.println("Erro na linha: atribuição não permitida.");
	  }
  }
  //	Ok: Imprime conteudo de variaveis, valores e strings
  public void outAEscritaComando(AEscritaComando node){
	  if(node.getExpressao().getClass().getSimpleName().equals("AVariavelExpressao")) {
		  if (symbol_table.containsKey(node.getExpressao().toString())) {
			  System.out.println(">>> "+symbol_table.get(node.getExpressao().toString()));
		  }
	  }
	  else {
		  String conteudo = node.getExpressao().toString();
		  System.out.println(">>> "+conteudo);
	  }
  }
  //
  public void outASeComando(ASeComando node){

  }
  
  public void outAAvalieComando(AAvalieComando node){}
  public void outAEnquantoComando(AEnquantoComando node){}
  public void outARepitaComando(ARepitaComando node){}
  public void outAParaComando(AParaComando node){}
  public void outAParaSegComando(AParaSegComando node){}

  /**
   * Se_corpo
   **/
  public void outASeSenaoSeCorpo (ASeSenaoSeCorpo node){
	  
  }
  public void outASeUnicoSeCorpo(ASeUnicoSeCorpo node){}
  public void outASeCorpo(ASeCorpo node){
	  
  }

  /**
   *Caso_comandos
   **/
  public void outACasoCasoComandos (ACasoCasoComandos node){}
  public void outACasosCasoComandos(ACasosCasoComandos node){}
  
  
}