public class ComandoInsertar implements Comando {

    private Character character;            // elemento a insertar
    private BufferGap<Character> buffer;
    private boolean state;  // estado, true (hecho) y false (deshecho)

    public ComandoInsertar (Character element, BufferGap<Character> bf) {
        character = element;
        buffer = bf;
        state = false;
        this.ejecutar();
    } // <-> end ComandoInsertar constructor



    public boolean ejecutar () {
        /* guarda un elemento en la lista del buffer */
        if (!state) {
            buffer.insertar(character);
            return true;
        }
        return false;
    } // <-> end ejecutar method


    public boolean deshacer () {
        /* deshace la insersión */
        if (state) {
            state = false;

            try {
                buffer.borrar();
                return true;
            } catch (Exception e) {
                return false;
            }

        } else {
            return state;
        }
    } // <-> end deshacer method


    public String descripcion () {
        return
        "pass"
        ;
    } // <-> end descripcion method
} // <> end ComandoInsertar class
