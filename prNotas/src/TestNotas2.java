import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import prNotas.Alumno;
import prNotas.Asignatura;

public class TestNotas2 {
    

    public static void main(String[] args) {
        Asignatura algebra = new Asignatura("Algebra");
        System.out.println(algebra);

        System.out.println("\n\n Lectura de fichero");
        try {
            algebra.leerDatos("datosAlumnos.txt");
            System.out.println(algebra); //Se duplican los errores pero no los alumnos
        } catch (IOException e) {
            System.out.println("Fichero no existe " + e.getMessage());
        }
        
              
        Alumno al1 = new Alumno("Lopez Gama, Luisa", "42424312G");    //Sólo hace falta dni y nombre para
        Alumno al2 = new Alumno("Fernandez Vara, Pedro", "34242442J"); //ver si son iguales. Lo demás se ignora

        try {
            System.out.println("Calificacion de " + al1 + ": "
                    + algebra.getCalificacion(al1));
            System.out.println("Calificacion de " + al2 + ": "
                    + algebra.getCalificacion(al2));
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\nCalificaciones " + algebra.calificacion());
        
        
        Collection<Alumno> c = new HashSet<>();
        c.add(al1);
        algebra.anularMatricula(c);
        algebra.vaciarErrores();
        System.out.println("Matrículas actualizadas \n" + algebra);   
        
        System.out.println("Máxima calificación\n" + algebra.maximaNota());
        
        System.out.println("Media: " + algebra.getMedia());

       
        System.out.println("Alumnos aprobados...");
        for (Alumno alumno : algebra.getAlumnosAprobados()) {
            System.out.println(alumno + ": " + alumno.getCalificacion());
        }
        
        System.out.println("Alumnos aprobados2...");
        for (Alumno alumno : algebra.getAlumnosAprobados()) {
            System.out.println(alumno + ": " + alumno.getCalificacion());
        }

        algebra.eliminar("Santana Medina, Petra");
        
        System.out.println("Asignatura...");
        System.out.println(algebra);
        
        System.out.println(algebra.calificacion());

    }
}

/*SALIDA
 * Algebra:
[]
Errores
[]


 Lectura de fichero
Algebra:
[25653443S Garcia Gomez, Juan, 23322443K Lopez Turo, Manuel, 24433522U Merlo Martinez, Juana, 53553421D Santana Medina, Petra, 42424312G Lopez Gama, Luisa]
Errores
[Faltan datos 55343442L,Godoy Molina, Marina-6.3, Nota no numérica 34242442J-Fernandez Vara, Pedro-tr]
Calificacion de 42424312G Lopez Gama, Luisa: 7.1
El alumno no se encuentra

Calificaciones {NOTABLE=[25653443S Garcia Gomez, Juan, 42424312G Lopez Gama, Luisa, 53553421D Santana Medina, Petra], APROBADO=[24433522U Merlo Martinez, Juana], SUSPENSO=[23322443K Lopez Turo, Manuel]}
Matrículas actualizadas 
Algebra:
[25653443S Garcia Gomez, Juan, 23322443K Lopez Turo, Manuel, 24433522U Merlo Martinez, Juana, 53553421D Santana Medina, Petra]
Errores
[]
Máxima calificación
[25653443S Garcia Gomez, Juan]
Media: 6.199999999999999
Alumnos aprobados...
25653443S Garcia Gomez, Juan: 8.1
24433522U Merlo Martinez, Juana: 5.3
53553421D Santana Medina, Petra: 7.1
Alumnos aprobados2...
25653443S Garcia Gomez, Juan: 8.1
24433522U Merlo Martinez, Juana: 5.3
53553421D Santana Medina, Petra: 7.1
Asignatura...
Algebra:
[25653443S Garcia Gomez, Juan, 23322443K Lopez Turo, Manuel, 24433522U Merlo Martinez, Juana]
Errores
[]
{NOTABLE=[25653443S Garcia Gomez, Juan], APROBADO=[24433522U Merlo Martinez, Juana], SUSPENSO=[23322443K Lopez Turo, Manuel]}
*/
