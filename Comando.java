public interface Comando {
    /* interface for comands */

    public boolean ejecutar ();       // ejecuta el comando

    public boolean deshacer ();       // deshace el comando, retorna false si no hay nada

    public String descripcion ();  // retorna una breve descipcion del comando

} // <> end Comando interface
