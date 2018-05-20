import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class FinalFundamentos {
  static String mejorMaquina = new String(); //Almacena la descripcion de la mejor MT que logra.
  static BufferedReader TF;
  static BufferedReader Kbr;
  static RandomAccessFile Datos = null;
  static String cadenaResultante; //Almacana la producción de la mejor MT que logra.
  static String cadenaMeta = ""; //Cadena a la que se busca llegar.
  static int L;

  /**
  *Metodo para almacenar la cadena a la que queremos llegar.
  **/
  static void cargaCadenaMeta() {
    Kbr=new BufferedReader(new InputStreamReader(System.in));
    String TTFN="";
    String sResp="";
    int i;
    System.out.println("Inserte el nombre de archivo y extension que contiene la cadena meta:");
    try {
      TTFN = Kbr.readLine().toUpperCase();
      Datos=new RandomAccessFile(new File(TTFN), "r");
    } catch (Exception e) {
      System.out.println("No se encontro ese archivo.");
    }
    String Car;
    while (true){
        System.out.println("Los datos estan en binario-ASCII? (S/N)");
        try {
          sResp=Kbr.readLine().toUpperCase();
        } catch (Exception e) {
          System.out.println("Error inesperado al recibir respuesta");
        }
        if (sResp.equals("S")||sResp.equals("N"))
            break;
    //endIf
    }//endWhile

    /*
    *Se intenta cargar cadena desde archivo seleccionado.
    */
   try {
      if (sResp.equals("N")){
        System.out.println("Deme el nombre del archivo de salida de imagen numerica:");
        String FTarget=Kbr.readLine().toUpperCase();
        PrintStream Fps=new PrintStream(new FileOutputStream(new File(FTarget)));
        int BytesEnDatos=0;
        byte X;
        /*
        *	Averigua el tamanio del archivo en bytes
        */
        while (true){
          Datos.seek(BytesEnDatos);
          try{X=Datos.readByte();}
          catch (Exception e){break;}
          BytesEnDatos++;
        }//endWhile
        Datos.close();
        System.out.println("Se leyeron "+BytesEnDatos+" datos\n");
        //
        /*
        *	Convierte en binario-ASCII
        */
        int Y,T;
        Datos=new RandomAccessFile(new File(TTFN), "r");
        for (i=0;i<BytesEnDatos;i++){
          Datos.seek(i);
          Y=Datos.readByte();
          T=Y;								// T <-- Numero original
          Car="";
          for (int j=0;j<8;j++){
            if (Y%2==0) Car="0"+Car; else Car="1"+Car;
            Y=Y/2;
          } // endFor
          cadenaMeta=cadenaMeta+Car;
        }//endFor
        Fps.printf(cadenaMeta);	// Escribe cada uno de los bytes leidos
        Fps.println();
      }else{
        Datos.close();
        BufferedReader FCinta;
        FCinta=new BufferedReader(new InputStreamReader(new FileInputStream(new File(TTFN))));
        cadenaMeta=FCinta.readLine();
      }//endIf
    } catch (Exception e) {
      System.out.println("Error inesperado al recibir respuesta");
    }
    L = cadenaMeta.length();

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
    return 1 - (double)(HammingDistance.getHD(cintaAComparar, cadenaMeta)/(double)cadenaMeta.length());
  }

  /**
  *Metodo para mutacion aleatoria de cadenas.
  **/
  static String muta(String maquinaAMutar) {
    String respuesta = "";
    int M = -1; //M representa el numero de bits a mutar (al menos uno)
    while (M < 1 | M >= 1024) M = (int)(Math.random()*1024);
    for (int i=0; i<M; i++){
      int nBit=-1; 
      while (nBit<0|nBit>=1024) nBit=(int)(Math.random()*1024); // BIT A MUTAR
      if (nBit==0) continue;	// NO MUTAR SI SOLO SON POSITIVOS
      String mBit="0";
      String G = maquinaAMutar;
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
    return UTM.NewTape(maquinaASimular,cinta,10000,0); //Numero max de transiciones puede variar.
  }

  public static void main(String[] args){
    double cercania = 0; //Almacena el porcentaje de cercania del mejor resutado
                        //respecto al resultado meta.
    double maxCercania = -1000;
    int iteracionLogroMaximo = -2;
    String mutada, resultadoDeMTMutada, cintaDeCeros="", cadenaActual="";

    try {
      generaMaquinaInicial();
      cargaCadenaMeta();
      mutada = mejorMaquina;

      System.out.println("La cadena esperada es: " + cadenaMeta + "\n");

      /*
      *Generamos una cadena llena de ceros con la extension de la cadena deseada.
      */
      for(int i = 0; i < cadenaMeta.length(); i++) {
        cintaDeCeros = cintaDeCeros + "0";
      }

      ////////
      //cadenaResultante = cintaDeCeros;
      ////////

      int MAX_ITERACIONES = 20000;
      int contadorIteraciones = 1;

      while(cercania != 1 && contadorIteraciones <= MAX_ITERACIONES) {
        //System.out.print("."); //Para hacer ver al usuario que seguimos computando.

        mutada = muta(mutada);
        //System.out.println(cintaDeCeros);
        //System.out.println(mutada);
        //System.out.println("Llego");

        //System.out.print(".");
        
        if(mutada.length()==1024){
            //Prueba simulando con la mejor resultante en lugar de puros ceros.
            resultadoDeMTMutada = simulaMaquina(mutada,cintaDeCeros);
            //System.out.println("Resultado a partir de MT mutada: " + resultadoDeMTMutada);
            //resultadoDeMTMutada = simulaMaquina(mutada,cadenaResultante);
            cadenaActual=resultadoDeMTMutada;
            cercania = similaridad(resultadoDeMTMutada);
            //System.out.println("Porcentaje de acierto: " + cercania);
        }        

        System.out.print(".");

        if (cercania > maxCercania) {
          System.out.println("El máximo porcentaje de cercania es: " + cercania);
          //System.out.println(resultadoDeMTMutada);
          maxCercania = cercania;
          mejorMaquina = mutada;
          cadenaResultante = cadenaActual;
          iteracionLogroMaximo = contadorIteraciones;
          System.out.println("Iteracion: " + contadorIteraciones);
        }

        //System.out.println("Iteracion: " + contadorIteraciones);
        contadorIteraciones++;
      }

    } catch (FileNotFoundException ex) {
      Logger.getLogger(FinalFundamentos.class.getName()).log(Level.SEVERE, null, ex);
    }

    System.out.println();
    System.out.println("La mejor cinta lograda fue: " + cadenaResultante);
    System.out.println("Se aproxima con un porcentaje de: " + maxCercania);
    System.out.println("Se produjo en la iteracion: " + iteracionLogroMaximo);
  }
}
