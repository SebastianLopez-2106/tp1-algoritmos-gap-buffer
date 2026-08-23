public class TestBufferGap {
    public static void main (String [] args) {
        BufferGap<Character> bf = new BufferGap<Character>();

        // Fase A ============================================================>
        System.out.println("\n-----------------------------------------------");
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
