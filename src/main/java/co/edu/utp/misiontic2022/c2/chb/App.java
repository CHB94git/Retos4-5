package co.edu.utp.misiontic2022.c2.chb;

import java.sql.SQLException;

import co.edu.utp.misiontic2022.c2.chb.util.JDBCUtilities;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            var conn = JDBCUtilities.getConnection();
            System.out.println("Conexión éxitosa!");
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
    }
}
