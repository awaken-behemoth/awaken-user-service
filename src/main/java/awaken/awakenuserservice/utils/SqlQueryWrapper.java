package awaken.awakenuserservice.utils;

import org.hibernate.JDBCException;

import java.util.function.Supplier;

public class SqlQueryWrapper {
  public  static <T> T excecute(Supplier<T> supplier) throws JDBCException {
    T value;
    try {
      value = supplier.get();
    }catch (RuntimeException exception){
      if (exception.getCause() instanceof JDBCException) throw (JDBCException) exception.getCause();
      else throw exception;
    }

    return value;
  }
}
