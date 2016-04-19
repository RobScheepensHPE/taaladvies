package be.vlaanderen.sbs.s6.taaladvies.utils;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Util {
    public final static String EOPRO_EMPTY_HTMLFORM = "<p></p>";

    public static boolean isValidDate(String date) {
        String pattern = "dd-MM-yyyy";
        if (date != null) {
            // --- Only parse the passed date if it has the same length as the pattern it should comply with ---
            if (date.length() == pattern.length()) {
                DateFormat df = new SimpleDateFormat(pattern);

                // --- Specify an exact match ---
                df.setLenient(false);

                // --- Verify wether the passed date can be parsed using the specified pattern ---
                try {
                    df.parse(date);
                    return true;
                }
                catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }
    public static boolean isValidDate(String date,String seperator) {
        String pattern = "dd"+seperator+"MM"+seperator+"yyyy";
        if (pattern != null && date != null) {
            // --- Only parse the passed date if it has the same length as the pattern it should comply with ---
            if (date.length() == pattern.length()) {
                DateFormat df = new SimpleDateFormat(pattern);

                // --- Specify an exact match ---
                df.setLenient(false);

                // --- Verify wether the passed date can be parsed using the specified pattern ---
                try {
                    df.parse(date);
                    return true;
                }
                catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isAnInteger(String a) {
        try {
            Integer.parseInt(a);
            return true;
        }
        catch(NumberFormatException nfe) {

            return false;
        }
    }

    public static boolean isValidMail(String a) {

 		boolean result = false;
 		int index = a.indexOf("@");
 		if (index > 0) {
 			int index_ = a.indexOf(".", index);
 			if ((index_ > index+1) && (a.length() > index_+1)) {
 				result = true;
 			}
 		}
 		return result;
    }

	/**
	 * Make sure that the user-give inputstring contains useful data
	 * If, after trimming, the string is empty, this is regarded as non-useful data
	 * The string will thus be nullified
	 * @param data the input given by the user
	 * @return a string that is trimmed is usefull data was found, null otherwise
	 **/

	public static String processInputString(String data)
	{
		if(data != null)
		{
			data = data.trim();

			if(data.equals(""))
				data = null;
		}

		return data;
	}

	public static void pstSetInt(PreparedStatement pst, int index, int parameter) throws java.sql.SQLException {
        if(parameter > 0) {
        	pst.setInt(index, parameter);
        }
        else {
        	pst.setNull(index, java.sql.Types.INTEGER);
        }
	}

	/**
     * @param pst
     * @param index
     * @param parameter
     * @throws SQLException
     */
    public static void pstSetClob(PreparedStatement pst, int index, String parameter) throws SQLException {
        if(parameter == null){
            parameter = "";
        }
        byte[] b_parameter = parameter.getBytes();
        ByteArrayInputStream bais_parameter = new ByteArrayInputStream(b_parameter);
        pst.setAsciiStream(index, bais_parameter, b_parameter.length);
    }

	public static String formatNewLinesToBr(String s){
	    String convertedString = null;
	    if(s != null){
	        convertedString = s.replaceAll("\n", "<BR>");
	    }
	    return convertedString;
	}

}

