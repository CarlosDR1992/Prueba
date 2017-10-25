package prNotas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Asignatura {
	private static final boolean Alumno = false;
	private static final double APROBADO = 0;
	private String nombre;
	private List<Alumno> alumnos;
	private List<String> errores;
		
	public Asignatura(String n){
		nombre = n;
		alumnos = new ArrayList<Alumno>();
		errores = new ArrayList<String>();
	}
	
	public void leerDatos(String nomFichero) throws IOException{
		try( Scanner fichero = new Scanner(new File(nomFichero))
		){
			while(fichero.hasNextLine()){
				StringAAlumno(fichero.nextLine());
			}
		}
		
	}
	
	public void leerDatos1(String nombreFichero) throws I
	
	private void StringAAlumno (String linea) {
		try (Scanner sc = new Scanner(linea)) {
			sc.useDelimiter("-");
			sc.useLocale(Locale.ENGLISH);
			
			String dni = sc.next();
			String nombre = sc.next();
			double nota = sc.nextDouble();
			Alumno al = null;
			
			if (sc.hasNext()) {
				double notaP = sc.nextDouble();
				al = new AlumnosErasmus(nombre, dni ,nota ,notaP);
			} else {
				al = new Alumno(nombre, dni, nota);
			}
		
			alumnos.add(al);
			
		} catch (InputMismatchException e) {
			errores.add("Error Formato no munerico " + linea);
		} catch (NoSuchElementException e) {
			errores.add("Error Faltan datos " + linea);
		} catch (AlumnoException e) {
			errores.add("Error nota negativa " + linea);
		}
	}
	
	public String toString() {
		return nombre + "\n" + alumnos.toString() + "\n" + errores.toString();
	}
	
	public List<Alumno> getAlumnos() {	
		return alumnos;
	}
	
	public List<String> getErrores() {
		return errores;
	}
	
	public double getMedia() {
		double media = 0.0;
		
		if (alumnos.size() != 0) {
			for (Alumno al: alumnos) {
				media =+ al.getNota();
			}
			
		media = media / alumnos.size();
		}
		
		return media;
	}
	
	public double getMedia2() {
		return alumnos.stream().mapToDouble(Alumno::getCalificacion).avarage()
				.orElseThrow(()-> new AlumnoException("No exsite el alumno"));
		}
	
	public Set<Alumno> getAlumnosAprobados(Comparator<Alumno> comparador) {
		Set<Alumno> aprobados; 
		
		if (comparador == null) {
			aprobados = new TreeSet<Alumno>();
		} else {
			aprobados = new TreeSet<Alumno>(comparador);
		}
	 
		
		for (int i = 0 ; i< alumnos.size(); i++) {
			Alumno al = alumnos.get(i);
			if (al.getNota() >= 5) {
				aprobados.add(al);
			}
		}
		
		return aprobados;
	}
	
	public Set<Alumno> getAlumnosAprobados1() {
		return alumnos.stream()
				.filter(al -> al.getNota() >= APROBADO)
				.collect(Collectors.toSet());
	}
	public Set<Alumno> getAlumnosAprobados2() {
		Comparator<Alumno> ca = Comparator.comparingDouble(Alumno::getNota).reversed()
				.thenComparing(Comparator.comparing(Alumno::getDouble));
		return alumnos.stream()
				.filter(al -> al.getNota() >= APROBADO)
				.collect(Collectors.toCollection(()->new TreeSet<>(ca)));
	}
	
	public void anularMatricula(Collection<Alumno> c) {
		for (Alumno al : c) {
			alumnos.remove(al);
		}		
	}
	
	public double getCalificacion(Alumno al) throws AlumnoException {
		double nota = -1;
		
		Iterator<Alumno> it = alumnos.iterator();
		
		while (it.hasNext() && nota < 0) {
			Alumno alumno = it.next();
			if (alumno.equals(al)) {
				nota = al.getNota();
			}
		}
		
		
		if (nota < 0) {
			throw new AlumnoException("Alumno no encontrado");
		}
		
		return nota;
	}
	
	public double getCalificacion2(Alumno al) throws AlumnoException {
		return alumnos.stream().filter(Predicate.isEqual(al)).findFirst()
						.orElseThrow(
								() -> new AlumnoException("No existe el alumno" + al)).getNota();	
	}
	
	public void vaciarErrores() {
		errores.clear();
	}
	
	public Set<Alumno> maximaNota() {
		Set<Alumno> trabajadores = new HashSet<Alumno>();
		double nota = calcularMaxNota();
		
		for (Alumno al : alumnos) {
			if (al.getNota() == nota) {
				trabajadores.add(al);
			}
		}
		
		return trabajadores;
	}

	private double calcularMaxNota( ) {
		double maxima = 0.0;
		
		for (int i = 0; i < alumnos.size(); i++) {
			double nota = alumnos.get(i).getNota();
			if (nota >= maxima) {
				maxima = nota;
			}
		}
		
		return maxima;
	}
	
	public Map<Double, Set<Alumno>> calificacion( ) {
		Map<Double, Set<Alumno>> m = new TreeMap<>();
		
		for (Alumno al : alumnos) {
			Double nota = al.getNota();
			
			Set<Alumno> alumnoMismanota = m.get(nota);
			
			if (alumnoMismanota == null) {
				alumnoMismanota = new HashSet<Alumno>();
				m.put(nota, alumnoMismanota);
			}
			alumnoMismanota.add(al);
		}
		
		return m;
	}
	
	public Map<Double, List<Alumno>> cnla() {
		Comparator<Double> cd = Comparator.<Double>naturalOrder().reversed();
		return alumnos.stream().collect(Collectors
				.groupingBy(Alumno::getNota,
						()-> new TreeMap<>(cd),
						Collectors.toSet()));
		
	}
	
	public Map<Double, Set<Alumno>> cnca() {
		return alumnos.stream()
				.collect(Collectors.groupingBy(Alumno::getNota,
						Collectors.toSet()));
	}
	
	public Map<Double, Set<String>> cncna() {
		return alumnos.stream()
				.collect(Collectors.groupingBy(Alumno::getNota,
						TreeMap::new,
						Collectors.mapping(Alumno::getNombre, 
								Collectors.toSet())));
						
	}
	
	public Map<Double, Long> cnnnal() {
		return alumnos.stream()
				.collect(Collectors.groupingBy(Alumno::getNota,
						Collectors.counting()));
	}
	public Map<Double, Integer> cnnnal2() {
		return alumnos.stream()
				.collect(Collectors.groupingBy(Alumno::getNota,
						Collectors.summingInt(al -> 1)));
	}
	
	
}