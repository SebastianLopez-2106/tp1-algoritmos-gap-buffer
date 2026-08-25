public class BufferHistoryManager {
    private BufferGap<Character> bf;
    private HistorialEdicion history;


    /**
     * Maneja la clase HistorialEdicion
     *
     * @param buffer el buffer sobre el cual se va a trabajar
     */
    public BufferHistoryManager ( BufferGap<Character> buffer ) {
        bf = buffer;
        history = new HistorialEdicion();
    } // <-> end class constructor


    /**
     * inserta un elemento a la lista
     * @param character caracter a ser insertado
     */
    public void insertar (char character) {
        /* inserta un caracter */
        history.ejecutar( new ComandoInsertar(character, bf) );
    } // <-> end insertar method


    /**
     * borra un elemento de la lista
     * @return true -> si se pudo borrar
     * @return false -> si no se completo la accion
     */
    public boolean borrar () {
        /* comprueba y borra el ultimo caracter ingresado, retorna false si no hay elementos */
        if ( bf.size() == 0 ) {
            return false;
        }

        history.ejecutar( new ComandoBorrar(bf) );
        return true;
    } // <-> end borrar method


    /**
     * mueve el cursor delta posiciones
     * @param delta posivito mueve a la derecha y negativo a la izquierda
     * @return true -> si se pudo borrar
     * @return false -> si no se completo la accion
     */
    public boolean moverCursor (int delta) {
        /* mueve el cursor delta posiciones, retorna false si el indice ingresado es invalido */
        int posicionFinal = delta + bf.posicionCursor();
        if ( posicionFinal < 0 || bf.size() <= posicionFinal ) { // verificar que el indice es valido
            return false;
        }

        history.ejecutar( new ComandoMoverCursor(delta, bf) );
        return true;
    } // <-> end moverCursor method


    /**
     * deshace el ultimo cambio realizado
     */
    public boolean deshacer () {
        /* deshace los cambios hechos por ejecutar() y rehacer() */
        return history.deshacer();
    } // end <-> deshacer method


    /**
     * rehace el ultimo cambio echo por deshacer
     * @return true -> si se pudo deshacer
     * @return false -> si no se pudo deshacer
     */
    public boolean rehacer () {
        /* rehace los cambios hechos por deshacer() */
        return history.rehacer();
    } // end <-> rehacer method


    /**
     * @return cantidad de elementos a deshacer
     */
    public int sizeDeshacer () {
        /* retorna cuantos caracteres hay en la pilaDeshacer */
        return history.sizeDeshacer();
    } // <-> end sizeDeshacer method


    /**
     * @return cantidad de elementos a rehacer
     */
    public int sizeRehacer () {
        /* retorna cuantos caracteres hay en la pilaRehacer */
        return history.sizeRehacer();
    } // <-> end sizeRehacer method


    /**
     * @return descripcion del siguiente elemento a deshacer
     */
    public String topeDeshacer () {
        /* retorna la descripcion del ultimo elemento de la pilaDeshacer */
        return history.topeDeshacer();
    } // <-> end topeDeshacer method


    /**
     * @return descripcion del siguiente elemento a rehacer
     */
        public String topeRehacer () {
        /* retorna la descripcion del ultimo elemento de la pilaRehacer */
        return history.topeRehacer();
    } // <-> end topeRehacer method

} // <> end class
