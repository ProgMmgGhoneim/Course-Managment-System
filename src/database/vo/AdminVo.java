
package database.vo;


public class AdminVo {
   private String FName;
   private String SName;
   private String LName;
   private String UserName;
   private String password;
   private String Repassword;
   private String mobilreOrEmail;
/////////////////related to log in    
   private String CheckUserName;
   private String CheckPass;

    public String getCheckUserName() {
        return CheckUserName;
    }

    public void setCheckUserName(String CheckUserName) {
        this.CheckUserName = CheckUserName;
    }

    public String getCheckPass() {
        return CheckPass;
    }

    public void setCheckPass(String CheckPass) {
        this.CheckPass = CheckPass;
    }
   
   

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return Repassword;
    }

    public void setRepassword(String Repassword) {
        this.Repassword = Repassword;
    }

    public String getMobilreOrEmail() {
        return mobilreOrEmail;
    }

    public void setMobilreOrEmail(String mobilreOrEmail) {
        this.mobilreOrEmail = mobilreOrEmail;
    }
   
   
}
