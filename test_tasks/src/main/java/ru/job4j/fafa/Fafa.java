package ru.job4j.fafa;

/**
 * A. Фафа и его компания
 * ограничение по времени на тест1 секунда
 * ограничение по памяти на тест256 мегабайт
 * вводстандартный ввод
 * выводстандартный вывод
 * Фафа руководит компанией, которая работает над большими проектами. Всего в компании Фафы n работников. Когда появляется новый проект, Фафа должен распределить задачи по проекту между всеми работниками.
 * <p>
 * Фафа недавно понял, что каждый раз делать это очень сложно. Поэтому он решил выбрать лучших l сотрудников из компании в руководители. Когда появляется новый проект, Фафа будет распределять задачи только между руководителями, а каждый руководитель будет отвечать за некоторое положительное число сотрудников и будет распределять задачи между ними. Чтобы все было честно, каждый руководитель должен получить одинаковое число сотрудников под свое руководство. Кроме того, каждый сотрудник, не являющийся руководителем, должен быть подчинен ровно одному руководителю, и никакой руководитель не должен быть подчинен никакому руководителю.
 * <p>
 * По данному числу сотрудников n найдите, сколькими способами Фафа может выбрать число руководителей l, чтобы можно было поровну разделить оставшихся сотрудников между ними.
 * <p>
 * Входные данные
 * В единственной строке содержится одно целое число n (2 ≤ n ≤ 105) — количество сотрудников в компании Фафы.
 * <p>
 * Выходные данные
 * Выведите одно целое число — ответ на задачу.
 * <p>
 * <p>
 * Примеры
 * входные данные
 * 2
 * выходные данные
 * 1
 * входные данные
 * 10
 * выходные данные
 * 3
 * Примечание
 * Во втором примере у Фафы есть три способа:
 * <p>
 * выбрать только 1 руководителя и определить остальных 9 сотрудников под его руководство.
 * выбрать 2-х руководителей, каждый будет руководить 4 сотрудниками.
 * выбрать 5 руководителей, каждый будет руководить 1 сотрудником.
 */

public class Fafa {

    public int calculateVariants(int employees) {
        int variants = 0;
        for (int chief = 1; chief < employees; chief++) {
            int junior = employees - chief;
            if (junior < chief) {
                break;
            }
            if (junior % chief == 0) {
                variants++;
            }
        }
        return variants;
    }

}
