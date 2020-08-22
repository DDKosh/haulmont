package org.testTask.DAO;

import org.testTask.DTO.AbstractDTO;
import org.testTask.DTO.Author;
import org.testTask.DTO.Genre;
import org.testTask.DTO.Publisher;

import java.sql.*;

public class GenreDAO extends AbstractDAO<Genre> {
    private static final int ONE = 1;

    private static final int TWO = 2;

    @Override
    protected Genre getObject(ResultSet resultSet) throws SQLException {
        return new Genre(resultSet.getLong(ONE), resultSet.getString(TWO));
    }

    @Override
    protected ResultSet getRsAll(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM GENRE");
    }

    @Override
    protected PreparedStatement getRsById(Connection connection, long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM GENRE WHERE ID = ?");
            statement.setLong(ONE, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsUpdate(Connection connection, Genre object, long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE GENRE SET NAME = ? WHERE ID = ?");
            statement.setString(ONE, object.getTitle());
            statement.setLong(TWO, id);
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
                    "DELETE FROM GENRE WHERE ID = ?");
            statement.setLong(ONE, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsAdd(Connection connection, Genre object) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO GENRE(ID, NAME) VALUES(?,?)");
            statement.setLong(ONE,object.getId());
            statement.setString(TWO, object.getTitle());
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

}
