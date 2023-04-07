import java.util.HashMap;
import java.lang.Exception;

public class Main {
// ----------------------------------------------------------------------------
    public static int template(String reponse) {
        return 0;
    }
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public static void main(String[] args) {
        System.out.print("\033\143");
// Robert souhaite se rendre à Valverney en première classe
        Gare availableDestination =  Gare.getDestination();
        Humain robert = new Humain("Robert");
        robert.book( "Valverney",Comfor.FIST_CLASS, Gare.getDestination()); 
        System.out.println( robert);


// Jeanne souhaite se rendre à Rimonrebourg, en seconde classe et en utilisant 2 bons de
// réduction
// j'ai compris que jeanne utilise les 2 bons de réduction pour son billet et 
// non 1 bon pour elle et l'autre pour le chien Max
        Humain jeanne = new Humain("Jeanne");
        jeanne.book( "Rimonrebourg",Comfor.SECOND_CLASS, Gare.getDestination()); 
        UpgradeTicket upgradeJeanneTicket = new UpgradeTicket(jeanne.getTicket());
                upgradeJeanneTicket.discount(DiscountValue.D25);    // 1er bon de réduction 
        upgradeJeanneTicket.discount(DiscountValue.D25);    // 2ième bon de réduction 
        jeanne.setTicket( upgradeJeanneTicket);
        System.out.println( jeanne);

// 3. Max le chien accompagne Jeanne sur sa route et dispose des mêmes réductions 
// !!!!!!!!!! j'ai compris que Max a aussi 2 bons de réductions (même tarif que Jeanne)
        Chien max = new Chien("Max");
        Traveler traveler = new Traveler(max);
        Ticket sameTicket = new Ticket (jeanne.getTicket());
        traveler.setTicket(sameTicket);
        System.out.println( traveler);


// 4. Christophe souhaite se rendre à Tamboujak et dispose d’un bon de passage en première
        Humain christophe = new Humain("Christophe");
        christophe.book( "Tamboujak",Comfor.SECOND_CLASS, Gare.getDestination()); 
        UpgradeTicket upgradeChristopheTicket = new UpgradeTicket(christophe.getTicket());
        upgradeChristopheTicket.upgradeComfor();
        christophe.setTicket( upgradeChristopheTicket);
        System.out.println( christophe);

    }


// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
// ----------------------------------------------------------------------------
    public static int testMain() {
        // testTicket();
        // testDestination();
        // testHuman();
        // testDecorationHuman();
        // testAdaptator();

        return 0;
    }

// ----------------------------------------------------------------------------
    public static int testAdaptator() {

        Chien myDog = new Chien("nomChien");
        Traveler traveler = new Traveler(myDog);
        traveler.book( "Valverney",Comfor.SECOND_CLASS, Gare.getDestination()); 

        System.out.println(  traveler);
         return 0;
    }
// ----------------------------------------------------------------------------
    public static int testDecorationHuman() {
        
        
        Humain humain = new Humain("Emile");
        // System.out.println(  humain);

        humain.book( "Valverney",Comfor.SECOND_CLASS,Gare.getDestination()); 
        UpgradeTicket upgradeTicket = new UpgradeTicket(humain.getTicket());

        System.out.println(  humain);
        upgradeTicket.upgradeComfor();

        humain.setTicket( upgradeTicket);
        System.out.println(  humain);

        upgradeTicket.discount(DiscountValue.D25);
        // humain.setTicket( upgradeTicket);
        System.out.println(  humain);

        upgradeTicket.setComfort(Comfor.SECOND_CLASS);
        // humain.setTicket( upgradeTicket);
        System.out.println(  humain);

        upgradeTicket.updateConforAndDiscount(DiscountValue.D50);
        System.out.println(  humain);


        return 0;
    }
// ----------------------------------------------------------------------------
    public static int testHuman() {
        
        Humain humain = new Humain("Emile");
        // System.out.println(  humain);

        humain.book( "Valverney",Comfor.FIST_CLASS, Gare.getDestination()); 
        System.out.println(  humain);
        return 0;
    }

// ----------------------------------------------------------------------------
    public static int testDestination() {
        // System.out.println(  destination.getDestination());

        System.out.printf("%.2d \n", Gare.getDestination().getDestinationPrice("Valverney"));

        return 0;
    }
// ----------------------------------------------------------------------------2
    public static int testTicket() {
        
        Ticket ticket = new Ticket("emile","paris",100.1,  Comfor.FIST_CLASS);
        System.out.println(  ticket);
        // Ticket ticket2 = new Ticket("arffeee","briiparis",1020.1,  Comfor.SECOND_CLASS);
        // System.out.println(  ticket2);

        Ticket ticket3 = new Ticket("Emile","Valverney",1020.1,  Comfor.FIST_CLASS);
        System.out.println(  ticket3);
        return 0;
    }

}

// ----------------------------------------------------------------------------2
enum Comfor {
            FIST_CLASS,
            SECOND_CLASS
        }

// ----------------------------------------------------------------------------2
// réduction de 25,50 ou 75 %
enum DiscountValue {
            D25,D50,D75}

class Ticket{

    private String owner ; 
    private String destination ; 
    private double charge ; 
    private Comfor comfort ; 
    
    
    public Ticket(String owner,String destination,double charge, Comfor comfort){
        this.setOwner(owner);
        this.setDestination(destination);
        this.setCharge(charge);
        this.setComfort(comfort);
    }

    public Ticket(Ticket copi){
        this(   copi.getOwner(),
                copi.getDestination(),
                copi.getCharge(),
                copi.getComfort()
            );
    }



    public  void  setComfort(Comfor value){ this.comfort = value ;  }
    public  Comfor  getComfort(){ return this.comfort ; }

    public  void  setOwner(String value){ this.owner = value ;  }
    public  String  getOwner(){ return this.owner ; }
    
    public  void  setDestination(String value){ this.destination = value ;  }
    public  String  getDestination(){ return this.destination ; }
    
    public  void  setCharge(double value){ this.charge = value ;  }
    public  double  getCharge(){ return this.charge ; }

    public  String toString() {
        return String.format ("nom de la personne: %s destination : %s  %s et prix : %2.2f",
                                    this.getOwner(),
                                    this.getDestination(),
                                    this.getComfort(),
                                    this.getCharge());
    }

}

// 4.	Permettre la décoration de la réservation pour appliquer

class UpgradeTicket extends Ticket {

    public UpgradeTicket(Ticket ticket){
        super(  ticket.getOwner(),
                ticket.getDestination(),
                ticket.getCharge(),
                ticket.getComfort() );
    }
    public  void  upgradeComfor(){ 
        this.setComfort (Comfor.FIST_CLASS) ; 
    }

    public  void  discount(DiscountValue discount){ 

        switch( discount){

            case D25 : this.setCharge( this.getCharge() * 0.75) ; break ;
            case D50 : this.setCharge( this.getCharge() * 0.5) ; break ;
            case D75 : this.setCharge( this.getCharge() * 0.25) ; break ;
            default : break;

        }

    }

    public  void  updateConforAndDiscount(DiscountValue discount){ 

        this.discount( discount);
        this.upgradeComfor();
    }


// a.	Soit  une ou plusieurs réductions de 25%
// b.	Soit un passage en première
// c.	Soit les deux

}

// ----------------------------------------------------------------------------
//1.	Créer les classes Humain
class  Humain{

    private String name ; 
    private Ticket ticket ; 
    

    public Humain(String name,Ticket ticket){
        this.setName(name);
        this.setTicket(ticket);
    }

    public Humain(String name){
        this(name,null);
    }

    public  void  setName(String value){ this.name = value ;  }
    public  String  getName(){ return this.name ; }

    public  void  setTicket(String destination,double price,Comfor comfor){ 
        this.setTicket(new Ticket (this.getName(),destination,price,comfor));
    }
    public  void  setTicket(Ticket value){ this.ticket = value ;  }
    public  Ticket  getTicket(){ return this.ticket ; }

// 3.	Ajouter à l’humain une méthode d’instance permettant de réserver un 
// voyage parmi ces destinations, en 1ère classe ou en 2nd, sans prendre en paramètre la gare
// ----------------------------------------------------------------------------
    public void book(String destination,Comfor comfort,Gare destinationList) {
// a.	Si la réservation est en 1ère classe, augmenter le tarif de 50%
        
        try{
             System.out.printf("[%s]\n", destination);

            double price = destinationList.getDestinationPrice(destination);

            Comfor comfor = Comfor.SECOND_CLASS;
            if (price > 0 ){
                if (comfort == Comfor.FIST_CLASS){
                    comfor = Comfor.FIST_CLASS;
                    price*=1.5;
                }
            }
            this.setTicket(destination,price,comfor);

        }catch (Exception e){

             System.out.print("erreur de destination les destinations disponible sont " );
            System.out.println(  destinationList.getDestination());

        
        }
    }

    public  String toString() {
        return String.format ("nom : %s ticket : %s  ",
                                    this.getName(),
                                    this.getTicket());
    }

}
//// doit etre un sigleton
class  Gare{

    private  HashMap<String, Double> destination ;
    private static Gare MyStation = null;

    private Gare(){
        this.destination = new HashMap<String, Double>();
// i.	Tamboujak pour 100€
        this.destination.put("Tamboujak",100.0);
// ii.	Rimonrebourg pour 55€
        this.destination.put("Rimonrebourg",55.0);
// iii.	Valverney pour 122€
        this.destination.put("Valverney",122.0);
// iv.	Castle-Bonsari pour 34€
        this.destination.put("	Castle-Bonsari",34.0);

    }


    public double getDestinationPrice (String key){
        double result =  this.destination.get(key);
        return result;
    }


    public static Gare getDestination (){
        if (Gare.MyStation != null){
            return Gare.MyStation;
        }else{
            return new Gare();
        }
    }

    
}

// 2.	Créer la classe Gare
// a.	Celle-ci doit être un Singleton


// 5.	Créer la classe Chien et, à l’aide d’un adaptateur, 
// lui permettre de réserver lui aussi un voyage


class Traveler extends Humain{

    public Traveler(Chien dog){
        super ("Pet-"+ dog.getPetName());
    }
}


class  Chien{
    String petName ; 

    public Chien(String petName){
        this.setPetName(petName);
    }
    public Chien(){
        this("Pet-NoName");
    }

    public  void  setPetName(String value){ this.petName = value ;  }
    public  String  getPetName(){ return this.petName ; }


    public  String toString() {
        return String.format ("nom  %s",
                                    this.getPetName());
    }

}

