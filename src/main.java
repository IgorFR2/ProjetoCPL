import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

import compilador.interpret.Interpreter;
import compilador.lexer.Lexer;
import compilador.lexer.LexerException;
import compilador.node.Start;
import compilador.node.Token;
import compilador.parser.Parser;

public class main {
    //	public static void escreva(Lexer lexer){
    //		String fim = null;
    //		do{
    //        	try {
    //        		fim = lexer.next().getClass().getSimpleName();
    //				switch(fim){
    //					case "TEnter":
    //						System.out.print("\n"); break;
    //					case "TEspaco":
    //						System.out.print(" "); break;
    //					case "TTab":
    //						System.out.print("\t"); break;
    //					case "TComentarioFimErro":
    //						System.out.print("Erro de comentario de bloco. Linha: "
    //					                     + lexer.peek().getLine() + ", posicao:"
    //								         + lexer.peek().getPos() + ". :"
    //					                     + lexer.peek().getClass().getSimpleName());
    //						break;
    //					case "TEOF":
    //						break;
    //					default:
    //						System.out.print(lexer.peek().getClass().getSimpleName()+' ');
    //				}
    //				
    //        	} catch (Exception error){
    //        		System.out.print(error.getMessage());
    //        	} 
    //        	
    //		}while(!fim.equals("TEOF"));	
    //	}
    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                /* Form our AST */
                comentarioAninhado lexer = new comentarioAninhado(new PushbackReader(new FileReader(args[0]), 1024));
                //           escreva(lexer);
                //System.out.print(lexer.next().getClass().getSimpleName()+" ");	
                while (true) {
                    try {
                        while (!(lexer.next().getClass().getSimpleName().equals("EOF"))) {
                            switch (lexer.peek().getClass().getSimpleName()) {
                                case "TEnter":
                                    System.out.print("\n");
                                    break;
                                case "TEspaco":
                                    System.out.print(" ");
                                    break;
                                case "TTab":
                                    System.out.print("\t");
                                    break;
                                case "TComentarioFimErro":
                                    System.out.print("Erro de comentario de bloco. Linha: " +
                                        lexer.peek().getLine() + ", posicao:" +
                                        lexer.peek().getPos() + ". :" +
                                        lexer.peek().getClass().getSimpleName());
                                    break;
                                case "EOF":
                                    break;
                                default:
                                    System.out.print(lexer.peek().getClass().getSimpleName() + ' ');
                            }
                        }
                        // Se chegou até o fim da entrada, pare o loop
                        break;
                    } catch (Exception error) {
                        System.out.print(error.getMessage() + '\n');
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.err.println("usage: java simpleAdder inputFile");
            System.exit(1);
        }
    }
}