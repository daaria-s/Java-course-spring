package edu.java.bot.utils;

import edu.java.bot.commands.HelpCommand;
import edu.java.bot.commands.ListCommand;
import edu.java.bot.commands.StartCommand;
import edu.java.bot.commands.TrackCommand;
import edu.java.bot.commands.UntrackCommand;
import org.junit.Test;
import java.util.Optional;
import static edu.java.bot.utils.CommandUtils.getCommand;
import static org.junit.Assert.assertEquals;

public class CommandUtilsTest {
    @Test
    public void getCommandTest() {
        assertEquals(Optional.empty(), getCommand("\\wrongcommand"));
        assertEquals(Optional.empty(), getCommand("start"));
        assertEquals(Optional.empty(), getCommand("\\untrack"));
        assertEquals(Optional.empty(), getCommand(""));


        assertEquals(StartCommand.class, getCommand("\\start").get().getClass());
        assertEquals(HelpCommand.class, getCommand("\\help").get().getClass());
        assertEquals(ListCommand.class, getCommand("\\list").get().getClass());
        assertEquals(TrackCommand.class, getCommand("\\track link").get().getClass());
        assertEquals(UntrackCommand.class, getCommand("\\untrack link").get().getClass());
    }
}
