package org.testTask.DAO;

import java.io.File;

public class JDBC {
    private static String url;
    private  static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String NAME = "db";
    private static JDBC jdbc;

    public String getUrl() {
        return url;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getNAME() {
        return NAME;
    }

    public static synchronized JDBC getJdbc() {
        if (jdbc == null) {
            jdbc = new JDBC();
            String path = new File("").getAbsolutePath()
                    .replaceAll("[\\\\/]", "/");
            url = "jdbc:hsqldb:file:" + path
                    + "/src/main/resources/database/db";
        }
        return jdbc;
    }

    private JDBC() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
