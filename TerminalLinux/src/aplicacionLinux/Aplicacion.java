package aplicacionLinux;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Aplicacion {

	private static Path rutaActual;
	private static String comando;
	private static int numeroLineas;
	private static String sistemaOperativo;
	private static String nombreUsuario;
	private static String separadorRuta;
	private static String nombre;

	public static String getNombre() {
		return nombre;
	}

	public static void setNombre(String nombre) {
		Aplicacion.nombre = nombre;
	}

	public static String getSeparadorRuta() {
		return separadorRuta;
	}

	public static void setSeparadorRuta(String separadorRuta) {
		Aplicacion.separadorRuta = separadorRuta;
	}

	public static String getNombreUsuario() {
		return nombreUsuario;
	}

	public static void setNombreUsuario(String nombreUsuario) {
		Aplicacion.nombreUsuario = nombreUsuario;
	}

	public static String getSistemaOperativo() {
		return sistemaOperativo;
	}

	public static void setSistemaOperativo(String sistemaOperativo) {
		Aplicacion.sistemaOperativo = sistemaOperativo;
	}

	public static int getNumeroLineas() {
		return numeroLineas;
	}

	public static void setNumeroLineas(int numeroLineas) {
		Aplicacion.numeroLineas = numeroLineas;
	}

	public static String getComando() {
		return comando;
	}

	public static void setComando(String opcion) {
		Aplicacion.comando = opcion;
	}

	public static Path getRutaActual() {
		return rutaActual;
	}

	public static void setRutaActual(Path rutaActual) {
		Aplicacion.rutaActual = rutaActual;
	}

	public Aplicacion() {
		Aplicacion.rutaActual = Paths.get("/etc");
		setSistemaOperativo(System.getProperty("os.name"));
		setNombreUsuario(System.getProperty("user.name"));

		if (getNombreUsuario().contains("Linux")) {
			setSeparadorRuta(System.getProperty("file.separator"));
		} else {
			setSeparadorRuta(System.getProperty("file.separator"));
		}
	}

	public void iniciar() throws IOException {
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println(getNombreUsuario() + "@" + getSistemaOperativo() + ":" + rutaActual + "$");
			setComando(teclado.nextLine());
			setNombre(getComando().substring(getComando().indexOf(" ") + 1));
			char primerCaracter = getComando().charAt(0);
			char segundoCaracter = getComando().charAt(1);
			if ((Character.isDigit(primerCaracter) && Character.isDigit(segundoCaracter)) || (Character.isDigit(primerCaracter))) {
				if (!Character.isDigit(segundoCaracter)) {
					setNumeroLineas(Integer.parseInt(Character.toString(primerCaracter)));
					Metodos.mostrarLineas();
				} else {
					String lineas = Character.toString(primerCaracter) + Character.toString(segundoCaracter);
					setNumeroLineas(Integer.parseInt(lineas));
				}
			} else {
				if (comando.equalsIgnoreCase("help")) {
					Metodos.ayuda();
				}
				if (comando.contains("cd ") && !comando.equalsIgnoreCase("cd /home")) {
					Metodos.cambiarDeDirectorio();
				}
				if (comando.equalsIgnoreCase("cd ..")) {
					Metodos.muestraDirectorioPadre();
				}

				if (comando.equalsIgnoreCase("cd /home")) {

					setRutaActual(Paths.get(System.getProperty("user.home")));

				}

				if (comando.equalsIgnoreCase("dir " + getNombre())) {

					Metodos.muestraContenido();

				}

				if (comando.contains("mkdir")) {
					Metodos.crearCarpeta();

				}

				if (comando.contains("cat ")) {
					Metodos.mostrarFichero();
				}
				if (comando.contains("info ")) {

					Metodos.info();

				}
				if (comando.contains("mkfile")) {
					Metodos.mkfile();
				}
				if (comando.contains("write")) {

					Metodos.write();

				}

				if (comando.contains("delete")) {
					Metodos.delete();

				}

			}

		} while (!comando.equalsIgnoreCase("exit") && !comando.equalsIgnoreCase(""));

		System.out.println("adios");
		teclado.close();

	}

}
