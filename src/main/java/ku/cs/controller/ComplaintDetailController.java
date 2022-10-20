package ku.cs.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ku.cs.model.Complaint;
import ku.cs.model.ComplaintList;
import ku.cs.service.ComplaintListFileDataSource;
import ku.cs.service.DataSource;

import java.io.IOException;

public class ComplaintDetailController {

    private Complaint complaint;
    
    private ComplaintList complaintList;
    
    private DataSource<ComplaintList> dataSource;
    
    @FXML
    private TableView<Complaint> complaintTableView;

    public void initialize(){
        readData();
        showComplaintTableView();
        
    }
    private void showComplaintTableView() {

        complaintTableView.getColumns().clear();

        ObservableList<Complaint> data = FXCollections.observableArrayList();
        for (Complaint complaint : this.complaintList.getComplaintsList()) {
            data.add(complaint);
        }
        TableColumn<Complaint, String> nameColumn = new TableColumn<>("ชื่อ");
        nameColumn.setCellValueFactory(cellData -> {
            Complaint complaint = cellData.getValue();
            return new ReadOnlyStringWrapper(complaint.getHeadLineName());
        });
        complaintTableView.getColumns().add(nameColumn);

        TableColumn<Complaint, String> categoryColumn = new TableColumn<>("หมวดหมู่");
        categoryColumn.setCellValueFactory(cellData -> {
            Complaint complaint = cellData.getValue();
            return new ReadOnlyStringWrapper(complaint.getCategory());
        });
        complaintTableView.getColumns().add(categoryColumn);

        TableColumn<Complaint, String> scoreVoteColumn = new TableColumn<>("คะแนน(Vote)");
        scoreVoteColumn.setCellValueFactory(cellData -> {
            Complaint complaint = cellData.getValue();
            return new ReadOnlyStringWrapper("" + complaint.getScoreVote());
        });
        complaintTableView.getColumns().add(scoreVoteColumn);

//        TableColumn<Complaint, String> organizationNameColumn = new TableColumn<>("หน่วบงานที่รับผิดชอบ");
//        organizationNameColumn.setCellValueFactory(cellData -> {
//            Complaint complaint  = cellData.getValue();
//            return new ReadOnlyStringWrapper(complaint.getOrganizationName());
//        });
//        complaintTableView.getColumns().add(organizationNameColumn);

//        TableColumn<Complaint, String> dateColumn = new TableColumn<>("วัน/เดือน/ปี");
//        dateColumn.setCellValueFactory(cellData -> {
//            Complaint complaint = cellData.getValue();
//            return new ReadOnlyStringWrapper("" + complaint.getLastLogin());
//        });
//        complaintTableView.getColumns().add(dateColumn);

        complaintTableView.setItems(data);
    }

    private void readData() {
        dataSource = new ComplaintListFileDataSource();
        complaintList = dataSource.readData();
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("menu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า menu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
    public void handleComplaintButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("complaint_add");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า complaint ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
