#!/bin/bash

# 1. Compila SimplifySyntaxJson.java
echo "Compiling Java..."
javac -cp json.jar SimplifySyntaxJson.java || { echo "Compilation failed"; exit 1; }

# 2. Crea il JAR
echo "Creating JAR..."
jar cfe SimplifySyntaxJson.jar SimplifySyntaxJson SimplifySyntaxJson.class || { echo "JAR creation failed"; exit 1; }

# 3. Esegui per generare output.json
echo "Running simplification..."
java -cp json.jar:SimplifySyntaxJson.jar SimplifySyntaxJson input.json output.json || { echo "Java execution failed"; exit 1; }

# 4. Avvia il server Spring Boot in background
echo "Starting Spring Boot server..."
./gradlew bootRun &

SERVER_PID=$!
echo "Server started with PID $SERVER_PID"

# 5. Attendi qualche secondo per assicurarsi che il server sia attivo
sleep 2

# 6. Rendi eseguibile lo script per generare il PNG
chmod +x generate-syntax-tree.sh

# 7. Genera l'immagine PNG
echo "Generating syntax tree PNG..."
bash generate-syntax-tree.sh < output.json || { echo "Tree generation failed"; kill $SERVER_PID; exit 1; }

# 8. Ferma il server
echo "Stopping server..."
kill $SERVER_PID

echo "Process complete. 'tree.png' should be in the current directory."
