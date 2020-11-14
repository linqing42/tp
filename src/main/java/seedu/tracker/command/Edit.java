package seedu.tracker.command;

import java.io.IOException;
import java.text.ParseException;
import seedu.tracker.common.DateConverter;
import seedu.tracker.exception.TrackerException;
import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

/**
 * THis is a edit class to edit the current project's description.
 */

public class Edit extends Command {
    public static final String word = "--edit";

    public Edit(String line, ProjectList projects, Ui ui, Storage storage) {
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
            String commandWithNewDescription = splits[2];
            String projectIndex = commandWithIndex.split(" ", 2)[1];
            String commandWord = commandWithNewDescription.split(" ", 2)[0];
            String newDescription = commandWithNewDescription.split(" ", 2)[1];

            String emailFormat = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if (commandWord.contains("email") && !newDescription.matches(emailFormat)) {
                throw new TrackerException("Invalid Email format");
            }

            int index = Integer.parseInt(projectIndex.trim()) - 1;
            String projectLine = projects.get(index).toString();

            if (projectIndex.isEmpty() || !projectLine.contains(commandWord)) {
                throw new TrackerException("It seems that you did not type in the correct format!\n"
                    + "Please type in the '--edit INDEX --commandName INPUT' format.");
            }

            if (commandWord.equals("startdate") || commandWord.equals("duedate")) {
                if (!new DateConverter(newDescription).dateChecker(newDescription, true)) {
                    return;
                }
            }

            String[] selectedProject = projectLine.split("--");
            String newData = "";

            for (int i = 1; i < selectedProject.length; i++) {
                String currentCommandWord = selectedProject[i].split(" ", 2)[0];
                String currentDescription = selectedProject[i].split(" ", 2)[1];

                if (commandWord.equalsIgnoreCase(currentCommandWord)) {
                    String temp = selectedProject[i].replace(currentDescription, newDescription);
                    newData = newData.concat("--" + temp + " ");
                } else {
                    newData = newData.concat("--" + selectedProject[i]);
                }
            }
            projects.set(index, new Project(newData));
            ui.printEditMessage(projects);
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