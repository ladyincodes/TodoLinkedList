package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TodoMenu {
    private final Scanner scanner;
    private final TodoManagement todoManager;

    public TodoMenu() {
        todoManager = new TodoManagement();
        scanner = new Scanner(System.in);


    }

    public void displayMenu() {
        while (true) {
            System.out.println("*** Main Menu ***");
            System.out.println("1. View all pending tasks");
            System.out.println("2. Add a new task");
            System.out.println("3. Mark a task as done");
            System.out.println("4. View completed tasks");
            System.out.println("5. Update a task's title");
            System.out.println("6. Remove a task by title");
            System.out.println("7. Remove a task by index");
            System.out.println("8. Mark a task as undone");
            System.out.println("9. View all(pending/completed) tasks");
            System.out.println("10. Exit");
            System.out.println("Enter your choice: ");
            int choice;

            // Reading user's choice
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scanner.nextLine(); // Clear invalid input
                continue; // Skip the rest of the loop iteration
            }

            switch (choice) {
                case 1:
                    // showing a list of all pending tasks
                    todoManager.showPendingItems();
                    break;

                case 2:
                    // creating a new task
                    createNewTask();
                    break;

                case 3:
                    // marking a task as done (completed)
                    markTaskAsDone();
                    break;

                case 4:
                    // showing a list of completed (done) tasks
                    todoManager.showCompletedItems();
                    break;

                case 5:
                    // updating a current task's title
                    updateToANewTitle();
                    break;

                case 6:
                    // removing a task from to-do list by its title
                    removeByTitle();
                    break;

                case 7:
                    // removing a task from to-do list by its number in the list
                    removeByNumber();
                    break;

                case 8:
                    // marking a completed(done) task as undone(pending)
                    markTaskAsUndone();
                    break;

                case 9:
                    // showing all the completed/pending items on the to-do list
                    todoManager.showAllItems();
                    break;

                case 10:
                    // saves the current todos status before closing the application
                    todoManager.saveTodosStatus();
                    return;

                default:
                    System.out.println("\nInvalid choice. Try again.");
            }

            System.out.println();
        }
    }

    private void markTaskAsUndone() {
        todoManager.showCompletedItems();
        System.out.println("Enter the task's title: ");
        String title = scanner.nextLine();
        todoManager.markAsUndone(title);
    }

    private void removeByNumber() {
        todoManager.showPendingItems();
        System.out.println("Enter the task's number: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        todoManager.removeItemByIndex(index);
    }

    private void removeByTitle() {
        todoManager.showPendingItems();
        System.out.println("Enter the task's title: ");
        String title = scanner.nextLine();
        todoManager.removeItemByTitle(title);
    }

    private void updateToANewTitle() {
        todoManager.showPendingItems();
        System.out.println("Enter the index of the task: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the new title: ");
        String newTitle = scanner.nextLine();
        todoManager.updateTitle(index, newTitle);
    }

    private void markTaskAsDone() {
        todoManager.showPendingItems();
        System.out.println("Enter the index of the completed task: ");
        int index = scanner.nextInt();
        todoManager.markAsDone(index);
    }

    private void createNewTask() {
        System.out.println("Enter task's title: ");
        String title = scanner.nextLine();
        todoManager.addItem(title);
    }


}
