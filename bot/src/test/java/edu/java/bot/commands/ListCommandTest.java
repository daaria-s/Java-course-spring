package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ListCommandTest {
    Update createMockUpdate(long chatId, String text) {
        Update update = Mockito.mock(Update.class);
        Message message = Mockito.mock(Message.class);
        Chat chat = Mockito.mock(Chat.class);

        when(update.message()).thenReturn(message);
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(chatId);
        when(message.text()).thenReturn(text);
        return update;
    }

    @Test
    public void listTest() {
        long chatId = 1;
        Update updateMock = createMockUpdate(chatId, "\\list");
        ListCommand list = new ListCommand();

        String expectedOutput = "Here will be links for tracking";
        SendMessage expectedMessage = new SendMessage(chatId, expectedOutput);

        assertEquals(expectedMessage.getParameters(), list.handle(updateMock).getParameters());

    }

}
