package seedu.tracker.command;

import java.io.IOException;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

/**
 * A command to delete a project from the project list.
 */
public class Delete extends Command {
    public static final String word = "--delete";

    public Delete(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        if (line.equalsIgnoreCase("all")) {
            while (projects.size() != 0) {
                projects.remove(0);
            }
            try {
                storage.updateStorage(projects);
                ui.printBorderline("All projects in the list have been deleted!\nWe now have "
                    + (projects.size()) + " project(s) in your list!");

            } catch (IOException e) {
                ui.printBorderline(e.getMessage());
            }
            return;
        }
        if (projects.size() == 0) {
            ui.printBorderline("It appears that you have no projects yet, so you can't delete any!\n"
                + "Perhaps you should start creating one?");
            return;
        }
        if (line.isEmpty()) {
            ui.printBorderline("You almost typed a proper delete command, but you missed out the number!\n"
                + "Please type in the '--delete INDEX' format.");
            return;
        }

        try {
            int index = Integer.parseInt(line.trim());
            ui.printProjectRemoved(projects, index - 1);
            projects.remove(index - 1);
            storage.updateStorage(projects);

        } catch (IOException e) {
            ui.printBorderline(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printBorderline(
                "I'm sorry, but the list goes numerically.\nPerhaps you could type a number for the index?");
        } catch (IndexOutOfBoundsException e) {
            ui.printBorderline("It appears that you only have " + projects.size() + " project(s) in your list,\n"
                + "perhaps you might want to try typing an index number from 1 to " + projects.size() + " instead?");
        }
    }
}