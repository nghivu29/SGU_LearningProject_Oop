package test.manager;

import test.data.Book;
import test.data.Request;
import test.data.Tracking;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerTracking extends ManagerLibAction<Tracking> {

    /**
     * phương thức chưa hoàn thành
     * làm tương tự phương thức của lớp bố nó
     * @param name
     * @return một đối tượng ManagerTracking mới quản lý
     */
    ManagerTracking getTrackingByBookName(String name){
        return null;
    }

    /**
     * phương thức chưa hoàn thành
     * làm tương tự phương thức của lớp bố nó
     * @param date
     * @return một đối tượng ManagerTracking mới quản lý
     */
    ManagerTracking getTrackingByDateBorrow(LocalDate date) {
        return null;
    }

    /**
     * phương thức chưa hoàn thành
     * nhận sách vào đồng thời thay đổi các trạng thái của sách để người sau có thể mượn tiếp
     * @param request
     * @return false nếu sách bị hư hỏng, ... có thể bổ sung điều kiện
     */
    boolean receiveBook(Request request){
        return false;
    }

    boolean deliverBook(Request request) {

        return false;
    }

    public Tracking getTrackingByBookId(String id){
        Tracking tracking = null;
         try {
             tracking = list.stream().filter(t -> t.getBook().getId().equals(id))
                     .findFirst()
                     .get();
         }catch (Exception e){
             System.out.println("system: Kiểm tra lại mã sách");
         }
         return tracking;
    }

    @Override
    public boolean saveData() {
        try{
            FileOutputStream writeData = new FileOutputStream("data_tracking.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(list);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean loadData() {
        List<Tracking> listTracking = new ArrayList<>();

        try{
            FileInputStream readData = new FileInputStream("data_tracking.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            listTracking = (ArrayList<Tracking>) readStream.readObject();
            readStream.close();
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("system: Data rỗng. Bắt đầu tạo mới");
            new ManagerTracking().saveData();
            return false;
        }
        list = listTracking;

        return true;
    }
}
