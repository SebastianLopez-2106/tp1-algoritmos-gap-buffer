/* pila enlazada generica */

public class PilaES <E> {

    private class Nodo {
        private E data;
        private Nodo nexNodo;

        public Nodo (E d) {
            data = d;
            nexNodo = null;
        } // <--> end Nodo constructor


        public E get_data () {
            /* return this node data */
            return this.data;
        } // <--> end getData method


        public Nodo get_nextNodo () {
            /* return this next node */
            return this.nexNodo;
        } // <--> end get_nextNodo method


        public boolean set_nextNodo (Nodo next) {
            /* set this next node */
            if (next != this) {
                this.nexNodo = next;
                return true;
            } else { return false; }
        } // <--> end set_nextNodo method
    } // <-> end Nodo class




    private Nodo head;
    private int size;


    public PilaES () {
        this.head = null;
        this.size = 0;
    } // <-> end PilaES constructor


    public void apilar (E data) {
        /* insert element */
        this.size += 1;
        Nodo nodo = new Nodo(data);
        if ( this.head == null ) {
            this.head = nodo;
            return;
        }

        nodo.set_nextNodo(this.head);
        this.head = nodo;

    } // <-> end apilar method


    public E desapilar () {
        /* return and delete this head list */
        if ( this.head != null ) {
            E temp = this.head.get_data();
            this.head = this.head.get_nextNodo();

            this.size -= 1;
            return temp;
        } else {
            return null;
        }
    } // <-> end desapilar method


    public E tope () {
        /* return this head data list, but not delete */
        if ( this.head != null ) {
            return this.head.get_data();
        } else {
            return null;
        }
    } // <-> end tope method


    public boolean estaVacia () {
        return this.head == null;
    } // <-> end estaVacia method


    public int size () {
        return this.size;
    } // <-> end size method

} // <> end PilaES class
