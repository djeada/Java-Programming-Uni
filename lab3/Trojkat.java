package lab3;

public class Trojkat implements Figura {
	private double dlugoscBoku1, dlugoscBoku2, dlugoscBoku3;
	
	Trojkat(double dlugoscBoku1, double dlugoscBoku2, double dlugoscBoku3){
		this.dlugoscBoku1 = dlugoscBoku1;
		this.dlugoscBoku2 = dlugoscBoku3;
		this.dlugoscBoku3 = dlugoscBoku3;
	}
	
	@Override
	public double obliczObwod() {
		// P = a + b + c
		return dlugoscBoku1+dlugoscBoku2+dlugoscBoku3;
	}

	@Override
	public double obliczPole() {
		// Heron's formula:
        // A = SquareRoot(s * (s - a) * (s - b) * (s - c)) 
        // where s = (a + b + c) / 2, or 1/2 of the perimeter of the triangle 
		double s = (dlugoscBoku1 + dlugoscBoku2 + dlugoscBoku3) / 2;
		return Math.sqrt(s * (s - dlugoscBoku1) * (s - dlugoscBoku2) * (s - dlugoscBoku3));
	}

}
