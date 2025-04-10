package controller;

import model.Todo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TodoManagement {
    LinkedList<Todo> todos;

    public TodoManagement() {
        todos = new LinkedList<>();
    }

    // Adding new items to the to-do list
    public void addItem(String title) {
        todos.add(new Todo(title));
    }

    // Removing an item from to-do list based on their title
    public void removeItemByTitle(String title) {
        if (todos.isEmpty()) {
            System.out.println("There's no item on your todo list.");
        } else {
            ListIterator<Todo> iterator = todos.listIterator();
            while (iterator.hasNext()) {
                Todo todo = iterator.next();
                if (todo.getTitle().equalsIgnoreCase(title.trim())) {
                    String todoTitle = todo.getTitle();
                    iterator.remove();
                    System.out.println("'" + todoTitle + "' removed successfully.\n");
                    return;
                }
            }
            System.out.println("'" + title + "' was not found on the list.\nCheck the spelling and try again.\n");
        }
    }

    // Removing an item from the to-do list based on their index
    // Removing from pending todos based on displayed index
    public void removeItemByIndex(int displayedIndex) {
        if (todos.isEmpty()) {
            System.out.println("There's no item on your todo list.");
        } else {
            List<Integer> actualIndexes = new ArrayList<>();

            // Building a mapping of displayed index to actual list index
            for (int i = 0; i < todos.size(); i++) {
                if (!todos.get(i).isComplete()) {
                    actualIndexes.add(i);
                }
            }

            if (displayedIndex < 0 || displayedIndex > actualIndexes.size()) {
                System.out.println("Invalid index. Please enter a valid number.");
                return;
            }

            int actualIndex = actualIndexes.get(displayedIndex - 1);
            Todo removed = todos.remove(actualIndex);
            System.out.println("'" + removed.getTitle() + "' removed successfully.\n");
        }
    }

    // Updating to-dos title
    public void updateTitle(int index, String newTitle) {
        try {
            String oldTitle = todos.get(index - 1).getTitle();
            todos.get(index - 1).setTitle(newTitle);
            System.out.println("'" + oldTitle + "' successfully updated to '" + newTitle + "'\n");
        } catch (Exception e) {
            System.err.println("A problem happened while updating the title.");
            System.err.println("Error: " + e.getMessage());
            System.out.println();
        }
    }

    // Updating a to-do status to completed
    public void markAsDone(int index) {
        // Checks if the list is empty
        if (todos.isEmpty()) {
            System.out.println("There's no item on your todo list.");
        } else if (todos.get(index - 1) == null) { // checks if the entered index is valid
            System.out.println("Item was not found. Make sure you are entering the right number.");
        } else {
            String itemTitle = todos.get(index - 1).getTitle();
            // Updated the status to completed
            todos.get(index - 1).setComplete(true);
            System.out.println("'" + itemTitle + "' marked as done.\n");
        }
    }

    // Updating a to-do status to pending based on their title
    public void markAsUndone(String title) {
        if (todos.isEmpty()) {
            System.out.println("There's no item on your todo list.");
        } else {
            ListIterator<Todo> iterator = todos.listIterator();
            while (iterator.hasNext()) {
                Todo todo = iterator.next();
                if (todo.isComplete() && todo.getTitle().equalsIgnoreCase(title.trim())) {
                    todo.setComplete(false);
                    System.out.println("'" + todo.getTitle() + "' marked as undone.\n");
                    return;
                }
            }
            System.out.println("'" + title + "' was not found on the list.\nCheck the spelling and try again.\n");
        }
    }


    // Showing the list of to-dos (pending items)
    public void showPendingItems() {
        // Checks if there is any item on the to-do list
        if (todos.isEmpty()) {
            System.out.println("There's no item on your todo list.");
        } else {
            int counter = 1;
            System.out.println("To-do List:");
            for (Todo item : todos) {
                if (!item.isComplete()) {
                    System.out.println(counter + ". " + item.getTitle());
                    counter++;
                }
            }
        }

        System.out.println();
    }

    // Showing a list of completed items
    public void showCompletedItems() {
        // Checks if there is any item on the to-do list
        if (todos.isEmpty()) {
            System.out.println("There's no item on your todo list.");
        } else {
            int counter = 1;
            System.out.println("Completed Items:");
            for (Todo item : todos) {
                if (item.isComplete()) {
                    System.out.println(counter + ". " + item.getTitle());
                    counter++;
                }
            }
        }

        System.out.println();
    }

    // Show all items on to-do list (both completed and pending)
    public void showAllItems() {
        // Checks if there is any item on the to-do list
        if (todos.isEmpty()) {
            System.out.println("There's no item on your todo list.");
        } else {
            int counter = 1;
            System.out.println("All Items:");
            for (Todo item : todos) {
                System.out.print(counter + ". " + item.getTitle() + " ");
                if (item.isComplete()) {
                    System.out.print('✅');
                } else {
                    System.out.print('❌');
                }
                System.out.println();
                counter++;
            }
        }

        System.out.println();
    }
}
