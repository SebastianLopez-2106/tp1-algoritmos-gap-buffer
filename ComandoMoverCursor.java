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
