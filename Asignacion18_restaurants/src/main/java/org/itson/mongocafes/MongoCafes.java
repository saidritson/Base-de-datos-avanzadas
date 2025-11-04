/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.mongocafes;

/**
 *
 * @author saidr
 */

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

public class MongoCafes {
    public static void main(String[] args) {
        // 1️⃣ Conexión a MongoDB local
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
        MongoDatabase database = mongoClient.getDatabase("restaurants");
        MongoCollection<Document> cafes = database.getCollection("cafes");

        // Limpiar la colección antes de comenzar (opcional)
        cafes.drop();

        // 2️⃣ Insertar un solo documento
        Document cafe1 = new Document("name", "Café de la Plaza")
                .append("stars", 4.3)
                .append("categories", Arrays.asList("Café", "Postres", "Desayuno"));
        cafes.insertOne(cafe1);

        // 3️⃣ Insertar varios documentos
        Document cafe2 = new Document("name", "Espresso Express")
                .append("stars", 4.8)
                .append("categories", Arrays.asList("Café", "Rápido", "Takeaway"));

        Document cafe3 = new Document("name", "The Tea House")
                .append("stars", 3.9)
                .append("categories", Arrays.asList("Té", "Infusiones", "Postres"));

        Document cafe4 = new Document("name", "Morning Brew")
                .append("stars", 4.0)
                .append("categories", Arrays.asList("Café", "Desayuno", "Bakery"));

        cafes.insertMany(Arrays.asList(cafe2, cafe3, cafe4));

        // 4️⃣ Filtros (consultas)
        System.out.println("\n📌 Documentos con stars >= 4.5:");
        cafes.find(Filters.gte("stars", 4.5)).forEach(System.out::println);

        System.out.println("\n📌 Documentos cuyo nombre contiene 'Café':");
        cafes.find(Filters.regex("name", "Café")).forEach(System.out::println);

        System.out.println("\n📌 Documentos con categories que incluyan 'Postres':");
        cafes.find(Filters.in("categories", "Postres")).forEach(System.out::println);

        System.out.println("\n📌 Documentos con stars entre 3 y 4.3:");
        cafes.find(Filters.and(Filters.gte("stars", 3), Filters.lte("stars", 4.3)))
                .forEach(System.out::println);

        System.out.println("\n📌 Documentos cuyo nombre empieza con 'T':");
        cafes.find(Filters.regex("name", "^T")).forEach(System.out::println);

        // 5️⃣ Updates
        System.out.println("\n🔄 Actualizando documentos...");

        // Cambiar stars a 4.5 para "Morning Brew"
        cafes.updateOne(Filters.eq("name", "Morning Brew"), Updates.set("stars", 4.5));

        // Incrementar stars +0.2 para categorías Bakery o Desayuno
        Bson filtroCategorias = Filters.or(
                Filters.in("categories", "Bakery"),
                Filters.in("categories", "Desayuno")
        );
        cafes.updateMany(filtroCategorias, Updates.inc("stars", 0.2));

        // Agregar campos phone y open a "Café de la Plaza"
        cafes.updateOne(
                Filters.eq("name", "Café de la Plaza"),
                Updates.combine(
                        Updates.set("phone", "555-111-2222"),
                        Updates.set("open", true)
                )
        );

        System.out.println("\n✅ Actualizaciones completadas.");

        // 6️⃣ Deletes
        System.out.println("\n🗑 Eliminando documentos...");

        // Eliminar documento con name = "Espresso Express"
        cafes.deleteOne(Filters.eq("name", "Espresso Express"));

        // Eliminar documentos con stars < 4
        cafes.deleteMany(Filters.lt("stars", 4));

        // Eliminar documentos con categories que contengan "Takeaway" o "Infusiones"
        Bson filtroEliminar = Filters.or(
                Filters.in("categories", "Takeaway"),
                Filters.in("categories", "Infusiones")
        );
        cafes.deleteMany(filtroEliminar);

        System.out.println("\n✅ Eliminaciones completadas.");

        // Mostrar colección final
        System.out.println("\n📋 Colección final:");
        cafes.find().forEach(System.out::println);

     
    }
}
