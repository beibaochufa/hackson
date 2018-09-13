package hackson.inte;

import java.sql.ResultSet;

/**
 * Created by whh on 2018/9/13.
 */
public interface PopulateTemplate<T> {
    public T populateFromResultSet(ResultSet rs);
}
