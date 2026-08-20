public class BufferVacioException extends Exception {
    /* excepcion para cuando se quiera modificar algo inexistente */
    public BufferVacioException (String message) {
        super(message);
    }
}
