class UTM {
    /**
     * Regresa el próximo estado dado el estado actual y el bit que se recibe
     * @param TT Descripcion de la Máquina de Turing
     * @param Tape Cinta de entrada
     * @param N Número máximo de transiciones
     * @param P Posición de la cabeza
     * @return Cinta transformada
     */
	public static String[] NewTape(String TT,String Tape,int N,int P){
		int count = 0;
		int posicionActual = P;
		int productividad = 0;
		String[] nextStep;
		String estadoActual = "0";
		String transformedTape = Tape;
                String msg;
		boolean halt = false;
		StateTable states = new StateTable(TT);

		while(count <= N && !halt) {
			nextStep = states.nextStep(transformedTape.charAt(posicionActual),Integer.parseInt(estadoActual));

                        if(nextStep[0].equals("1") && transformedTape.charAt(posicionActual) == '0') {
                            productividad++;
			} else if(nextStep[0].equals("0") && transformedTape.charAt(posicionActual) == '1') {
                            productividad--;
                        }

			transformedTape = transformedTape.substring(0,posicionActual) + nextStep[0] + transformedTape.substring(posicionActual+1);

			if(nextStep[1].equals("R")) {
				if(posicionActual == transformedTape.length()-1)
				{
					posicionActual = 0;
				} else {
					posicionActual = posicionActual+1;
				}
			} else {
				if(posicionActual == 0) {
					posicionActual = transformedTape.length()-1;
				} else {
					posicionActual = posicionActual-1;
				}
			}

			estadoActual = nextStep[2];

			if(estadoActual.equals("H")){
				halt = true;
			}

			count++;
		}

		if(halt) {
			msg = "Se alcanzo estado HALT.\n";
		}else {
			msg = "No se alcanzo estado HALT.\n";
		}

		msg = msg + "Se realizaron " + count + " transiciones. \n" + "La productividad fue de " + productividad + ".\n";

		String[] resultado = {transformedTape, msg};
		return resultado;
	}

    /**
     * @param args the command line arguments
     * Main de prueba
     */
//    public static void main(String[] args) {
//        String TT = "10000001110000100100000001000011110000001111111111000001100001000000001100000001";
//        //"100000010000000010000010000000001011111100000000";
//        String tape = "000000000000000000000000000000000000000000000000000000";
//        StateTable st = new StateTable(TT);
//        System.out.println(st.getState());
//        int trans = 1000;
//        int pos = 4;
//        String resultado = UTM.NewTape(TT,tape,trans,pos);
//        System.out.println(resultado);
//    }

}
