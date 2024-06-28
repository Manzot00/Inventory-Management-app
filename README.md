# Inventory-Management-app

# GUIDA INVENTORY MANAGEMENT SYSTEM
Questa applicazione si basa sull’architettura Model-View-Controller
AVVIO
Per il corretto funzionamento del programma bisogna assicurarsi di aver importato le librerie/Jar:
-	JRE System Library [Java-SE-21]
-	JavaFX SDK
-	JavaFX (user library)
-	Mysql-connetor-j-8.3.0.jar
Inoltre, dato che il programma usa un collegamento ad un database sql bisogna assicurarsi che il database “inventory” esista. In questo caso il database è sul localhost accessibile sulla porta 3306 (è stato utilizzato xampp per la realizzazione del database).
Non è necessario creare tabelle e righe nel database basta che esso esista, dato che il programma crea le tabelle e le sue righe se non sono presenti.
Se questi passi sono rispettati basterà avviare il “Main.java” per far partire il programma.

# LOGIN
Una volta avviato il programma ci si troverà nella schermata di login. Gli utenti abilitati all’accesso sono:
•	Username: admin1	Password: password1
•	Username: admin2	Password: password2
•	Username: admin3	Password: password3
Se vengono inserite queste credenziali, premendo sul pulsante “Login”, si potrà accedere alle funzionalità del programma, altrimenti verrà visualizzato un messaggio di errore.
Inoltre, è possibile chiudere il programma in qualsiasi momento premendo sulla X della finestra.
Un alert comparirà chiedendoci conferma.

# HOME
Effettuato il login ci si troverà nella schermata HOME dove potremo visualizzare il numero di ordini effettuati, il guadagno totale e il numero di Item che hanno una quantità minore di 10.
Sotto di essi ci sono 2 chart che rappresentano il guadagno e il numero di ordini nel tempo.
Sulla sinistra invece sono presenti 4 tasti per navigare tra le funzionalità del programma:
•	HOME (la schermata di partenza dove ci si trova una volta loggati)
•	MANAGE ITEMS
•	ORDERS
•	SIGN OUT (tasto per effettuare il logout e tornare alla schermata di login)

# MANAGE ITEMS
Premendo sul tasto “Manage Items” sulla sinistra ci si troverà nella schermata di gestione degli Item.
Sulla destra è presente una tabella con tutti gli Item presenti nel database, mentre sulla sinistra una lista di campi compilabili rappresentanti ogni campo di un Item.
Qui è possibile aggiungere, modificare o eliminare un item.
Per aggiungere un nuovo item bisogna compilare ogni campo utilizzando un ID ancora non in uso e poi premere sul pulsante “Add”. Se fatto correttamente il nuovo Item verrà visualizzato sulla tabella.
Per aggiornare un item bisogna assicurarsi che l’ID inserito sia quello corretto, poi basterà modificare i campi necessari (ogni campo deve essere compilato) e premere sul pulsante “Update”. Per semplificare le cose se si preme su un item della tabella, in automatico i campi verranno compilato con quelli dell’Item selezionato, facilitando così la modifica.
Per eliminare un item bisogna assicurarsi che l’ID inserito sia quello corretto (ogni campo deve essere compilato) e premere sul pulsante “Delete”. Anche qui per semplificare le cose possiamo semplicemente cliccare sull’item che vogliamo eliminare e premere il pulsante “Delete”.
Il pulsante clear permette di ripulire tutti i campi impostandoli vuoti.
Inoltre, è possibile cercare un determinato item per nome, tipo o supplier cercandolo nella textfield sopra la tabella. La tabella si aggiornerà in tempo reale mentre si scrive.
Le righe di colore rosso rappresentano gli item che hanno una quantità minore di 10.

# ORDERS 
Premendo sul pulsante “Orders” sulla sinistra ci troverà nella schermata degli ordini.
Qui possiamo effettuare degli ordini, ovvero vendere i nostri item.
Sulla sinistra è presente la stessa tabella della schermata “Manage Items” con apposita textfield per la ricerca. 
Sulla destra invece sono presenti vari campi. I primi due ID e Name sono utilizzate per selezionare l’item da aggiungere all’ordine. Se uno viene compilato l’altro in automatico viene compilato a sua volta in modo corrispondente, ma solo se l’ID o il nome inserito è esistente. Anche qui per semplificare le cose basterà premere su item della tabella per compilare in automatico e 2 campi.
Il campo “Quantity” permette di selezione quanti item di quello selezionare aggiungere all’ordine.
Premendo sul tasto “Add” una volta compilati i primi 3 campi, si potrà visualizzare un’anteprima dell’ordine nella ListView sottostante con relativo totale (Total).
Questo processo può essere ripetuto più volte, ogni volta verrà aggiornata l’anteprima e il totale in tempo reale.
Si ha anche la possibiità di aggiungere un cliente all’ordine tramite il campo “Customer” (è opzionale).
Il pulsante “Remove” serve per rimuovere una riga dall’ordine (quella selezionata nella ListView).
Il pulsante “Clear” invece cancella tutte le righe dall’ordine e resetta i campi.
Per confermare l’ordine bisogna premere sul pulsante “Sell”. L’ordine verrà effettuato e le quantità degli item, l’income totale, e il numero di ordini verranno aggiornati. Inoltre verrà data la possibilità di stampare una ricevuta che verrà salvata come file txt.
