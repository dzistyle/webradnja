package classPaket;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Proizvod")
public class Proizvod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="pro_name")
    private String pro_name;
    @Column(name="price")
    private int price;
    @Column(name="available")
    private int available;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getpro_name() {return pro_name;}
    public void setpro_name(String pro_name) {this.pro_name = pro_name;}
    public int getprice() {return price;}
    public void setprice(int price) {this.price = price;}
    public int getavailable() {return available;}
    public void setavailable(int available) {this.available = available;}
    
    public Proizvod(){}
    public Proizvod(String pro_name,int price, int available){
        this.pro_name=pro_name;
        this.price=price;
        this.available=available;
    }

    @Override
    public String toString() {return "<pre>"+this.id+"&#9;"+this.pro_name+"&#9;$"+this.price+"&#9;"+this.available;}
    
    
    
    


    
}
