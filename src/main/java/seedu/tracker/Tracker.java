package seedu.tracker;

import java.util.NoSuchElementException;
import java.util.Scanner;
import seedu.tracker.command.Command;
import seedu.tracker.command.Exit;
import seedu.tracker.parser.Parser;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

public class Tracker {
    /**
     * Main entry-point for the java.seedu.tracker.Tracker application.
     */
    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private final Scanner in = new Scanner(System.in);
    private final ProjectList projects = new ProjectList();
    private final Storage storage = new Storage("projects.txt", projects, ui);

    public static void main(String[] args) {
        new Tracker().run();
    }

    private void run() {
        ui.printGreeting();
        ui.printHelp();
        storage.readStorage(projects, ui, storage);
        readUserInput();
    }

    private void readUserInput() {
        while (true) {
            try {
                Command command = parser.parseInput(in.nextLine(), ui, projects, storage);
                command.execute();
                if (isExit(command)) {
                    return;
                }
            } catch (NoSuchElementException e) {
                ui.printBorderline(e.getMessage());
            }
        }
    }

    private boolean isExit(Command command) {
        return Exit.class.isAssignableFrom(command.getClass());
    }
}
