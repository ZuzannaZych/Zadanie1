package zadanie_1.kog;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZadaniePierwsze {


	@Test
	public void testConnection() {
		try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")) {

		} catch (SQLException e) {
			Assertions.fail(e);
		}
	}
	
		
		public void Table(Statement s) throws SQLException {
			s.execute("DROP TABLE IF EXISTS KANDYDAT_WYBORY");
			s.execute("CREATE TABLE KANDYDAT_WYBORY (ID INT IDENTITY PRIMARY KEY, IMIE VARCHAR(255), NAZWISKO VARCHAR(255), WYNIK_1_TURA DOUBLE, WYNIK_2_TURA DOUBLE, CZY_WYGRAL BOOLEAN)");
		}
		
		@Test
		public void testTable() {
			try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")) {
				try(Statement s = c.createStatement()){
					Table(s);
				}
			} catch (SQLException e) {
				Assertions.fail(e);
			}
		}
		
		public void wartoœciTable(PreparedStatement s) throws SQLException{
			s.setString(1, "Zuzanna");
			s.setString(2, "Zych");
			s.execute();
			s.setString(1, "Gabriela");
			s.setString(2, "Stankiewicz");
			s.execute();
			s.setString(1, "Patryk");
			s.setString(2, "Vega");
			s.execute();
			s.setString(1, "Robert");
			s.setString(2, "Biedroñ");
			s.execute();
			s.setString(1, "Marcin");
			s.setString(2, "Dubiel");
			s.execute();
		}
		
		@Test
		public void testWartoœci() {
			try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")) {
				try(Statement s = c.createStatement()){
					Table(s);
				}
				try(PreparedStatement s = c.prepareStatement("INSERT INTO KANDYDAT_WYBORY (IMIE, NAZWISKO) VALUES (?, ?)")){
					wartoœciTable(s);		
				}
				try(Statement s = c.createStatement()){
					List<Object[]> results = new ArrayList<>();
					try (ResultSet rs = s.executeQuery("SELECT * FROM KANDYDAT_WYBORY")) {
						while (rs.next()) {
							Object[] row = new Object[2];
							row[0] = rs.getString("IMIE");
							row[1] = rs.getString("NAZWISKO");
							results.add(row);
						}
					}
					Assertions.assertArrayEquals(new Object[] { "Zuzanna", "Zych" }, results.get(0));
					Assertions.assertArrayEquals(new Object[] { "Gabriela", "Stankiewicz" }, results.get(1));
					Assertions.assertArrayEquals(new Object[] { "Patryk", "Vega" }, results.get(2));
					Assertions.assertArrayEquals(new Object[] { "Robert", "Biedroñ" }, results.get(3));
					Assertions.assertArrayEquals(new Object[] { "Marcin", "Dubiel" }, results.get(4));
					
				}
			} catch (SQLException e) {
				Assertions.fail(e);
			}
		}
		
		private ArrayList<Integer> getIds(Statement s) throws SQLException{
			ResultSet rs = s.executeQuery("SELECT ID FROM KANDYDAT_WYBORY");
			ArrayList<Integer> ids = new ArrayList<>();
			while (rs.next()) {
				int row = rs.getInt("ID");
				ids.add(row);
			}
			return ids;
		}


		private void wyniki(PreparedStatement s, ArrayList<Integer> ids, double[] wynik) throws SQLException {
			
			s.setDouble(1, 1.05);
			s.setInt(2, ids.get(0));
			s.execute();
			
			s.setDouble(1, 2.05);
			s.setInt(2, ids.get(1));
			s.execute();
			
			s.setDouble(1, 3.05);
			s.setInt(2, ids.get(2));
			s.execute();
			
			s.setDouble(1, 3.75);
			s.setInt(2, ids.get(3));
			s.execute();
			
			s.setDouble(1, 90.00);
			s.setInt(2, ids.get(4));
			s.execute();
			}
		
		private void wyniki2(PreparedStatement s, ArrayList<Integer> ids, double[] wynik) throws SQLException {
			
			s.setDouble(1, 0);
			s.setInt(2, ids.get(0));
			s.execute();
			
			s.setDouble(1, 0);
			s.setInt(2, ids.get(1));
			s.execute();
			
			s.setDouble(1, 0);
			s.setInt(2, ids.get(2));
			s.execute();
			s.setDouble(1, 10.05);
			s.setInt(2, ids.get(3));
			s.execute();
			
			s.setDouble(1, 89.95);
			s.setInt(2, ids.get(4));
			s.execute();
			}
		
		@Test
		public void testWyników() {
			try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")) {
				try(Statement s = c.createStatement()){
					Table(s);
				}
				try(PreparedStatement s = c.prepareStatement("INSERT INTO KANDYDAT_WYBORY (IMIE, NAZWISKO) VALUES (?, ?)")){
					wartoœciTable(s);		
				}
				try (Statement s = c.createStatement()) {
					ArrayList<Integer> ids = getIds(s);
					try(PreparedStatement ps = c.prepareStatement("UPDATE KANDYDAT_WYBORY SET WYNIK_1_TURA = ? WHERE ID = ?")){
						double[] wyniki = {1.05, 2.05, 3.05, 3.75, 90.00};
						wyniki(ps, ids, wyniki);
					}
				}
				try (Statement s = c.createStatement()) {
					List<Object[]> results = new ArrayList<>();
					try (ResultSet rs = s.executeQuery("SELECT * FROM KANDYDAT_WYBORY")) {
						while (rs.next()) {
							Object[] row = new Object[3];
							row[0] = rs.getString("IMIE");
							row[1] = rs.getString("NAZWISKO");
							row[2] = rs.getDouble("WYNIK_1_TURA");
							results.add(row);
						}
					}
					Assertions.assertArrayEquals(new Object[] { "Zuzanna", "Zych", 1.05 }, results.get(0));
					Assertions.assertArrayEquals(new Object[] { "Gabriela", "Stankiewicz", 2.05 }, results.get(1));
					Assertions.assertArrayEquals(new Object[] { "Patryk", "Vega", 3.05 }, results.get(2));
					Assertions.assertArrayEquals(new Object[] { "Robert", "Biedroñ", 3.75 }, results.get(3));
					Assertions.assertArrayEquals(new Object[] { "Marcin", "Dubiel", 90.00 }, results.get(4));
					
				}
			} catch (SQLException e) {
				Assertions.fail(e);
			}
		}
		
		@Test
		public void pierwszaTura() {
			try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")) {
				try(Statement s = c.createStatement()){
					Table(s);
				}
				try(PreparedStatement s = c.prepareStatement("INSERT INTO KANDYDAT_WYBORY (IMIE, NAZWISKO) VALUES (?, ?)")){
					wartoœciTable(s);		
				}
				try (Statement s = c.createStatement()) {
					ArrayList<Integer> ids = getIds(s);
					try(PreparedStatement ps = c.prepareStatement("UPDATE KANDYDAT_WYBORY SET WYNIK_1_TURA = ? WHERE ID = ?")){
						double[] wyniki = {1.05, 2.05, 3.05, 3.75, 90.00};
						wyniki(ps, ids, wyniki);
					}
				}
				try (Statement s = c.createStatement()) {
					List<Object[]> results = new ArrayList<>();
					try (ResultSet rs = s.executeQuery("SELECT * FROM KANDYDAT_WYBORY ORDER BY WYNIK_1_TURA DESC LIMIT 2")) {
						while (rs.next()) {
														
							Object[] row = new Object[3];
							row[0] = rs.getString("IMIE");
							row[1] = rs.getString("NAZWISKO");
							row[2] = rs.getDouble("WYNIK_1_TURA");
							results.add(row);
						}
					}
					Assertions.assertArrayEquals(new Object[] { "Robert", "Biedroñ", 3.75 }, results.get(1));
					Assertions.assertArrayEquals(new Object[] { "Marcin", "Dubiel", 90.00 }, results.get(0));
					
				}
			} catch (SQLException e) {
				Assertions.fail(e);
			}
		}
		
		@Test
		public void drugaTura() {
			try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")) {
				try(Statement s = c.createStatement()){
					Table(s);
				}
				try(PreparedStatement s = c.prepareStatement("INSERT INTO KANDYDAT_WYBORY (IMIE, NAZWISKO) VALUES (?, ?)")){
					wartoœciTable(s);		
				}
				
				try (Statement s = c.createStatement()) {
					ArrayList<Integer> ids = getIds(s);
					try(PreparedStatement ps = c.prepareStatement("UPDATE KANDYDAT_WYBORY SET WYNIK_2_TURA = ? WHERE ID = ?")){
						double[] wyniki = {0,0,0, 10.05, 89.95};
						wyniki2(ps, ids, wyniki);
					}
				}
				try (Statement s = c.createStatement()) {
					List<Object[]> results = new ArrayList<>();
					try (ResultSet rs = s.executeQuery("SELECT * FROM KANDYDAT_WYBORY ORDER BY WYNIK_2_TURA DESC LIMIT 2")) {
						while (rs.next()) {
														
							Object[] row = new Object[3];
							row[0] = rs.getString("IMIE");
							row[1] = rs.getString("NAZWISKO");
							row[2] = rs.getDouble("WYNIK_2_TURA");
							results.add(row);
						}
					}
					Assertions.assertArrayEquals(new Object[] { "Robert", "Biedroñ", 10.05 }, results.get(1));
					Assertions.assertArrayEquals(new Object[] { "Marcin", "Dubiel", 89.95 }, results.get(0));
					
				}
			} catch (SQLException e) {
				Assertions.fail(e);
			}
		}
		public void zwyciêzca(PreparedStatement s) throws SQLException {
			s.setBoolean(1, true);
			s.execute();
		}
		public void przegrany(PreparedStatement s) throws SQLException {
			s.setBoolean(1, false);
			s.execute();
		}
		@Test
		public void wygrana() {
			try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")) {
				try(Statement s = c.createStatement()){
					Table(s);
				}
				try(PreparedStatement s = c.prepareStatement("INSERT INTO KANDYDAT_WYBORY (IMIE, NAZWISKO) VALUES (?, ?)")){
					wartoœciTable(s);		
				}
				
				try (Statement s = c.createStatement()) {
					ArrayList<Integer> ids = getIds(s);
					try(PreparedStatement ps = c.prepareStatement("UPDATE KANDYDAT_WYBORY SET WYNIK_2_TURA = ? WHERE ID = ?")){
						double[] wyniki = {0,0,0, 10.05, 89.95};
						wyniki2(ps, ids, wyniki);
					}
				}
				try (Statement s = c.createStatement()) {
					
					try(PreparedStatement ps = c.prepareStatement("UPDATE KANDYDAT_WYBORY SET CZY_WYGRAL = ? WHERE WYNIK_2_TURA = 89.95")){

						zwyciêzca(ps);
					}	
					try(PreparedStatement ps = c.prepareStatement("UPDATE KANDYDAT_WYBORY SET CZY_WYGRAL = ? WHERE WYNIK_2_TURA != 89.95")){

						przegrany(ps);
					}
					}try (Statement s = c.createStatement()) {
						List<Object[]> results = new ArrayList<>();
						try (ResultSet rs = s.executeQuery("SELECT * FROM KANDYDAT_WYBORY")) {
							while (rs.next()) {
															
								Object[] row = new Object[3];
								row[0] = rs.getString("IMIE");
								row[1] = rs.getString("NAZWISKO");
								row[2] = rs.getBoolean("CZY_WYGRAL");
								results.add(row);
							}
						} Assertions.assertArrayEquals(new Object[] { "Robert", "Biedroñ", false }, results.get(3));
						Assertions.assertArrayEquals(new Object[] { "Marcin", "Dubiel", true }, results.get(4));
						Assertions.assertArrayEquals(new Object[] { "Zuzanna", "Zych", false }, results.get(0));
						Assertions.assertArrayEquals(new Object[] { "Gabriela", "Stankiewicz", false }, results.get(1));
						Assertions.assertArrayEquals(new Object[] { "Patryk", "Vega", false }, results.get(2));
						
				}
			} catch (SQLException e) {
				Assertions.fail(e);
			}
		}

	}

