import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Event {
    String name;
    LocalDate date;
    LocalTime time;
    String note;

    public Event(String name,LocalDate date,LocalTime time, String note) {
        this.name=name;
        this.date=date;
        this.time=time;
        this.note=note;
    }
}

class CalendarPro{
    Map<String, List<Event>> events;

    public CalendarPro(){
        events=new HashMap<>();
    }

    public void addEvent(String category, Event event){
        events.computeIfAbsent(category, k -> new ArrayList<>()).add(event);
    }

    public void viewEvents(String category){
        if (events.containsKey(category)){
            System.out.println("events in category: "+category);
            List<Event> categoryEvents=events.get(category);
                        System.out.println("******************************************************************");

            System.out.println("Category      Name           Date           Time           Note");
            System.out.println("------------------------------------------------------------------");
            for (Event event : categoryEvents){
                System.out.println(category+"      "+event.name+"           "+event.date+"        "+ event.time+"           "+event.note);
            }
        } 
    }
    public void viewAllEvents(){
        System.out.println("All Events:");
        for (String category : events.keySet()) {
            viewEvents(category);
        }
    }
    
    public void viewtoday(){
        LocalDate today = LocalDate.now();
        System.out.println("events for today (" +today+"):");
                    System.out.println("******************************************************************");

        System.out.println("Category      Name           Date           Time           Note");
        System.out.println("------------------------------------------------------------------");
        for(String category : events.keySet()) {
            for(Event event : events.get(category)) {
                if(event.date.equals(today)) {
                    System.out.println(category+"      "+event.name+"           "+event.date+"        "+ event.time+"           "+event.note);                }
            }
        }
    }
    
    public void viewmonth(int month) {
        System.out.println("events for month "+month+":");
                    System.out.println("******************************************************************");

        System.out.println("Category      Name           Date           Time           Note");
        System.out.println("------------------------------------------------------------------");
        for(String category : events.keySet()) {
            for(Event event : events.get(category)) {
                if(event.date.getMonthValue()==month) {
                    System.out.println(category+"      "+event.name+"           "+event.date+"        "+ event.time+"           "+event.note);                }
            }
        }
    }
    
    public void viewyear(int year){
        System.out.println("events for year " +year+":");
                    System.out.println("******************************************************************");

        System.out.println("Category      Name           Date           Time           Note");
        System.out.println("------------------------------------------------------------------");
        for (String category : events.keySet()) {
            for (Event event : events.get(category)) {
                if (event.date.getYear()==year) {
                    System.out.println(category+"      "+event.name+"           "+event.date+"        "+ event.time+"           "+event.note);                }
            }
        }
    }

    public void smartSchedule(Event event, String category) {
        LocalDate eventDate = event.date;

        for (List<Event> categoryEvents : events.values()) {
            for (Event existingEvent : categoryEvents) {
                if (existingEvent.date.equals(eventDate)) {
                    System.out.println("An event is already scheduled on "+eventDate+".please choose a different date.");
                    return;
                }
            }
        }

        addEvent(category,event);
        System.out.println("event smart scheduled successfully!");
    }
}

class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        CalendarPro Cpro=new CalendarPro();

        while(true){
    
            System.out.println("------------------------------------------------------------------");
            System.out.println("Welcome to CalendarPro:");
            System.out.println("choose option:");
            System.out.println("1.add Event");
            System.out.println("2.view all Events or view by cateogory");
            System.out.println("3.view events by today,month,year(easy viewing)");
            System.out.println("4.smart schedule(before add check is event already present on that day)");
            System.out.println("5.exit");
            System.out.println("------------------------------------------------------------------");
            System.out.print("select an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("select a category:");
                    System.out.println("1.Health\n2.work\n3.personal\n4.meet\n5.other");
                    
                    int cChoice = sc.nextInt();
                    sc.nextLine();
                    String scatgry;

                    if(cChoice==1) {
                        scatgry = "Health";
                    }
                    else if(cChoice==2) {
                        scatgry = "work";
                    } 
                    else if(cChoice==3) {
                        scatgry = "personal";
                    } 
                    else if(cChoice==4) {
                        scatgry = "meet";
                    } 
                    else if(cChoice==5) {
                        scatgry="other";
                    } 
                    else{
                        scatgry="other";
                    }


                    System.out.print("enter event name: ");
                    String name=sc.nextLine();
                    System.out.print("enter event date (dd-MM-yyyy): ");
                    String dateString=sc.nextLine();
                    DateTimeFormatter dformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate date=LocalDate.parse(dateString, dformatter);
                    System.out.print("enter event time (HH:mm): ");
                    LocalTime time=LocalTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
                    System.out.print("enter event note: ");
                    String note=sc.nextLine();

                    Event event=new Event(name,date,time,note);
                    Cpro.addEvent(scatgry, event);
                    System.out.println("event added successfully to category: "+scatgry);
                    break;
                case 2:
                   System.out.println("1. view all events");
                    System.out.println("2. view events by categry");
                    System.out.print("select an option: ");
                    int vChoice=sc.nextInt();
                    sc.nextLine(); 

                    if(vChoice==1) {
                        Cpro.viewAllEvents();
                    } 
                    else if(vChoice==2) {
                        
                        System.out.print("select a category option: ");
                        System.out.println("1.Health,2.Work,3.personal,4.meet,5.other");
                        cChoice=sc.nextInt();
                        sc.nextLine();

          

                        if(cChoice==1) {
                            scatgry="Health";
                        } 
                        else if(cChoice==2){
                            scatgry="Work";
                        } 
                        else if(cChoice==3){
                            scatgry="personal";
                        } 
                        else if(cChoice==4){
                            scatgry="meet";
                        } 
                        else if(cChoice == 5){
                            scatgry="other";
                        } 
                        else{
                            scatgry="other";
                        }


                        Cpro.viewEvents(scatgry);
                    } else{
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 3:
                System.out.println("1.view events by today");
                System.out.println("2.view events by month");
                System.out.println("3.view events by year");
                    System.out.print("select an option: ");
                    int t=sc.nextInt();
                    sc.nextLine(); 

                    switch (t) {
                        case 1:
                            Cpro.viewtoday();
                            break;
                        case 2:
                            System.out.print("enter month (1-12): ");
                            int month=sc.nextInt();
                            Cpro.viewmonth(month);
                            break;
                        case 3:
                            System.out.print("enter year: ");
                            int year = sc.nextInt();
                            Cpro.viewyear(year);
                            break;
                        default:
                            System.out.println("invalid choice.");
                    }
                    break;
                case 4:
                    System.out.println("select a category:");
                    System.out.println("1.Health\n2.work\n3.personal\n4.meet\n5.other");
                    cChoice = sc.nextInt();
                    sc.nextLine();

                    if(cChoice==1) {
                        scatgry="Health";
                    } else if(cChoice==2){
                        scatgry = "work";
                    } else if(cChoice==3){
                        scatgry="personal";
                    } else if(cChoice==4){
                        scatgry="meet";
                    } else if(cChoice==5){
                        scatgry="other";
                    } else{
                        scatgry="other";
                    }

                    System.out.print("enter event name: ");
                    name=sc.nextLine();
                    System.out.print("enter event date (dd-MM-yyyy): ");
                    dateString=sc.nextLine();
                    dformatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    date = LocalDate.parse(dateString, dformatter);
                    System.out.print("enter event time (HH:mm): ");
                    time=LocalTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("HH:mm"));
                    
                    System.out.print("enter event note: ");
                    note=sc.nextLine();

                    Event smartScheduledEvent = new Event(name,date,time,note);
                    Cpro.smartSchedule(smartScheduledEvent, scatgry);
                    break;
                    
                case 5:
                    System.out.println("exiting CalendarPro");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
