package Windows;

import java.util.ArrayList;

public class USER_INFO {

    ArrayList<String> userInfo = new ArrayList<>();

    public USER_INFO() {
    }

    public USER_INFO(String email, String password) {
        userInfo.add(email);
        userInfo.add(password);
    }

    public ArrayList<String> getInformation(){
        System.out.println(userInfo.get(0) + userInfo.get(1));
        return userInfo;
    }

}
