package ldubgd.coursework.TestFTE;

import ldubgd.coursework.TestFTE.processors.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public class TestFireTechnicalEquipmentBot extends TelegramLongPollingBot {
    @Value("${bot.BOT_TOKEN}")
    private String botToken;

    @Value("${bot.BOT_USERNAME}")
    private String botUsername;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        processor.process(update);
    }
    @Autowired
    private Processor processor;
}
