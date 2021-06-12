package zadanie_1.kog;

public class Kandydaci {

	String imiê, nazwisko;
	Double wynik1, wynik2;
	String wynik;
	public String getImiê() {
		return imiê;
	}
	public void setImiê(String imiê) {
		this.imiê = imiê;
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
	public Kandydaci(String imiê, String nazwisko, Double wynik1, Double wynik2, String wynik) {
		super();
		this.imiê = imiê;
		this.nazwisko = nazwisko;
		this.wynik1 = wynik1;
		this.wynik2 = wynik2;
		this.wynik = wynik;
	}
	
}
