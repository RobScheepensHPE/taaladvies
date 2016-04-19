package be.vlaanderen.sbs.s6.utils;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Insert the type's description here.
 * Creation date: (5/11/01 4:26:17 PM)
 * @author: 
 */
public class DbUtil {
	utMessage utMes = new utMessage(4,"DbUtil");
/**
 * DbUtil constructor comment.
 */
public DbUtil() {
	super();
}

/**
 * Insert the method's description here.
 * Creation date: (5/11/01 5:34:50 PM)
 * @return java.sql.PreparedStatement
 * @param pst java.sql.PreparedStatement
 * @param index int
 * @param parameter java.lang.Integer
 * @exception java.sql.SQLException The exception description.
 */
public static void pstSetDate(PreparedStatement pst, int index, Date parameter) throws java.sql.SQLException {
	if(parameter != null)
		pst.setDate(index, new java.sql.Date(parameter.getTime()));
	else
		pst.setNull(index, java.sql.Types.DATE);
}
/**
 * Insert the method's description here.
 * Creation date: (5/28/01 3:46:15 PM)
 */
public static void pstSetDate(PreparedStatement pst, int index, java.util.Date parameter) throws java.sql.SQLException {
	if(parameter != null)
		pst.setDate(index, new java.sql.Date(parameter.getTime()));
	else
		pst.setNull(index, java.sql.Types.DATE);
}
/**
 * Insert the method's description here.
 * Creation date: (5/11/01 5:37:38 PM)
 * @return java.sql.PreparedStatement
 * @param pst java.sql.PreparedStatement
 * @param index int
 * @param parameter java.sql.Date
 * @exception java.sql.SQLException The exception description.
 */
public static void pstSetDouble(PreparedStatement pst, int index, Double parameter) throws java.sql.SQLException {
	if(parameter != null)
		pst.setDouble(index, parameter.doubleValue());
	else
		pst.setNull(index, java.sql.Types.DOUBLE);
}
/**
 * Insert the method's description here.
 * Creation date: (5/11/01 5:32:08 PM)
 * @return java.sql.PreparedStatement
 * @param pst java.sql.PreparedStatement
 * @param index int
 * @param parameter java.lang.Integer
 * @exception java.sql.SQLException The exception description.
 */
public static void pstSetInt(PreparedStatement pst, int index, Integer parameter) throws java.sql.SQLException {
	if(parameter != null)
		pst.setInt(index, parameter.intValue());
	else
		pst.setNull(index, java.sql.Types.INTEGER);
}
/**
 * Insert the method's description here.
 * Creation date: (5/11/01 4:28:23 PM)
 * @return java.sql.PreparedStatement
 */
public static void pstSetObject(PreparedStatement pst, int index, Object parameter, int sqlType)throws SQLException {
	if(parameter != null)
		pst.setObject(index, parameter, sqlType);
	else
		pst.setNull(index, sqlType);
}
/**
 * Insert the method's description here.
 * Creation date: (5/11/01 5:39:09 PM)
 * @return java.sql.PreparedStatement
 * @param pst java.sql.PreparedStatement
 * @param index int
 * @param parameter java.lang.Short
 * @exception java.sql.SQLException The exception description.
 */
public static void pstSetShort(PreparedStatement pst, int index, Short parameter) throws java.sql.SQLException {
	if(parameter != null)
		pst.setInt(index, parameter.shortValue());
	else
		pst.setNull(index, java.sql.Types.SMALLINT);
}
/**
 * Insert the method's description here.
 * Creation date: (5/11/01 5:00:21 PM)
 * @return java.sql.PreparedStatement
 */
public static void pstSetString(PreparedStatement pst, int index, String parameter) throws SQLException{
	if(parameter != null)
		pst.setString(index, parameter);
	else
		pst.setNull(index, java.sql.Types.VARCHAR);		
}
/**
 * Insert the method's description here.
 * Creation date: (5/28/01 3:36:01 PM)
 */
public static Double rsGetDouble(ResultSet rs, int index) throws java.sql.SQLException {
	Double d = new Double(rs.getDouble(index));
	if(rs.wasNull())
		d = null;
	return d;
}
/**
 * Insert the method's description here.
 * Creation date: (5/28/01 3:36:01 PM)
 */
public static Double rsGetDouble(ResultSet rs, String column) throws java.sql.SQLException {
	Double d = new Double(rs.getDouble(column));
	if(rs.wasNull())
		d = null;
	return d;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/01 12:25:06 PM)
 * @return java.lang.Integer
 * @param rs java.sql.ResultSet
 * @param index int
 * @exception java.sql.SQLException The exception description.
 */
public static Integer rsGetInt(ResultSet rs, int index) throws java.sql.SQLException {
	Integer i = new Integer(rs.getInt(index));
	if(rs.wasNull())
		i = null;
	return i;
}
/**
 * Insert the method's description here.
 * Creation date: (5/28/01 3:36:01 PM)
 */
public static Integer rsGetInt(ResultSet rs, String column) throws java.sql.SQLException {
	Integer i = new Integer(rs.getInt(column));
	if(rs.wasNull())
		i = null;
	return i;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/01 12:44:00 PM)
 * @return java.lang.Short
 * @param rs java.sql.ResultSet
 * @param index int
 * @exception java.sql.SQLException The exception description.
 */
public static Short rsGetShort(ResultSet rs, int index) throws java.sql.SQLException {
	Short s = new Short(rs.getShort(index));
	if(rs.wasNull())
		s = null;
	return s;
}
/**
 * Insert the method's description here.
 * Creation date: (5/23/01 12:41:40 PM)
 * @return java.lang.Short
 * @param rs java.sql.ResultSet
 * @param column java.lang.String
 */
public static Short rsGetShort(ResultSet rs, String column) throws java.sql.SQLException {
	Short s = new Short(rs.getShort(column));
	if(rs.wasNull())
		s = null;
	return s;
}

}
