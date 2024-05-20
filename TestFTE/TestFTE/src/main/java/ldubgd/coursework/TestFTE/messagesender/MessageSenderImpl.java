package ldubgd.coursework.TestFTE.messagesender;

import ldubgd.coursework.TestFTE.TestFireTechnicalEquipmentBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
@Service
public class MessageSenderImpl implements MessageSender{
    @Autowired
    private TestFireTechnicalEquipmentBot testFireTechnicalEquipmentBot;
    @Override
    public void sendPhoto(SendPhoto sendPhoto) {
        try {
            testFireTechnicalEquipmentBot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void sendVideo(SendVideo sendVideo) {
        try {
            testFireTechnicalEquipmentBot.execute(sendVideo);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(SendMessage sendMessage) {
        try {
            testFireTechnicalEquipmentBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
