📘 ISTRUZIONI PER GENERARE UN SYNTAX TREE IN PNG CON SPRING BOOT + JGRAPX

Questo progetto ti permette di creare un'immagine PNG di un albero sintattico a partire
dall'output JSON dell'API Google Cloud NLP.

────────────────────────────────────────────
📁 CONTENUTO DEL PROGETTO

- springboot-jgraphx/         ← cartella del progetto Spring Boot
- generate-syntax-tree.sh     ← script per incollare JSON e generare immagine
- README.txt                  ← questo file

────────────────────────────────────────────
▶️ PRIMA ESECUZIONE: COMPILAZIONE E AVVIO SERVER

1. Apri il terminale e vai nella cartella del progetto:

   cd ~/Desktop/springboot-jgraphx (esempio se si trova nel Desktop)

2. Rendi eseguibile lo script (una volta sola):

   chmod +x ../generate-syntax-tree.sh

3. Avvia il server Spring Boot:

   ./gradlew bootRun

   🔁 Mantieni il terminale aperto. Il server resterà attivo su:
   → http://localhost:8080/tree/generate

────────────────────────────────────────────
🖼️ GENERA L'IMMAGINE DA JSON (IN UN ALTRO TERMINALE)

1. Apri un altro terminale (mentre il server è attivo)

2. Vai nella cartella dove si trova lo script:

   cd ~/Desktop   (o dov'è salvato)

3. Lancia lo script:

   ./generate-syntax-tree.sh

4. Quando richiesto, incolla l'intero output JSON della NLP API
   (quello che contiene i "tokens" con "text.content" e "headTokenIndex")

5. Premi CTRL+D per inviare

✅ Il file tree.png verrà generato nella stessa cartella.

────────────────────────────────────────────
🛑 CHIUSURA

Per spegnere il server Spring Boot, torna al terminale dove gira
e premi CTRL+C

Qui sotto lascio un esempio di json che potete incollare nel terminale per testare:

{
  "tokens": [
    {
      "text": {
        "content": "The"
      },
      "dependencyEdge": {
        "headTokenIndex": 3
      }
    },
    {
      "text": {
        "content": "quick"
      },
      "dependencyEdge": {
        "headTokenIndex": 3
      }
    },
    {
      "text": {
        "content": "brown"
      },
      "dependencyEdge": {
        "headTokenIndex": 3
      }
    },
    {
      "text": {
        "content": "fox"
      },
      "dependencyEdge": {
        "headTokenIndex": 4
      }
    },
    {
      "text": {
        "content": "jumps"
      },
      "dependencyEdge": {
        "headTokenIndex": 4
      }
    },
    {
      "text": {
        "content": "over"
      },
      "dependencyEdge": {
        "headTokenIndex": 4
      }
    },
    {
      "text": {
        "content": "the"
      },
      "dependencyEdge": {
        "headTokenIndex": 8
      }
    },
    {
      "text": {
        "content": "lazy"
      },
      "dependencyEdge": {
        "headTokenIndex": 8
      }
    },
    {
      "text": {
        "content": "dog"
      },
      "dependencyEdge": {
        "headTokenIndex": 5
      }
    },
    {
      "text": {
        "content": "slowly"
      },
      "dependencyEdge": {
        "headTokenIndex": 4
      }
    }
  ]
}