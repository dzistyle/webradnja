package classPaket;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Korisnik")
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="balance")
    private int balance;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int getBalance() {return balance;}
    public void setBalance(int balance) {this.balance = balance;}
    
    public Korisnik(){}
    public Korisnik(String username,String password, int balance){
        this.username=username;
        this.password=password;
        this.balance=balance;
    }
    @Override
    public String toString() {return "<pre>"+this.id+"&#9;"+this.username+"&#9;&#9;"+this.password+"&#9;&#9;$"+this.balance+"</pre>";}
    
    
    
    


    
}
