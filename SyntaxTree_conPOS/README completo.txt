Automazione creazione SyntaxTree in formato png.
Ora basta incollare l'output GREZZO dell'API nel file input.json e poi eseguire da terminale (all'interno della cartella) :
- "chmod +x run_all.sh" (solo la prima volta)
- "./run_all.sh"
In automatico nella cartella sarà generato il png
N.B. Nel caso in cui non funzioni potrebbe essere dovuto allo sleep time impostato dopo l'accensione del server, per sistemare andare dentro il file run_all.sh e a riga  23 aumentare il numero affianco a sleep. Fatemi sapere se anche a voi va così (impostato a 2 seocndi) o se dipende dalle prestazioni del pc.
Aggiunta anche cartella "Esempio" con esempi di input e png generati da questi ultimi.
