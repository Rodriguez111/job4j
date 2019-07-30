package ru.job4j.siteparser.sql;

import ru.job4j.siteparser.sql.ConsumeEx;
import ru.job4j.siteparser.sql.FuncEx;
import ru.job4j.siteparser.sql.TripleConsumeEx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QueryManager {
    private final Connection connection;
    private final Map<Class, TripleConsumeEx<Integer, PreparedStatement, Object>> dispatcher = new HashMap<>();

    public QueryManager(Connection connection) {
        this.connection = connection;
        initDispatcher();
    }

    private void initDispatcher() {
        dispatcher.put(Integer.class, (index, ps, obj) -> {
            ps.setInt(index, (int) obj);
        });
        dispatcher.put(String.class, (index, ps, obj) -> {
            ps.setString(index, (String) obj);
        });
    }

//    public void executeQuery(String query, List<Object> params, ConsumeEx<PreparedStatement> consumeEx) {
//        try {
//            PreparedStatement ps = connection.prepareStatement(query);
//            consumeEx.accept(ps);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public <R> Optional<R> executeQuery(String query, List<Object> params, FuncEx<PreparedStatement, R> funcEx) {
        Optional<R> result = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.size(); i++) {
                dispatcher.get(params.get(i).getClass()).accept(i + 1, preparedStatement, params.get(i));
            }
            result = Optional.of(funcEx.apply(preparedStatement));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
