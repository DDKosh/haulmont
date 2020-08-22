package org.testTask.DAO;

import org.testTask.DTO.*;

import java.sql.*;

public class BookDAO extends AbstractDAO<Book> {
    private static final int ONE = 1;

    private static final int TWO = 2;

    private static final int THREE = 3;

    private static final int FOUR = 4;

    private static final int FIVE = 5;

    private static final int SIX = 6;

    private static final int SEVEN = 7;

    @Override
    protected Book getObject(ResultSet resultSet) throws SQLException {
        Author author = new AuthorDAO().getByID(resultSet.getLong(THREE));
        Genre genre = new GenreDAO().getByID(resultSet.getLong(FOUR));
        Publisher publisher = new PublisherDAO().getByID(resultSet.getLong(FIVE));
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(resultSet.getDate(YEAR));
        return new Book(resultSet.getLong(ONE), resultSet.getString(TWO),
                author, genre, publisher, resultSet.getInt(SIX),
                resultSet.getString(SEVEN));
    }

    @Override
    protected ResultSet getRsAll(Statement statement) throws SQLException {
        return statement.executeQuery("SELECT * FROM BOOK");
    }

    @Override
    protected PreparedStatement getRsById(Connection connection, long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM BOOK WHERE ID = ?");
            statement.setLong(ONE, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsUpdate(Connection connection, Book object, long id) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "UPDATE BOOK SET TITLE = ?, AUTHOR = ?, GENRE = ?, PUBLISHER = ?, YEAR = ?, CITY = ? WHERE ID = ?");
            //setValues(statement, object);
            statement.setString(ONE, object.getTitle());
            statement.setLong(TWO, object.getAuthor().getId());
            statement.setLong(THREE, object.getGenre().getId());
            statement.setLong(FOUR, object.getPublisher().getId());
            statement.setInt(FIVE, object.getYear());
            statement.setString(SIX, object.getCity());
            statement.setLong(SEVEN, id);
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
                    "DELETE FROM BOOK WHERE ID = ?");
            statement.setLong(ONE, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsAdd(Connection connection, Book object) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO BOOK(ID, TITLE, AUTHOR, GENRE, PUBLISHER, YEAR, CITY) VALUES(?, ?, ?, ?, ?, ?, ?)");
            setValues(statement, object);
            statement.setLong(ONE,object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsByAuthor(Connection connection, Author object) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM BOOK WHERE AUTHOR = ?");
            statement.setLong(ONE,object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsByPublisher(Connection connection, Publisher object) throws SQLException {
        PreparedStatement statement = null;
        try {
                statement = connection.prepareStatement("SELECT * FROM BOOK WHERE PUBLISHER = ?");
            statement.setLong(ONE,object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsByGenre(Connection connection, Genre object) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM BOOK WHERE GENRE = ?");
            statement.setLong(ONE,object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement getPsByTitle(Connection connection, String title) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM BOOK WHERE TITLE = ?");
            statement.setString(ONE,title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    private void setValues(final PreparedStatement statement, final Book book) throws SQLException {
        statement.setString(TWO, book.getTitle());
        statement.setLong(THREE, book.getAuthor().getId());
        statement.setLong(FOUR, book.getGenre().getId());
        statement.setLong(FIVE, book.getPublisher().getId());
        statement.setInt(SIX, book.getYear());
        statement.setString(SEVEN, book.getCity());
    }
}
