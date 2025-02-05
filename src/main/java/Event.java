import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");

    public Event(String description, String from, String to) {
        super(description);
        this.startTime = parseDateTime(from);
        this.endTime = parseDateTime(to);
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

        String formattedStart = (startTime.toLocalTime().equals(LocalTime.MIDNIGHT)) ?
                startTime.format(saveFormatWithoutTime) : startTime.format(saveFormatWithTime);

        String formattedEnd = (endTime.toLocalTime().equals(LocalTime.MIDNIGHT)) ?
                endTime.format(saveFormatWithoutTime) : endTime.format(saveFormatWithTime);

        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                formattedStart + " | " + formattedEnd;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(OUTPUT_FORMATTER) +
                " to: " + endTime.format(OUTPUT_FORMATTER) + ")";
    }
}
