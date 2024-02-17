package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TrackCommandTest {
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
    public void trackTest() {
        long chatId = 1;
        Update updateMock = createMockUpdate(chatId, "\\track https://edu.tinkoff.ru");
        TrackCommand help = new TrackCommand();

        String expectedOutput = "You started tracking this link";
        SendMessage expectedMessage = new SendMessage(chatId, expectedOutput);

        assertEquals(expectedMessage.getParameters(), help.handle(updateMock).getParameters());

    }

}
