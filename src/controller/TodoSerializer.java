package controller;

import model.Todo;

import java.io.*;
import java.util.List;

public class TodoSerializer {

    // saves last status of the todos object to a file
    public void saveTodos(List<Todo> todos, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(todos);
        } catch (FileNotFoundException e) {
            System.err.println("Error: File was not found.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // checks and loads if there is a serialized object saved
    public List<Todo> loadTodos(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object restoredObject = ois.readObject();
            return (List<Todo>) restoredObject;

        } catch (EOFException e) {
            System.err.println("The file is empty or corrupted: " + fileName);
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }
}
