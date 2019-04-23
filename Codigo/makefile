_ANTLR = antlr-4.7.2-complete.jar
_FILES = *.java
_XCC = java xcc

compilacao:
	@echo "Gerar compilador xcc -----------"
	java -jar $(_ANTLR) Xcc.g4
	javac -classpath $(_ANTLR) $(_FILES)
	@echo ""
	@echo "xcc run lex -----------"
	@$(_XCC) $(FILE)
	@mv xcc.java xcc.jbvb
	@mv Lexema.java Lexema.jbvb
	@mv OcorrenciaLexema.java OcorrenciaLexema.jbvb
	@mv TabelaDeSimbolo.java TabelaDeSimbolo.jbvb
	@mv TokenLexico.java TokenLexico.jbvb
	@rm *.class
	@rm *.java
	@rm *.tokens
	@rm *.interp
	@mv xcc.jbvb xcc.java
	@mv Lexema.jbvb Lexema.java
	@mv OcorrenciaLexema.jbvb OcorrenciaLexema.java
	@mv TabelaDeSimbolo.jbvb TabelaDeSimbolo.java
	@mv TokenLexico.jbvb TokenLexico.java
