package ku.cs.service;

import ku.cs.model.Complaint;
import ku.cs.model.ComplaintList;


public class ComplaintRecord {

    public String recordComplaint(String headLineName, String detail, String category) {

        //ใช้ตรวจสอบข้อความที่กรอกเข้ามา
        if (headLineName.equals("") || detail.equals("")) {
            return "ยังไม่ได้กรอก AccountName หรือ UserName ";
        } else if (category.equals("") ) {
            return "กรุณาเลือก หมวดหมู่ ";
        }

        Complaint complaint = new Complaint(headLineName, detail,category , 0);

        DataSource<ComplaintList> dataSource;
        dataSource = new ComplaintListFileDataSource();
        ComplaintList complaintList = dataSource.readData();

        complaintList.addComplaint(complaint);

        dataSource.writeData(complaintList);

        return "P";
    }
}
