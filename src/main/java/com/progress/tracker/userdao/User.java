package main.java.com.progress.tracker.userdao;

public class User 
{
    int userId;
    String name;
    String userName;
    String password;
    
     public User(int userId, String name, String userName, String password)
     {
        this.userId = userId;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

     //Id Methods.
   public int getuserId()
   {
      return userId;
   }

   public void setuserId(int userId)
   {
      this.userId = userId;
   }

   //Name Methods.
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   //Username Methods
   public String getuserName()
   {
      return userName;
   }

   public void setuserName(String userName)
   {
      this.userName = userName;
   }

   //Password Methods.
   public String getPassword()
   {
      return password;
   } 

   public void setPassword(String password)
   {
      if (password.length() < 3 || password.length() > 10)
      {
         throw new IllegalArgumentException("Password must be between 3 and 10 characters.");
      }
      this.password = password;
   }

   //ToString Method.
   @Override
   public String toString()
   {
      return "User [userId=" + userId + ", name=" + name + ", userName=" + userName
      + ", password=" + password + "]";
   }

}
