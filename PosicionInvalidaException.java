public class PosicionInvalidaException extends RuntimeException {
    /* excepciones para cuando se quiera acceder a indices fuera de un rango valido */
    public PosicionInvalidaException (String message) {
        super(message);
    }
}
