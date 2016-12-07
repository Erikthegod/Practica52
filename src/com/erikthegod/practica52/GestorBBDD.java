package com.erikthegod.practica52;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author kinton
 */
public class GestorBBDD {

    String user;
    String pass;
    String url;
    Connection conex;
    Statement stm;

    public GestorBBDD() {
        user = "hr";
        pass = "hr";
        url = "jdbc:oracle:thin:@localhost:1521:xe";
        conex = null;
        stm = null;

    }

    public void conectar() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("oracle.jdbc.OracleDriver").newInstance();
        conex = DriverManager.getConnection(url, user, pass);
    }

    public String leerDatosBD() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conectar();
        stm = conex.createStatement();
        String cadSql = "";
        cadSql = "select * from employees";
        ResultSet rs = stm.executeQuery(cadSql);
        String datos = "";
        while (rs.next()) {
            datos += (rs.getInt(1) + "  " + rs.getString(2) + "  " + rs
                    .getString(3)) + "\n";
        }
        return datos;
    }

    public void ingresarDatosBD() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conectar();
        int r;
        String cadSql;
        stm = conex.createStatement();
        cadSql = "insert into employees (employee_id,first_name,last_name,email,phone_number,"
                + "hire_date,job_id,salary,manager_id,department_id) "
                + "values (2200,'Erik','Martin','yo@yo.com','5858','07/12/2016','AD_PRES',280000,100,90)";
        r = stm.executeUpdate(cadSql);
    }

    public int eliminarDatos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conectar();
        int r;
        String cadSql;
        stm = conex.createStatement();
        cadSql = "delete from employees where hire_date between '01/01/2016' and '31/12/2016'";
        r = stm.executeUpdate(cadSql);
        return r;

    }
}
