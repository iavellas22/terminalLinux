package aplicacionLinux;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PRUEBA {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce nombre de carpeta");
		String nombre = teclado.nextLine();

		Path archivo = Paths.get("/datos/usuarios/alumnos/irene.avellas/Escritorio", nombre);

		while (archivo.toFile().isDirectory()) {

			if (archivo.toFile().list() == null) {
				archivo.toFile().delete();
			} else {
				String[] archivosDentro = archivo.toFile().list();

				if (archivosDentro.length > 0) {
					for (int i = 0; i < archivosDentro.length; i++) {

						Path archivoSecundario = Paths.get(
								"/datos/usuarios/alumnos/irene.avellas/Escritorio" + "/" + nombre, archivosDentro[i]);
						if (archivoSecundario.toFile().isDirectory()) {
							nombre = nombre + "/" + archivosDentro[i];
							archivo = Paths.get("/datos/usuarios/alumnos/irene.avellas/Escritorio" + "/" + nombre);
							i = archivosDentro.length;
						} else {
							archivoSecundario.toFile().delete();
						}

					}

				} else {
					archivo.toFile().delete();
					try {
						nombre = nombre.substring(0, nombre.lastIndexOf("/"));
						archivo = Paths.get("/datos/usuarios/alumnos/irene.avellas/Escritorio" + "/" + nombre);

					} catch (StringIndexOutOfBoundsException e) {
						System.out.println("Carpeta BORRADA");
					}

				}

			}

		}
		
		/*
		 * 		String ruta[] = getRutaActual().toString().split(getSeparadorRuta());
		String directorioS = ruta[ruta.length - 1];
		String rutaActual = null;
		if (ruta.length > 2) {
			int limite = ruta.length - 2;
			Path rutaDelDirectorio;
			for (int i = 1; i <= limite; i++) {

				if (rutaActual != null) {
					rutaActual = rutaActual + getSeparadorRuta() + ruta[i];

				} else {
					rutaActual = getSeparadorRuta() + ruta[i];

				}

			}
			rutaDelDirectorio = Paths.get(rutaActual);
			Path directorio = Paths.get(rutaDelDirectorio.toString(), directorioS);

			String archivos[] = directorio.toFile().list();

			for (int j = 1; j < archivos.length; j++) {
				System.out.print(archivos[j] + "      ");
			}
			System.out.println();

		} else {
			int limite = ruta.length - 1;
			Path rutaDelDirectorio;
			for (int i = 1; i <= limite; i++) {

				if (rutaActual != null) {
					rutaActual = rutaActual + getSeparadorRuta() + ruta[i];

				} else {
					rutaActual = getSeparadorRuta() + ruta[i];

				}

			}
			rutaDelDirectorio = Paths.get(rutaActual);
			Path directorio = Paths.get(rutaDelDirectorio.toString(), directorioS);

			String archivos[] = directorio.toFile().list();
			System.out.println(archivos.length);

			for (int j = 0; j < archivos.length; j++) {
				System.out.print(archivos[j] + "      ");
			}
			System.out.println();

		}
		 */

	}

}
