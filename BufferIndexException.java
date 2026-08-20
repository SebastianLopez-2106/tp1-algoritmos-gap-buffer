public class BufferIndexException extends Exception {
    /* excepciones para cuando se quiera acceder a indices fuera de un rango valido */
    public BufferIndexException (String message) {
        super(message);
    }
}
