package seedu.tracker.ui;

import java.text.ParseException;
import seedu.tracker.common.DateConverter;
import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;

/**
 * Responsible for displaying all messages and its format to the user except for error-related messages.
 */

public class Ui {
    public String colorRed(String input) {
        return "\033[31m" + input + "\033[0m";
    }

    public String colorGreen(String input) {
        return "\033[33m" + input + "\033[0m";
    }

    /**
     * Prints the input string with a borderline.
     */

    public void printBorderline(String input) {
        System.out.println(input + "\n_________________________________________________________________"
            + "_______________________________________________________________________________________\n");
    }

    /**
     * Prints the Tracker's greeting.
     */

    public void printGreeting() {
        String greeting = "Hello! I'm Tracker.\n"
            + "I can help you manage a list of projects!\n";
        printBorderline("");
        System.out.print(greeting);
    }

    /**
     * Prints all available commands.
     */

    public void printHelp() {
        String help = "What you can tell me to do is listed below:\n"
            + " - See all commands            | " + colorGreen("--help\n")
            + " - Create a new project        | " + colorGreen(
            "--project --name INPUT --description INPUT --involve INPUT --client INPUT"
                + " --startdate dd/mm/yyyy --duedate dd/mm/yyyy --incharge INPUT --email INPUT \n")
            + " - Complete a project          | " + colorGreen("--complete INDEX\n")
            + " - Edit a project detail       | " + colorGreen("--edit INDEX --commandName INPUT\n")
            + " - Add an extra project detail | " + colorGreen("--add INDEX --commandName INPUT\n")
            + " - Delete a project            | " + colorGreen("--delete INDEX\n")
            + " - Send a project by email     | " + colorGreen("--send INDEX\n")
            + " - List down all projects      | " + colorGreen("--list\n")
            + " - Find word(s) in projects    | " + colorGreen("--find KEYWORD\n")
            + " - Find and replace word(s)    | " + colorGreen("--find KEYWORD --replace KEYWORD\n")
            + " - Exit my program             | " + colorGreen("--exit or hit Enter");
        printBorderline(help);
    }

    public void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printBorderline(farewell);
    }

    /**
     * Prints the created project information.
     */

    public void printProjectCreated(ProjectList projects) {
        String created = "Great! You have created a proper project as shown below:\n"
            + colorGreen(displayProject(projects.get(projects.size() - 1))
            + "\nWe now have " + projects.size() + " project(s) in your list!");
        printBorderline(created);
    }

    /**
     * Prints the removed project information.
     */

    public void printProjectRemoved(ProjectList projects, int index) {
        String added = "Okay! We have removed the project as shown below:\n"
            + colorRed(displayProject(projects.get(index))
            + colorGreen("\nWe now have " + (projects.size() - 1) + " project(s) in your list!"));
        printBorderline(added);
    }

    /**
     * Prints a message that shows an invalid command was inputted.
     */

    public void printInvalidCommand() {
        String message = "It seems that you have typed something out of my unfortunately\n"
            + "limited vocabulary. Can you try again?";
        printBorderline(message);
    }

    /**
     * Prints a message that shows no project file was found.
     */

    public void printNoFileFound() {
        String message = "It looks like it's your first time as I could not find any existing project file.\n"
            + "Get started by creating a project!";
        printBorderline(message);
    }

    public void printFileExists() {
        String message = "An existing project file is found!";
        printBorderline(message);
    }

    public void printAddMessage(ProjectList projects) {
        String add = "Got it. I've added this information into the project as shown below:\n"
            + colorGreen(displayProject(projects.get(projects.size() - 1)));
        printBorderline(add);
    }

    public void printEditMessage(ProjectList projects) {
        String edit = "Got it. I've edited the information in the project as shown below:\n"
            + colorGreen(displayProject(projects.get(projects.size() - 1)));
        printBorderline(edit);
    }

    public String displayProject(Project project) {
        String display;
        String[] projectDetails = project.getDescription().split("--");
        String startDate = projectDetails[5].split(" ", 2)[1];
        String dueDate = projectDetails[6].split(" ", 2)[1];
        String duration = new DateConverter(startDate, dueDate).getDateDiff();
        String daysLeft = new DateConverter(dueDate).getDaysLeft();
        if (Integer.parseInt(daysLeft.trim()) < 0) {
            daysLeft = "Overdue";
        }

        display = "Project Name: " + projectDetails[1].split(" ", 2)[1] + "\n"
            + "Project Description: " + projectDetails[2].split(" ", 2)[1] + "\n"
            + "Project Team Members: " + projectDetails[3].split(" ", 2)[1] + "\n"
            + "Client: " + projectDetails[4].split(" ", 2)[1] + "\n"
            + "Start Date: " + startDate + "\n"
            + "Due Date: " + dueDate + "\n"
            + "Person in Charge: " + projectDetails[7].split(" ", 2)[1] + "\n"
            + "Email: " + projectDetails[8].split(" ", 2)[1] + "\n"
            + "Duration: " + duration + "\n"
            + "Days Left: " + daysLeft + "\n"
            + "Status: " + project.getStatus() + "\n";
        return display;
    }

    public void printProjectCompleted(int index, ProjectList projects) {
        String complete = "Good Job! You have completed:\n"
            + colorGreen(displayProject(projects.get(projects.size() - 1)));
        printBorderline(complete);
    }
}
