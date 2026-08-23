import java.util.Iterator;


public class BufferGap <E> implements Iterable<E>{
    private final int TAM_INICIAL = 16;

    private int capacidad;          // 'inicioHueco' -> Cursor (donde se va a escribir)
    private int inicioHueco;        // 'capacidad' empieza desde el 1 y es la capacidad de 'datos'
    private int finHueco;           // 'finHueco' es el primer espacio luego del hueco
    private long desplazamientos;   // contador de cuantas veces se hicieron movimientos fisicos
    private E [] datos;             // arreglo de datos principal


    @SuppressWarnings("unchecked")
    public BufferGap () {
        datos = (E[]) new Object[TAM_INICIAL]; // realizamos un casting de Object -> E
        inicioHueco = 0;
        finHueco = capacidad = TAM_INICIAL;
        desplazamientos = 0;
    } // <-> end BufferGap constructor



    @SuppressWarnings("unchecked")
    public void insertar (E element) {
        /*
        * Inserta obj en la posición del cursor y avanza el cursor una posición. Si el hueco
        * se agota, la capacidad se duplica.
        */

        datos[inicioHueco] = element;   // insersion
        inicioHueco += 1;

        // si el buffer se queda sin espacio
        if ( inicioHueco == finHueco ) {                        // la capacidad se duplica
            E [] tempList = (E[]) new Object[capacidad * 2];    // crea una lista temporal

            for ( int i = 0; i < inicioHueco; i++ ) {         // recorrer los elementos antes del cursor
                tempList[i] = datos[i];

                desplazamientos += 1; // <<<<<<<<<<<<<<<<<<<<<<<<<<<< desplazamiento <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            }

            // si luego del buffer existian elementos, colocarlos al final de la nueva lista
            for ( int i = finHueco; i < capacidad; i++ ) {
                tempList[i + capacidad] = datos[i];     // el nuevo buffer tiene el tamaño de 'capacidad' anterior

                desplazamientos += 1; // <<<<<<<<<<<<<<<<<<<<<<<<<<<< desplazamiento <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            }

            datos = tempList;
            tempList = null;
            finHueco += capacidad;
            capacidad *= 2;
        } // end if
    } // <-> end insertar method



    public E borrar () throws BufferVacioException {
        /* Elimina y retorna el elemento inmediatamente anterior al cursor */

        if ( inicioHueco == 0 ) {
            throw new BufferVacioException("No eisten elementos detras del cursor");
        }

        inicioHueco -= 1;
        return datos[inicioHueco];
    } // <-> end borrar method



    public void moverCursor (int delta) throws PosicionInvalidaException {
        /*
        * Desplaza el cursor delta posiciones (negativo hacia la izquierda, positivo hacia la derecha),
        * trasladando los elementos necesarios de un lado del hueco al otro.
        * Lanza una excepción no chequeada si el cursor quedaría fuera de [0, size()].
        */

        // si el cursor cae fuera del rango valido [0; size()]
        if ( inicioHueco + delta < 0 || this.size() < inicioHueco + delta )
            throw new PosicionInvalidaException("indice fuera de rango");

        // mueve el cursor a la izquierda
        if ( delta <= 0) {
            desplazamientos += -delta; // <<<<<<<<<<<<<<<<<<<<<<<<<<<< desplazamiento <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

            for (int i = 1; i <= -delta; i++) {
                datos[ finHueco - i ] = datos[ inicioHueco - i ];
            }

        } else {
            desplazamientos += delta; // <<<<<<<<<<<<<<<<<<<<<<<<<<<< desplazamiento <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

            for (int i = 0; i < delta; i++) {
                datos[ inicioHueco + i ] = datos[ finHueco + i ];
            }
        } // end if else

        inicioHueco += delta;
        finHueco += delta;

    } // <-> end moverCursor method



    public int posicionCursor () {
        /* retorna la posicion logica del cursor */
        return inicioHueco;
    } // <-> end pisicionCursor method



    public int get_finHueco () {
        /* retorna el indice de finHueco */
        return finHueco;
    }



    public E get(int index) throws PosicionInvalidaException {
        /* retorna el elemento ubicado en la posicion logica 'index' */
        if (index >= this.size() || index < 0) {
            throw new PosicionInvalidaException("indice fuera de rango");
        }


        if (index < inicioHueco) {
            return datos[index];
        } else {
            return datos[index + (finHueco - inicioHueco)];
        }
    } // <-> end get method



    public E set(E element, int index) throws PosicionInvalidaException {
        /* reemplaza un elemento existente por uno nuevo */
        if (index >= this.size() || index < 0) {
            throw new PosicionInvalidaException("indice fuera de rango");
        }


        if (index < inicioHueco) {
            E temp = datos[index];
            datos[index] = element;
            return temp;
        } else {
            E temp = datos[index + (finHueco - inicioHueco)];
            datos[index + (finHueco - inicioHueco)] = element;
            return temp;
        }
    } // <-> end set method



    public int size () {
        /* retorna la cantidad de elementos almacenados */
        return capacidad - (finHueco - inicioHueco);
    } // <-> end size method



    public int capacidad () {
        /* retorna la capacidad del arreglo interno */
        return capacidad;
    } // <-> end capacidad method



    public long desplazamientos () {
        /* retorna la cantidad actual de desplazamientos */
        return desplazamientos;
    } // <-> end desplazamientos method



    public void reiniciarDesplazamientos () {
        /* reinicia el desplazamiento +_+ */
        desplazamientos = 0;
    } // <-> end reiniciarDesplazamientos method




    // Iterator ===============================================================>
    @Override
    public Iterator<E> iterator () {
        /* Retorna un Iterator que recorre los elementos en orden lógico, salteando el hueco. */
        return new BufferIterator();
    } // end iterator method

    private class BufferIterator implements Iterator<E> {
        private int index = 0;

        @Override
        public boolean hasNext () {
            return index < size();
        } // end <--> hasNext method (override)

        @Override
        public E next (){

            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            if (index < inicioHueco) {
                index += 1;
                return datos[index - 1];
            } else {
                index += 1;
                return datos[(index - 1) + (finHueco - inicioHueco)]; // index + tamaño del hueco
            }

        } // end <--> next method (override)
    } // end <-> BufferIterator method (override)
    // Iterator ==============================================================>



    @Override
    public String toString () {
        /* Retorna el contenido en orden lógico, con el carácter ` */
        String data = "'";

        if (this.size() == 0) {
            data += "|";
        }


        int cont = 1;
        for (E element : this) {
            data += String.valueOf(element);

            if (cont == inicioHueco) {
                data += String.valueOf('|');
            }
            cont++;
        }
        data += "'";

        return data;
    } // <-> end toString method (override)




    public void view () {
        System.out.println("datos: ");
        int cont = 0;
        for (E element : datos) {
            if (cont == inicioHueco || cont == finHueco - 1) {
                System.out.print("| <" + element + "> |");
            } else {
                System.out.print("| " + element + " |");
            }
            cont++;
        }
        System.out.println();
    }


} // <> end BufferGap<E> class