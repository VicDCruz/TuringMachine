import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class FinalFundamentos {
  static String mejorMaquina = new String(); //Almacena la descripcion de la mejor MT que logra.
  static BufferedReader TF;
  static BufferedReader Kbr;
  static String cadenaResultante; //Almacana la producción de la mejor MT que logra.
  static String cadenaMeta = ""; //Cadena a la que se busca llegar.
  static int L;

  /**
  *Metodo para almacenar la cadena a la que queremos llegar.
  **/
  static void cargaCadenaMeta() {
    Kbr=new BufferedReader(new InputStreamReader(System.in));
    String TTFN="";
    System.out.println("Inserte el nombre de archivo y extension que contiene la cadena meta:");
    try {
      TTFN = Kbr.readLine().toUpperCase();

      ///////
      System.out.println("Carga exitosa!");
      System.out.println(TTFN);
      ///////

    } catch (Exception e) {
      System.out.println("No se encontro ese archivo.");
    }
    /*
    *Se intenta cargar cadena desde archivo seleccionado.
    */
    try {
        TF = new BufferedReader(new InputStreamReader(new FileInputStream(new File(TTFN))));
        System.out.println();
        cadenaMeta = TF.readLine();
        //break;
    }//endTry
    catch (Exception e1){
        System.out.println("No se encontro \""+TTFN+"\"");
        //continue;
    }//endCatch

    L = cadenaMeta.length();

    boolean FF;
    String caracter;
    int intCar;
    /*
    *Detectamos si el formato es valido.
    */
    for (int i=0; i<cadenaMeta.length(); i++){
        caracter = cadenaMeta.substring(i,i+1);
        //				System.out.println(i+") : "+Car);
        try {
          intCar = Integer.parseInt(caracter);
          FF = false;
        }
        catch (Exception e){
          FF = true;
          intCar = -2; //Valor anormal para detectar error.
        }
        if ((intCar != 0 && intCar != 1) || FF){
            System.out.println("Error en el formato de la cadena que se quiere generar.");
            System.out.println("Deben ser solamente \"0\" o \"1\"");
            break;	 	// Exit For
        }//endIf
    }//endFor
  }

  /**
  *Generador de la descripcion de la maquina de Turing inicial, y se guarda
  *en un txt.
  **/
  static void generaMaquinaInicial() throws FileNotFoundException {
    mejorMaquina = "";
    /*
     * Generamos aleatoriamente TOH.
     */
    for (int j=0;j<1024;j++){
      if (Math.random()<0.5) {
        mejorMaquina = mejorMaquina + "0";
      }
      else {
        mejorMaquina = mejorMaquina + "1";
      }//end If
    }//end For

    /*
     * Enviamos la "mejor" TM a un archivo TXT.
     */
    PrintStream TM = new PrintStream(new FileOutputStream(new File("TM.TXT")));
    TM.println(mejorMaquina);
  }

  /**
  *Funcion para verifar que tan similares son la cadena objetivo y la cadena
  *generada.
  **/
  static double similaridad(String cintaAComparar) {
    return 1 - (HammingDistance.getHD(cintaAComparar, cadenaMeta)/1024);
  }

  /**
  *Metodo para mutacion aleatoria de cadenas.
  **/
  static String muta() {
    String respuesta = "";
    int M = -1; //M representa el numero de bits a mutar (al menos uno)
    while (M < 1 | M >= 1024) M = (int)(Math.random()*1024);
    for (int i=0; i<M; i++){
      int nBit=-1; while (nBit<0|nBit>=L) nBit=(int)(Math.random()*L); // BIT A MUTAR
      if (nBit==0) continue;	// NO MUTAR SI SOLO SON POSITIVOS
      String mBit="0";
      String G = mejorMaquina;
      // 1) SI EL BIT ESTA EN UN LUGAR INTERMEDIO
      if (nBit != 0 & nBit != 1024-1){
        if (G.substring(nBit,nBit+1).equals("0")) mBit="1";
        respuesta = G.substring(0,nBit)+(mBit)+(G.substring(nBit+1));
        continue;
      }//endif
      // 2) SI EL BIT ES EL PRIMERO
      if (nBit==0){
        if (G.substring(0,1).equals("0")) mBit="1";
        respuesta = mBit+(G.substring(1));
        continue;
      }//endif
      // 3) SI EL BIT ES EL ULTIMO
      if (nBit == 1024-1){
        if (G.substring(1024-1).equals("0")) mBit="1";
        respuesta = G.substring(0,1024-1)+(mBit);
        continue;
      }//endif
    }//endFor
      return respuesta;
  }//endMuta

  /**
  *Metodo para simular una el resultado de una MT determinada.
  **/
  static String simulaMaquina(String maquinaASimular, String cinta) {
    return UTM.NewTape(maquinaASimular,cinta,2000,0); //Numero max de transiciones puede variar.
  }

  public static void main(String[] args){
    double cercania; //Almacena el porcentaje de cercania del mejor resutado
                     //respecto al resultado meta.
    double maxCercania = -1000;
    String mutada, resultadoDeMTMutada, cintaDeCeros="";

    /*
    *Generamos una cadena llena de ceros con la extension de la cadena deseada.
    */
    for(int i = 0; i < cadenaMeta.length(); i++) {
      cintaDeCeros = cintaDeCeros + "0";
    }

    try {
        generaMaquinaInicial();
        cargaCadenaMeta();
        mutada = muta();
        System.out.println(cintaDeCeros);
        System.out.println(mutada);
        System.out.println("Llego");
        resultadoDeMTMutada = simulaMaquina(mutada,cintaDeCeros);
        System.out.println(resultadoDeMTMutada);
        cercania = similaridad(resultadoDeMTMutada);

        if (cercania > maxCercania) {
          maxCercania = cercania;
          mejorMaquina = mutada;
          cadenaResultante = resultadoDeMTMutada;
        }
    } catch (FileNotFoundException ex) {
        Logger.getLogger(FinalFundamentos.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
