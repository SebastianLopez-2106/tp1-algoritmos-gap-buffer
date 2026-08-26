/* comando para insertar caracteres en el bufferlist */

/**
 * ELEMENTOS GUARDADOS
 *  > El caracter insertado -> ( Character character )
 *      es necesario para rehacer los cambios deshechos por deshacer().
 *
 *  > La lista con buffer -> ( BufferGap<Character> buffer )
 *      es necesario para que el programa sepa donde insertar el caracter
 *
 *  > El estado de la operacion -> ( boolean state )
 *      es necesario para saber si se puede revertir o rehacer esta operacion, si aun no se ha
 *      insertado el caracter no puedes rehacer la operacion por que todavia no lo
 *      has hecho, esta variable sirve para controlar esto.
 */
public class ComandoInsertar implements Comando {

    private Character character;    // elemento a insertar
    private BufferGap<Character> buffer;
    private boolean state;          // estado, true (hecho) y false (deshecho o no hecho)

    public ComandoInsertar (Character element, BufferGap<Character> bf) {
        character = element;
        buffer = bf;
        state = false;
    } // <-> end ComandoInsertar constructor



    public void ejecutar () {
        /* guarda un elemento en la lista del buffer */

        if (!state) { // si no se ejecuto
            buffer.insertar(character);
            state = true;
        } // end if

    } // <-> end ejecutar method


    public void deshacer () {
        /* deshace la insersión */

        if (state) { // si ya se ejecuto se puede deshacer
            try {
                buffer.borrar();
                state = false;
            } catch (BufferVacioException e) {
                System.out.println(e);
            }
        } // end if

    } // <-> end deshacer method


    public String descripcion () {
        if (state) { // si ya se inserto
            return "inserted '" + character + "'";
        } else { // si aun no se inserto
            return "insert '" + character + "'";
        }
    } // <-> end descripcion method
} // <> end ComandoInsertar class
