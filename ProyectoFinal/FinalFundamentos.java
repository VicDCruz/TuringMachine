import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

class FinalFundamentos {
  static String[] mejorMaquina = new String[1024]; //Almacena la descripcion de la mejor MT que logra.
  static BufferedReader TF;
  static BufferedReader Kbr;
  String cadenaResultante; //Almacana la producción de la mejor MT que logra.
  String cadenaMeta; //Cadena a la que se busca llegar.

  /**
  *Metodo para almacenar la cadena a la que queremos llegar-
  **/
  static void cargaCadenaMeta() {
    Kbr=new BufferedReader(new InputStreamReader(System.in));
    String TTFN="";
    System.out.println("Inserte el nombre de archivo y extension que contiene la cadena meta:");
    TTFN = Kbr.readLine().toUpperCase();
    /*
    *Se intenta cargar cadena desde archivo seleccionado.
    */
    try {
        TF = new BufferedReader(new InputStreamReader(new FileInputStream(new File(TTFN))));
        System.out.println();
        break;
    }//endTry
    catch (Exception e1){
        System.out.println("No se encontro \""+TTFN+"\"");
        continue;
    }//endCatch

    cadenaMeta = TF.readLine();

    boolean forever = false, FF;
    String caracter;
    int intCar;
    /*
    *Detectamos si el formato es valido.
    */
    for (i=0; i<cadenaMeta.lenght(); i++){
        caracter = TT.substring(i,i+1);
        //				System.out.println(i+") : "+Car);
        try {
          intCar = Integer.parseInt(Car);
          FF = false;
        }
        catch (Exception e){
          FF = true;
        }
        if ((intCar != 0 && intCar != 1) || FF){
            System.out.println("Error en el formato de la Maquina de Turing");
            System.out.println("Deben ser solamente \"0\" o \"1\"");
            forever = true;
            break;	 	// Exit For
        }//endIf
    }//endFor

    if (forever)
        continue;
    //endIf
    if (cadenaMeta.length() % 16 != 0){
        System.out.println("La longitud de la Maquina de Turing debe ser multiplo de 16");
        continue;
    }//endIf
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
  double similaridad(String maquinaAComparar) {
    return 1 - (HammingDistance.getHD(mejorMaquina, maquinaAComparar)/1024);
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
      if (nBit==0&SP) continue;	// NO MUTAR SI SOLO SON POSITIVOS
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

  public static void main(String[] args){
    try {
        generaMaquinaInicial();
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Pruebita.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
