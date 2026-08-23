public interface Comando {
    /* interface for comands */

    public void ejecutar ();       // ejecuta el comando

    public void deshacer ();       // deshace el comando, retorna false si no hay nada

    public String descripcion ();  // retorna una breve descipcion del comando

} // <> end Comando interface
