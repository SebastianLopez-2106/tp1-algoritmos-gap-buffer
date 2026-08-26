
/**
 * JUSTIFICACIÓN: Se implementa como no chequeada porque solicitar un índice fuera de los
 * límites en get, set o moverCursor representa un error de lógica en el código (un bug).
 * No es una situación de la que el programa deba recuperarse en tiempo de ejecución, sino
 * un error que el programador debe corregir asegurándose de pasar índices válidos.
 */
public class PosicionInvalidaException extends  RuntimeException{
    /* excepciones para cuando se quiera acceder a indices fuera de un rango valido */
    public PosicionInvalidaException (String message) {
        super(message);
    }
}
