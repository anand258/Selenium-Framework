package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Developed by Anand Singh on 06/Jul/2021, 12:09 AM.
 * Copyright (c) 2021. All rights reserved.
 */
public class JdbcConnection {
    public ResultSet connect() throws SQLException, IOException {
        Properties prop = new Properties();
        FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data.properties");
        prop.load(fin);
        String jdbcUrl = "jdbc:mysql://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/"+prop.getProperty("database");
        Connection con = DriverManager.getConnection(jdbcUrl, prop.getProperty("jdbcUsername"), prop.getProperty("jdbcPassword"));
        Statement s = con.createStatement();
        return s.executeQuery("select * from Credentials");
    }
}
