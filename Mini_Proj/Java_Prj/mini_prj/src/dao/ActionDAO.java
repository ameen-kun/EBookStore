package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import model.Action;
import utility.DatabaseUtil;

public class ActionDAO {
    public void createAction(Action action){
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO actions(action_type,affecting,performed_on,changes) VALUES (?, ?, ?, ?)");
                stmt.setString(1, action.getAction_type());
                stmt.setInt(2, action.getAffecting());
                stmt.setTimestamp(3,Timestamp.valueOf(action.getPerformed_on()));
                stmt.setString(4, action.getChange());
                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Action> getActions(){
        List<Action> allActions=new LinkedList<>();
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM actions");
            ResultSet res=stmt.executeQuery();
            while(res.next()){
                allActions.add(new Action(res.getInt("action_id"), res.getString("action_type"), res.getInt("affecting"), res.getString("changes"), res.getTimestamp("performed_on").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allActions;
    }

    public List<Action> getTimeline(int id){
        List<Action> filteredActions=new LinkedList<>();
        try{
            Connection connection=DatabaseUtil.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM actions WHERE affecting=?");
            stmt.setInt(1, id);
            ResultSet res=stmt.executeQuery();
            while(res.next()){
                filteredActions.add(new Action(res.getInt("action_id"), res.getString("action_type"), res.getInt("affecting"), res.getString("changes"), res.getTimestamp("performed_on").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredActions;
    }
}
