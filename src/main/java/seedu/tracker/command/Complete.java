package seedu.tracker.command;

import java.io.IOException;
import seedu.tracker.exception.TrackerException;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;


/**
 * This is a complete class to make one project to be done.
 */

public class Complete extends Command {

    public static final String word = "--complete";

    public Complete(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        try {
            if (projects.size() == 0) {
                ui.printBorderline("It appears that you have no projects yet, so you can't complete any!\n"
                    + "Perhaps you should start creating one?");
                return;
            }
            if (line.isEmpty()) {
                throw new TrackerException(
                    "You almost typed a proper complete command, but you missed out the number!\n"
                        + "Please type in the '--complete INDEX' format.");
            }

            int index = Integer.parseInt(line.trim()) - 1;
            projects.get(index).setComplete();
            ui.printProjectCompleted(index, projects);

            storage.updateStorage(projects);

        } catch (TrackerException | IOException e) {
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
