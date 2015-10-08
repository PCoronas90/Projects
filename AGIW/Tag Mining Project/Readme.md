Progetto Tag Mining
----------------------------------------------------------

Il programma prende in input dataset compressi di pagine HTML in formato warc. In output produce:
1) Statistiche dei tag estratti
2) Frasi estratte senza tag
3) Frasi estratte con tag
4) Tag estratto
5) Rumore (frasi scartate considerate non comprensibili)
-----------------------------------------------------------

Il matching è stato realizzato tramite Regex (espressioni regolari). I tag che vengono trovati nelle pagine HTML sono:
- tempo ( Orari)
- Formati di file
- Moneta
- Link
- Unità
- Indirizzi
- Dimensioni
- Date
- Email
- Indirizzi IPV4
- Numeri (Telefonici o sequenze numeriche)
- Distanze
----------------------------------------------------------------

Gli step che il programma segue sono divisi in:
- Estrazione dei vari HTML nei file warc e salvataggio dei singoli.
- Parsing dei documenti ed estrazione dell'informazione testuale
- Divisione in linee
- Estrazione Tag tramite Regex
----------------------------------------------------------------

Creato Da: Pietro Coronas, Dorjan Dika
