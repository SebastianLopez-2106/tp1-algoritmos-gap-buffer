public class TestBufferGap {
    








    public void showData (BufferGap<Character> bf) {
        System.out.println(bf.toString());
        System.out.printf(" > inicioHueco: %d\n", bf.posicionCursor());
        System.out.printf(" > finHueco: %d\n", bf.get_finHueco());
        System.out.printf(" > capacidad: %d\n", bf.capacidad());
        System.out.printf(" > desplazamientos: %d\n\n", bf.desplazamientos());
    } // <-> end showData method

} // <> end TestBufferGap class
