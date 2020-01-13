package com.rahulg;

import java.sql.*;

public class RedShiftConnector {
    private static  String DRIVER_NAME = "com.amazon.redshift.jdbc42.Driver";
    private  static String CONNECTION_STR = "jdbc:redshift://redshift-cluster-1.xxxx.us-east-1.redshift.amazonaws.com/rg_tickit?user=<username>>&password=<password>";
    private static  String access_key = "";
    private static  String secret_key= "";

    public static void main(String args[]) {
        System.out.println("Example RedShift Connecotr using Amazon JDBC.");
        //loadEventDetailsFromS3(); //TODO : Try to write this .
        getEventDetails();
        //loadEventDetailsInS3();

    }

    public  static  void loadEventDetailsInS3() {
        Connection conn = null;
        Statement stmt = null;
        String sql = "";
        String secret = "TO 's3://xxx-datasets/tmp/51de41a5-acaa-4bbf-8b2f-2bec51b651ab/'  WITH CREDENTIALS 'aws_access_key_id=" + access_key+";aws_secret_access_key="+ secret_key +"' ESCAPE MANIFEST ALLOWOVERWRITE";
        try {
            conn = getDriverConnection();
            //sql = "select * from event where eventname in (\\'Grease\\')";
            sql = "SELECT \"catid\", \"dateid\", \"eventid\", \"venueid\", \"eventname\", \"starttime\" FROM (SELECT * from event where eventname in (\\'Salome\\')) ";
            String unloadSql = "UNLOAD (\'" + sql + "\') " + secret;
            System.out.println("unloadSql: " + unloadSql);

            stmt = conn.createStatement();
            stmt.executeQuery(unloadSql);
            System.out.println("Result: Done");
            conn.close();
        }
        catch (Exception e) {
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            System.out.println("Done with loadEventDetailsInS3..");
        }

    }

    public static  void getEventDetails() {
        Connection conn = null;
        Statement stmt = null;
        String sql = "";
        try {
            conn = getDriverConnection();
            sql = "select * from event where eventname = 'Grease' limit 10" ;
            //sql = "SELECT \"catid\", \"dateid\", \"eventid\", \"venueid\", \"eventname\", \"starttime\" FROM (SELECT * from event where eventname in ('Salome')) ";
            //sql = "SELECT \"catid\", \"dateid\", \"eventid\", \"venueid\", \"eventname\", \"starttime\" FROM (SELECT * from event where eventname in (\'Salome\')) ";

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Result: ");
            while(rs.next()) {
              System.out.print(rs.getString("eventname") + "  ");
              System.out.print(rs.getString("eventid"));
              System.out.println("\n");

            }
            conn.close();
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
        finally {
            System.out.println("Done with getEvenetDetails");
        }
    }

    public static Connection getDriverConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(CONNECTION_STR);
        }
        catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " + e.getMessage());
        }
        catch (SQLException sq) {
            System.err.println("SQLException: " + sq.getMessage());
        }
        return connection;
    }
}
