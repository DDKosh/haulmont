package org.testTask.DAO;

import org.testTask.DTO.AbstractDTO;
import org.testTask.DTO.Author;
import org.testTask.DTO.Genre;
import org.testTask.DTO.Publisher;

import java.sql.*;

/**
 * The type Publisher dao.
 */
public class PublisherDAO extends AbstractDAO<Publisher> {
    private static final int ID = 1;

    private static final int NAME = 2;

    @Override
    protected Publisher getObject(ResultSet resultSet) throws SQLException {
        for (Publisher publisher : Publisher.values()) {
            if (publisher.getName().equals(resultSet.getString(NAME))){
                return publisher;
            }
        }
        return null;
       // return Publisher.valueOf(resultSet.getString(NAME).);
    }

    @Override
    protected ResultSet getRsAll(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM PUBLISHER");
    }

    @Override
    protected PreparedStatement getRsById(Connection connection, long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM PUBLISHER WHERE ID = ?");
            statement.setLong(ID, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsUpdate(
            Connection connection, Publisher object, long id) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsDelete(
            Connection connection, long id) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsAdd(
            Connection connection, Publisher object) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsByTitle(
            Connection connection, String title) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsByAuthor(
            Connection connection, Author object) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsByPublisher(
            Connection connection, Publisher object) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement getPsByGenre(
            Connection connection, Genre object) throws SQLException {
        return null;
    }
}
