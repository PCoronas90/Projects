Progetto Search Engine
----------------------------------------------------------

Il programma prende in input dataset compressi di pagine HTML in formato warc. Features:
1) Feel lucky
2) Paginazione
3) Ricerca per immagini
4) Funzione "forse cercavi"
5) Snippet dei risultati
6) Tempo di ricerca
7) Numero di risultati
8) Score dei risultati
9) Visualizzazione della pagina HTML ricercata
-----------------------------------------------------------

L'Interfaccia web è stata realizzata tramite JSF e Tomcat v8. L'indicizzazione e la ricerca sono stati implementati tramite la libreria Apache Lucene. Per il parsing dei documenti HTML è stata utilizzata la libreria JUnit
----------------------------------------------------------------

Gli step che il programma segue sono divisi in:
1) Estrazione dei singoli record HTML dal file Warc 
2) Parsgin dei documenti HTML
3) Indicizzazione del contenuto
4) Indicizzazione per immagini
5) Creazione Spell Index per contenuto e per immagini
----------------------------------------------------------------

Creato Da: Pietro Coronas, Dorjan Dika

