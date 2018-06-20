/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softw2.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author wildh
 */
public class Datenbank
{

    private static final Logger LOG = Logger.getLogger(Datenbank.class.getName());

    private static final String dbType = "jdbc:sqlite:";

    private static Datenbank datenbank = null;

    public static Datenbank getStandardDatenbank()
    {
        if (datenbank == null)
        {
            createDatenbank("DBStandard");
        }
        return datenbank;
    }

    private Connection verbindung = null;

    private static synchronized void createDatenbank(String name)
    {
        if (datenbank == null)
        {
            datenbank = new Datenbank(name);
        }
    }

    public Datenbank(String name)
    {
        verbindeZuDatenbank(name);
    }

    /**
     * Connect to a sample database
     *
     * @param name the database file name
     */
    private void verbindeZuDatenbank(String name)
    {

        String url = dbType + name + ".sqlite";

        try
        {
            Connection connection = DriverManager.getConnection(url);

            DatabaseMetaData meta = connection.getMetaData();
            LOG.log(Level.FINE, "Der Name des Treibes lautet {0}", meta.getDriverName());
            if (connection.isValid(10))
            {
                this.verbindung = connection;
            } else
            {
                LOG.warning("Es wurde keine zuverlässige verbindung zur Datenbank " + name + " aufgebaut-");
            }

        } catch (SQLException e)
        {
            LOG.severe("Es konnte keine Verbindung zur Datenbank hergestellt werden.");
        }
    }

    public ResultSet queryAusfuehren(String query)
    {
        //Es wird davon ausgegangen, dass immer eine Verbindung besteht.
        try
        {
            Statement stmt = verbindung.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e)
        {
            if (e.getErrorCode() == 101)//querry does not return result set
            {
                LOG.finer( e.getMessage()  +" bei " + query);
            }
            else
            {
                LOG.info("Fehler bei " + query + " :" + e.getMessage() + e.getErrorCode());
            }
        }
        return null;
    }
}
