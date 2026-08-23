public class ComandoMoverCursor implements Comando {

    private int delta;            // elemento a insertar
    private BufferGap<Character> buffer;
    private boolean state;  // estado, true (hecho) y false (deshecho)

    public ComandoMoverCursor (int delta, BufferGap<Character> bf) {
        this.delta = delta;
        buffer = bf;
        state = false;
        this.ejecutar();
    } // <-> end ComandoInsertar constructor



    public boolean ejecutar () {
        /* mueve el cursor */
        if (!state) {
            try {
                buffer.moverCursor(delta);
                state = true;
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    } // <-> end ejecutar method


    public boolean deshacer () {
        /* devuelve el cursor a su posicion anterior */
        if (state) {
            try {
                buffer.moverCursor(-delta);
                state = false;
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    } // <-> end deshacer method


    public String descripcion () {
        return
        "pass"
        ;
    } // <-> end descripcion method
}
