package ku.cs.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ComplaintList {

    private ArrayList<Complaint> complaintList;

    public ComplaintList(){complaintList = new ArrayList<>();}

    public void addComplaint(Complaint complaint){
        complaintList.add(complaint);
    }
    public ArrayList<Complaint> getComplaintsList() {
        return complaintList;
    }

    public void sort(Comparator<Complaint> complaintComparator) {
        Collections.sort(complaintList, complaintComparator);
    }
    public String toCsv(){
        String result = "";
        for (Complaint complaint: this.complaintList){
            result += complaint.toCsv() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "ComplanitList{" +
                "complaints=" + complaintList +
                '}';
    }
}
