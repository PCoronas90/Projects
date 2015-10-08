package App2;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//
public class TrendSalesReducer extends Reducer</*Valore chiave uscente dal mapper*/Text,
												/*Valore Valure uscente dal mappper*/Text, 
												/*Valore della chiave che usi nel reducer*/Text, 
												/*Value uscente da questa classe*/Text> {
	private int conta1;
	private int conta2;
	private int conta3;
	
	public void reduce(/*Valore chiave uscente dal mapper*/Text key,
						/*Sempre tipo Iterable, perà tra<> cè sempre Valore Valure uscente dal mappper*/ Iterable<Text> values,
						Context context) throws IOException, InterruptedException {
		conta1=0;
		conta2=0;
		conta3=0;
		
		List<Date> date = new ArrayList<Date>();
		for (Text value : values) {
			try {
				date.add(getDate(value.toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		for (Iterator iterator = date.iterator(); iterator.hasNext();) {
			Date date2 = (Date) iterator.next();
			if(date2.getMonth()==1)
				conta1++;
			if(date2.getMonth()==2)
				conta2++;
			if(date2.getMonth()==3)
				conta3++;	
		}
		
		String trimestre1 = "1/2015:".concat(""+conta1+"");
		String trimestre2 = "2/2015:".concat(""+conta2+"");
		String trimestre3 = "3/2015:".concat(""+conta3+"");
		
		Text trimestre1Text = new Text();
		trimestre1Text.set(trimestre1);
		
		Text trimestre2Text = new Text();
		trimestre2Text.set(trimestre2);
		
		Text trimestre3Text = new Text();
		trimestre3Text.set(trimestre3);
		
		String trimestre = trimestre1+"\t"+trimestre2+"\t"+trimestre3;
		Text trimestreText = new Text();
		trimestreText.set(trimestre);
		context.write(key, trimestreText);		
	}	
	
	private Date getDate(String data) throws ParseException{
		String string = data;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = format.parse(string);
		return date;
	}
	

}
