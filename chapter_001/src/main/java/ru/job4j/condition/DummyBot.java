package ru.job4j.condition;

/**
 * @author Rodriguez (mymail4java@gmail.com)
 * @version 1
 * @since 15.11.2018
 */

public class DummyBot {

    /**
     * Answering questions.
     * @param question - question.
     * @return - answer.
     */

    public String answer(String question) {
        if (question.equals("Привет, Бот.")) {
            return "Привет, умник.";
        } else if (question.equals("Пока.")) {
            return "До скорой встречи.";
        }
        return  "Это ставит меня в тупик. Спросите другой вопрос.";
    }
}
