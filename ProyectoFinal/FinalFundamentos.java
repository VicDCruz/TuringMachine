import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

class FinalFundamentos {
  static String[] mejorMaquina = new String[1024]; //Almacena la descripcion de la mejor MT que logra.
  String cadenaResultante; //Almacana la producción de la mejor MT que logra.

  /**
  *Generador de la descripcion de la maquina de Turing inicial, y se guarda
  *en un txt.
  **/
  static void generaMaquinaInicial() throws FileNotFoundException {
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
  void similaridad() {

  }

  public static void main(String[] args){
    try {
        generaMaquinaInicial();
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Pruebita.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
