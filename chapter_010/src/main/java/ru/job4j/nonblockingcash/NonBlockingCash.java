package ru.job4j.nonblockingcash;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. Неблокирующий кеш[#145852]
 * 1. Необходимо сделать кеш для хранение моделей. в кеше должны быть методы
 * add(Base model), update(Base model) delete(Base model),
 * <p>
 * class Base {
 * int id
 * int version
 * }
 * <p>
 * 2. Для хранения данных в кеше нужно использовать ConcurrentHashMap<Integer, Base>.
 * <p>
 * В качестве ключа используйте int id. в качестве значения Base модель
 * <p>
 * 3. В кеше должна быть возможность проверять актуальность данных. Для этого в модели данных должно быть после int version.
 * Это после должно увеличиваться на единицу каждый раз, когда произвели изменения данных в модели.
 * Например. Два пользователя прочитали данные task_1
 * первый пользователь изменил поле имя и второй сделал тоже самое. нужно перед обновлением данных проверить. что текущий пользователь не затер данные другого пользователя. если данные затерты то выбросить OptimisticException - такая реализация достигается за счет введение в модель поля version. Перед обновлением данных необходимо проверять текущую версию и ту что обновляем и увеличивать на единицу каждый раз, когда произошло обновление. Если версии не равны -  кидать исключение.
 * <p>
 * Исключение - OptimisticException нужно сделать самостоятельно.
 * <p>
 * public class OptimisticException extends RuntimeException {
 * }
 * <p>
 * Исключение должно быть RuntimeException, так как обработчик для BiFunction не может кидать исключение.
 * <p>
 * Использовать метод https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html#computeIfPresent-K-java.util.function.BiFunction-
 * <p>
 * Пример.
 * <p>
 * Нить 1 изменила объект 1, тогда version должно стать 1.
 * Нить 2 в это же время изменила объект 1, тут тоже самое version станет 1.
 * <p>
 * Объекты 1 - создаются в разной области памяти. По сути эти два разных объекта с одинаковыми полями.
 * <p>
 * Когда нить 1 будет обновлять данные, обновление пройдет успешно. потому что данные в кеше будут на единицу отличаться.
 * <p>
 * С другой стороны нить 2 выкинет исключение. потому, что версия в кеше не будет соответствовать текущей версии.
 * <p>
 * Тесты.
 * <p>
 * Если мы запускаем нить в тесте, то ошибки в этой нити не будут влиять на результат теста.
 * <p>
 * Посмотри код.
 * <p>
 * public class CatchException {
 *
 * @Test public void whenThrowException() throws InterruptedException {
 * Thread thread = new Thread(
 * () -> {
 * throw new RuntimeException("Throw Exception in Thread");
 * }
 * );
 * thread.start();
 * thread.join();
 * }
 * }
 * <p>
 * Тест выполняется успешно. Это связано с тем, что главная нить не видит, что происходит во второстепенной нити.
 * <p>
 * Чтобы это поправить нам нужно передать исключение к главную нить.
 * <p>
 * public class CatchException {
 * @Test public void whenThrowException() throws InterruptedException {
 * AtomicReference<Exception> ex = new AtomicReference<>();
 * Thread thread = new Thread(
 * () -> {
 * try {
 * throw new RuntimeException("Throw Exception in Thread");
 * } catch (Exception e) {
 * ex.set(e);
 * }
 * }
 * );
 * thread.start();
 * thread.join();
 * Assert.assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
 * }
 * }
 * <p>
 * Теперь мы можем проверить, что такой код падает.
 */

public class NonBlockingCash {
    private final ConcurrentHashMap<Integer, Base> cash = new ConcurrentHashMap<>();

    public void add(Base model) {
        this.cash.put(model.getId(), model);
    }

    public void update(Base model) throws OptimisticException {
        this.cash.computeIfPresent(model.getId(), (id, currentModel) -> {
            int currentVersion = cash.get(model.getId()).getVersion();
            if (!compareAndUpdate(currentVersion, currentModel, model)) {
                throw new OptimisticException("Version does not match");
            }
            return model;
        });
    }

    private boolean compareAndUpdate(int expectedVersion, Base oldModel, Base newModel) {
        boolean result = false;
        if (cash.get(oldModel.getId()).getVersion() == expectedVersion) {
            newModel.setVersion(expectedVersion + 1);
            cash.put(newModel.getId(), newModel);
            result = true;
        }
        return result;
    }

    public Base getBase(int id) {
        return cash.get(id);
    }

    public void delete(Base model) {
        this.cash.remove(model.getId());
    }
}
