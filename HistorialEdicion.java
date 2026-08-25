/* maneja la logica para el historial de cambios (Ctrl-y/Ctrl-z) */

public class HistorialEdicion {
    /* las pilas guardan objetos del tipo comando que permiten interactuar con la lista del buffer */
    private PilaES<Comando> pilaDeshacer;   // pila para deshacer (Ctrl-z)
    private PilaES<Comando> pilaRehacer;    // pila para rehacer (Ctrl-y)


    public HistorialEdicion () {
        pilaDeshacer = new PilaES<Comando>();
        pilaRehacer = new PilaES<Comando>();
    } // <-> end HistorialEdicion constructor



    public void ejecutar(Comando c) {
        /*
         * Ejecuta el comando recibido y lo apila en Deshacer, luego vacía la pila Rehacer
         * ya que al ejecutar un nuevo comando el futuro que estaba en deshacer ya no es válido.
         */
        c.ejecutar();
        pilaDeshacer.apilar(c);
        pilaRehacer = new PilaES<>();
    }



    public boolean deshacer(){
        /*
         *    Deshace el ultimo comando realizo(solamente si hay algo para deshacer o sino retorna falso) y lo pasa a la pila de Rehacer.
         */
        if(pilaDeshacer.estaVacia()){
            return false;
        }
        Comando c = pilaDeshacer.desapilar();
        c.deshacer();
        pilaRehacer.apilar(c);
        return true;
    }



    public boolean rehacer () {
        /*
        *Vuelve a ejecutar el último comando deshecho y lo devuelve a la pila de deshacer.
        * Retorna false si no hay nada que rehacer.
        */
        if (pilaRehacer.size() > 0) {
            pilaRehacer.desapilar().ejecutar();
            return true;
        }

        return false;
    } // <-> end rehacer method



    public int sizeDeshacer(){
        /* retorna cantidad de elementos que contiene pilaDeshacer*/
        return pilaDeshacer.size();
    }



    public int sizeRehacer () {
        /* retorna la cantidad de elementos que contiene pilaCtrlY */
        return pilaRehacer.size();
    } // <-> end sizeRehacer method



    public String topeDeshacer () {
        /* retorna la descripcion del ultimo elemento de la pilaDeshacer */
        if (pilaDeshacer.tope() != null) {
            return pilaDeshacer.tope().descripcion();
        }
        return "no hay elementos para deshacer";
    } // <-> end topeDeshacer method


    public String topeRehacer () {
        /* retorna la descripcion del ultimo elemento de la pilaRehacer */
        if (pilaRehacer.tope() != null) {
            return pilaRehacer.tope().descripcion();
        }
        return "no hay elementos para rehacer";
    } // <-> end topeRehacer method


} // <> end HistorialEdicion class
