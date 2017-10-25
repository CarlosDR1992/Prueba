import java.io.IOException;

import prNotas.Alumno;
import prNotas.Asignatura;
import prNotas.OrdenAlternativoAlumno;

public class TestNotas {
    
    public static void main(String[] args) {
        Asignatura algebra = new Asignatura("Algebra");
    
        System.out.println("\n\n Lectura de fichero");
        try {
            algebra.leerDatos("datosAlumnos.txt");
        } catch (IOException e) {
            System.out.println("Fichero no existe " + e.getMessage());
        }
        
        System.out.println(algebra);
        
        for (Alumno al : algebra.getAlumnos()) {
        		System.out.println(al.getNombre() + " -> " + al.getNota());
        }
        
        System.out.println(" \n Aprobados ordenados por dni/ nombre \n");
        for (Alumno al : algebra.getAlumnosAprobados(new OrdenAlternativoAlumno())) {
        		System.out.println(al);
        }
        System.out.println(" \n Aprobados ordenados por nombre/ dni \n");
        for (Alumno al : algebra.getAlumnosAprobados(null)) {
        		System.out.println(al);
        }
        System.out.println(algebra.calificacion());
    }
}
