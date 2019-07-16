package sg.edu.np.s10177991h.madexercise7;

public class Account {
    private String username;
    private String password;

    public Account() {}

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Account (String usr, String pwd)
    {
        username = usr;
        password = pwd;

    }
}
