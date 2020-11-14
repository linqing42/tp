package seedu.tracker.command;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import seedu.tracker.common.DateConverter;
import seedu.tracker.project.NewProject;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

/**
 * This is a create project class, all project will be created from here and validate the command line.
 */
public class Create extends Command {
    public static final String word = "--project";

    public Create(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        try {
            ArrayList<String> newProject = new ArrayList<>();
            newProject.add("name");
            newProject.add("description");
            newProject.add("involve");
            newProject.add("client");
            newProject.add("startdate");
            newProject.add("duedate");
            newProject.add("incharge");
            newProject.add("email");

            String[] splits = line.split("--");
            boolean hasMistake = false;


            for (String command : newProject) {
                if (!line.contains("--" + command)) {
                    System.out.println("The command line is missing for --" + command);
                    hasMistake = true;
                }
            }
            if (hasMistake) {
                System.out.println("Please create the project in the correct format:\n"
                    + "--project --name INPUT --description INPUT --involve INPUT --client INPUT "
                    + "--startdate dd/mm/yyyy --duedate dd/mm/yyyy --incharge INPUT --email INPUT");
                return;
            }

            String newData = "";
            String temp;

            String startDate = "";
            String dueDate = "";

            for (String command : newProject) {
                for (int num = 1; num < splits.length; num++) {
                    if (splits[num].contains(command)) {
                        temp = "--" + splits[num];
                        newData = newData.concat(temp);
                    }
                    if (splits[num].contains("startdate")) {
                        String[] arr = splits[num].split(" ", 2);
                        startDate = arr[1];
                    }
                    if (splits[num].contains("duedate")) {
                        String[] arr = splits[num].split(" ", 2);
                        dueDate = arr[1];
                    }
                    if (splits[num].contains("email")) {
                        String[] arr = splits[num].split(" ",2);
                        if (!(arr[1].matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"))) {
                            System.out.println("Invalid Email Format");
                            return;
                        }
                    }
                }
            }

            if (new DateConverter(startDate, dueDate).dateChecker(startDate, true)
                && new DateConverter(startDate, dueDate).dateChecker(dueDate, true)
                    &&  new DateConverter(startDate, dueDate).dateValidator(startDate, dueDate)) {
                projects.add(new NewProject(newData));
                ui.printProjectCreated(projects);
                storage.updateStorage(projects);
            }

        } catch (IOException e) {
            ui.printBorderline(e.getMessage());
        }
    }
}
