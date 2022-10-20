package ku.cs.service;

import ku.cs.model.Admin;
import ku.cs.model.Officer;
import ku.cs.model.User;
import ku.cs.model.UserList;

import java.io.*;
import java.time.LocalDateTime;

public class UserListFileDataSource implements DataSource<UserList>{

    // การทำงาน ใน ส่วนของ Userb อ่านและเขียนไฟล์
    private String directoryName;
    private String filename;

    public UserListFileDataSource(){
        this("file_csv","user.csv");
    }

    public UserListFileDataSource(String directoryName,String filename){
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
    public UserList readData() {
        UserList userList = new UserList();
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
                String role = data[0];
                if (role.equals("User")){
                    userList.addUser(new User(data[1],
                            data[2],
                            data[3],
                            data[4],
                            LocalDateTime.parse(data[5]),
                            Integer.parseInt(data[6])));
                }
                else if(role.equals("Officer")){
                    userList.addUser(new Officer(data[1],
                            data[2],
                            data[3],
                            data[4],
                            LocalDateTime.parse(data[5]),
                            data[6]));
                }
                else if(role.equals("Admin")){
                    userList.addUser(new Admin(data[1],
                            data[2],
                            data[3],
                            data[4],
                            LocalDateTime.parse(data[5])));
                }
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
        return userList;
    }

    @Override
    public void writeData(UserList userList) {
        String path = directoryName + File.separator + filename;
        File file = new File(path);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            buffer.write(userList.toCsv());
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
