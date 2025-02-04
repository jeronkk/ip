import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime dueDate;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");

    public Deadline(String description, String by) {
        super(description);
        this.dueDate = parseDateTime(by);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        try {
            DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTime, formatterWithTime);
        } catch (DateTimeParseException e1) {
            try {
                DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("d/M/yyyy");
                return LocalDate.parse(dateTime, formatterWithoutTime).atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format! Use 'd/M/yyyy HHmm' (e.g., 2/12/2019 1800) or 'd/M/yyyy' (e.g., 2/12/2019).");
            }
        }
    }


    @Override
    public String toFileFormat() {
        DateTimeFormatter saveFormatWithTime = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter saveFormatWithoutTime = DateTimeFormatter.ofPattern("d/M/yyyy");
        String formattedDate = (dueDate.toLocalTime().equals(LocalTime.MIDNIGHT)) ?
                dueDate.format(saveFormatWithoutTime) : dueDate.format(saveFormatWithTime);

        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + formattedDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(OUTPUT_FORMATTER) + ")";
    }
}
