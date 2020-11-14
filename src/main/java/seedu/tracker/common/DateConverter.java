package seedu.tracker.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Locale;
import seedu.tracker.ui.Ui;

/**
 * DateConverter class, is a common class, all the data validation or conversion will be done here.
 *
 */
public class DateConverter {
    String startDate;
    String dueDate;
    String dateDiff;
    String daysLeft;
    Ui ui;

    public DateConverter(String startDate, String dueDate) {
        this.startDate = startDate;
        this.dueDate = dueDate;
        setDateDiff();
    }

    public DateConverter(String dueDate) {
        this.dueDate = dueDate;
        setDaysLeft();
    }

    public DateConverter(Ui ui) {
        this.ui = ui;
    }

    public void setDateDiff() {
        dateConverter(startDate);
        dateConverter(dueDate);
        Float day = calculateDateDiff(startDate, dueDate);
        dateDiff = String.format("%.0f", day);
    }

    public void setDaysLeft() {
        String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        dateConverter(currentDate);
        dateConverter(dueDate);
        Float day = calculateDateDiff(currentDate, dueDate);
        daysLeft = String.format("%.0f", day);
       
    }

    public boolean dateChecker(String dateInString, boolean displayError) {
        try {
            // ResolverStyle.SMART for checking actual calendar dates.
            LocalDate.parse(dateInString.trim(),
                DateTimeFormatter.ofPattern("d/M/yyyy")
                    .withResolverStyle(ResolverStyle.SMART)
            );
        } catch (DateTimeParseException e) {
            if (displayError) {
                ui.printBorderline(dateInString
                    + " is not within actual calendar dates or not in the correct format: dd/MM/yyyy");
            }
            return false;
        }
        return true;
    }

    public void dateConverter(String dateInString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public float calculateDateDiff(String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        float daysBetween = 0;
        try {
            Date dateBefore = formatter.parse(startDate);
            Date dateAfter = formatter.parse(endDate);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (float) (difference / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return daysBetween;
    }

    public boolean dateValidator(String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        boolean isSdEarlierThanEd = false;

        try {
            Date sd = formatter.parse(startDate);
            Date ed = formatter.parse(endDate);
            if (sd.compareTo(ed) < 0) {
                isSdEarlierThanEd = true;
            } else {
                System.out.println("Due Date is earlier than Start Date, please enter a proper date!");
                isSdEarlierThanEd = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isSdEarlierThanEd;
    }

    public String getDateDiff() {
        return dateDiff;
    }

    public String getDaysLeft() {
        return daysLeft;
    }
}
