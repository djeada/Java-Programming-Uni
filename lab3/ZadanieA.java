package lab3;

public class ZadanieA {
	public static void main(String[] args) {
		
		// Test dla kola
		double promien = 5.20;
		Figura kolo = new Kolo(promien);
		System.out.println("Promien kola: " + promien + "\nobliczone pole: " + kolo.obliczPole() + "\nobliczony obwod: " + kolo.obliczObwod() + "\n");
		
		// Test dla trojkata
		double a = 5, b = 3, c = 4;
		Figura trojkat = new Trojkat(a,b,c);
		System.out.println("Dlugosci bokow: " + a + ", " + b + ", " + c + "\nobliczone pole: " + trojkat.obliczPole()+ "\nobliczony obwod: " + trojkat.obliczObwod() + "\n");
	}
}
