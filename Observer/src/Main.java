import java.util.*;

public class Main {
    public static void main(String[] args) {
        PostOffice office = new PostOffice();
        // Создаем подписчиков
        Observer firstSubscriber = new Subscriber("First Subscriber");
        Observer secondSubscriber = new Subscriber("Second Subscriber");
        // Создаем журналы, газеты
        office.addMagazine("First Magazine");
        office.addNewspaper("First Newspaper");
        //Подписыаем людей на рассылку
        office.subscribe(PostOffice.MAGAZINE,firstSubscriber);
        office.subscribe(PostOffice.NEWSPAPER,secondSubscriber);
        //Делаем рассылку
        office.sendToTheObservers();
    }
}

interface Observer {
    void handleNotification();
    void sendMails(List<String> mail);
}

interface Manager {
    void subscribe(String type, Observer observer);
    void unsubscribe(String type, Observer observer);
    void sendToTheObservers();
}

class Subscriber implements Observer{
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public String getName() {return name;}

    @Override
    public void handleNotification() {
            System.out.println("Dear "+name+",\n You can check an update in the mail query"  +
                    "\n----------------------------------------------------\n");
    }
    @Override
    public void sendMails(List<String> mail)  {
        System.out.println("Dear "+name+",\n You have a new mail\n" + mail +
                "\n----------------------------------------------------\n");
    }
}


class PostOffice implements Manager {
    private List<String> magazines = new ArrayList<>();
    private List<String> newspapers = new ArrayList<>();
    private Map<String,List<Observer>> observerMap= new HashMap<String,List<Observer>>()
    {{
        put(MAGAZINE, new ArrayList<>());
        put(NEWSPAPER, new ArrayList<>());
    }};
    public static final String MAGAZINE ="Magazine";
    public static final String NEWSPAPER ="Newspaper";
    @Override
    public void subscribe(String type, Observer observer){
        observerMap.get(type).add(observer);
    }
    @Override
    public void unsubscribe(String type, Observer observer){
        observerMap.get(type).remove(observer);
    }

    public void addMagazine(String magazine){
        this.magazines.add(magazine);
    }

    public void addNewspaper(String newspaper){
        this.newspapers.add(newspaper);
    }

    public void sendMagazines(){
        List<Observer> observers = observerMap.get(MAGAZINE);
        for (Observer observer: observers) {
            observer.sendMails(magazines);
            observer.handleNotification();
        }
    }

    public void sendNewspapers(){
        List<Observer> observers = observerMap.get(NEWSPAPER);
        for (Observer observer: observers) {
            observer.sendMails(newspapers);
            observer.handleNotification();
        }
    }

    @Override
    public void sendToTheObservers() {
        sendMagazines();
        sendNewspapers();
    }
}