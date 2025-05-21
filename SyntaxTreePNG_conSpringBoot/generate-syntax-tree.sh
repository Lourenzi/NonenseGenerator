#!/bin/bash

echo "📥 Incolla qui l'output JSON della Google NLP API (termina con CTRL+D):"
json_input=$(</dev/stdin)

# Estrai i token in formato richiesto dal backend
tokens=$(echo "$json_input" | jq '[.tokens[] | {content: .text.content, headTokenIndex: .dependencyEdge.headTokenIndex}]')

# Mostra il payload
echo "📦 Payload JSON generato:"
echo "$tokens"

# Invia la richiesta
curl -X POST http://localhost:8080/tree/generate \
     -H "Content-Type: application/json" \
     -d "$tokens" \
     --output tree.png

echo "✅ Immagine salvata come tree.png"
