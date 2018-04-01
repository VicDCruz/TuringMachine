# TuringMachine
Fundamentos Matemáticos de la Computación: Proyecto 1 - Simular una Máquina de Turing

## Comentarios:
* Se nos proporcionó:
	  * La cinta con 0's y 1's en ASCII
	  * La máquina de estados en 2 dimensiones
	  * La posición de la cabeza
	  * El tamaño de la cinta
	  * El número de movimientos

* Debemos:
	  1. Crear la clase UTM (*Universal Turing Machine*)
	    * Argumentos:
		      * TT: Descripción de la MT en Binario-ASCII (La "tabla" de estados)
		      * Cinta: Contenido de la cinta en Binario-ASCII para una MT simulada
		      * N: Número máximo de transiciones
		      * P: Posición de la cabeza
	  2. Leer el car de donde está la cabeza
	  3. Ir a la "tabla" de estados
	  4. Revisar que caracter poner y hacia dónde movernos
	  5. Regresar a (i)

### Nuestro código
Se utiliza en la línea **268**

Debemos crear la función **NewTape** en la clase UTM
