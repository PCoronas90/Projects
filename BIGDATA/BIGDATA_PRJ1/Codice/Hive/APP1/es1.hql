--Necessario per lanciare più volte l'esercizio, altrimenti da errore--
DROP TABLE prodotti_vendite;

--Creo la tabella vuota.--
CREATE TABLE IF NOT EXISTS prodotti_vendite(line STRING);

--Carico i dati nella tabella--
LOAD DATA LOCAL INPATH '/home/peter/Scrivania/esempio.txt' OVERWRITE INTO TABLE prodotti_vendite;

--Indico directory del risultato--
INSERT OVERWRITE LOCAL DIRECTORY '/home/peter/Scrivania/Risultato_es1_hive' 

SELECT p.prodotto, COUNT(1) as vendite_tot

FROM (SELECT explode(split(line, ',')) AS prodotto FROM prodotti_vendite) p 
WHERE prodotto NOT LIKE '20%'

--Raggruppo per prodotto--
GROUP BY p.prodotto

--Ordino in base alle vendite--
SORT BY vendite_tot DESC;