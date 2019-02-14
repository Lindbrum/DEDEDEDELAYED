package Users;

public abstract class Utente {
    private String username;
    private String password;
    private int livelloDettaglio;

    public Utente() {
    }

    public Utente(String username, String password, int livelloDettaglio) {
        this.username = username;
        this.password = password;
        this.livelloDettaglio = livelloDettaglio;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLivelloDettaglio() {
        return livelloDettaglio;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLivelloDettaglio(int livelloDettaglio) {
        this.livelloDettaglio = livelloDettaglio;
    }

}
