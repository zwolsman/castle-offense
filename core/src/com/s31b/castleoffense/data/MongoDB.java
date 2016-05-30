package com.s31b.castleoffense.data;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

/**
 *
 * @author Shaikun
 */
public class MongoDB implements IDataBase {

    MongoClient mongoClient;
    MongoDatabase db;

    public MongoDB() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        mongoClient = new MongoClient("lensert.com", 27017);
        db = mongoClient.getDatabase("castleoffense");
    }

    @Override
    public <T> List<T> getAll(Class<T> type) {
        List<T> list = new ArrayList();
        String collName = type.getSimpleName();
        MongoCollection coll;
        Gson gson = new Gson();

        coll = db.getCollection(collName);
        MongoCursor cursor = coll.find().iterator();
        while (cursor.hasNext()) {
            Document d = (Document) cursor.next();
            list.add(gson.fromJson(d.toJson(), type));
        }
        return list;
    }

    @Override
    public <T> void insert(T object) {
        String collName = object.getClass().getSimpleName();

        //checking if collection exists.
        //if not, create the collection and use it directly to insert the document created.
        try {
            MongoCollection coll;
            db.getCollection(collName);
            Document data = createDocument(object);
            coll = db.getCollection(collName);
            coll.insertOne(data);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public <T> void update(T object, T newObject) {
        String collName = object.getClass().getSimpleName();

        //initializing the old and new document and update the data.
        try {
            MongoCollection coll;
            Document data = createDocument(object);
            Document newData = createDocument(newObject);
            coll = db.getCollection(collName);
            coll.updateOne(data, newData);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public <T> void delete(T object) {
        String collName = object.getClass().getSimpleName();

        //initialize the document that is about to be deleted and delete it.
        try {
            MongoCollection coll;
            Document data = createDocument(object);
            coll = db.getCollection(collName);
            coll.deleteOne(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Object runGetter(Field field, Object o) {
        //Find the get method for the field
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    try {
                        return method.invoke(o, null);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }
        }
        return null;
    }

    private <T> Document createDocument(T object) {
        ArrayList<Field> objectFields = new ArrayList();
        //Recursive field finding (for superclasses)
        for (Class<?> c = object.getClass(); c != null; c = c.getSuperclass()) {
            Field[] fields = c.getDeclaredFields();
            objectFields.addAll(Arrays.asList(fields));
        }

        //Generating Document with the field data to insert into the database.
        Document data = new Document();
        for (Field f : objectFields) {
            Object r = runGetter(f, object);
            data.append(f.getName(), r);
        }
        return data;
    }
}
