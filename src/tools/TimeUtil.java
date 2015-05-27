package tools;

import java.text.DateFormat;
import java.util.Calendar;

public class TimeUtil
{
	public static String getFormatDate(Calendar time) {
		String datestr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(time.getTime());
		return datestr;
	}
}