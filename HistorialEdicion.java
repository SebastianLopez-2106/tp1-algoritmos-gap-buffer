public class HistorialEdicion {
    private BufferGap<Character> buffer;    // instancia de BufferGap sobre el cual realizar el historial
    private PilaES<Comando> pilaDeshacer;      // pila para deshacer (Ctrl-z)
    private PilaES<Comando> pilaRehacer;      // pila para rehacer (Ctrl-y)


    public HistorialEdicion (BufferGap<Character> bf) {
        buffer = bf;
        pilaDeshacer = new PilaES<Comando>();
        pilaRehacer = new PilaES<Comando>();
    } // <-> end HistorialEdicion constructor



    public boolean rehacer () {
        /*
        *Vuelve a ejecutar el último comando deshecho y lo devuelve a la pila de deshacer.
        * Retorna false si no hay nada que rehacer.
        */
        if (pilaRehacer.size() > 0) {
            pilaRehacer.desapilar().ejecutar();
            return true;
        }

        return false;
    } // <-> end rehacer method



    public int sizeRehacer () {
        /* retorna la cantidad de elementos que contiene pilaCtrlY */
        return pilaRehacer.size();
    } // <-> end sizeRehacer method

} // <> end HistorialEdicion class
