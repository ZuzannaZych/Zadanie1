package zadanie_1.kog;

public class Kandydaci {

	String imi�, nazwisko;
	Double wynik1, wynik2;
	String wynik;
	public String getImi�() {
		return imi�;
	}
	public void setImi�(String imi�) {
		this.imi� = imi�;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public Double getWynik1() {
		return wynik1;
	}
	public void setWynik1(Double wynik1) {
		this.wynik1 = wynik1;
	}
	public Double getWynik2() {
		return wynik2;
	}
	public void setWynik2(Double wynik2) {
		this.wynik2 = wynik2;
	}
	public String getWynik() {
		return wynik;
	}
	public void setWynik(String wynik) {
		this.wynik = wynik;
	}
	public Kandydaci(String imi�, String nazwisko, Double wynik1, Double wynik2, String wynik) {
		super();
		this.imi� = imi�;
		this.nazwisko = nazwisko;
		this.wynik1 = wynik1;
		this.wynik2 = wynik2;
		this.wynik = wynik;
	}
	
}
