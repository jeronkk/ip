package lechatbot.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * A <code>Deadline</code> object contains a description and a due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");
    private final LocalDateTime dueDate;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The deadline in "d/M/yyyy HHmm" or "d/M/yyyy" format.
     */
    public Deadline(String description, String by) {
        super(description);
        this.dueDate = parseDateTime(by);
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     * Accepts "d/M/yyyy HHmm" for full date-time or "d/M/yyyy" for date only.
     *
     * @param dateTime The string representation of the date and time.
     * @return The parsed LocalDateTime object.
     * @throws IllegalArgumentException If the format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        try {
            DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateTime, formatterWithTime);
        } catch (DateTimeParseException e1) {
            try {
                DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("d/M/yyyy");
                return LocalDate.parse(dateTime, formatterWithoutTime).atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format! Use 'd/M/yyyy HHmm' "
                        + "(e.g., 2/12/2019 1800) or 'd/M/yyyy' (e.g., 2/12/2019).");
            }
        }
    }

    /**
     * Converts the deadline task to a file-friendly format.
     *
     * @return A string representing the deadline task in file format.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter saveFormatWithTime = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter saveFormatWithoutTime = DateTimeFormatter.ofPattern("d/M/yyyy");
        String formattedDate = (dueDate.toLocalTime().equals(LocalTime.MIDNIGHT))
                ? dueDate.format(saveFormatWithoutTime) : dueDate.format(saveFormatWithTime);

        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + formattedDate;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(OUTPUT_FORMATTER) + ")";
    }
}
