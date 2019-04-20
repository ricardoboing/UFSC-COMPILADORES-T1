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
	@echo "Update file ListaDeToken.txt"
	@mv xcc.java xcc.jbvb
	@rm *.class
	@rm *.java
	@rm *.tokens
	@rm *.interp
	@mv xcc.jbvb xcc.java
