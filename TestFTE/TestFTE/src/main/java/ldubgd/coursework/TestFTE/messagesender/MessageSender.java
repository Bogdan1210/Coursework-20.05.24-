package ldubgd.coursework.TestFTE.messagesender;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;

public interface MessageSender {
    void sendMessage(SendMessage sendMessage);
    void sendPhoto(SendPhoto sendPhoto);
    void sendVideo(SendVideo sendVideo);
}
