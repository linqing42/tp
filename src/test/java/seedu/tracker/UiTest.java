package seedu.tracker;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import org.junit.jupiter.api.Test;
import seedu.tracker.common.DateConverter;
import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.ui.Ui;

public class UiTest {
    Ui ui = new Ui();

    @Test
    public void testDisplayProject() {

        Project project1 = new Project("--name Clinical Trials --description regarding hospital task"
                + "--involve Tom, Lucy --client Desi --startdate 11/11/2020 --duedate 12/12/2020"
                + "--incharge Derek --email linqing4267@gmail.com");
        ProjectList projects = new ProjectList();
        projects.add(project1);
        String daysLeft;

        String projectDetail1 = ui.displayProject(projects.get(0));

        daysLeft = new DateConverter("12/12/2020").getDaysLeft();

        String projectDetail2 =
                "Project Name: Clinical Trials " + "\n"
                        + "Project Description: regarding hospital task" + "\n"
                        + "Project Team Members: Tom, Lucy " + "\n"
                        + "Client: Desi " + "\n"
                        + "Start Date: 11/11/2020 " + "\n"
                        + "Due Date: 12/12/2020" + "\n"
                        + "Person in Charge: Derek " + "\n"
                        + "Email: linqing4267@gmail.com" + "\n"
                        + "Duration: 31" + "\n"
                        + "Days Left: " + daysLeft + "\n"
                        + "Status: " + project1.getStatus() + "\n";
        assertEquals(projectDetail1, projectDetail2);
    }

}
