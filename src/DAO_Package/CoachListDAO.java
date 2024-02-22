package DAO_Package;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import java.awt.Dimension;

public class CoachListDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static JScrollPane getCoachList() {
        JTable coachTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(coachTable);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM coach";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            Vector<Vector<Object>> data = new Vector<>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getObject(i));
                }
                data.add(row);
            }

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            coachTable.setModel(tableModel);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de la récupération des coachs : " + ex.getMessage());
        }

        return scrollPane;
    }
}
