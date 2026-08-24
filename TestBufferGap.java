import java.util.Random;


public class TestBufferGap {
    public static void main (String [] args) {

        System.out.println("\n1.========================================================");
        /*
        * 1. nstancie un BufferGap<Character> y reproduzca exactamente la traza de las
        * tablas anteriores, imprimiendo después de cada operación el contenido,
        * inicioHueco, finHueco, la capacidad y el contador de desplazamientos. La salida
        * tiene que coincidir con las tablas; si no coincide, hay un bug.
        */

        BufferGap<Character> bf = new BufferGap<Character>();

        // Fase A ============================================================>
        System.out.println("-----------------------------------------------");
        System.out.println("Fase A: Escribir Hola de corrido.");
        System.out.println("-----------------------------------------------");

        System.out.println("(Inicial)");
        showData(bf);

        System.out.println("insertar ('H')");
        bf.insertar('H');
        showData(bf);

        System.out.println("insertar ('o')");
        bf.insertar('o');
        showData(bf);

        System.out.println("insertar ('l')");
        bf.insertar('l');
        showData(bf);

        System.out.println("insertar ('a')");
        bf.insertar('a');
        showData(bf);

        // Fase B ============================================================>
        System.out.println("\n-----------------------------------------------");
        System.out.println("Fase B: Volver atras y editar");
        System.out.println("-----------------------------------------------");

        System.out.println("moverCursor(-2)");
        bf.moverCursor(-2);
        showData(bf);

        System.out.println("insertar ('X')");
        bf.insertar('X');
        showData(bf);

        System.out.println("get(4) -> '" + bf.get(4) + "'");
        showData(bf);

        try {
            System.out.println("borrar() -> '" + bf.borrar() + "'");
        } catch (Exception e) { }
        showData(bf);




        System.out.println("\n2.========================================================");
        /*
        * 2. Inserte 100.000 caracteres aleatorios al final y verifique con el iterador (for-each)
        * que la cantidad y el orden son correctos.
        */

        System.out.println("moverCursor(2)");
        bf.moverCursor(2);
        showData(bf);

        Character [] control = new Character[100000];

        // add random characters
        Random random = new Random();
        for (int i = 1; i <= 100000; i++) {
            Character c = Character.valueOf( (char) (random.nextInt(26) + 'a'));
            bf.insertar( c );
            control[i-1] = c;
        }


        int cont = 0;
        for (Character character : bf) {

            if (cont >= 4) { // saltea el 'Hola'
                if ( !control[cont-4].equals(character) ) {   // imprime los indices donde hay errores de orden
                    System.out.println("Error de orden en el indice: " + cont);
                }
            }
            cont++;
        } // end for

        System.out.printf("\ncapacidad(): %d\nsize(): %d\nfor-each: %d\n", bf.capacidad(), bf.size(), cont);




        System.out.println("\n3.========================================================");
        /*
        * 3. Conteo de desplazamientos en el medio. Con n caracteres ya cargados, ubique el
        * cursor en n/2, reinicie el contador de desplazamientos e inserte 10.000 caracteres
        * en esa posición. Reporte cuántos desplazamientos hizo la estructura. Repita para
        * varios n con incrementos de 100.000, empezando de 100.000 hasta 1.000.000.
        */

        for (int i = 1; i <= 10; i++) { // n = i*100.000

            BufferGap<Character> bf2 = new BufferGap<>();
            for (int j = 1; j <= i*100000; j++) {   // carga n (i*100.000) caracteres
                bf2.insertar('#');
            }

            bf2.moverCursor(-i*100000/2);            // cursor en n/2
            bf2.reiniciarDesplazamientos();         // reiniciar desplazamientos

            for (int j = 1; j <= 10000; j++) {      // carga 10.000 caracteres en n/2
                bf2.insertar('#');
            }
            System.out.printf("> P/n = %d\n    desplazamientos: %d\n\n", i*100000, bf2.desplazamientos());

        }// end for

    } // <-> end main method



    public static void showData (BufferGap<Character> bf) {
        /* para imprimir los datos solicitados en cada modificacion de la lista */
        System.out.println(bf.toString());
        System.out.printf(" > inicioHueco: %d\n", bf.posicionCursor());
        System.out.printf(" > finHueco: %d\n", bf.get_finHueco());
        System.out.printf(" > capacidad: %d\n", bf.capacidad());
        System.out.printf(" > desplazamientos: %d\n\n", bf.desplazamientos());
    } // <-> end showData method

} // <> end TestBufferGap class
