package ru.web_str.sweater.repos;

import org.springframework.data.repository.CrudRepository;
import ru.web_str.sweater.domain.Message;

import java.util.List;

/**
 * Метод поиска в базе данных по тэгу.
 * hibernate (jpa) создает в бд sql запрос на основе названия метода.
 * Спецификация jpa репозитория:
 * https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 */
public interface MessageRepos extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
