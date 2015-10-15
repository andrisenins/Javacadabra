package lv.id.andrise.javacadabra.webservice.user;

import org.joda.time.DateTime;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by andris on 15.15.10.
 */
public class User {

    private Long id;

    private String userName;

    private String userRole;

    private String userPassword;

    private String salt;

    private DateTime createDate;

    private DateTime lastAccessDate;

    public User(Long id, String userName, String userRole, String userPassword, String salt, DateTime createDate, DateTime lastAccessDate) {
        this.id = id;
        this.userName = userName;
        this.userRole = userRole;
        this.userPassword = userPassword;
        this.salt = salt;
        this.createDate = createDate;
        this.lastAccessDate = lastAccessDate;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt() {
        this.salt = createSalt();
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public DateTime getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(DateTime lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    private String createSalt() {
        Random random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return Arrays.toString(salt);
    }

    public String toCsv() {
        String separator = ";";
        return getId() + separator + getUserName() + separator + getUserRole() + separator + getUserPassword() + separator + getSalt() + separator + getCreateDate() +separator + getLastAccessDate() + "\n";
    }
}