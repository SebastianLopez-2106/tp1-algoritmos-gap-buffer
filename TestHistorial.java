class TestHistorial {
    public static void main(String[] args) {
        BufferGap<Character> bf = new BufferGap<Character>();
        bf.insertar('H');
        bf.insertar('o');
        bf.insertar('l');
        bf.insertar('a');


        BufferHistoryManager history = new BufferHistoryManager( bf );

        // ...
    }
}
