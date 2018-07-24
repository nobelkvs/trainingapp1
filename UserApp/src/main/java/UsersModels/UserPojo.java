package UsersModels;

import UsersController.UserController;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Map;


/**
 * Userpojo is a model class where it has all the user properties of user
 * Here it is using Lomback plugin to avoid writing setters and getters methods
 */
@Getter
@Setter
@ToString
public class UserPojo {
    static final Logger log = Logger.getLogger(UserController.class);

    private int userId;
    private String userName;
    private String password;
    private String userEmailAddress;
    private String userRole;
    private String userPhoneNumber;
    private LocalDate userCreationDate;

}
