public class BufferHistoryManager {
    private BufferGap<Character> bf;
    private HistorialEdicion history;

    public BufferHistoryManager ( BufferGap<Character> buffer ) {
        bf = buffer;
        history = new HistorialEdicion();
    } // <-> end class constructor



    public void insertar (char character) {
        /* inserta un caracter */
        history.ejecutar( new ComandoInsertar(character, bf) );
    } // <-> end insertar method



    public boolean borrar () {
        /* comprueba y borra el ultimo caracter ingresado, retorna false si no hay elementos */
        if ( bf.size() == 0 ) {
            return false;
        }

        history.ejecutar( new ComandoBorrar(bf) );
        return true;
    } // <-> end borrar method



    public boolean moverCursor (int delta) {
        /* mueve el cursor delta posiciones, retorna false si el indice ingresado es invalido */
        int posicionFinal = delta + bf.posicionCursor();
        if ( posicionFinal < 0 || bf.size() <= posicionFinal ) { // verificar que el indice es valido
            return false;
        }

        history.ejecutar( new ComandoMoverCursor(delta, bf) );
        return true;
    } // <-> end moverCursor method



    public boolean deshacer () {
        /* deshace los cambios hechos por ejecutar() y rehacer() */
        return history.deshacer();
    } // end <-> deshacer method



    public boolean rehacer () {
        /* rehace los cambios hechos por deshacer() */
        return history.rehacer();
    } // end <-> rehacer method



    public int sizeDeshacer () {
        /* retorna cuantos caracteres hay en la pilaDeshacer */
        return history.sizeDeshacer();
    } // <-> end sizeDeshacer method



    public int sizeRehacer () {
        /* retorna cuantos caracteres hay en la pilaRehacer */
        return history.sizeRehacer();
    } // <-> end sizeRehacer method



    public String topeDeshacer () {
        /* retorna la descripcion del ultimo elemento de la pilaDeshacer */
        return history.topeDeshacer();
    } // <-> end topeDeshacer method



        public String topeRehacer () {
        /* retorna la descripcion del ultimo elemento de la pilaRehacer */
        return history.topeRehacer();
    } // <-> end topeRehacer method

} // <> end class
