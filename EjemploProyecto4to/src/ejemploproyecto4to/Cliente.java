
package ejemploproyecto4to;


public class Cliente {
   String nit, nombre;
   int edad;
   char genero;

    public Cliente(String nit, String nombre, int edad, char genero) {
        this.nit = nit;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public String getNit() {
        return this.nit;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }

   
}
