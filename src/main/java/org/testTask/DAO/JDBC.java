package org.testTask.DAO;

import java.io.File;

/**
 * The type Jdbc.
 */
public class JDBC {
    /**
     * The url
     */
    private static String url;

    /**
     * The user name
     */
    private  static final String USER = "root";

    /**
     * The password
     */
    private static final String PASSWORD = "";

    /**
     * The database name
     */
    private static final String NAME = "db";

    /**
     * The jdbc
     */
    private static JDBC jdbc;

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUSER() {
        return USER;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPASSWORD() {
        return PASSWORD;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * Gets jdbc.
     *
     * @return the jdbc
     */
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
