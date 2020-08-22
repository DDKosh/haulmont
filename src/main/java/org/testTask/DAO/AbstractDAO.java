package org.testTask.DAO;

import org.testTask.DTO.AbstractDTO;
import org.testTask.DTO.Author;
import org.testTask.DTO.Genre;
import org.testTask.DTO.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<E> {
    private final JDBC jdbc = JDBC.getJdbc();

    protected abstract E getObject(ResultSet resultSet) throws SQLException;

    protected abstract ResultSet getRsAll(Statement statement) throws SQLException;

    protected abstract PreparedStatement getRsById(Connection connection, long id) throws SQLException;

    protected abstract PreparedStatement getPsUpdate(Connection connection,E object, long id) throws SQLException;

    protected abstract PreparedStatement getPsDelete(Connection connection, long id) throws SQLException;

    protected abstract PreparedStatement getPsAdd(Connection connection, E object) throws SQLException;

    protected abstract PreparedStatement getPsByTitle(Connection connection, String title) throws SQLException;

    protected abstract PreparedStatement getPsByAuthor(Connection connection, Author object) throws SQLException;

    protected abstract PreparedStatement getPsByPublisher(Connection connection, Publisher object) throws SQLException;

    protected abstract PreparedStatement getPsByGenre(Connection connection, Genre object) throws SQLException;

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbc.getUrl(), jdbc.getUSER(), jdbc.getPASSWORD());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<E> getAll() {
        List<E> list = new ArrayList<>();
        try(Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = getRsAll(statement);
            while (resultSet.next())
                list.add(getObject(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<E> getAllByAuthor(Author object) {
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

    public List<E> getAllByPublisher(Publisher object) {
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

    public List<E> getAllByGenre(Genre object) {
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

    public List<E> getAllByTitle(String title) {
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

    public E getByID(final long id) throws SQLException {
        E object = null;
        try(PreparedStatement statement = getRsById(getConnection(), id);
            ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            object = getObject(resultSet);
        } catch (SQLException e) {
            System.err.println(e);
        }
        return object;
    }

    public void delete(final long id) throws SQLException {
        try {
            PreparedStatement statement = getPsDelete(getConnection(), id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void add(final E object) throws SQLException {
        try {
            PreparedStatement statement = getPsAdd(getConnection(), object);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

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
