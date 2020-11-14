package seedu.tracker.command;

import java.io.IOException;

import seedu.tracker.exception.TrackerException;
import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

/**
 * This is a Add class for user to add new description behind the current description.
 */

public class Add extends Command {
    public static final String word = "--add";

    public Add(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        try {
            if (projects.size() == 0) {
                ui.printBorderline("It appears that you have no projects yet, so you can't edit any!\n"
                        + "Perhaps you should start creating one?");
                return;
            }

            String[] splits = line.split("--");
            String commandWithIndex = splits[1];
            String commandWithAdditionalWord = splits[2];

            String projectIndex = commandWithIndex.split(" ", 2)[1];
            String commandWord = commandWithAdditionalWord.split(" ", 2)[0];
            String additionalWord = commandWithAdditionalWord.split(" ", 2)[1];

            int index = Integer.parseInt(projectIndex.trim()) - 1;
            String line = projects.get(index).toString();

            if (projectIndex.isEmpty() || !line.contains(commandWord)) {
                throw new TrackerException("It seems that you did not type in the correct format!\n"
                        + "Please type in the '--add INDEX --commandName INPUT' format.");
            }
            if (commandWord.contains("startdate") || commandWord.contains("duedate") || commandWord.contains("email")) {
                throw new TrackerException(" Start date, Due date and Email do not allow in Add command");
            }


            String[] selectedProject = line.split("--");
            String newData = "";

            for (int i = 1; i < selectedProject.length; i++) {
                String currentCommandWord = selectedProject[i].split(" ", 2)[0];
                String currentDescription = selectedProject[i].split(" ", 2)[1];

                if (commandWord.equalsIgnoreCase(currentCommandWord)) {
                    String temp = currentDescription + ", " + additionalWord;
                    String temp2 = "--" + selectedProject[i].replace(currentDescription, temp) + " ";
                    newData = newData.concat(temp2);
                } else {
                    newData = newData.concat("--" + selectedProject[i]);
                }
            }
            projects.set(index, new Project(newData));
            ui.printAddMessage(projects);
            storage.updateStorage(projects);

        } catch (TrackerException | IOException e) {
            ui.printBorderline(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printBorderline(
                    "I'm sorry, but the list goes numerically.\nPerhaps you could type a number for the index?");
        } catch (IndexOutOfBoundsException e) {
            ui.printBorderline("It appears that you only have " + projects.size() + " project(s) in your list,\n"
                    + "perhaps you might want to try typing an index number from 1 to "
                    + projects.size() + " instead?");
        }
    }
}
