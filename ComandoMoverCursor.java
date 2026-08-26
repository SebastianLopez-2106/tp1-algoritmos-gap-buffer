/* comando para mover el cursor del bufferlist */

/**
 * ELEMENTOS GUARDADOS
 *  > Los desplazamientos -> ( int delta )
 *      decidimos guardar los desplazamientos devido a que los cambios hechos son guardados
 *      en una pila (pilaDeshacer) y estos se revierten en orden inverso, de modo que cuando
 *      llegue el momento de revertir esta operacion el cursor se posicionara automaticamente
 *      'delta' posiciones de su ubicacion antes de ser movido, para revertir esto simplemente se
 *      deve mover el cursor '-delta' posiciones y volvera a su estado inicial.
 *      Guardar el indice desencadenaria calculos extra para calcular el valor de -delta
 *
 *  > La lista con buffer -> ( BufferGap<Character> buffer )
 *      es necesario para que el programa sepa en que lista mover el cursor
 *
 *  > El estado de la operacion -> ( boolean state )
 *      es necesario para saber si se puede revertir o rehacer esta operacion, si aun no se ha
 *      movido el cursor 'delta' posiciones no puedes rehacer la operacion por que todavia no lo
 *      has hecho, esta variable sirve para controlar esto.
 */
public class ComandoMoverCursor implements Comando {

    private int delta;      // pasos a mover el cursor (indice logico)
    private BufferGap<Character> buffer;
    private boolean state;  // estado, true (hecho) y false (deshecho o no hecho)

    public ComandoMoverCursor (int delta, BufferGap<Character> bf) {
        this.delta = delta;
        buffer = bf;
        state = false;
    } // <-> end ComandoMoverCursor constructor



    public void ejecutar () {
        /* mueve el cursor */

        if (!state) { // si no se ejecuto
            try {
                buffer.moverCursor(delta);
                state = true;
            } catch (PosicionInvalidaException e) {
                System.out.println(e);
            }
        } // end if
    } // <-> end ejecutar method


    public void deshacer () {
        /* devuelve el cursor a su posicion anterior */

        if (state) { // si ya se ejecuto se puede deshacer
            try {
                buffer.moverCursor(-delta);
                state = false;

            } catch (PosicionInvalidaException e) {
                System.out.println(e);
            }
        }// end if

    } // <-> end deshacer method


    public String descripcion () {
        if (state) { // si se ejecuto
            return "moved " + delta + " positions";
        } else {
            return "returns " + -delta + " positions";
        }
    } // <-> end descripcion method
}
