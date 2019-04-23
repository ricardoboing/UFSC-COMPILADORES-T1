import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

public class xcc {
	public static void main(String[] args) throws IOException {
		String diretorio = Paths.get("").toAbsolutePath().toString()+"/";
		String arquivo = args[0];
		
		CharStream input = CharStreams.fromFileName(diretorio+arquivo);

		XccLexer lexer = new XccLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.getNumberOfOnChannelTokens();
		
		Vocabulary vocabularioAntlr = lexer.getVocabulary();
		
		List<Token> listTokens = tokens.getTokens();
		FileWriter arquivoListaDeToken, arquivoTabelaDeSimbolo;
		
		TabelaDeSimbolo tabelaDeSimbolo;
		tabelaDeSimbolo = new TabelaDeSimbolo(vocabularioAntlr);
		
		try {
			arquivoListaDeToken = new FileWriter(new File(diretorio+"ListaDeToken.txt"));
			arquivoTabelaDeSimbolo = new FileWriter(new File(diretorio+"TabelaDeSimbolo.txt"));
			
			System.out.println("-------------------- LISTA DE TOKENS --------------------");
			for (int c = 0; c < listTokens.size(); c++) {
				Token token = listTokens.get(c);
				
				if (token.getText() != "<EOF>") {
					String nomeDoToken;
					nomeDoToken = vocabularioAntlr.getSymbolicName(token.getType());
					
					arquivoListaDeToken.write(nomeDoToken+"\n");
					System.out.println(nomeDoToken);
					
					tabelaDeSimbolo.addLexema(token);
				}
			}
			System.out.println();
			
			String tabelaDeSimboloToString;
			tabelaDeSimboloToString = tabelaDeSimbolo.getToString();
			
			arquivoTabelaDeSimbolo.write(tabelaDeSimboloToString);
			System.out.println("-------------------- TABELA DE SIMBOLOS --------------------");
			System.out.println(tabelaDeSimboloToString);
			
			arquivoListaDeToken.close();
			arquivoTabelaDeSimbolo.close();
			
		} catch (IOException e) {
		} catch (Exception e) {}

		System.out.println("Update file ListaDeToken.txt");
		System.out.println("Update file TabelaDeSimbolos.txt");
	}
}
