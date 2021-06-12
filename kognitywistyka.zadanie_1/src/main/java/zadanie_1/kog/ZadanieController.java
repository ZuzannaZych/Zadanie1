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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ZadanieController {

	@FXML Button zatwierdz;
	@FXML TableView<Kandydaci> tabela;
	@FXML Button tura2;
	@FXML Button tura1;
	@FXML Button pokaz;
	@FXML Button zaladuj;
	@FXML TableColumn<Kandydaci, String> imiê;
	@FXML TableColumn<Kandydaci, String>  nazwisko;
	@FXML TableColumn<Kandydaci, Double>  wynik1;
	@FXML TableColumn<Kandydaci, Double>  wynik2;
	@FXML TableColumn<Kandydaci, String>  wynik;
	
	ObservableList<Kandydaci> list = FXCollections.observableArrayList(
			new Kandydaci("Zuzanna", "Zych", null, null, null),
			new Kandydaci("Gabriela", "Stankiewicz", null, null, null),
			new Kandydaci("Patryk", "Vega", null, null, null),
			new Kandydaci("Robert", "Biedroñ", null, null, null),
			new Kandydaci("Marcin", "Dubiel", null, null, null)
			
			);
	
	public void initialize () {
		imiê.setCellValueFactory(new PropertyValueFactory<Kandydaci, String>("imiê"));
		nazwisko.setCellValueFactory(new PropertyValueFactory<Kandydaci, String>("nazwisko"));
		wynik1.setCellValueFactory(new PropertyValueFactory<Kandydaci, Double>("wynik1"));
		wynik2.setCellValueFactory(new PropertyValueFactory<Kandydaci, Double>("wynik2"));
		wynik.setCellValueFactory(new PropertyValueFactory<Kandydaci, String>("wynik"));
		
		
	}
	
	public void Table(Statement s) throws SQLException {
		s.execute("DROP TABLE IF EXISTS KANDYDAT_WYBORY");
		s.execute("CREATE TABLE KANDYDAT_WYBORY (ID INT IDENTITY PRIMARY KEY, IMIE VARCHAR(255), NAZWISKO VARCHAR(255), WYNIK_1_TURA DOUBLE, WYNIK_2_TURA DOUBLE, CZY_WYGRAL BOOLEAN)");
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
	public void zwyciêzca(PreparedStatement s) throws SQLException {
		s.setBoolean(1, true);
		s.execute();
	}
	public void przegrany(PreparedStatement s) throws SQLException {
		s.setBoolean(1, false);
		s.execute();}
	
	@FXML public void ³adowanie() {
		pokaz.setDisable(false);
		try (Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "")) {
			try(Statement s = c.createStatement()){
				Table(s);
			}
			try(PreparedStatement s = c.prepareStatement("INSERT INTO KANDYDAT_WYBORY (IMIE, NAZWISKO) VALUES (?, ?)")){
				wartoœciTable(s);		
			}
			

		} catch (SQLException e) {
			Assertions.fail(e);
		}

	}

	@FXML public void pokazuj() {
		tabela.setItems(list);
		tura1.setDisable(false);
	}

	@FXML public void glosowanie1() {
		
		list.set(0, new Kandydaci("Zuzanna", "Zych", 1.05, null, null));
		list.set(1, new Kandydaci("Gabriela", "Stankiewicz", 2.05, null, null));
		list.set(2, new Kandydaci("Patryk", "Vega", 3.05, null, null));
		list.set(3, new Kandydaci("Robert", "Biedroñ", 3.75, null, null));
		list.set(4, new Kandydaci("Marcin", "Dubiel", 90.00, null, null));
		
		
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
			
		} catch (SQLException e) {
			Assertions.fail(e);
		}
		tura2.setDisable(false);
		
	}

	@FXML public void glosowanie2() {
		
		list.set(0, new Kandydaci("Zuzanna", "Zych", 1.05, null, null));
		list.set(1, new Kandydaci("Gabriela", "Stankiewicz", 2.05, null, null));
		list.set(2, new Kandydaci("Patryk", "Vega", 3.05, null, null));
		list.set(3, new Kandydaci("Robert", "Biedroñ", 3.75, 10.05, null));
		list.set(4, new Kandydaci("Marcin", "Dubiel", 90.00, 89.95, null));
		
		
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
			
		} catch (SQLException e) {
			Assertions.fail(e);
		}
		zatwierdz.setDisable(false);
	}

	@FXML public void wyniki() {
		list.set(0, new Kandydaci("Zuzanna", "Zych", 1.05, null, "przegrany"));
		list.set(1, new Kandydaci("Gabriela", "Stankiewicz", 2.05, null, "przegrany"));
		list.set(2, new Kandydaci("Patryk", "Vega", 3.05, null, "przegrany"));
		list.set(3, new Kandydaci("Robert", "Biedroñ", 3.75, 10.05, "przegrany"));
		list.set(4, new Kandydaci("Marcin", "Dubiel", 90.00, 89.95, "wygrany"));
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
				}
		} catch (SQLException e) {
			Assertions.fail(e);
		}
		
	}
	
	
	
}
