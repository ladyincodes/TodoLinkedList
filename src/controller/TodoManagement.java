package controller;

import model.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TodoManagement {
    private List<Todo> todos;
    private final TodoSerializer serializer = new TodoSerializer();
    private final UserInteractionLogger logger = new UserInteractionLogger();

    public TodoManagement() {
        logger.log("Program started.");

        todos = new ArrayList<>();
        // checks and loads if there is a to-do object file saved previously
        List<Todo> restoredTodos = serializer.loadTodos("src/resources/todos.ser");
        if (restoredTodos != null) {
            // the object might exist, so checks if it's not empty
            if (!restoredTodos.isEmpty()) {
                setTodos(restoredTodos);
                System.out.println("Todos loaded successfully from the file.\n");
                logger.log("Loaded todos from the file successfully.");
            }
        }
    }

    // saving todos object to the defined fileName
    public void saveTodosStatus() {
        serializer.saveTodos(todos, "src/resources/todos.ser");
        System.out.println("Last status of the todos saved to the file.");
        logger.log("Saved todos last status to the file. Closing the app...");
    }

    // in case there was a to-do object saved in the past
    private void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    // Adding new items to the to-do list
    public void addItem(String title) {
        todos.add(new Todo(title));
        logger.log("Added a new task to the list: " + title);
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
                    logger.log("Removed a task by its title: " + todoTitle);
                    return;
                }
            }
            System.out.println("'" + title + "' was not found on the list.\nCheck the spelling and try again.\n");
            logger.log("Failed to remove a task by its title: " + title);
        }
    }

    // Removing an item from the to-do list based on their index
    // Removing from pending todos based on displayed index
    public void removeItemByIndex(int displayedIndex) {
        if (todos.isEmpty()) {
            System.out.println("There's no item on your todo list.");
        } else {

            // getting the actual index of the item
            int actualIndex = getActualIndex(displayedIndex);
            if (actualIndex != -1) {
                Todo removed = todos.remove(actualIndex);
                System.out.println("'" + removed.getTitle() + "' removed successfully.\n");
                logger.log("Removed a task by its index: " + removed.getTitle());

            } else {
                System.out.println("Invalid index. Please enter a valid number.");
                logger.log("Failed to remove a task by its index: " + displayedIndex);
            }
        }
    }

    // Updating to-dos title
    public void updateTitle(int index, String newTitle) {
        try {
            int actualIndex = getActualIndex(index);
            String oldTitle = todos.get(actualIndex).getTitle();
            todos.get(actualIndex).setTitle(newTitle);
            System.out.println("'" + oldTitle + "' successfully updated to '" + newTitle + "'\n");
            logger.log("Update a task's title from " + oldTitle + " to " + newTitle);
        } catch (Exception e) {
            System.err.println("A problem happened while updating the title.");
            System.err.println("Error: " + e.getMessage());
            System.out.println();
            logger.log("Failed to update a task's title at index " + index);
        }
    }

    // Updating a to-do status to completed
    public void markAsDone(int index) {
        // Checks if the list is empty
        if (todos.isEmpty()) {
            System.out.println("There's no item on your todo list.");
        } else {
            int actualIndex = getActualIndex(index);
            if (actualIndex != -1) {
                String itemTitle = todos.get(actualIndex).getTitle();

                // Updated the status to completed
                todos.get(actualIndex).setComplete(true);

                System.out.println("'" + itemTitle + "' marked as done.\n");
                logger.log("Marked '" + itemTitle + "' as done.");
            } else {
                System.out.println("Item was not found. Make sure you are entering the right number.");
                logger.log("Failed to mark the task at index " + index + " as done.");
            }
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
                    logger.log("Marked '" + todo.getTitle() + "' as undone.");
                    return;
                }
            }

            System.out.println("'" + title + "' was not found on the list.\nCheck the spelling and try again.\n");
            logger.log("Failed to mark '" + title + "' as undone.");
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

            logger.log("Showed a list of pending tasks.");
        }
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

            logger.log("Showed a list of completed tasks.");
        }
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

            logger.log("Showed a list of all tasks.");
        }
    }

    private int getActualIndex(int displayedIndex) {
        List<Integer> actualIndexes = new ArrayList<>();

        // Building a mapping of displayed index to actual list index
        for (int i = 0; i < todos.size(); i++) {
            if (!todos.get(i).isComplete()) {
                actualIndexes.add(i);
            }
        }

        // checking for invalid index
        if (displayedIndex < 0 || displayedIndex > actualIndexes.size()) {
            return -1;
        }

        return actualIndexes.get(displayedIndex - 1);
    }
}
