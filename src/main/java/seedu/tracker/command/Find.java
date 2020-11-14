package seedu.tracker.command;

import java.io.IOException;
import java.text.ParseException;
import seedu.tracker.common.DateConverter;
import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

/**
 * THis is a find class, finding keyword and finding keyword then replace all the keywords with another one.
 *
 */

public class Find extends Command {
    public static final String word = "--find";

    public Find(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        try {

            if (projects.size() == 0) {
                ui.printBorderline("It appears that you have no projects yet, so you can't find anything!");
                return;
            }
            String searchWord = line.split("--replace")[0].trim();

            if (line.isEmpty()) {
                ui.printBorderline("You didn't type in any keyword to find!\n"
                    + "Please type in the '--find KEYWORD' format!");
                return;
            }

            String matches = "Here are the project(s) that matches '" + searchWord + "'!\n";
            boolean hasMatch = false;

            for (int i = 0; i < projects.size(); i++) {
                if (projects.get(i).getDescription().toLowerCase().contains(searchWord.toLowerCase())) {
                    matches = matches.concat(i + 1 + ".\n" + ui.displayProject(projects.get(i)));
                    hasMatch = true;
                }
            }
            if (!hasMatch) {
                matches = "It appears that are no matches for '" + searchWord + "'!\n"
                    + "Perhaps you could try searching another word?";
            }
            if (hasMatch && line.contains("--replace")) {
                findAndReplace();
                return;
            }
            ui.printBorderline(matches);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printBorderline("Please type in the '--find KEYWORD --replace KEYWORD' format!");
        }
    }

    private void findAndReplace() {
        try {
            String searchWord = line.split("--replace")[0].trim();
            String replaceWord = line.split("--replace")[1].trim();

            boolean time = false;
            if (searchWord.isEmpty() || replaceWord.isEmpty()) {
                ui.printBorderline("Please type in the '--find KEYWORD --replace KEYWORD' format!");
                return;
            }

            if (new DateConverter(ui).dateChecker(searchWord, false)) {
                if (!new DateConverter(ui).dateChecker(replaceWord, true)) {
                    return;
                }
            }

            String changes = "Here are the project(s) that has the word '" + searchWord
                + "' replaced with '" + replaceWord + "'!\n";

            for (int i = 0; i < projects.size(); i++) {
                String line = projects.get(i).toString();
                String[] currentProject = line.split("--");
                String newData = "";

                for (int j = 1; j < currentProject.length; j++) {
                    String currentCommand = currentProject[j].split(" ", 2)[0]; // unused command
                    String currentDescription = currentProject[j].split(" ", 2)[1];
                    if (currentCommand.equalsIgnoreCase("startdate")
                            || currentCommand.equalsIgnoreCase("duedate")) {
                        newData = newData.concat("--" + currentProject[j].trim() + " ");
                        time = true;
                    } else if (currentDescription.toLowerCase().contains(searchWord.toLowerCase())) {
                        currentDescription = currentProject[j].replace(searchWord, replaceWord);
                        newData = newData.concat("--" + currentDescription + " ").trim();
                    } else {
                        newData = newData.concat("--" + currentProject[j].trim() + " ");
                    }
                }
                projects.set(i, new Project(newData));
                changes = changes.concat(i + 1 + ".\n" + ui.displayProject(projects.get(i)));
            }

            if (time) {
                System.out.println("* Keyword has been detected in date, date cannot be replaced *");
            }

            storage.updateStorage(projects);
            ui.printBorderline(changes);

        } catch (IOException e) {
            ui.printBorderline(e.getMessage());
        }
    }
}
