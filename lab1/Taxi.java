
public class Taxi extends Auto {
	
		float[] zarobki = new float[12];
	
		Taxi() {		
			for(int i=0;i<zarobki.length;i++){
		        zarobki[i] = randomFill();
		        }		
		}

		float srZarobki(){
			float suma = 0;
			
			for(int i=0; i < zarobki.length; i++) 
			    suma += zarobki[i]; 
			
			float srednia = suma / zarobki.length;; 
			
			return srednia;
		
		}
		
		public static void main(String[ ] args){
			Taxi obiekt = new Taxi();
			
			System.out.println(obiekt.srZarobki());
			System.out.println(obiekt.srPrzebieg());
		}

}


