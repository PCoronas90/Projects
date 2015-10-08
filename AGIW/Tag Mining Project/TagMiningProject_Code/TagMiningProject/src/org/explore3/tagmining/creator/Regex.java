package org.explore3.tagmining.creator;

import java.util.regex.Pattern;

/**Definizione delle Regex e dei pattern che verranno richiamati nella classe tagExtractor**/
public class Regex {



	static String noise,link,date,distance,unit,number,email,time,dimension,address,IPV4Address,FileFormat,Money;
	static Pattern patternNoise,patternLink,patternDate,patternDistance,patternUnit,patternNumber,patternEmail,patternTime,
	patternDimension,patternAddress,patternIPV4Address,patternFileFormat,patternMoney;


	public static void CreateRegex(){
		noise= ".*([?]{2,}).*|.*([<]{2,}).*|"
				+ ".*([>]{2,}).*|.*([&]{2,}).*|.*[<].*[<].*|.*[>].*[>].*|"
				+ ".*[&].*[&].*|[|<|>|{|}|__|~]|(\\/>)|#|.*([\\[]{1,}).*((\\]){1,}) |]|"
				+ "\\[|(--)|.*[/].*[/].*[/].*| [E](-)Mail |Password |» |[ ]?[a-zA-Z]+\\?[A-Za-z]+ ";	
		patternNoise = Pattern.compile(noise);	


		link="(http[s]?:\\/\\/)?(www)[.][a-zA-Z-]+[.][a-zA-Z]{2,3}([.][a-zA-Z]{2,3})?([.][a-zA-Z]{2,3})?([/a-zA-Z0-9]+)?([.][a-zA-Z]{2,4})?|"
				+ "(http[s]?:\\/\\/)[a-zA-Z]+[.][a-zA-Z]+[.]?[a-zA-Z]{1,3} |"
				+"[ ][a-zA-Z]+[.](pl|com|uk|it|fr|en|edu)[ ]";
		patternLink = Pattern.compile(link);	


		date="(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d(?:,) | "
				+ "[0-3][0-9][,.][0-1][0-9][,.][0-9][0-9]"
				+ "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d(?:,) |" 
				+"[0-3]?[0-9](th|TH|rd|RD)?[ ,.]((j|J)anuary|(f|F)ebruary|(m|M)arch|(a|A)pril|(j|J)une|(j|J)uly|(a|A)ugust|(s|S)eptember|(o|O)ctober|(n|N)ovember|(d|D)ecember)[ ,.][0-2][0|9][0-9][0-9]|"
				+ "(Jan|Feb|Marc|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[^a-zA-Z]?[0-3][0-9][,]?[^a-zA-Z][0-2][0|9][0-9][0-9] |"
				+ "[0-9]?[0-9][- ./](Jan|Feb|Marc|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[- ./][0-9][0|9][0-9][0-9] | "	
				+ "\\b(jan|feb|marc|apr|may|jun|jul|aug|sep|oct|nov|dec)\\s+\\b\\d+th\\b | "
				+ "((j|J)anuary|(f|F)ebruary|(m|M)arch|(a|A)pril|(j|J)une|(j|J)uly|(a|A)ugust|(s|S)eptember|(o|O)ctober|(n|N)ovember|(d|D)ecember)[ ][0-3]?[0-9](TH|th|st|ST)?[,]?[ ][1-2][0|9][0-9][0-9]|"
				+ "([0-3]\\d|\\d)\\s"+"\\b((j|J)anuary|(f|F)ebruary|(m|M)arch|(a|A)pril|(j|J)une|(j|J)uly|(a|A)ugust|(s|S)eptember|(o|O)ctober|(n|N)ovember|(d|D)ecember)\\b "+"\\b |"
				+ "\\b"+"\\b((j|J)anuary|(f|F)ebruary|(m|M)arch|(a|A)pril|(j|J)une|(j|J)uly|(a|A)ugust|(s|S)eptember|(o|O)ctober|(n|N)ovember|(d|D)ecember)\\b "+"\\s+(\\d\\d\\d\\d|\\d\\d) |"
				+ "[1-2][0-9][0-5][0-9][- ./](Jan|Feb|Marc|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[- ./][0-9][0-9] | "				
				+ "[1-2][0|9][0-9][0-9][ /,.-][0-1]?[0-9][ /,.-][0-3]?[0-9] |"
				+"[0-3]?[0-9]?((j|J)anuary|(f|F)ebruary|(m|M)arch|(a|A)pril|(j|J)une|(j|J)uly|(a|A)ugust|(s|S)eptember|(o|O)ctober|(n|N)ovember|(d|D)ecember)[ ]?[0-2][0|9][0-9][0-9] |"
				+"((j|J)anuary|(f|F)ebruary|(m|M)arch|(a|A)pril|(j|J)une|(j|J)uly|(a|A)ugust|(s|S)eptember|(o|O)ctober|(n|N)ovember|(d|)ecember)[ ,./|][0-3][0-9] |"
				+"[0-3]?[0-9][ ,./|]((g|G)ennaio|(f|F)ebbraio|(m|M)arzo|(a|A)prile|(m|M)aggio|(g|G)iugno|(l|L)uglio|(a|A)gosto|(s|S)ettembre|(o|O)ttobre|(n|N)ovembre|(d|D)ocembre)([ .,_-][1-2][0|9][0-9][0-9]) |"
				+"((M|m)on|(T|t)ue|(W|w)ed|(T|t)hu|(F|f)ri|(S|s)at|(S|s)un)[ ,./_-](Jan|Feb|Marc|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[ ,./_-][0-3]?[0-9][,. -]?[ ]?[1-2]?[0|9]?[0-9]?[0-9]? |"
				+"(Gen|Feb|Mar|Apr|Mag|Giu|lug|Ago|Set|Ott|Nov|Dic)[ /,.-][0-9][0-9]?[ ,./-][ ]?[1-2][0|9][0-9][0-9][ ,]? |"
				+"[0-3]?[0-9][/.,-][0-1][0-9][/.,-][1-2][0|9][0-9][0-9] |"
				+ "(Jan|Feb|Marc|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[ ,./-][0-9][0|9][0-9][0-9] |"
				+ "(Jan|Feb|Marc|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[ ,./-][ ]?([1-2][0-9])";

		patternDate = Pattern.compile(date);	


		distance="[ ][(]?[0-9]?[0-9]?[0-9]?[,-]?[0-9][ ]?(km|kilometer|mi|ft|yd|m|cm|dm)(s?)[ ]?[^0-9a-zA-Z]";
		patternDistance = Pattern.compile(distance);


		unit="[ ][0-9]?[0-9]?[.,]?[0-9]?[0-9]?[0-9][ ]?(byte|MB|KB|kb|mw|kw|ton|µg|µl|µgml|kg|kilogram|pound|mb|gb|pb|mg|oz|lb|rpm|mhz|g|gr|ml|cl|dl|hz|hertz|hours|(g\\/l))(s?)[ ] |"			
		+ "(([0-9]\\-[0-9])|[0-9][0-9]\\-[0-9][0-9])[ ]?(byte|MB|KB|kb|mw|kw|ton|µg|µl|µgml|kg|kilogram|pound|mb|gb|pb|mg|oz|lb|rpm|mhz|g|gr|ml|cl|dl|hz|hertz|hours|(g\\/l))(s?)[ ]";
		patternUnit = Pattern.compile(unit);


		number="\\d{10} | \\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4} | \\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5} |"
				+ "\\(\\d{3}\\)-\\d{3}-\\d{4} | [+][0-9][0-9]?[0-9]?[0-9 -.,/][0-9 -.,/()][0-9 -.,/()][0-9 -.,/()][0-9 -.,/()][0-9 -.,/()][0-9 -.,/()][0-9 -.,/()][0-9 -.,/()][0-9 -.,/()][0-9 -.,/()][0-9 -.,/()]?[0-9 -.,/()]?[0-9 -.,/()]?[0-9 -.,/()]?[0-9 -.,/()]? |"
				+"[0-9]?[0-9]?[0-9]?[-]?[(]?[0-9][0-9][0-9][)]?[ /·|,.-][0-9][0-9][0-9][ ,·./|-][0-9][0-9][0-9][0-9]";
		patternNumber = Pattern.compile(number);


		email="(?:[a-z0-9_-]+(?:\\.[a-z0-9_-]+)*)@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])? |\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:+)\\])";
		patternEmail = Pattern.compile(email);


		time="[^:A-Za-z][0-2][0-9]:[0-5][0-9]:[0-5][0-9][ ]?(AM|am|pm|PM)?|[^:A-Za-z0-9][0-2][0-9]:[0-5][0-9][ ]?((AM|PM)|(am|pm)|(M|m))?|[^a-zA-Z0-9][0-9]:[0-5][0-9][ ]?((AM|PM)|(am|pm)|(M|m))|[^a-zA-Z0-9][0-9]:[0-5][0-9]:[0-5][0-9][ ]?((AM|PM)|(am|pm)|(M|m))?|[^a-zA-Z0-9][(][0-9]:[0-5][0-9][)]|[^a-zA-Z0-9][0-2][0-4]:[0-5][0-9]|[^a-zA-Z0-9][0-2]?[0-9]:[0-5][0-9]";
		// "[^:A-Za-z][0-2]?[0-9]?:[0-5][0-9][^:0-9][ ]?((AM|PM)|(am|pm)|(M|m))[^a-zA-Z]|[ ]?[0-2]?[0-9]:[0-5][0-9]:[0-5][0-9][ ]?((AM|PM)|(am|pm))?|[^a-zA-z][0-9]?[0-9][ ,.]?[s][^a-zA-z0-9]|[ ][0-9]?[0-9]:[0-9][0-9]|[(][0-9]?[0-9]:[0-9][0-9][)]";
		patternTime = Pattern.compile(time);


		dimension="[0-9]?[0-9](ft|yd|m|cm|dm|mm)?[ ]?[xX][ ]?[0-9]?[0-9](ft|yd|m|cm|dm|mm)|[0-9]?[0-9][ ](ft|yd|m|cm|dm|mm)[ ]?[xX][ ]?[0-9]?[0-9][ ](ft|yd|m|cm|dm|mm)|[0-9]?[0-9][ ][ ]?[xX][ ]?[0-9]?[0-9][ ](ft|yd|m|cm|dm|mm) |"
				+"[0-9]?[0-9][.,]?[0-9]?[0-9]?[xX][0-9]?[0-9][.,]?[0-9]?[0-9]?[ ]?(ft|yd|m|cm|dm|mm)|[0-9]?[0-9][.,]?[0-9]?[0-9]?[ ][xX][ ][0-9]?[0-9][.,]?[0-9]?[0-9]?[ ]?(ft|yd|m|cm|dm|mm)";
		patternDimension = Pattern.compile(dimension);


		address="[0-9]?[0-9]?[0-9]?[0-9]?[ ]?((N|n)orth|(W|w)est|(S|s)outh|(E|e)ast|N|W|S|E|NW|NE|SW|SE)?[ ]?[0-9]?[0-9]?[1-9][ ]?(th|TH|Th|rd|RD|nd|ND)[ ](STREET|Street)|"
				+ "[0-9]?[0-9]?[0-9]?[0-9]?[ ]?((N|n)orth|(W|w)est|(S|s)outh|(E|e)ast)[ ]([a-zA-Z]+)?[ ]?(Street|street) |"
				+ "[0-9][0-9]?[0-9][0-9][ ]([a-zA-Z]+)?[ ]?(Street|street)|[0-9]?[0-9]?[0-9][ ]?(th|TH|RD|rd|nd|ND)[ ](Street|street|Avenue|avenue) |"
				+ "[0-9]?[0-9]?[0-9]?[0-9][0-9][ ]([a-zA-Z]+)?[ ](street|Street|avenue|Avenue)|"
				+ "[0-9]?[0-9]?[0-9][0-9][ ][0-9]?[0-9]?[0-9][ ]?(th|TH|RD|rd|nd|ND)[ ](avenue|Avenue|Street|street)";
		patternAddress = Pattern.compile(address);


		//UsStateOrProvince = "(?:Ala(?:bama|ska)|Arizona|Arkansas|California|Colorado|"
		//		+ "Connecticut|Delaware|District of Columbia|Florida|Georgia|Hawaii|Idaho|Illinois|"
		//		+ "Indiana|Iowa|Kansas|Kentucky|Louisiana|Maine|Maryland|Massachusetts|Michigan|"
		//		+ "Minnesota|Miss(?:issippi|ouri)|Montana|Nebraska|Nevada|New (?:Hampshire|Jersey|Mexico|York)"
		//		+ "|North (?:Carolina|Dakota)|Ohio|Oklahoma|Oregon|Pennsylvania|Rhode Island|South (?:Carolina|Dakota)"
		//		+ "|Tennessee|Texas|Utah|Vermont|Virginia|Washington|West Virginia|Wisconsin|Wyoming)";	
		//patternUsStateOrProvince = Pattern.compile(UsStateOrProvince);


		IPV4Address ="[0-9][0-9][0-9][.][0-9]{1,3}[.][0-9]{1,3}[.][0-9]{1,3}";
		patternIPV4Address = Pattern.compile(IPV4Address);

		FileFormat="[a-zA-Z0-9-]+\\.(flac|mpeg|jpeg|avi|jpg|mpg|mp3|mp4|gif)[^A-Za-z0-9(),./-]";
		patternFileFormat = Pattern.compile(FileFormat);

		Money="[$|£][0-9]+[.|,]?([0-9]+)?|(usd|euro?)[ ]?[0-9]+[.|,]?([0-9]+)[ ]?(mn|bn|billion|million)?";
		patternMoney = Pattern.compile(Money);

	}



	//public String getUsStateOrProvince() {
	//	return UsStateOrProvince;
	//}

	public Pattern getPatternNoise() {
		return patternNoise;
	}

	public Pattern getPatternLink() {
		return patternLink;
	}

	public Pattern getPatternDate() {
		return patternDate;
	}


	public Pattern getPatternDistance() {
		return patternDistance;
	}


	public Pattern getPatternUnit() {
		return patternUnit;
	}


	public Pattern getPatternNumber() {
		return patternNumber;
	}



	public Pattern getPatternEmail() {
		return patternEmail;
	}


	public Pattern getPatternTime() {
		return patternTime;
	}



	public Pattern getPatternDimension() {
		return patternDimension;
	}



	public Pattern getPatternAddress() {
		return patternAddress;
	}



	//public Pattern getPatternUsStateOrProvince() {
	//	return patternUsStateOrProvince;
	//	}
	//


	public Pattern getPatternIPV4Address() {
		return patternIPV4Address;
	}



	public Pattern getPatternFileFormat() {
		return patternFileFormat;
	}


	public Pattern getPatternMoney() {
		return patternMoney;
	}



}
