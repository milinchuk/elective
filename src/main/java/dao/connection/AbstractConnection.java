package dao.connection;

/**
 * This Abstract connection interface provide operations for any exactly connection to database
 *
 * @author Anastasia Milinchuk
 */
public interface AbstractConnection extends AutoCloseable {
    /**
     * Method for obtaining connection
     *
     * @return directly connection to some database
     */
    public Object getConnection();

    /**
     * Method for begin transaction. Must provides correct begin transaction.
     */
    public void beginTransaction();

    /**
     * Rollback method must provides rollback in case of some problems.
     */
    public void rollback();

    /**
     * Commit method must provides commit.
     */
    public void commit();

    /**
     * Close of connection
     */
    public void close();
}
