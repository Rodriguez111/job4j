package ru.job4j.trackersql;

import ru.job4j.trackersql.exeptions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QueryHandler {
  private  Map<Class, TripleConsumerEx<Integer, PreparedStatement, Object>> dispatcher = new HashMap<>();
  private final Connection connection;

    public QueryHandler(Connection connection) {
        this.connection = connection;
        initDispatcher();
    }

    private void initDispatcher() {
        dispatcher.put(Integer.class, (index, ps, value) -> ps.setInt(index, (int) value));
        dispatcher.put(String.class, (index, ps, value) -> ps.setString(index, (String) value));
    }

    /**
     * For-each statement with index.
     * @param list list.
     * @param consumer consumer.
     * @param <T> type.
     * @throws Exception possible exception.
     */
    public <T> void forIndex(List<T> list, BiConsumerEx<Integer, T> consumer) throws Exception {
        for (int index = 0; index != list.size(); index++) {
            consumer.accept(index, list.get(index));
        }
    }

    public  <R> Optional<R> processQuery(String query, List<Object> parameters, FuncEx<PreparedStatement, R> func, int key) {
        Optional<R> result = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, key);
            forIndex(parameters,
                    (index, value) -> dispatcher.get(value.getClass()).accept(index + 1, preparedStatement, value));

            result = Optional.of(func.apply(preparedStatement));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  <R> Optional<R> processQuery(String query, List<Object> parameters, FuncEx<PreparedStatement, R> func) {
        Optional<R> result = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            forIndex(parameters,
                    (index, value) -> dispatcher.get(value.getClass()).accept(index + 1, preparedStatement, value));
            result = Optional.ofNullable(func.apply(preparedStatement));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public Map<Class, TripleConsumerEx<Integer, PreparedStatement, Object>> getDispatcher() {
        return dispatcher;
    }
}
