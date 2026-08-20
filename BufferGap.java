class BufferGap <E> {
    final int TAM_INICIAL = 16;

    private int capacidad;          // 'inicioHueco' -> Cursor (donde se va a escribir)
    private int inicioHueco;        // 'capacidad' empieza desde el 1 y es la capacidad de 'datos'
    private int finHueco;           // 'finHueco' es el primer espacio luego del hueco
    private long desplazamientos;   // contador de cuantas veces se hicieron movimientos fisicos
    private E [] datos;             // arreglo de datos principal


    public BufferGap () {
        datos = (E[]) new Object[TAM_INICIAL]; // realizamos un casting de Object -> E
        inicioHueco = 0;
        finHueco = capacidad = TAM_INICIAL;
        desplazamientos = 0;
    } // <-> end BufferGap constructor



    public void insertar (E element) {
        /*
        * Inserta obj en la posición del cursor y avanza el cursor una posición. Si el hueco
        * se agota, la capacidad se duplica.
        */

        datos[inicioHueco] = element;   // insersion
        inicioHueco += 1;

        // si el buffer se queda sin espacio
        if ( inicioHueco == finHueco ) {                                 // la capacidad se duplica
            E [] tempList = (E[]) new Object[capacidad * 2];    // crea una lista temporal

            for (int i = 0; i < inicioHueco; i++) {         // recorrer los elementos antes del cursor
                tempList[i] = datos[i];
            }

            // si luego del buffer existian elementos, colocarlos al final de la nueva lista
            for (int i = finHueco; i < capacidad; i++) {
                tempList[i + capacidad] = datos[i];     // el nuevo buffer tiene el tamaño de 'capacidad' anterior
            }

            datos = tempList;
            tempList = null;
            finHueco += capacidad;
            capacidad *= 2;
        } // end if
    } // <-> end insertar method



    public E borrar () {
        /* Elimina y retorna el elemento inmediatamente anterior al cursor */


    }


} // <> end BufferGap<E> class