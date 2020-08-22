package org.testTask.DAO;

import org.testTask.DTO.Author;
import org.testTask.DTO.Genre;
import org.testTask.DTO.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract dao.
 *
 * @param <E> the type parameter
 */
public abstract class AbstractDAO<E> {
    /**
     * The jdbc connection
     */
    private final JDBC jdbc = JDBC.getJdbc();

    /**
     * Gets object.
     *
     * @param resultSet the result set
     * @return the object
     * @throws SQLException the sql exception
     */
    protected abstract E getObject(ResultSet resultSet) throws SQLException;

    /**
     * Gets rs all.
     *
     * @param statement the statement
     * @return the rs all
     * @throws SQLException the sql exception
     */
    protected abstract ResultSet getRsAll(Statement statement) throws SQLException;

    /**
     * Gets rs by id.
     *
     * @param connection the connection
     * @param id         the id
     * @return the rs by id
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getRsById(
            Connection connection, long id) throws SQLException;

    /**
     * Gets ps update.
     *
     * @param connection the connection
     * @param object     the object
     * @param id         the id
     * @return the ps update
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsUpdate(
            Connection connection, E object, long id) throws SQLException;

    /**
     * Gets ps delete.
     *
     * @param connection the connection
     * @param id         the id
     * @return the ps delete
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsDelete(
            Connection connection, long id) throws SQLException;

    /**
     * Gets ps add.
     *
     * @param connection the connection
     * @param object     the object
     * @return the ps add
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsAdd(
            Connection connection, E object) throws SQLException;

    /**
     * Gets ps by title.
     *
     * @param connection the connection
     * @param title      the title
     * @return the ps by title
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsByTitle(
            Connection connection, String title) throws SQLException;

    /**
     * Gets ps by author.
     *
     * @param connection the connection
     * @param object     the object
     * @return the ps by author
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsByAuthor(
            Connection connection, Author object) throws SQLException;

    /**
     * Gets ps by publisher.
     *
     * @param connection the connection
     * @param object     the object
     * @return the ps by publisher
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsByPublisher(
            Connection connection, Publisher object) throws SQLException;

    /**
     * Gets ps by genre.
     *
     * @param connection the connection
     * @param object     the object
     * @return the ps by genre
     * @throws SQLException the sql exception
     */
    protected abstract PreparedStatement getPsByGenre(
            Connection connection, Genre object) throws SQLException;

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    jdbc.getUrl(), jdbc.getUSER(), jdbc.getPASSWORD());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<E> getAll() {
        List<E> list = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = getRsAll(statement);
            while (resultSet.next())
                list.add(getObject(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Gets all by author.
     *
     * @param object the object
     * @return the all by author
     */
    public List<E> getAllByAuthor(final Author object) {
        List<E> list = new ArrayList<>();
        try {
            ResultSet resultSet = getPsByAuthor(getConnection(), object).executeQuery();
            while (resultSet.next())
                list.add(getObject(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Gets all by publisher.
     *
     * @param object the object
     * @return the all by publisher
     */
    public List<E> getAllByPublisher(final Publisher object) {
        List<E> list = new ArrayList<>();
        try {
            ResultSet resultSet = getPsByPublisher(getConnection(), object).executeQuery();
            while (resultSet.next())
                list.add(getObject(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Gets all by genre.
     *
     * @param object the object
     * @return the all by genre
     */
    public List<E> getAllByGenre(final Genre object) {
        List<E> list = new ArrayList<>();
        try {
            ResultSet resultSet = getPsByGenre(getConnection(), object).executeQuery();
            while (resultSet.next())
                list.add(getObject(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Gets all by title.
     *
     * @param title the title
     * @return the all by title
     */
    public List<E> getAllByTitle(final String title) {
        List<E> list = new ArrayList<>();
        try {
            ResultSet resultSet = getPsByTitle(getConnection(), title).executeQuery();
            while (resultSet.next())
                list.add(getObject(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws SQLException the sql exception
     */
    public E getByID(final long id) throws SQLException {
        E object = null;
        try (PreparedStatement statement = getRsById(getConnection(), id);
            ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            object = getObject(resultSet);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return object;
    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws SQLException the sql exception
     */
    public void delete(final long id) throws SQLException {
        try {
            PreparedStatement statement = getPsDelete(getConnection(), id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Add.
     *
     * @param object the object
     * @throws SQLException the sql exception
     */
    public void add(final E object) throws SQLException {
        try {
            PreparedStatement statement = getPsAdd(getConnection(), object);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     * Update.
     *
     * @param object the object
     * @param id     the id
     * @throws SQLException the sql exception
     */
    public void update(final E object, final long id) throws SQLException {
        try {
            PreparedStatement statement =
                    getPsUpdate(getConnection(), object, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
