package ru.web_str.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.web_str.sweater.domain.Message;
import ru.web_str.sweater.repos.MessageRepos;

import java.util.List;
import java.util.Map;

/**
 * TODO list:
 * 1. Выбрать каким фреймворком обрабатывать json файл
 * 2. Собрать новый спринг прокут
 * 3. Поключить выбранный json фреймворк
 * 4. Написать и заполнить класс Form полями необходимыми для валидации
 * 5. Написать контроллер валидации.
 */
@Controller
public class MainController {
    @Autowired
    private MessageRepos messageRepos;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return  "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model ) {
        Iterable<Message> messages = messageRepos.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(

            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepos.save(message);
        Iterable<Message> messages = messageRepos.findAll();
        model.put("messages", messages);
        return "main";
    }

    /**
     *  PostMapping("filter") значение в скобках рядом с анотацией мапит запрос для метода filter
     *  Если фиьтр пустой возвращает все элементы бд
     * @param filter String тэг по котору ищем в бд
     * @param model Map карта
     * @return String имя веб страницы
     */
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepos.findByTag(filter);
        } else {
            messages = messageRepos.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}
