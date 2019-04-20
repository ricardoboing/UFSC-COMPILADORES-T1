import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class xcc {
	public static void main(String[] args) throws IOException {
		String diretorio = Paths.get("").toAbsolutePath().toString()+"/";
		String arquivo = args[0];
		
		CharStream input = CharStreams.fromFileName(diretorio+arquivo);

		XccLexer lexer = new XccLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.getNumberOfOnChannelTokens();
		
		List<Token> listTokens = tokens.getTokens();
		FileWriter novoArquivo;
		
		try {
			novoArquivo = new FileWriter(new File(diretorio+"ListaDeToken.txt"));
			
			for (int c = 0; c < listTokens.size(); c++) {
				Token token = listTokens.get(c);
				
				if (token.getText() != "<EOF>") {
					novoArquivo.write(token.getText()+"\n");
				}
			}
			
			novoArquivo.close();
		} catch (IOException e) {
		} catch (Exception e) {
		}
	}
}
