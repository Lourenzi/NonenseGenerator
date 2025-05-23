Passaggi:
- incollare output API su input.json
- aprire il terminale dentro la cartella e scrivi : 
	javac -cp json.jar SimplifySyntaxJson.java
- crea il .jar : 
	jar cfe SimplifySyntaxJson.jar SimplifySyntaxJson SimplifySyntaxJson.class
- esegui: 
	java -cp json.jar:SimplifySyntaxJson.jar SimplifySyntaxJson input.json output.json
In seguito ti avrà creato il file output.json che è una versione semplificata dell'output grezzo dell'API, servirà ora per la creazione del syntax tree.
- fai partire il server : 
	./gradlew bootRun
Entra nella cartella da un altro terminale:
- rendi eseguibile il file .sh: 
	chmod +x generate-syntax-tree.sh    
- crea il png:
	bash generate-syntax-tree.sh < output.json
Ora nella cartella ci sara il file png contenete il syntax tree