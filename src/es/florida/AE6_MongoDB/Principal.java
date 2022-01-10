package es.florida.AE6_MongoDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;



import static com.mongodb.client.model.Filters.*;





public class Principal {
	
	public static void llamarLibros() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Bibioteca");
		MongoCollection<Document> coleccion = database.getCollection("Libros");
		
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while(cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			System.out.println(obj.getString("Titol"));
			
		}
	}
	
	
	public static void buscarLibro() {
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Bibioteca");
		MongoCollection<Document> coleccion = database.getCollection("Libros");
		
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Digame el nombre del libro que quiere: ");
		String nombrelibro=teclado.next();
		
		
		Bson query = eq("Titol",nombrelibro);
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		while(cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}
		
		
	}
	
	public static void crearLibro() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Bibioteca");
		MongoCollection<Document> coleccion = database.getCollection("Libros");
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Dime el titulo del libro");
		String titulo = teclado.next();
		System.out.print("Dime el autor del libro");
		String autor=teclado.next();
		System.out.print("Dime el any de naixement del autor:");
		int any_naix = teclado.nextInt();
		System.out.print("Dime el any de publicacio del llibre:");
		int any_publicacio = teclado.nextInt();
		System.out.print("Dime la editorial del llibre:");
		String editorial = teclado.next();
		System.out.print("Dime el numero de pagines del llibre:");
		int pagines = teclado.nextInt();
		
		ArrayList<Document> listaDocs = new ArrayList<Document>();
		for(int i =0; i < 1 ; i++) {
			Document doc = new Document();
			doc.append("Titol", titulo);
			doc.append("Autor", autor);
			doc.append("Any_naixement", any_naix);
			doc.append("Any_publicacio", any_publicacio);
			doc.append("Editorial", editorial);
			doc.append("Pagines", pagines);
			listaDocs.add(doc);
		}
		coleccion.insertMany(listaDocs);
	}
	
	
	public static void actualizarLibro() {
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Bibioteca");
		MongoCollection<Document> coleccion = database.getCollection("Libros");
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Dime la ID del llibre que vols actualizar");
		int id = teclado.nextInt();
		System.out.println("Actualizar datos (introducir 'igual' (si es un numero introducir 0) para preservar valor): ");
		try {
		System.out.print("Actualizar título: "); 
		String nuevoTitulo = teclado.next();
		if (!nuevoTitulo.equals("igual")) {
			
		}
		
		System.out.print("Actualizar autor: ");
		String nuevoAutor = teclado.next();
		if (!nuevoAutor.equals("igual")) {
			
		}
		
		System.out.print("Actualizar any naixement: ");
		int nuevoAnynaix = teclado.nextInt();
		if (nuevoAnynaix!=0) {
			
		}
		
		System.out.print("Actualizar any publicacio: ");
		int nuevoAnypubli = teclado.nextInt();
		if (nuevoAnypubli!=0) {
			
		}
		
		System.out.print("Actualizar editorial: ");
		String nuevoEditorial = teclado.next();
		if (!nuevoEditorial.equals("igual")) {
			
		}
		
		System.out.print("Actualizar pagines: ");
		int nuevoPagines = teclado.nextInt();
		if (nuevoPagines!=0) {
			
		}
		
		
		
	}catch ( Exception e) {
		System.out.println("No se ha podido actualizar el libro");
		e.printStackTrace();
	}
}
	
	public static void borrarLibro() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Bibioteca");
		MongoCollection<Document> coleccion = database.getCollection("Libros");
		
		Scanner teclado = new Scanner(System.in);
		System.out.print("Introduzca el nombre del libro que desea borrar: ");
		String nombre = teclado.next();
		
		coleccion.deleteOne(eq("Titol",nombre));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in);
		int opcion = 0;
		

		while (opcion != 6) {
			System.out.println("\n\n=============================================");
			System.out.println("              MENÚ BIBLIOTECA");
			System.out.println("=============================================");
			System.out.println("1. Mostrar todos los títulos de la biblioteca");
			System.out.println("2. Mostrar información detallada de un libro");
			System.out.println("3. Crear nuevo libro");
			System.out.println("4. Actualizar libro");
			System.out.println("5. Borrar libro");
			System.out.println("6. Cerrar la biblioteca");
			System.out.print("\n >>> Elegir una opción: ");
			opcion = Integer.parseInt(teclado.next());
			switch(opcion) {
			case 1:
				llamarLibros();
				break;
			case 2:
				buscarLibro();
				break;
			case 3:
				crearLibro();
				break;
			case 4:
				actualizarLibro();
				break;
			case 5:
				borrarLibro();
				break;
			case 6:
				System.out.println("Bye!");
				teclado.close();
				break;
			default:
				break;	
			}
		}
		
		
		
	}
}
