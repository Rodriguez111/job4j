package cinema.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QueryManager {

    private static final Logger LOG = LoggerFactory.getLogger(QueryManager.class);
    private final Connection connection;
    private final Map<Class, TriConsumer<Integer, PreparedStatement, Object>> dispatcher = new HashMap<>();

    public QueryManager(Connection connection) {
        this.connection = connection;
        initDispatcher();
    }

    private void initDispatcher() {
        dispatcher.put(Integer.class, (index, ps, obj) -> {
            ps.setInt(index + 1, (int) obj);
        });
        dispatcher.put(String.class, (index, ps, obj) -> {
            ps.setString(index + 1, (String) obj);
        });
        dispatcher.put(Boolean.class, (index, ps, obj) -> {
            ps.setBoolean(index + 1, (Boolean) obj);
        });
        dispatcher.put(Float.class, (index, ps, obj) -> {
            ps.setFloat(index + 1, (Float) obj);
        });
    }


    public void runQuery(String query, List<Object> params, Consumer<PreparedStatement> consumer) throws Exception {

            PreparedStatement ps = connection.prepareStatement(query);
            for (int i = 0; i < params.size(); i++) {
                dispatcher.get(params.get(i).getClass()).accept(i, ps, params.get(i));
            }
            consumer.accept(ps);
    }

    public void runQueryWithHandledException(String query, List<Object> params, Consumer<PreparedStatement> consumer) {

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            for (int i = 0; i < params.size(); i++) {
                dispatcher.get(params.get(i).getClass()).accept(i, ps, params.get(i));
            }
            consumer.accept(ps);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }
    }

    public <R> Optional<R> runQuery(String query, List<Object> params, Func<PreparedStatement, R> func) throws Exception {
        Optional<R> result = Optional.empty();

            PreparedStatement ps = connection.prepareStatement(query);
            for (int i = 0; i < params.size(); i++) {
                dispatcher.get(params.get(i).getClass()).accept(i, ps, params.get(i));
            }
            result = Optional.of(func.apply(ps));
        return result;
    }

    public <R> Optional<R> runQueryWithHandledException(String query, List<Object> params, Func<PreparedStatement, R> func) {
        Optional<R> result = Optional.empty();

        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
            for (int i = 0; i < params.size(); i++) {
                dispatcher.get(params.get(i).getClass()).accept(i, ps, params.get(i));
            }
            result = Optional.of(func.apply(ps));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }
        return result;
    }




}
