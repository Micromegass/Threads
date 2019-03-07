
import java.util.Random;

 abstract class Wesen extends Thread {
	
	private static Random random = new Random(); 
	
	protected Welt dieWelt; 
	protected int lPos; //Längengrad
	protected int bPos; //Breitengrad
	
	Wesen(Welt dieWelt, int lPos, int bPos) {
		this.dieWelt = dieWelt; 
		this.lPos = lPos; 
		this.bPos = bPos; 	
	}
	
	
	@Override 
    public void run() {
		do {
		try {
			
			sleep(random.nextInt(5000));
           
			} catch (InterruptedException ie) {
            
			}
		
        } while (!istHandelnNoetig());
		
		handeln(); }
  
		protected abstract boolean istHandelnNoetig();
		protected abstract void handeln();
	}



	class Lebewesen extends Wesen {
		
		Lebewesen(Welt dieWelt, int lPos, int bPos) {
			super(dieWelt, lPos, bPos); 
		}
		
		@Override 
		protected boolean istHandelnNoetig() {
			
			int i = dieWelt.anzahlNachbarn(lPos, bPos); 
			return (i<2) || (3 < i); 
		}
		
		@Override
		protected void handeln() {
			
			Wesen w = new Leerwesen(dieWelt, lPos, bPos); 
			dieWelt.setWesen(lPos, bPos, w); 
			w.start(); 
		}
	}
		
		class Leerwesen extends Wesen {
			
			Leerwesen(Welt dieWelt, int lPos, int bPos) {
				
				super(dieWelt, lPos, bPos); 
				
			}
			

		    @Override
		    protected boolean istHandelnNoetig() {
		        return dieWelt.anzahlNachbarn(lPos, bPos) == 3;
		    }

			
			@Override 
			protected void handeln() {
				Wesen w = new Lebewesen(dieWelt, lPos, bPos); 
				dieWelt.setWesen(lPos, bPos, w); 
				w.start();
			}
			
		}
		
		
	
	
	class Welt {
		
		public final int MXG; 
		private Wesen[][] felder;  
		private Oberflaeche oberflaeche; 
		
		Welt(int MXG) {
			this.MXG = MXG;
			felder = new Wesen[MXG][MXG]; 
			
			for (int lPos = 0; lPos < MXG; lPos++) {
				
				for(int bPos = 0; bPos < MXG; bPos++) {
					
					if (Math.random() < 0.25) {
						
						felder[lPos][bPos] = new Lebewesen(this, lPos, bPos); 
						
					} else {
						
						felder[lPos][bPos] = new Leerwesen(this, lPos, bPos); 
					}
					
				}
				
			}
			
			oberflaeche = new Oberflaeche(this); 
			for (int lPos)
			
			
		}
				
 	}
