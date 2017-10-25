package prNotas;

public class Alumno implements Comparable<Alumno> {
	private String nombre;
	private String dni;
	private double nota;
	
	public Alumno(String nombre,String dni,double nota) throws AlumnoException{
		if (nota < 0) {
			throw new AlumnoException("Nota Negativa");
		}
		
		this.nombre = nombre;
		this.dni = dni;
		this.nota = nota;
	}
	public Alumno(String nombre, String dni) throws AlumnoException{
		this(nombre,dni,0.0);
	}
	public String getNombre() {
		return nombre;
	}
	public String getDni() {
		return dni;
	}
	public double getNota() {
		return nota;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Alumno) {
			Alumno aux = (Alumno) o;
			res = aux.dni.equalsIgnoreCase(dni) && aux.nombre.equalsIgnoreCase(nombre);
		}
		
		return res;
	}
	
	@Override
	public int hashCode(){
		return dni.toLowerCase().hashCode() + nombre.toUpperCase().hashCode();
	}
	
	public String toString(){
		return dni + " " + nombre;
	}
	@Override
	public int compareTo(Alumno al) {
		int res = 0;
		
		res = this.nombre.compareToIgnoreCase(al.nombre);
		if (res == 0) {
			res = this.dni.compareToIgnoreCase(al.dni);
		}
		
		return res;
	}
}
