public class HistorialEdicion {
    private BufferGap<Character> buffer;    // instancia de BufferGap sobre el cual realizar el historial
    private PilaES<Comando> pilaCtrlZ;      // pila para Ctrl-Z
    private PilaES<Comando> pilaCtrlY;      // pila para Ctrl-y


    public HistorialEdicion (BufferGap<Character> bf) {
        buffer = bf;
        pilaCtrlZ = new PilaES<Comando>();
        pilaCtrlY = new PilaES<Comando>();
    } // <-> end HistorialEdicion constructor



} // <> end HistorialEdicion class
