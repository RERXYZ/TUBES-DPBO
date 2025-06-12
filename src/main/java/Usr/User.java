package Usr;

public class User {
	private String username, gender, noTelp, password;
    private int umur, access;

    public User(String username, String gender, String noTelp, int umur, int access, String password) {
        this.username = username;
        this.gender = gender;
        this.noTelp = noTelp;
        this.umur = umur;
        this.access = access;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public int getUmur() {
        return umur;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public int getAccess() {
		return access;
	}
}
