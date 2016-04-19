package be.vlaanderen.sbs.s6.taaladvies.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *  <p>
 *
 *  Title: Interface voor het connectie beheer naar de databank.</p> <p>
 *
 *  Description: </p> <p>
 *
 *  Copyright: Copyright (c) 2003</p> <p>
 *
 *  Company: Siemens Business Services</p>
 *
 *@author     Davy Tutak
 *@version    1.0
 */
public interface IDbConnection {

    /**
     *  Aanvragen van een connectie.
     *
     *@return                    java.sql.Connections
     */
    public Connection getConnection();


    /**
     *  Vrijgave van de connectie.
     *
     *@param  con             java.sql.Connection
     *@throws   SQLException  ApplicationException
     */
    public void releaseConnection(Connection con) throws SQLException;


}
