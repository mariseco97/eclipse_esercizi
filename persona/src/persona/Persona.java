package persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Persona {

	private Connection con;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//L'istanza della classe per accedere al DB
		Persona person = new Persona();
		try {
			
			//System.out.println(person.getConnection().isValid(50));
			//System.out.println(person.getConnection().isClosed());
			//person.personInsert("Mari", "Secondo", "marisecondo@gmail.com");
			//person.personInsert("Minnie", "disney", "minnieTekTonik@gmail.com");
			//person.personInsert("topolino", "disney", "mouse@yahoo.com");
			//person.personDelete(1);
			person.personaSelect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private Connection getConnection() throws SQLException {
		
		if(con == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName("127.0.0.1");
			dataSource.setPortNumber(3306);
			dataSource.setUser("root");
			dataSource.setPassword("root");
			dataSource.setDatabaseName("prova_persona");
			con = dataSource.getConnection();
		}
		return con;
	}
	
	private void personInsert(String nome, String cognome, String mail) throws SQLException {
		String sql = "INSERT INTO persona(nome, cognome, mail)  VALUES ('"+ nome +"','" + cognome +"' ,'" + mail +"') ";
		PreparedStatement statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.executeUpdate();
		
		ResultSet rs = statement.getGeneratedKeys();
		
		
	}
	
	private void personDelete(int id) throws SQLException {
		String sql = "DELETE FROM persona WHERE idpersona = '"+ id +"'";
		PreparedStatement statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.executeUpdate();
	}
	
	private void personUpdate() throws SQLException {
		String sql = "UPDATE persona SET cognome='Secondo' WHERE idpersona=1";
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.executeQuery(); // Si usa quando si fa la select Metodo SELECT
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			System.out.println("idpersona" + rs.getInt(1));
			System.out.println("nome:" + rs.getString(2));
			System.out.println("cognome:" + rs.getString(3));
			System.out.println("mail:" + rs.getString(4));
			System.out.println("--------------------------");
		}
		
	}
	
	
	private void personaSelect() throws SQLException {
		/*Questa riga ci andrà a sostituire l'elenco dei clienti*/
		String sql = "SELECT * FROM persona";
		// passiamo la query in ingresso
		
		PreparedStatement statement = getConnection().prepareStatement(sql);
		statement.executeQuery();
		
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			System.out.println("id" + rs.getInt(1));
			System.out.println("nome:" + rs.getString(2));
			System.out.println("cognome:" + rs.getString(3));
			System.out.println("--------------------------");
		}
	}
	
	
}
