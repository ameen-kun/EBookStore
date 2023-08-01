package model;
import java.time.LocalDateTime;

public class Action extends Model{
    private int action_id;
    private String action_type;
    private int affecting;
    private String change;
    private LocalDateTime performed_on;

    public Action(int action_id, String action_type, int affecting, String change, LocalDateTime performed_on) {
        this.action_id = action_id;
        this.action_type = action_type;
        this.affecting = affecting;
        this.change = change;
        this.performed_on = performed_on;
    }
    public Action(String action_type, int affecting, String change, LocalDateTime performed_on) {
        this.action_type = action_type;
        this.affecting = affecting;
        this.change = change;
        this.performed_on = performed_on;
    }
    public int getAction_id() {
        return action_id;
    }
    public void setAction_id(int action_id) {
        this.action_id = action_id;
    }
    public String getAction_type() {
        return action_type;
    }
    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }
    public int getAffecting() {
        return affecting;
    }
    public void setAffecting(int affecting) {
        this.affecting = affecting;
    }
    public String getChange() {
        return change;
    }
    public void setChange(String change) {
        this.change = change;
    }
    public LocalDateTime getPerformed_on() {
        return performed_on;
    }
    public void setPerformed_on(LocalDateTime performed_on) {
        this.performed_on = performed_on;
    }
    
    public String toString(){
        return "******\nACTION DETAILS :\nID: "+action_id+"\nType: "+action_type+"\nAffecting: "+affecting+"\nPerformed On: "+performed_on.toString()+"\nChanges: "+change+"\n";
    }
}
