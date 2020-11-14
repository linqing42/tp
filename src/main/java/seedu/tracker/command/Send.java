package seedu.tracker.command;

import seedu.tracker.project.ProjectList;
import seedu.tracker.email.SendEmail;
import seedu.tracker.ui.Ui;

/**
 * A send class, send email to the project incharge.
 */

public class Send extends Command {
    public static final String word = "--send";

    public Send(String line, ProjectList projects, Ui ui) {
        super(line, projects, ui);
    }

    @Override
    public void execute() {
        try {
            if (projects.size() == 0) {
                ui.printBorderline("It appears that you have no projects yet, so you can't edit any!\n"
                        + "Perhaps you should start creating one?");
                return;
            }

            int projectNumber = Integer.parseInt(line.trim()) - 1;

            if (projects.size() == 0) {
                ui.printBorderline("It appears that you have no project! Perhaps you should start creating one?");
                return;
            }
            if (line.isEmpty() || projectNumber > projects.size() || projectNumber < 0) {
                ui.printBorderline("Please type in the right project");
                return;
            }
            String getString = projects.get(projectNumber).toString();
            new SendEmail(getEmail(getString), ui.displayProject(projects.get(projectNumber)));
        } catch (NumberFormatException ex) {
            System.out.println(" Please input a number");
        }
    }

    public String getEmail(String line) {

        String[] arr = line.split("--");
        for (String s : arr) {
            String[] arr2 = s.split(" ", 2);
            if (arr2[0].contains("email")) {
                line = arr2[1].trim().toLowerCase();
            }
        }
        return line;
    }


}
