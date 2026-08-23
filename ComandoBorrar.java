public class ComandoBorrar implements Comando {

    private Character character;            // elemento a insertar
    private BufferGap<Character> buffer;
    private boolean state;  // estado, true (hecho) y false (deshecho)


    public ComandoBorrar (BufferGap<Character> bf) {
        buffer = bf;
        state = false;
    } // <-> end ComandoBorrar constructor


    public void ejecutar() {
        /* borra un elemento */

        if (!state) { // si no se ejecuto
            try {
                character = buffer.borrar();
                state = true;

            } catch (BufferVacioException e) {
                System.out.println(e);
            }
        }

    } // <-> end ejecutar method



    public void deshacer () {
        /* inserta el elemento borrado por ejecutar */

        if (state) { // si se ejecuto se puede deshacer
            buffer.insertar(character);
            state = false;
        }

    } // <-> end deshacer method



    public String descripcion () {
        if (state) { // si se borro
            return "deleted '" + character + "'";
        } else {

            if (character == null) { // si aun no se obtuvo el caracter

                if (buffer.posicionCursor() > 0) { // si el cursor no esta en el indice 0
                    return "delete '" + buffer.get(buffer.posicionCursor() - 1) + "'";

                } else { // si esta en el indice 0 retorna un texto generico
                    return "delete character";
                }

            } else { // si ya se tiene el caracter
                return "delete '" + character + "'";
            }

        } // end if-else

    } // <-> end descripcion method

} // <> end ComandoBorrar class
