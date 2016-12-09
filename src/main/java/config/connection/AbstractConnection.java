package config.connection;

/**
 * Created by click on 11/14/2016.
 */
public interface AbstractConnection extends AutoCloseable {
    public Object getConnection();
    public void beginTransaction();
    public void rollback();
    public void commit();
    public void close();
}
