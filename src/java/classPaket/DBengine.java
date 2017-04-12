package classPaket;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DBengine {
   
        static Session s = HibernateUtil.createSessionFactory().openSession();
        static Transaction t = null;
    
   public static void insertNewUser(String username, String password, int balance){
        Korisnik k = new Korisnik(username, password, balance);
        try {
            Session sn = HibernateUtil.createSessionFactory().openSession();
            t = sn.beginTransaction();
            sn.persist(k);
            t.commit();
            //sn.close();
        }
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi insertNewUser"+ex);}
        finally {
            HibernateUtil.close();}
    }
   
        public static List<Korisnik> Klist;
   public static void ListAllUsers() {
          Session s2 = HibernateUtil.createSessionFactory().openSession();
            String getAll = "from Korisnik";
            Query q = s.createQuery(getAll);
        try {
            t = s.beginTransaction();
            Klist = q.list();
            t.commit();
        //s2.close();
        } 
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi ListAllUsers"+ex);}
        finally {
            HibernateUtil.close();}         
    }
   
   public static void DeleteUser(int IDfromDB) {
        try {
            //Session s = HibernateUtil.createSessionFactory().openSession();
            t = s.beginTransaction();
            Korisnik ko = (Korisnik)s.load(Korisnik.class, IDfromDB);
            s.delete(ko);
            t.commit();
        //    s.close();
        } 
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi deleteUser"+ex);}
        finally {
            HibernateUtil.close();}
    }
   
   public static void UpdateUser(int ID,String name, String password, int balance) {
        try {
           // Session s4 = HibernateUtil.createSessionFactory().openSession();
            t = s.beginTransaction();
            Korisnik ku = (Korisnik)s.load(Korisnik.class, ID);
            ku.setUsername(name);
            ku.setPassword(password);
            ku.setBalance(balance);
            s.update(ku);
            t.commit();
        //    s.close();
        } 
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi updateUser"+ex);}
        finally {
            HibernateUtil.close();}
    }
   
   public static void insertNewProduct(String pro_name, int price, int available){
        Proizvod p = new Proizvod(pro_name, price, available);
        
        try {
            Session snp = HibernateUtil.createSessionFactory().openSession();
            t = snp.beginTransaction();
            snp.persist(p);
            t.commit();
        }
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi insertNewProduct"+ex);}
        finally {
            HibernateUtil.close();}
    }
 
        public static List<Proizvod> ProductList;
   public static void ListAllProduct() {
       Session ses = HibernateUtil.createSessionFactory().openSession();     
       String getAll = "from Proizvod";
            Query q2 = ses.createQuery(getAll);
        try {
            t = ses.beginTransaction();
            ProductList = q2.list();
            t.commit();
        //ses.close();
        } 
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi listAllProducts"+ex);}
        finally {
            HibernateUtil.close();}         
    }
       
   public static void DeleteProduct(int IDfromDB) {
        try {
            
            t = s.beginTransaction();
            Proizvod pr = (Proizvod)s.load(Proizvod.class, IDfromDB);
            s.delete(pr);
            t.commit();} 
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println(ex + "   F4 ne radi");}
        finally {
            HibernateUtil.close();}
    }
   
   public static void UpdateProduct(int ID, String name, int price, int available) {
        try {
            t = s.beginTransaction();
            Proizvod pu = (Proizvod)s.load(Proizvod.class, ID);
            pu.setpro_name(name);
            pu.setprice(price);
            pu.setavailable(available);
            s.update(pu);
            t.commit();} 
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println(ex + " update F3 ne radi");}
        finally {
            HibernateUtil.close();}
    }
   
           public static List<Korisnik> specKlist;
   public static void ListSpecUser(String userName, String passWord) {
            Session s9 = HibernateUtil.createSessionFactory().openSession();
            String getU = "from Korisnik where username='"+userName+"' and password='"+passWord+"'";
            Query q = s9.createQuery(getU);
        try {
            t = s9.beginTransaction();
            specKlist = q.list();
            t.commit();
           // s.close();
        }
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi listSpecUser"+ex);}
        finally {
            HibernateUtil.close();}         
    }
   
   
           public static List<Proizvod> specProductList;
   public static void ListSpecProduct(String ProductName) {
            Session s9x = HibernateUtil.createSessionFactory().openSession();
            String getSP = "from Proizvod where pro_name='"+ProductName+"'";
            Query q = s9x.createQuery(getSP);
        try {
            t = s9x.beginTransaction();
            specProductList = q.list();
            System.out.println(specProductList.get(0).getpro_name()+"provera");
            t.commit();
           s9x.close();
        }
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println("ne radi listSpecProduct"+ex);}
        finally {
            HibernateUtil.close();}         
    }
   
   
   
      public static void updPRODUCTandUSER(int productID, int userID, int newINSTOCK, int newBALANCE) {
        try {
            Session sx = HibernateUtil.createSessionFactory().openSession();
            t = sx.beginTransaction();
            Proizvod proizvod = (Proizvod)sx.load(Proizvod.class, productID);
            Korisnik korisnik = (Korisnik)sx.load(Korisnik.class, userID); 
            proizvod.setavailable(newINSTOCK);
            sx.update(proizvod);
            korisnik.setBalance(newBALANCE);
            sx.update(korisnik);
            t.commit();
            //sx.close();
        } 
        catch (HibernateException ex) {
            if(t != null){
                t.rollback();}
            System.out.println(ex + " updateBOTH ne radi");}
        finally {
            HibernateUtil.close();}
    }
   
   
}
