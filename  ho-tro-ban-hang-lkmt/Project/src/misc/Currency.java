package misc;

import java.text.DecimalFormat;

public class Currency {
	static private DecimalFormat f = new DecimalFormat("#,###");
	
	static public String getCurrency(long l) {
		return f.format(l);
	}
}
