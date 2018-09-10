import compilador.analysis.DepthFirstAdapter;
import compilador.node.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.lang.System;
import java.util.Scanner;

public class Tradutor extends DepthFirstAdapter {
  Hashtable symbol_table = new Hashtable();
  //			Esquecidos
  public void outACorpoPrograma(ACorpoPrograma node){} 
  public void outADeclaracao(ADeclaracao node){}
  public void outAConstanteDeclaracao(AConstanteDeclaracao node){}
  public void outAInteiroTiposDados(AInteiroTiposDados node) {}
  public void outARealTiposDados(ARealTiposDados node) {}
  public void outACaractereTiposDados(ACaractereTiposDados node) {}
  public void outAVetorVariavel(AVetorVariavel node) {}
  //			Manipula��o de dados e valores
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
//	        System.out.println("Declarada a variavel "+key+".");
	        symbol_table.put(key, key);
	      }
	    }
	  }
  public void outAVariavelExpressao(AVariavelExpressao node){
	  String key = node.getVariavel().toString().trim();
	  AValorExpressao noValor;
  	if (symbol_table.containsKey(key)) {
  		// Retornar um tipo de n�, mas qual?
  		// Se houver letras, � uma string. Se n�o, se houver virgula � real, se n�o � inteiro.
  		String conteudo = symbol_table.get(key).toString().trim();
  			// Tipo num�rico
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
  	// Caso n�o haja a variavel na tabela
  	else {
  		System.out.println("Erro na linha <linha> Variavel '"+key+"' n�o existe na tabela.");
  	}
  }
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
  // 			Booleanos
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
  // 			Logico
  public void outAMenorIgExpressao(AMenorIgExpressao node){
	  AValorExpressao noValor;
	  TVetorString tokenChar;
	  String esquerda = node.getLeft().toString().trim();
	  String direita = node.getRight().toString().trim();
	  double floatE, floatD;
	  int intE, intD;
//	  Se ambos forem n�meros (Int Real | Real Real | Int Int)
	  if  (esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		&& direita.matches("^[0-9]+[,]{0,1}[0,9]+")
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
//	  Se ambos forem n�meros (Int Real | Real Real | Int Int)
	  if  (esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		&& direita.matches("^[0-9]+[,]{0,1}[0,9]+")
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
//	  Se ambos forem n�meros (Int Real | Real Real | Int Int)
	  if  (esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		&& direita.matches("^[0-9]+[,]{0,1}[0,9]+")
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
//	  Se ambos forem n�meros (Int Real | Real Real | Int Int)
	  if  (esquerda.matches("^[0-9]+[,]{0,1}[0,9]+")
		&& direita.matches("^[0-9]+[,]{0,1}[0,9]+")
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
  // 			Matematico
  public void outASomarExpressao(ASomarExpressao node) {
	  String esquerdo = node.getLeft().toString().trim();
	  String direito =  node.getRight().toString().trim();
	  if(eNumero(esquerdo) && eNumero(direito))
	  	{
			 try {
		 	 String resultado;
		 	 AValorExpressao noValor;
		 	 /*
		 	  * Verificar se � inteiro ou float
		 	  * */
		 	 if(esquerdo.indexOf(',')<0 &&
		 		direito.indexOf(',')<0 ) 
		 	 {
		 		 resultado = Integer.toString(
		 				 Integer.valueOf(esquerdo) +
		 				 Integer.valueOf(direito)
		 				 );
		 		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
			    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
			    	 noValor = new AValorExpressao(valorInteiro);
		 	 } else {
		 		 
		 		 resultado = Double.toString(
		 				 Double.valueOf(esquerdo.replace(',', '.')) +
		 				 Double.valueOf(direito.replace(',', '.'))
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
		} else {
			System.out.println("Erro na linha "+"<linha>"+" (Caracteres n�o podem ser somados).");
			
		}
	}
  public void outASubtrairExpressao(ASubtrairExpressao node){
	  String esquerdo = node.getLeft().toString().trim();
	  String direito =  node.getRight().toString().trim();
	  if(eNumero(esquerdo) && eNumero(direito))
	  	{
			 try {
		 	 String resultado;
		 	 AValorExpressao noValor;
		 	 /*
		 	  * Verificar se � inteiro ou float
		 	  * */
		 	 if(esquerdo.indexOf(',')<0 &&
		 		direito.indexOf(',')<0 ) 
		 	 {
		 		 resultado = Integer.toString(
		 				 Integer.valueOf(esquerdo) -
		 				 Integer.valueOf(direito)
		 				 );
		 		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
			    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
			    	 noValor = new AValorExpressao(valorInteiro);
		 	 } else {
		 		 
		 		 resultado = Double.toString(
		 				 Double.valueOf(esquerdo.replace(',', '.')) -
		 				 Double.valueOf(direito.replace(',', '.'))
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
		} else {
			System.out.println("Erro na linha "+"<linha>"+" (Caracteres n�o podem ser subtraidos).");
		}
  }
  public void outAMultiplicarExpressao(AMultiplicarExpressao node){
	  String esquerdo = node.getLeft().toString().trim();
	  String direito =  node.getRight().toString().trim();
	  if(eNumero(esquerdo) && eNumero(direito))
	  	{
			 try {
		 	 String resultado;
		 	 AValorExpressao noValor;
		 	 /*
		 	  * Verificar se � inteiro ou float
		 	  * */
		 	 if(esquerdo.indexOf(',')<0 &&
		 		direito.indexOf(',')<0 ) 
		 	 {
		 		 resultado = Integer.toString(
		 				 Integer.valueOf(esquerdo) *
		 				 Integer.valueOf(direito)
		 				 );
		 		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
			    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
			    	 noValor = new AValorExpressao(valorInteiro);
		 	 } else {
		 		 
		 		 resultado = Double.toString(
		 				 Double.valueOf(esquerdo.replace(',', '.')) *
		 				 Double.valueOf(direito.replace(',', '.'))
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
		} else {
			System.out.println("Erro na linha "+"<linha>"+" (Caracteres n�o podem ser multiplicados).");
		}
  }
  public void outADividirExpressao(ADividirExpressao node){
	  String esquerdo = node.getLeft().toString().trim();
	  String direito =  node.getRight().toString().trim();
	  if(eNumero(esquerdo) && eNumero(direito))
	  	{
			 try {
		 	 String resultado;
		 	 AValorExpressao noValor;
		 	 /*
		 	  * Verificar se � inteiro ou float
		 	  * */
		 	 if(esquerdo.indexOf(',')<0 &&
		 		direito.indexOf(',')<0 ) 
		 	 {
		 		 resultado = Integer.toString(
		 				 Integer.valueOf(esquerdo) /
		 				 Integer.valueOf(direito)
		 				 );
		 		 TNumInteiro tokenInteiro = new TNumInteiro(resultado);
			    	 AValInteiroValor valorInteiro = new AValInteiroValor(tokenInteiro);
			    	 noValor = new AValorExpressao(valorInteiro);
		 	 } else {
		 		 
		 		 resultado = Double.toString(
		 				 Double.valueOf(esquerdo.replace(',', '.')) /
		 				 Double.valueOf(direito.replace(',', '.'))
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
		} else {
			System.out.println("Erro na linha "+"<linha>"+" (Caracteres n�o podem ser divididos).");
		}
	  }
  //			Comandos base
  public void outAAtribuicaoComando(AAtribuicaoComando node) {
	  try {
			  String key = node.getVariavel().toString().trim();
			  String value = node.getExpressao().toString();
			  //Tabela comt�m o simbolo, agora verificar tipo para atribuir ou n�o
		      if (symbol_table.containsKey(key)) {
		    	  // N�o s�o tipos iguais, portanto erro
		    	  if(eNumero(symbol_table.get(key).toString())^eNumero(value))
		    		  System.out.println(
		    				  "Erro na linha <linha>. Atribui��o n�o permitida (vari�vel '"
		    				  + key
		    				  +"' � do tipo "
		    				  + eTipo(key)
		    				  + " e '"
		    				  + value
		    				  + " � do tipo "
		    				  + eTipo(value)
		    				  + ")."
		    		  			);
		    	  else {
		    		  if(eTipo(key).equals("real"))
		    		  {
		    			  	 if(value.indexOf(",")<0)
		    			  		 value.concat(",0");
		    		  } 
		    		  if(eTipo(key).equals("inteiro") & eTipo(value).equals("real"))
		    			  System.out.println("Erro na linha <linha>. Atribui��o n�o permitida (vari�vel '"
		    				  + key
		    				  +"' � do tipo "
		    				  + eTipo(key)
		    				  + " e '"
		    				  + value
		    				  + " � do tipo "
		    				  + eTipo(value)
		    				  + ").");
		    		  else { 
		    			 
						  symbol_table.replace(key, value);
//				    	  System.out.println(
//				            "Atribuir "
//				            + key
//				            + " com valor "
//				            + value
//				            +"."
//				            );  
		    		  }
		    	  }
			      
		      } else {
		        System.out.println("Erro na linha <linha>. (Variavel '"+key+"' n�o declarada).");
		      }
	  	} catch (Exception e) {
//	  		System.out.println("Erro na linha <linha>. Atribui��o n�o permitida.");
	  	}
	}
  public void outALeituraComando(ALeituraComando node){
	  Scanner leitor = new Scanner(System.in);
	  String conteudo = "9";
//	  String conteudo = leitor.nextLine();
	  
	  leitor.close();
	  if (symbol_table.containsKey(node.getVariavel().toString().trim())) {
		  symbol_table.replace(node.getVariavel().toString().trim() , conteudo);
		  System.out.println("Atribuido valor lido '"+conteudo+"' � variavel '"+node.getVariavel().toString().trim()+"'.");
	  } 
	  else {
		  System.out.println("Erro na linha: atribui��o n�o permitida.");
	  }
  }
  public void outAEscritaComando(AEscritaComando node){
	  if(node.getExpressao().getClass().getSimpleName().equals("AVariavelExpressao")) {
		  if (symbol_table.containsKey(node.getExpressao().toString())) {
			  System.out.println(symbol_table.get(node.getExpressao().toString()));
		  }
	  }
	  else {
		  String conteudo = node.getExpressao().toString();
		  System.out.println(conteudo);
	  }
  }
  // 			Repeat Block
  public void outAEnquantoComando(AEnquantoComando node){}
  public void outARepitaComando(ARepitaComando node){}
  public void outAParaComando(AParaComando node){}
  public void outAParaSegComando(AParaSegComando node){}
  // 			Dead Block
  public void outASeComando(ASeComando node){}
  public void outASeSenaoSeCorpo (ASeSenaoSeCorpo node){}
  public void outASeUnicoSeCorpo(ASeUnicoSeCorpo node){}
  public void outASeCorpo(ASeCorpo node){}
  public void outAAvalieComando(AAvalieComando node){}
  public void outACasoCasoComandos (ACasoCasoComandos node){}
  public void outACasosCasoComandos(ACasosCasoComandos node){}
  //			Auxiliares
  public boolean eNumero(String x) {
	  return x.matches("[0-9]+[,]?[0-9]*");
	 }
  public String eTipo(String x) {
	  if (eNumero(x)) {
		  if (x.indexOf(',')>=0)
			  return "real";
		  else
			  return "inteiro";
	  } else return "caractere";
  }
}