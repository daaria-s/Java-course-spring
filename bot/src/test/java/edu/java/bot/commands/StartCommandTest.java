package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.utils.UsersUtils;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class StartCommandTest {
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
        Update updateMock = createMockUpdate(chatId, "\\start");
        StartCommand list = new StartCommand();

        String expectedOutput = "You have been successfully registered in LinkTrackingBot";
        SendMessage expectedMessage = new SendMessage(chatId, expectedOutput);

        assertEquals(expectedMessage.getParameters(), list.handle(updateMock).getParameters());

        UsersUtils.addNewUser(1);

        String expectedOutput2 = "You are already registered in LinkTrackingBot";
        SendMessage expectedMessage2 = new SendMessage(chatId, expectedOutput2);

        assertEquals(expectedMessage2.getParameters(), list.handle(updateMock).getParameters());


        UsersUtils.removeUser(1);

    }

}
