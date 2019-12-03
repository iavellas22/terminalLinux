package aplicacionLinux;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Metodos extends Aplicacion {

	public static void ayuda() {

		System.out.println("cd <nombre directorio>: Este comando permite accede a un directorio de manera que al\n"
				+ "escribir cd y el nombre de un directorio accederá a él.\n" + "Cuatro casuísticas:\n"
				+ "• cd : Muestra el directorio actual\n" + "• cd .. : Accede al directorio padre.\n"
				+ "• cd <nombre_directorio>: Accede a un directorio dentro del directorio actual.\n"
				+ "• cd <C:\\Nombre_Directorio>: Accede a una ruta absoluta del sistema.\n"
				+ "dir: Lista los archivos contenidos en el directorio actual.\n"
				+ "mkdir <nombre_directorio>: Crea un directorio en la ruta actual.\n"
				+ "Info <nombre_archivo_o_directorio> este comando imprimirá los datos básicos de un archivo\n"
				+ "o directorio: su nombre, nombre de la carpeta padre, el tamaño del archivo o la carpeta."
				+ "cat <nombre_fichero> : muestra el contenido de un fichero a través del terminal.top\n"
				+ "<numero_lineas> <nombre_fichero>: muestras las primeras <numero_lineas> de un fichero.\n"
				+ "De manera que top 20 hola.txt muestra las primeras 20 líneas del fichero hola.txt\n"
				+ "mkfile <nombre_fichero> <texto>: Crea un nuevo fichero llamado <nombre fichero> y escribe\n"
				+ "en el <texto>.\n"
				+ "write <nombre_fichero> <texto>: Añade <texto> al final de <nombre_fichero> que ya existe.\n"
				+ "Delete <nombre_archivo_o_directorio>: Borra un archivo o una carpeta, en caso de ser una\n"
				+ "carpeta borra todos los archivos que la contienen.");

	}

	public static void muestraContenido() {

		String nombre = getComando().substring(getComando().indexOf(" ") + 1);
		Path archivo = Paths.get(getRutaActual().toString(), nombre);

		if (archivo.toFile().exists()) {

			String contenido[] = archivo.toFile().list();

			if (contenido.length > 0) {

				for (int i = 0; i < contenido.length; i++) {
					System.out.println(contenido[i]);
				}

			} else {
				System.out.println("No hay contenido");
			}

		} else {
			System.out.println("el archivo no existe");
		}

	}

	public static void mostrarLineas() throws IOException {
		String nombreArchivo = getComando().substring(getComando().indexOf(" ") + 1);
		Path archivo = Paths.get(getRutaActual().toString(), nombreArchivo);
		int contadorLineas = 0;
		try {
			BufferedReader lector = new BufferedReader(new FileReader(archivo.toFile()));
			String linea = lector.readLine();
			while (linea != null && contadorLineas < getNumeroLineas()) {
				System.out.println(linea);
				linea = lector.readLine();
				contadorLineas++;
			}
			lector.close();

		} catch (FileNotFoundException e) {
			System.out.println("No existe el archivo");
		}
		if (Files.isDirectory(archivo)) {
			System.out.println("Es una carpeta");
		} else {

		}

	}

	public static void info() throws IOException {

		String nombre = getComando().substring(getComando().indexOf(" ") + 1, getComando().length());
		Path archivo = Paths.get(getRutaActual().toString(), nombre);
		boolean existeDirectorio = false;

		if (archivo.toFile().exists()) {
			existeDirectorio = true;

			System.out.println("informacion de " + nombre);

			Path directorio = Paths.get(getRutaActual().toString(), nombre);

			System.out.println("el directorio padre es " + directorio.getParent().getFileName());

			if (archivo.toFile().isDirectory()) {
				System.out.println("El tamaño es del directorio es " + Files.size(archivo) + "bytes");
				String nombresDirectorios[] = archivo.toFile().list();

				for (int i = 0; i < nombresDirectorios.length && !existeDirectorio; i++) {
					if (nombre.equalsIgnoreCase(nombresDirectorios[i])) {
						existeDirectorio = true;
					}

				}
			}
			if (archivo.toFile().isFile()) {
				System.out.println("El tamaño es del archivo es " + Files.size(archivo) + " bytes");
				String carpetaDondeSeEncuentra = getRutaActual().toString()
						.substring(getRutaActual().toString().lastIndexOf(getSeparadorRuta()) + 1);
				System.out.println("Se encuentra en la carpeta: " + carpetaDondeSeEncuentra);

			}

		} else {
			System.out.println("No existe el directorio");

		}

	}

	public static void muestraDirectorioPadre() {
		
		String arrayDirectorios [];
		
		if (System.getProperty("os.name").contains("Windows")) {
			 arrayDirectorios = getRutaActual().toString().split("\\\\");

		}else {
			 arrayDirectorios = getRutaActual().toString().split(getSeparadorRuta());

		}
		

		int limite = arrayDirectorios.length - 2;
		String rutaActual = null;
		for (int i = 1; i <= limite; i++) {
			if (rutaActual != null) {
				rutaActual = rutaActual + getSeparadorRuta() + arrayDirectorios[i];
			} else {
				rutaActual = getSeparadorRuta() + arrayDirectorios[i];
			}
		}
		if (rutaActual != null) {
			setRutaActual(Paths.get(rutaActual));

		}

	}
	

	public static void crearCarpeta() {
		String nombreCarpetaNueva = getComando().substring(getComando().indexOf(" ") + 1, getComando().length());
		System.out.println(nombreCarpetaNueva);
		Path ruta = Paths.get(getRutaActual().toString(), nombreCarpetaNueva);

		if (ruta.toFile().mkdir()) {
			System.out.println("carpeta creada");

		} else {
			System.out.println("no se ha creado");

		}

	}

	public static void cambiarDeDirectorio() {
		String nombre = getComando().substring(3, getComando().length());
		if (getComando().equalsIgnoreCase("cd " + nombre) && (!nombre.equalsIgnoreCase(".."))) {
			Path directorio = Paths.get(getRutaActual().toString(), nombre);
			if (directorio.toFile().isFile()) {
				System.out.println("es un archivo a lo que quieres acceder, para mas informacion 'help'");
			}
			if (directorio.toFile().isDirectory()) {
				setRutaActual(Paths.get(getRutaActual().toString() + getSeparadorRuta() + nombre));
			}
			if (!directorio.toFile().exists()) {
				System.out.println("No existe el archivo o directorio");
			}
		}

	}

	public static void mostrarFichero() {
		String nombreFichero = getComando().substring(getComando().indexOf(" ") + 1, getComando().length());

		try {
			Path archivo = Paths.get(getRutaActual().toString(), nombreFichero);
			BufferedReader lector = new BufferedReader(new FileReader(archivo.toFile()));
			String linea = lector.readLine();

			while ((linea != null)) {

				System.out.println(linea);
				linea = lector.readLine();
			}

			lector.close();

		} catch (FileNotFoundException e) {
			System.out.println("No existe el fichero");

		} catch (IOException e) {
			System.out.println("Fin de fichero");
		}
	}

	public static void mkfile() {

		try {
			String nombreYcontenido = getComando().substring(getComando().indexOf(" ") + 1);
			String nombre = nombreYcontenido.substring(0, nombreYcontenido.indexOf(" "));
			String contenido = nombreYcontenido.substring(nombreYcontenido.indexOf(" ") + 1);

			Path archivo = Paths.get(getRutaActual().toString(), nombre);

			if (archivo.toFile().exists()) {
				BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo.toFile(), true));
				escritor.newLine();
				escritor.write(contenido);
				escritor.close();

			} else {
				BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo.toFile()));
				escritor.write(contenido);
				escritor.close();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Tienes que introducir al lado del nombre del archivo lo que quieres escribir");
		}

	}

	public static void write() {

		String nombreYcontenido = getComando().substring(getComando().indexOf(" ") + 1);
		String nombreFichero = nombreYcontenido.substring(0, nombreYcontenido.indexOf(" "));
		String contenido = nombreYcontenido.substring(nombreYcontenido.indexOf(" ") + 1);
		Path archivo = Paths.get(getRutaActual().toString(), nombreFichero);

		if (archivo.toFile().exists()) {

			try {
				BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo.toFile(), true));
				escritor.write(contenido);
				escritor.newLine();
				escritor.close();

			} catch (IOException e) {
				System.out.println("Ha habido un error");
			}

		} else {
			System.out.println("El archivo no existe");
		}

	}

	public static void delete() {

		String nombre = getComando().substring(getComando().indexOf(" ") + 1);
		Path archivo = Paths.get(getRutaActual().toString(), nombre);

		while (archivo.toFile().isDirectory()) {

			if (archivo.toFile().list() == null) {
				archivo.toFile().delete();
			} else {
				String[] archivosDentro = archivo.toFile().list();

				if (archivosDentro.length > 0) {
					for (int i = 0; i < archivosDentro.length; i++) {

						Path archivoSecundario = Paths.get(getRutaActual().toString() + getSeparadorRuta() + nombre,
								archivosDentro[i]);
						if (archivoSecundario.toFile().isDirectory()) {
							nombre = nombre + getSeparadorRuta()+ archivosDentro[i];
							archivo = Paths.get(getRutaActual().toString() + getSeparadorRuta() + nombre);
							i = archivosDentro.length;
						} else {
							archivoSecundario.toFile().delete();
						}

					}

				} else {
					archivo.toFile().delete();
					try {
						nombre = nombre.substring(0, nombre.lastIndexOf(getSeparadorRuta()));
						archivo = Paths.get(getRutaActual().toString() + getSeparadorRuta() + nombre);

					} catch (StringIndexOutOfBoundsException e) {
						System.out.println("Carpeta BORRADA");
					}

				}

			}

		}
		if (archivo.toFile().exists()) {
			archivo.toFile().delete();
		}

	}

}
