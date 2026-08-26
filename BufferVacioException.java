
/**
 * JUSTIFICACIÓN: Se implementa como chequeada porque intentar borrar en un buffer vacío (cuando el cursor está en la posición 0)
 * es una situación normal y previsible que el programa debe manejar. Obligamos al código que llama a usar un try-catch para
 * capturarla y simplemente ignorar la acción o realizar alguna otra como emitir un sonido, sin que el programa colapse.
 */
public class BufferVacioException extends Exception {
    /* excepcion para cuando se quiera modificar algo inexistente */
    public BufferVacioException (String message) {
        super(message);
    }
}
