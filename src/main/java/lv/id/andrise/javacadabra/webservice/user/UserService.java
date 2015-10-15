package lv.id.andrise.javacadabra.webservice.user;

import lv.id.andrise.javacadabra.webservice.user.password.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by andris on 15.15.10.
 */

@Service
public class UserService {
    @Autowired
    private HashPassword hashPassword;

    public Boolean createNewUser(User user) {
        user.setSalt();
        return addNewUserRecord(user);
    }

    public User findUserById(Long id) {
        User user = new User();
        return user;
    }

    public User findUserByUserName(String userName) {
        User user = new User();
        return user;
    }

    private Boolean addNewUserRecord(User user) {
        File file = new File("user-db.csv");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file, true);
            user.setUserPassword(hashPassword.hashPassword(user));
            fileWriter.write(user.toCsv());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
