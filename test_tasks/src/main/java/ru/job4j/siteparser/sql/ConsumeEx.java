package ru.job4j.siteparser.sql;

import java.sql.PreparedStatement;

public interface ConsumeEx<T> {
   void accept(T type) throws Exception;
}
