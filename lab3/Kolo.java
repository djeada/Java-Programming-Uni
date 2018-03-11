package lab3;

public class Kolo implements Figura {

	private double promien;
	
	public Kolo(double promien){
		this.promien = promien;
	}
	
	@Override
	public double obliczObwod() {
		// P = 2 pi r
		return 2.0 * pi * promien;
	}

	@Override
	public double obliczPole() {
		// A = pi r^2
		return pi * Math.pow(promien, 2.0);
	}

}
