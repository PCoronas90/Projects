/*Mi serve un jar per creare le coppie*/
REGISTER '/home/peter/Scrivania/EserciziPig/pig3.jar';

/*Carico il dataset*/
dati= LOAD '/home/peter/Scrivania/esempio.txt' USING TextLoader() as (word:chararray);

crea_coppie = FOREACH dati GENERATE FLATTEN(BigData.Pig3.CreaCoppie(word)) as e;

gruppo_coppie = GROUP crea_coppie BY e;

conteggio_coppie = FOREACH gruppo_coppie GENERATE $0,COUNT(crea_coppie);

ordinamento_coppie = ORDER conteggio_coppie BY $1 DESC;

result = LIMIT ordinamento_coppie 10; 

store result into '/home/peter/Scrivania/EserciziPig/OutputEsercizioPig3' using PigStorage();






