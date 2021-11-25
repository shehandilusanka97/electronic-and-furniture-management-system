package dto;

public class LoginDTO {
    private String UserName;
    private String Password;
    private String Roll;

    public LoginDTO() {
    }

    public LoginDTO(String userName, String password, String roll) {
        UserName = userName;
        Password = password;
        Roll = roll;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRoll() {
        return Roll;
    }

    public void setRoll(String roll) {
        Roll = roll;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", Roll='" + Roll + '\'' +
                '}';
    }
}
