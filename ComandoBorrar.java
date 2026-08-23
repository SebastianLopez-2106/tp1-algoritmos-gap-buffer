public class ComandoBorrar implements Comando {

    private Character character;            // elemento a insertar
    private BufferGap<Character> buffer;
    private boolean state;  // estado, true (hecho) y false (deshecho)


    public ComandoBorrar (BufferGap<Character> bf) {
        buffer = bf;
        state = false;
        this.ejecutar();
    } // <-> end ComandoBorrar constructor


    public boolean ejecutar() {
        /* borra un elemento */
        if (!state) {

            try {
                character = buffer.borrar();
                state = true;
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    } // <-> end ejecutar method



    public boolean deshacer () {
        /* inserta el elemento borrado por ejecutar */
        if (state) {
            buffer.insertar(character);
            state = false;
            return true;
        }
        return false;
    } // <-> end deshacer method



    public String descripcion () {
        return
        "pass"
        ;
    } // <-> end descripcion method

} // <> end ComandoBorrar class
