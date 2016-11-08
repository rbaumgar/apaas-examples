package example;

import static spark.Spark.get;
import static spark.Spark.port;

import java.sql.SQLException;
import java.util.Optional;

public class Main {
	
    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));
    public static final Optional<String> EECS_USER = Optional.ofNullable(System.getenv("EECS_USER"));
    public static final Optional<String> EECS_PASSWORD = Optional.ofNullable(System.getenv("EECS_PASSWORD"));

    private static final String RUNNING = "Running...Open '/date' to query Exadate Express CS database.";
    private static final String MISCONFIGURED = "Error...not able to connect to EECS: user id and password not configured.";
  
    public static void main(String[] args) throws SQLException {
		new Main();
	}
 
    public Main() throws SQLException {
        Integer port = Integer.valueOf(PORT.orElse("8080"));
		port(port);
		System.out.println("Listening on port " + port);
		
		boolean isConfigured = EECS_USER.isPresent() && EECS_PASSWORD.isPresent();
		final String status = isConfigured ? RUNNING : MISCONFIGURED;
		System.out.println(status);
		
		if (isConfigured){
        	final DataSourceSample sample = new DataSourceSample(EECS_USER.get(), EECS_PASSWORD.get());      
        	get("/date", (req, res) -> {
        		return sample.getDate();
        	});
		}
		
        get("/", (req, res) -> {
       		return status;
        });

     }



}