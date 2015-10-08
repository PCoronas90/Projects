 /*Caricamento file*/
data= LOAD '/home/peter/Scrivania/esempio.txt' USING PigStorage(',');

 /*Prendo i prodotti e scarto la data*/
prodotti_senza_data = FOREACH data GENERATE $1 .. ;

 /*Metto i prodotti singolarmente*/
prodotti_singoli = FOREACH prodotti_senza_data  GENERATE FLATTEN(TOBAG(*));

 /*Li raggruppo*/
raggruppa_prodotti= GROUP prodotti_singoli  BY $0;

 /*Conteggio dei prodotti per le quantità*/
prodotti_e_quantita = FOREACH raggruppa_prodotti GENERATE group, COUNT(prodotti_singoli);

 /*Li ordino*/
prodotti_ordinati = ORDER prodotti_e_quantita BY $1 DESC;

 /*Faccio lo store*/
store prodotti_ordinati into '/home/peter/Scrivania/OutputEsercizioPig1' using PigStorage();
