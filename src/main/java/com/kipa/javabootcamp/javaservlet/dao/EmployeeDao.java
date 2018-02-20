package com.kipa.javabootcamp.javaservlet.dao;

import com.kipa.javabootcamp.javaservlet.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private static String _TABLE = "employee";

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public EmployeeDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException();
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO " + _TABLE + " (" +
                "employee_code, " +
                "employee_username, " +
                "employee_password, " +
                "employee_name, " +
                "employee_address, " +
                "employee_phone, " +
                "employee_grade, " +
                "employee_stream" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, employee.getCode());
        statement.setString(2, employee.getUsername());
        statement.setString(3, employee.getPassword());
        statement.setString(4, employee.getName());
        statement.setString(5, employee.getAddress());
        statement.setString(6, employee.getPhone());
        statement.setString(7, employee.getGrade());
        statement.setString(8, employee.getStream());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Employee> listAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<Employee>();

        String sql = "SELECT * FROM " + _TABLE;

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Integer id = resultSet.getInt("employee_id");
            String code = resultSet.getString("employee_code");
            String username = resultSet.getString("employee_username");
            String password = resultSet.getString("employee_password");
            String name = resultSet.getString("employee_name");
            String address = resultSet.getString("employee_address");
            String phone = resultSet.getString("employee_phone");
            String grade = resultSet.getString("employee_grade");
            String stream = resultSet.getString("employee_stream");

            Employee employee = new Employee(id, code, username, password, name, address, phone, grade, stream);
            employees.add(employee);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return employees;
    }

    public boolean deleteEmployee(Employee employee) throws SQLException {
        String sql = "DELETE FROM "+ _TABLE +" where employee_id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, employee.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE "+ _TABLE +" SET " +
                "employee_code = ?, " +
                "employee_username = ?, " +
                "employee_password = ?, " +
                "employee_name = ?, " +
                "employee_address = ?, " +
                "employee_phone = ?, " +
                "employee_grade = ?, " +
                "employee_stream = ?";

        sql += " WHERE employee_id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, employee.getCode());
        statement.setString(2, employee.getUsername());
        statement.setString(3, employee.getPassword());
        statement.setString(4, employee.getName());
        statement.setString(5, employee.getAddress());
        statement.setString(6, employee.getPhone());
        statement.setString(7, employee.getGrade());
        statement.setString(8, employee.getStream());
        statement.setInt(9, employee.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Employee loginEmployee(String reqUsername, String reqPassword) throws SQLException {
        Employee employee = null;
        String sql = "SELECT * FROM "+ _TABLE +" WHERE employee_username = ? AND employee_password = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, reqUsername);
        statement.setString(2, reqPassword);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Integer id = resultSet.getInt("employee_id");
            String code = resultSet.getString("employee_code");
            String username = resultSet.getString("employee_username");
            String password = resultSet.getString("employee_password");
            String name = resultSet.getString("employee_name");
            String address = resultSet.getString("employee_address");
            String phone = resultSet.getString("employee_phone");
            String grade = resultSet.getString("employee_grade");
            String stream = resultSet.getString("employee_stream");

            employee = new Employee(id, code, username, password, name, address, phone, grade, stream);
        }

        return employee;
    }

    public Employee getEmployee(Integer id) throws SQLException {
        Employee employee = null;
        String sql = "SELECT * FROM "+ _TABLE +" WHERE employee_id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String code = resultSet.getString("employee_code");
            String username = resultSet.getString("employee_username");
            String password = resultSet.getString("employee_password");
            String name = resultSet.getString("employee_name");
            String address = resultSet.getString("employee_address");
            String phone = resultSet.getString("employee_phone");
            String grade = resultSet.getString("employee_grade");
            String stream = resultSet.getString("employee_stream");

            employee = new Employee(id, code, username, password, name, address, phone, grade, stream);
        }

        resultSet.close();
        statement.close();

        return employee;
    }
}
