package lv.id.andrise.javacadabra.webservice.user.password;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import lv.id.andrise.javacadabra.webservice.user.User;
import org.springframework.stereotype.Service;

/**
 * Created by andris on 15.15.10.
 */
@Service
public class HashPassword {

    public String hashPassword(User user) {
        return Hashing.sha256().hashString(user.getUserPassword() + user.getSalt(), Charsets.UTF_8).toString();
    }
}
