package tr.edu.metu.WikiKnowledgeExtractor.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

public class CommonVariables {
	public static final String DB_PASSWORD = "Aa123456";
	public static final String DB_USER = "postgres";
	public static final String URL = "jdbc:postgresql://localhost:5432/cordis";
	public static final String ELASTIC_URL = "http://localhost:9200";
	public static final String NEO4J_URL = "http://localhost:7474/db/data/";
	public static final String DANDELION_URL = "https://api.dandelion.eu/datatxt/nex/v1";

	// START SNIPPET: createReltype
	public static enum RelTypes implements RelationshipType {
		IS_CITY_OF, IS_A, LOCATED_IN, CONTACT_PERSON, EXECUTED_IN, COORDINATOR, PARTICIPANT, PUBLISHED_BY, REPORTED_BY, WRITTEN_BY
	}

	public static final Label REPORT_LABEL = new Label() {

		@Override
		public String name() {
			return "Report";
		}
	};

	public static Label COUNTRY_LABEL = new Label() {

		@Override
		public String name() {
			return "County";
		}
	};
	public static Label CITY_LABEL = new Label() {

		@Override
		public String name() {
			return "City";
		}
	};
	public static Label SIDCODE_LABEL = new Label() {

		@Override
		public String name() {
			return "SidCode";
		}
	};
	public static Label FRAMEWORK_LABEL = new Label() {

		@Override
		public String name() {
			return "Framework";
		}
	};
	public static Label PROGRAM_LABEL = new Label() {

		@Override
		public String name() {
			return "Program";
		}
	};
	public static Label PROJECT_LABEL = new Label() {

		@Override
		public String name() {
			return "Project";
		}
	};
	public static Label PUBLICATION_LABEL = new Label() {

		@Override
		public String name() {
			return "Publication";
		}
	};
	public static Label ORGANIZATION_LABEL = new Label() {

		@Override
		public String name() {
			return "Organization";
		}
	};
	public static Label PERSON_LABEL = new Label() {

		@Override
		public String name() {
			return "Person";
		}
	};

	public static Connection connectToPostGreSQL() {
		System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
			return null;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(CommonVariables.URL, CommonVariables.DB_USER, CommonVariables.DB_PASSWORD);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;
	}
}
