import java.io.FileReader;
import java.io.PushbackReader;

import compilador.interpret.Interpreter;
import compilador.lexer.Lexer;
import compilador.node.Start;
import compilador.parser.Parser;

public class main { 
   public static void main(String[] args) { 
      if (args.length > 0) { 
         try { 
            /* Form our AST */ 
            Lexer lexer = new Lexer (new PushbackReader( 
               new FileReader(args[0]), 1024)); 
            
            while (!lexer.peek().getClass().getSimpleName().equals("EOF")){
            	switch(lexer.peek().getClass().getSimpleName()){
            		case "TEspaco":
            			System.out.print(" "); break;
            		case "TTab":
            			System.out.print("\t"); break;
            		default:
            			System.out.print(lexer.peek().getClass().getSimpleName());
            	}
            	System.out.print(" ");
            	lexer.next();
            	
            }
         } 
         catch (Exception e) { 
            System.out.println (e) ; 
         } 
      } else { 
         System.err.println("usage: java simpleAdder inputFile"); 
         System.exit(1); 
      } 
   } 
}