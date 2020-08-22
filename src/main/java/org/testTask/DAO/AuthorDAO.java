package org.testTask.DAO;

import org.testTask.DTO.AbstractDTO;
import org.testTask.DTO.Author;
import org.testTask.DTO.Genre;
import org.testTask.DTO.Publisher;

import java.sql.*;

public class AuthorDAO extends AbstractDAO<Author> {

    private static final int ONE = 1;

    private static final int TWO = 2;

    private static final int THREE = 3;

    private static final int FOUR = 4;

    @Override
    protected Author getObject(ResultSet resultSet) throws SQLException {
        return new Author(resultSet.getLong("ID"), resultSet.getString("NAME"),
                resultSet.getString("SURNAME"), resultSet.getString("PATRONYMIC"));
    }

    @Override
    protected ResultSet getRsAll(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM AUTHOR");
    }

    @Override
    protected PreparedStatement getRsById(Connection connection, long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM AUTHOR WHERE ID = ?");
            statement.setLong(ONE, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsUpdate(Connection connection, Author object, long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE AUTHOR SET NAME = ?, SURNAME = ?, PATRONYMIC = ? WHERE ID = ?");
            //setValues(statement, object);
            statement.setString(ONE, object.getName());
            statement.setString(THREE, object.getSurname());
            statement.setString(TWO, object.getPatronymic());
            statement.setLong(FOUR, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsDelete(Connection connection, long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "DELETE FROM AUTHOR WHERE ID = ?");
            statement.setLong(ONE, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsAdd(Connection connection, Author object) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO AUTHOR(ID, NAME, PATRONYMIC, SURNAME) VALUES(?, ?, ?, ?)");
            statement.setLong(ONE, object.getId());
            setValues(statement, object);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsByTitle(Connection connection, String title) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsByAuthor(Connection connection, Author object) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsByPublisher(Connection connection, Publisher object) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsByGenre(Connection connection, Genre object) throws SQLException {
        return null;
    }

    private void setValues(final PreparedStatement statement, final Author author) throws SQLException {
        statement.setString(TWO, author.getName());
        statement.setString(FOUR, author.getSurname());
        statement.setString(THREE, author.getPatronymic());
    }
}
