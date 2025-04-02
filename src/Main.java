import controller.TodoManagement;

public class Main {
    public static void main(String[] args) {
        TodoManagement todoManager = new TodoManagement();

        todoManager.showPendingItems();

        // Adding new items to the list
        todoManager.addItem("Wash the dishes");
        todoManager.addItem("Develop a to-do app");
        todoManager.addItem("Meditation");
        todoManager.addItem("Call Eva");
        todoManager.addItem("Prepare dinner");

        // showing a list of pending to-dos
        todoManager.showPendingItems();

        // marking an item as done
        todoManager.markAsDone(2);
        // showing a list of completed items
        todoManager.showCompletedItems();
        // showing a list of pending to-dos
        todoManager.showPendingItems();

        // updating an item's title
        todoManager.updateTitle(4, "Call David");
        // showing a list of pending to-dos
        todoManager.showPendingItems();

        // removing an item from the list
         todoManager.removeItemByTitle("meditation");
        todoManager.removeItemByIndex(1);
        // showing a list of pending to-dos
        todoManager.showPendingItems();

        // marking an item as undone
        todoManager.markAsUndone("develop a to-do app");
        // showing a list of pending to-dos
        todoManager.showPendingItems();
        // showing a list of completed items
        todoManager.showCompletedItems();
        
        // show all items in to-do list
        todoManager.showAllItems();
    }
}
