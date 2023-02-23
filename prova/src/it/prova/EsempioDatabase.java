package it.prova;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource; 


public class EsempioDatabase {
	private Connection con;
	public static void main(String[] args) {
		
		EsempioDatabase d = new EsempioDatabase();
		
		
		try {
			//System.out.println(d.startConnection().isValid(100));
			//System.out.println(d.startConnection().isClosed());
			//d.esempioSelect2();
			//d.esempioInsert("Luca", "Rossi");
			//d.esempioDelete();
			d.esempioUpdate();
		}catch (SQLException e){
			System.out.println("Siamo nel catch, c'è un errore di connessione");
			e.printStackTrace();
			
		}
		
	}
	
	private Connection startConnection() throws SQLException {
		if(con == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName("127.0.0.1");
			dataSource.setPortNumber(3306);
			dataSource.setUser("root");
			dataSource.setPassword("root");
			dataSource.setDatabaseName("corso_java_prova");
			con = dataSource.getConnection();
			
		}
		return con;
	}
	
	private void esempioSelect() throws SQLException {
		/*Questa riga ci andrà a sostituire l'elenco dei clienti*/
		String sql = "SELECT * FROM utenti";
		// passiamo la query in ingresso
		
		PreparedStatement statement = startConnection().prepareStatement(sql);
		statement.executeQuery();
		
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			System.out.println("id" + rs.getInt(1));
			System.out.println("nome:" + rs.getString(2));
			System.out.println("cognome:" + rs.getString(3));
			System.out.println("--------------------------");
		}
	}
	
	private void esempioSelect2() throws SQLException {
		String sql = "SELECT idUtenti, nome, cognome FROM utenti WHERE cognome LIKE '%ROS%'";
		PreparedStatement statement = startConnection().prepareStatement(sql);
		statement.executeQuery();
		
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			System.out.println("id" + rs.getInt(1));
			System.out.println("nome:" + rs.getString(2));
			System.out.println("cognome:" + rs.getString(3));
			System.out.println("--------------------------");
	}
		
		

	}
	
	private void esempioInsert(String nome, String cognome) throws SQLException {
		String sql = "INSERT INTO utenti(nome, cognome) VALUES ('"+ nome +"','" + cognome +"')";
		PreparedStatement statement = startConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.executeUpdate();
		
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		System.out.println("id: "+ rs.getInt(1));
		

	}
	
	private void esempioDelete() throws SQLException {
		String sql = "DELETE FROM utenti WHERE idUtenti = 3";
		PreparedStatement statement = startConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.executeUpdate();
	}
	
	private void esempioUpdate() throws SQLException {
		String sql = "UPDATE utenti SET cognome='Taverna' WHERE idUtenti=2";
		PreparedStatement statement = startConnection().prepareStatement(sql);
		statement.executeUpdate();
		
	}
}