package ku.cs.service;

import ku.cs.model.*;

import java.io.*;
import java.time.LocalDateTime;

public class ComplaintListFileDataSource implements DataSource<ComplaintList> {

    private String directoryName;
    private String filename;

    public ComplaintListFileDataSource(){
        this("file_csv","complaint.csv");
    }
    public ComplaintListFileDataSource(String directoryName,String filename){
        this.directoryName = directoryName;
        this.filename = filename;
        initialFileIfNotExist();
    }
    //ถ้าไฟล์หายจะสร้างใหม่
    private void initialFileIfNotExist(){
        File file = new File(directoryName);
        if (!file.exists()){
            file.mkdir();
        }

        String path = directoryName + File.separator + filename;
        file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ComplaintList readData() {
        ComplaintList complaintList = new ComplaintList();
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileReader reader = null;
        BufferedReader buffer = null;
        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while( (line = buffer.readLine()) != null){
                String [] data = line.split(",");
                complaintList.addComplaint(new Complaint(data[1],
                        data[2],
                        data[3],
                        LocalDateTime.parse(data[4]),
                        Integer.parseInt(data[5])));
            }
            buffer.close();
            reader.close();

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return complaintList;
    }


    @Override
    public void writeData(ComplaintList complaintList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(complaintList.toCsv());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
