package ldubgd.coursework.TestFTE.handlers;

import ldubgd.coursework.TestFTE.entity.InlineKeyboard;
import ldubgd.coursework.TestFTE.entity.Menu;
import ldubgd.coursework.TestFTE.entity.Media;
import ldubgd.coursework.TestFTE.messagesender.MessageSender;
import ldubgd.coursework.TestFTE.repository.InlineKeyboardRepository;
import ldubgd.coursework.TestFTE.repository.MenuRepository;
import ldubgd.coursework.TestFTE.repository.MediaRepository;
import ldubgd.coursework.TestFTE.util.InlineButton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.InputStream;
import java.util.List;

@Component
public class CallbackQueryHandler implements Handler<CallbackQuery> {
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private InlineKeyboardRepository inlineKeyboardRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private InlineButton inlineButton;
    @Override
    public void choose(CallbackQuery callbackQuery) {
        //надіслати нове повідомлення
        Message message = callbackQuery.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        //надсилання фото в конкретний чат
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(message.getChatId()));
        //надсилання відео
        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId(message.getChatId());

        Menu byMenu = menuRepository.findByNameMenu(callbackQuery.getData());
        switch (callbackQuery.getData()){
            //надіслати фото і текст
            case "Електрозахисні засоби/start":
            case "Рукавні затримки/start":
            case "Мотузки рятувальні/start":
            case "Пояси/Індивідуальний захист":
                Media photoByMenu = mediaRepository.findByMenu(callbackQuery.getData());
                sendPhoto.setPhoto(new InputFile(photoByMenu.getUrl()));
                messageSender.sendPhoto(sendPhoto);

                sendMessage.setText(byMenu.getMenu());
                break;
                //надіслати текст
            case "Електрофікований інструмент/start":
            case "Висувна/Драбини":
                sendMessage.setText(byMenu.getMenu());
                break;
                //надіслати текст і клавіатуру
            case "Індивідуальний захист":
            case "Пожежні драбини/start":
                sendMessage.setText(byMenu.getMenu());
                List<InlineKeyboard> keyboardByMenu = inlineKeyboardRepository.findByMenu(callbackQuery.getData());
                sendMessage.setReplyMarkup(inlineButton.getCreateInlineKeyboard(keyboardByMenu));
                break;
            case "Штурмова/Драбини":
            case "Палиця/Драбини":
            case "Апарати/Індивідуальний захист":
                //надіслати відео і текст
                Media nameOfVideo = mediaRepository.findByMenu(callbackQuery.getData());
                InputStream videoStream = MessageHandler.class.getClassLoader().getResourceAsStream("video/"+ nameOfVideo.getUrl());
                sendVideo.setVideo(new InputFile(videoStream,nameOfVideo.getUrl()));
                messageSender.sendVideo(sendVideo);
                sendMessage.setText(byMenu.getMenu());

        }
        messageSender.sendMessage(sendMessage);
    }
}
