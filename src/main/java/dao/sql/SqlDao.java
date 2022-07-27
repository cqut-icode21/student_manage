package dao.sql;

import java.lang.reflect.Field;

public interface SqlDao {
    static String updateSql(Class<? extends Field[]> aClass) {
        return null;
    }

   static String addSql(Class<?> aClass) {

        return null;
    }


}
