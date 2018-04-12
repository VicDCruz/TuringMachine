/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utm;

/**
 *
 * @author marco
 */
public class UTM {

    /**
     * Regresa el próximo estado dado el estado actual y el bit que se recibe
     * @param TT Descripcion de la Máquina de Turing
     * @param Tape Cinta de entrada
     * @param N Número máximo de transiciones
     * @param P Posición de la cabeza
     * @return Cinta transformada
     */
	public static String NewTape(String TT,String Tape,int N,int P){
		int count = 0;
		int posicionActual = P;
		String[] nextStep;
		String estadoActual = "0";
		String transformedTape = TT;
		boolean halt = false;
		StateTable states = new StateTable(TT);

		while(count <= N && !halt) {
			nextStep = states.nextStep(Tape.charAt(posicionActual),Integer.parseInt(estadoActual));

			transformedTape = Tape.substring(0,posicionActual) + nextStep[0] + Tape.substring(posicionActual+1);

			if(nextStep[1].equals("R")) {
				posicionActual = posicionActual+1;				 
			} else {
				posicionActual = posicionActual-1;
			}

			estadoActual = nextStep[2];

			if(estadoActual.equals("H")){
				halt = true;
			} 
			
			count++;
		}

		return transformedTape;
	}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
}
