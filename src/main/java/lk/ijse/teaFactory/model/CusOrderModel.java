package lk.ijse.teaFactory.model;

import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.CusOrderDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CusOrderModel {
/*
    public boolean cusOrdersSaved (CusOrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?, ?, ?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getCId());
        pstm.setString(3, dto.getCatagary());
        pstm.setString(4, dto.getWeigth());
        pstm.setString(5, dto.getDate());
        pstm.setString(6, dto.getDescreption());
        pstm.setDouble(7,dto.getPayment());
        pstm.setString(8,"0");

        boolean isSaved = pstm.executeUpdate() >0;


        return isSaved;
    }

 */







    public List<CusOrderDto> loadAll() throws SQLException {
        Connection connection =DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM orders";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<CusOrderDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String cId = resultSet.getString(2);
            String catagary = resultSet.getString(3);
            String weigth = resultSet.getString(4);
            String date = resultSet.getString(5);
            String descreption = resultSet.getString(6);
            double payment = resultSet.getDouble(7);
            String isCompleted = resultSet.getString(8);

            var dto = new CusOrderDto(id,cId,catagary,weigth,date,descreption,payment,isCompleted);
            dtoList.add(dto);
        }
        return dtoList;
    }



    public static boolean deleteItem(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM orders WHERE order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

/*
    public boolean update (final CusOrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE customer SET cus_id = ?, o_catogary = ?, o_weigth, o_date = ?, descrreption = ?,payment = ?, isCompleted = ? WHERE order_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCId());
        pstm.setString(2, dto.getCatagary());
        pstm.setString(3, dto.getWeigth());
        pstm.setString(4, dto.getDate());
        pstm.setString(5, dto.getDescreption());
        pstm.setDouble(6,dto.getPayment());
        pstm.setString(7, "0");
        pstm.setString(8, dto.getId());


        return pstm.executeUpdate() > 0;
    }

 */






    public static String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentCusId = null;

        if (resultSet.next()) {
            currentCusId = resultSet.getString(1);
            return splitOrderId(currentCusId);
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentCusId) {    //O008
        if (currentCusId != null) {
            String[] split = currentCusId.split("O");
            int id = Integer.parseInt(split[1]);    //008
            id++;  //9
            return "O00" + id;
        }
        return "O001";
    }

    public boolean saveOrder(String orderId, String customerId, String catagary, double weigth, LocalDate date, String descreption, String payment) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?, ?, ?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, orderId);
            pstm.setString(2, customerId);
            pstm.setString(3, catagary);
            pstm.setDouble( 4, weigth);
            pstm.setString(5, String.valueOf(date));
            pstm.setString(6, descreption);
            pstm.setString(7, payment);

        return pstm.executeUpdate() > 0;
    }

    public int cusCount() throws SQLException {
        int rowCount = 0;
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT COUNT(order_id) AS row_count FROM orders";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                rowCount = resultSet.getInt("row_count");
                System.out.println("Number of rows: " + rowCount);
            }
        }
        return rowCount;
    }


}
