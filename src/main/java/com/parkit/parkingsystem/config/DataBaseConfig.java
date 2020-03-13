package com.parkit.parkingsystem.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;

public class DataBaseConfig {

    private static final Logger logger = LogManager.getLogger("DataBaseConfig");

    public Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        String pathProperties = "resources/properties.xml";

        logger.info("Create DB connection");

        //BufferedReader br = new BufferedReader(new FileReader(pathProperties), "UTF8"));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathProperties), StandardCharsets.UTF_8));
        String root = br.readLine();
        String pass = br.readLine();
        br.close();

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=Europe/Paris",root, pass);
    }

    public void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
                logger.info("Closing DB connection");
            } catch (SQLException e) {
                logger.error("Error while closing connection",e);
            }
        }
    }

    public void closePreparedStatement(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
                logger.info("Closing Prepared Statement");
            } catch (SQLException e) {
                logger.error("Error while closing prepared statement",e);
            }
        }
    }

    public void closeResultSet(ResultSet rs) {
        if(rs!=null){
            try {
                rs.close();
                logger.info("Closing Result Set");
            } catch (SQLException e) {
                logger.error("Error while closing result set",e);
            }
        }
    }
}
