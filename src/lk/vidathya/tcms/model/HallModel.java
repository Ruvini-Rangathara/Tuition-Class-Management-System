package lk.vidathya.tcms.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.vidathya.tcms.transferObject.Hall;
import lk.vidathya.tcms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HallModel {

    public static String getLastHallNo() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT hallNo FROM Hall ORDER BY hallNo DESC LIMIT 1");
        if(result.next()){
            return result.getString("hallNo");
        }
        return null;
    }

    public static ObservableList<String> loadHallNoToComboBox() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT hallNo FROM Hall ORDER BY hallNo ASC");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (result.next()) {
            options.add(result.getString("hallNo"));
        }
        return options;
    }

    public static int getHallCount() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT hallNo FROM Hall");
        int count=0;
        while(result.next()){
            count++;
        }
        return count;
    }

    public static boolean addHall(Hall hall) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO Hall VALUES (?,?,?)",
                hall.getHallNo(),
                hall.getCapacity(),
                hall.getAvailableFacilities()
                );
        return isAdd;
    }

    public static Hall getDetails(String hallNo) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Hall WHERE hallNo=?", hallNo);
        if(result.next()){
            Hall hall = new Hall(
                    result.getString("hallNo"),
                    result.getString("capacity"),
                    result.getString("availableFacilities")
            );
            return hall;
        }
        return null;
    }

    public static boolean updateHallDetails(Hall hall) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE Hall SET capacity=?, availableFacilities=? WHERE hallNo=?",
                hall.getCapacity(),
                hall.getAvailableFacilities(),
                hall.getHallNo()
                );
        return isUpdate;
    }

    public static boolean deleteHall(String hallNo) throws SQLException, ClassNotFoundException {
        boolean isDelete = CrudUtil.execute("DELETE FROM Hall WHERE hallNo=?",hallNo );
        return isDelete;
    }

    public static ResultSet getAllData() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Hall");
        return result;
    }


}
