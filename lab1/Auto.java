
import java.util.Random;

public class Auto {
	
	float[] przebieg = new float[12];
	
	Auto() {				 
		 for(int i=0;i<przebieg.length;i++)
		    {
		        przebieg[i] = randomFill();
		    }
	}
	
	public static float randomFill(){
        Random rand = new Random();
        int randomNum = rand.nextInt() % 100;
        return randomNum;
    }

	
	float srPrzebieg(){
		float suma = 0;
		
		for(int i=0; i < przebieg.length; i++) 
		    suma += przebieg[i]; 
		
		float srednia = suma / przebieg.length;; 
		
		return srednia;
	
	}
	
}