package ldubgd.coursework.TestFTE.handlers;

import ldubgd.coursework.TestFTE.entity.InlineKeyboard;
import ldubgd.coursework.TestFTE.entity.Menu;
import ldubgd.coursework.TestFTE.entity.Users;
import ldubgd.coursework.TestFTE.messagesender.MessageSender;
import ldubgd.coursework.TestFTE.repository.InlineKeyboardRepository;
import ldubgd.coursework.TestFTE.repository.MenuRepository;
import ldubgd.coursework.TestFTE.repository.UsersRepository;
import ldubgd.coursework.TestFTE.util.InlineButton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class MessageHandler implements Handler<Message> {
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private InlineKeyboardRepository inlineKeyboardRepository;
    @Autowired
    private InlineButton inlineButton;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void choose(Message message) {
        if (message.hasText()){
            String  userId = String.valueOf(message.getChatId());
            //виведення повідомлення користувача в консоль
            log.info("{}:{}", userId, message.getText());
            //взаємодія з конкретним чатом
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(userId);
            sendMessage.setParseMode(ParseMode.HTML);

            Menu getMessage = menuRepository.findByNameMenu(message.getText());
            switch (message.getText()){
                case "/start":
                    Users users = new Users();
                    Optional<Users> byTelegramId = usersRepository.findByTelegramId(userId);
                    if (byTelegramId.isPresent()){
                        users = byTelegramId.get();
                    }else {
                        users.setTelegramId(userId);
                        users.setDateJoined(new Date());
                        usersRepository.save(users);
                    }
                    sendMessage.setText(getMessage.getMenu());
                    List<InlineKeyboard> startInlineKeyboard = inlineKeyboardRepository.findByMenu(message.getText());
                    sendMessage.setReplyMarkup(inlineButton.getCreateInlineKeyboard(startInlineKeyboard));
                    break;
                case "/about":
                    sendMessage.setText(getMessage.getMenu());
                    break;
            }
            messageSender.sendMessage(sendMessage);
        }else {

        }
    }
}
