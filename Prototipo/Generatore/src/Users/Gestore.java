package Users;

public class Gestore extends Utente {
    private String IDZona;

    public Gestore(String username, String password, int livelloDettaglio, String IDZona) {
        super(username, password, livelloDettaglio);
        this.IDZona = IDZona;
    }

    public String getIDZona() {
        return IDZona;
    }

    public void setIDZona(String IDZona) {
        this.IDZona = IDZona;
    }

}
