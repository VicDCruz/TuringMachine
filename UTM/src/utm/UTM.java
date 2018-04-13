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
		String transformedTape = Tape;
		boolean halt = false;
		StateTable states = new StateTable(TT);

		while(count <= N && !halt) {
			nextStep = states.nextStep(transformedTape.charAt(posicionActual),Integer.parseInt(estadoActual));
			
                        transformedTape = transformedTape.substring(0,posicionActual) + nextStep[0] + transformedTape.substring(posicionActual+1);

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
     * Main de prueba
     */
//    public static void main(String[] args) {
//        String TT = "100000010000000010000010000000001011111100000000";
//        String tape = "000000000000000000000000000000000000000000000000000000";
//        int trans = 1000;
//        int pos = 4;
//        String resultado = UTM.NewTape(TT,tape,trans,pos);
//        System.out.println(resultado);
//    }
    
}
