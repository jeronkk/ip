package lechatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import lechatbot.command.AddCommand;
import lechatbot.command.Command;
import lechatbot.command.DeadlineCommand;

public class ParserTest {

    @Test
    public void parse_validTodoCommand_success() {
        try {
            Command command = Parser.parse("todo read book");
            assertNotNull(command);
            assertTrue(command instanceof AddCommand);
        } catch (LeChatBotException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void parse_validDeadlineCommand_success() {
        try {
            Command command = Parser.parse("deadline submit report /by 10/02/2025");
            assertNotNull(command);
            assertTrue(command instanceof DeadlineCommand);
        } catch (LeChatBotException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        Exception exception = assertThrows(LeChatBotException.class, () -> {
            Parser.parse("invalidcommand");
        });

        assertEquals(
                "OOPS!!! Invalid command! Try: todo, deadline, event, list, mark, unmark, bye", exception.getMessage());
    }
}
