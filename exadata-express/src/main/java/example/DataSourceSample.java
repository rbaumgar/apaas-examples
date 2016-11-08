package example;
/* Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.*/

/*
   DESCRIPTION    
   The code sample shows how to use the DataSource API to establish a connection
   to the Database. You can specify properties with "setConnectionProperties".
   This is the recommended way to create connections to the Database.

   Note that an instance of oracle.jdbc.pool.OracleDataSource doesn't provide
   any connection pooling. It's just a connection factory. A connection pool,
   such as Universal Connection Pool (UCP), can be configured to use an
   instance of oracle.jdbc.pool.OracleDataSource to create connections and 
   then cache them.
    
   NOTES
    Use JDK 1.7 and above

   MODIFIED    (MM/DD/YY)
    nbsundar    02/17/15 - Creation
    ssmith      31/10/26 - Adapted for ACCS Example
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class DataSourceSample {
	// @dbaccess is a reference to the connection defined in the tnsnames.ora file
	final static String DB_URL = "jdbc:oracle:thin:@dbaccess";

	private String userid;
	private String password;
	
	public DataSourceSample(String userid, String password) {
		super();
		this.userid = userid;
		this.password = password;
	}

	/*
	 * The method gets a database connection using
	 * oracle.jdbc.pool.OracleDataSource. It also sets some connection level
	 * properties, such as,
	 * OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH,
	 * OracleConnection.CONNECTION_PROPERTY_THIN_NET_CHECKSUM_TYPES, etc., There
	 * are many other connection related properties. Refer to the
	 * OracleConnection interface to find more.
	 */
	protected Connection getConnection() throws SQLException {
		Properties info = new Properties();
		info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, userid);
		info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, password);
	
		OracleDataSource ods = new OracleDataSource();
		ods.setURL(DB_URL);
		ods.setConnectionProperties(info);

		OracleConnection connection = (OracleConnection) ods.getConnection();
		// Get the JDBC driver name and version
		DatabaseMetaData dbmd = connection.getMetaData();
		System.out.println("Driver Name: " + dbmd.getDriverName());
		System.out.println("Driver Version: " + dbmd.getDriverVersion());
		// Print some connection properties
		System.out.println("Default Row Prefetch Value is: " + connection.getDefaultRowPrefetch());
		System.out.println("Database Username is: " + connection.getUserName());
		System.out.println();
		return connection;
	}

	protected String getDate() throws SQLException {
		try (Connection connection = getConnection()) {
			// Statement and ResultSet are AutoCloseable and closed
			// automatically.
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery("select sysdate from dual")) {
					// Expect only single result
					if (resultSet.next()) {
						String date = resultSet.getString(1);
						System.out.println(date);
						return date;
					} else {
						return null;
					}
				}
			}
		} 
	}
}
