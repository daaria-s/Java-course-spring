package edu.java.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BotTest {

    BotListener listener = new BotListener(new TelegramBot(""));

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
    public void unknownCommandTest() {
        Update update1 = createMockUpdate(1, "bad command");
        SendMessage expectedMessage1 = new SendMessage(1L, "Unknown command");
        assertEquals(expectedMessage1.getParameters(), listener.processUpdate(update1).getParameters());

        Update update2 = createMockUpdate(2L, "");
        SendMessage expectedMessage2 = new SendMessage(2L, "Unknown command");
        assertEquals(expectedMessage2.getParameters(), listener.processUpdate(update2).getParameters());

        Update update3 = createMockUpdate(3L, "");
        SendMessage expectedMessage3 = new SendMessage(3L, "Unknown command");
        assertEquals(expectedMessage3.getParameters(), listener.processUpdate(update3).getParameters());

    }

    @Test
    public void commandsTest() {
        Update update1 = createMockUpdate(5L, "/start");
        SendMessage expectedMessage1 = new SendMessage(5L, "You have been successfully registered in LinkTrackingBot");
        assertEquals(expectedMessage1.getParameters(), listener.processUpdate(update1).getParameters());


        Update update2 = createMockUpdate(5L, "/list");
        SendMessage expectedMessage2 = new SendMessage(5L, "Here will be links for tracking");
        assertEquals(expectedMessage2.getParameters(), listener.processUpdate(update2).getParameters());

    }


    @Test
    public void commandsWithoutRegistrationTest() {
        Update update1 = createMockUpdate(4L, "/track link");
        SendMessage expectedMessage1 = new SendMessage(4L, "You should register first");
        assertEquals(expectedMessage1.getParameters(), listener.processUpdate(update1).getParameters());

        Update update2 = createMockUpdate(4L, "/list");
        SendMessage expectedMessage2 = new SendMessage(4L, "You should register first");
        assertEquals(expectedMessage2.getParameters(), listener.processUpdate(update2).getParameters());

        Update update3 = createMockUpdate(4L, "/start");
        SendMessage expectedMessage3 = new SendMessage(4L, "You have been successfully registered in LinkTrackingBot");
        assertEquals(expectedMessage3.getParameters(), listener.processUpdate(update3).getParameters());

    }
}
