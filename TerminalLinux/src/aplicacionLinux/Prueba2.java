package aplicacionLinux;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Prueba2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Escribe nombre");
		Scanner teclado= new Scanner (System.in);
		
		String nombre = teclado.nextLine();
		Path carpeta= Paths.get("/datos/usuarios/alumnos/irene.avellas/Escritorio/Carpetita/carpeta2", nombre);
		String contenido []= carpeta.toFile().list();
		
		for(int i=0; i< contenido.length;i++) {
			System.out.println(contenido[i]);
		}
		
		
		//os.name

	}

}
