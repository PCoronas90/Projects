/*Carico l'input*/
myinput = LOAD '/home/peter/Scrivania/esempio.txt' AS (f1:chararray);


token = FOREACH myinput  GENERATE FLATTEN(TOKENIZE($0)),$0;
filtered = FILTER token BY NOT(STARTSWITH($0,'2'));

/*Gennaio*/
prodotti_gennaio = FILTER filtered BY STARTSWITH($1,'2015-1-');
gruppo_gennaio = GROUP prodotti_gennaio BY $0;
conteggio_gennaio = FOREACH gruppo_gennaio GENERATE group, COUNT(prodotti_gennaio) as gennaio_count;

/*Febbraio*/
prodotti_febbraio = FILTER filtered BY STARTSWITH($1,'2015-2-');
gruppo_febbraio = GROUP prodotti_febbraio BY $0;
conteggio_febbraio = FOREACH gruppo_febbraio GENERATE group, COUNT(prodotti_febbraio) as febbraio_count;

/*Marzo*/
prodotti_marzo = FILTER filtered BY STARTSWITH($1,'2015-3-');
gruppo_marzo = GROUP prodotti_marzo BY $0;
conteggio_marzo = FOREACH gruppo_marzo GENERATE group, COUNT(prodotti_marzo) as marzo_count;

/*Due Join separati*/
result_temp = JOIN conteggio_gennaio BY $0 FULL, conteggio_febbraio BY $0 ;
result_trimestre = JOIN result_temp BY $0 FULL, conteggio_marzo BY $0;

trimestre = FOREACH result_trimestre GENERATE ((chararray)$0 is not null?(chararray)$0:(chararray)$2),
CONCAT('1/2015:',((chararray)$1 is not null?(chararray)$1:'0')),
CONCAT('2/2015:',((chararray)$3 is not null?(chararray)$3:'0')),
CONCAT('3/2015:',((chararray)$5 is not null?(chararray)$5:'0'));

store trimestre into '/home/peter/Scrivania/EserciziPig/OutputEsercizioPig2';