package seedu.tracker.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import seedu.tracker.command.List;
import seedu.tracker.command.Send;
import seedu.tracker.common.DateConverter;
import seedu.tracker.project.NewProject;
import seedu.tracker.project.ProjectList;
import seedu.tracker.ui.Ui;

/**
 * Responsible for all interactions between the in-memory projects and the projects file.
 */
public class Storage {
    String fileName;


    public Storage(String fileName, ProjectList projects, Ui ui) {
        this.fileName = fileName;
    }

    public void updateStorage(ProjectList projects) throws IOException {
        FileWriter fw = new FileWriter("./" + fileName, false);
        for (int i = 0; i < projects.size(); i++) {
            String line = (i + 1) + ". " + projects.get(i).getDescription() + "\n";
            fw.write(line);
        }
        fw.close();
    }

    public void readStorage(ProjectList projects, Ui ui, Storage storage) {
        try {
            ArrayList<String> lines = getLines(fileName);
            projects = extractProjects(lines, projects, ui, storage);
            ui.printFileExists();
            new List(projects, ui).execute();

        } catch (FileNotFoundException e) {
            ui.printNoFileFound();
        }
    }

    private ArrayList<String> getLines(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        s.close();
        return list;
    }

    public ProjectList extractProjects(ArrayList<String> lines, ProjectList projects, Ui ui, Storage storage) {
        try {
            int dataSet = 1;
            for (String line : lines) {
                FileWriter fw = new FileWriter(fileName, true);

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
                String newData = "";
                String temp;
                String daysLeft = "";
                for (String command : newProject) {
                    for (int num = 1; num < splits.length; num++) {
                        if (splits[num].contains(command)) {
                            temp = "--" + splits[num];
                            newData = newData.concat(temp);
                        }
                        if (splits[num].contains("duedate")) {
                            String[] arr = splits[num].split(" ", 2);
                            daysLeft = new DateConverter(arr[1].trim()).getDaysLeft();
                        }
                    }
                }
                projects.add(new NewProject(newData));

                String projectStatus = splits[0].split(" ", 2)[1].trim();
                if (projectStatus.equals("[Complete]")) {
                    projects.get(projects.size() - 1).setComplete();
                }
                if (Integer.parseInt(daysLeft) < 7 && Integer.parseInt(daysLeft) >= 0) {
                    new Send(Integer.toString(dataSet), projects, ui).execute();
                }
                dataSet++;
            }
        } catch (IOException e) {
            ui.printBorderline(e.getMessage());
        }
        return projects;
    }
}