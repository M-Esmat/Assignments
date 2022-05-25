import java.util.Scanner;

class Semaphore {
    protected int value = 0;

    protected Semaphore() {
        value = 0;
    }

    protected Semaphore(int initial) {
        value = initial;
    }

    public synchronized void sleep() {

        value--;
        if (value < 0)
            try {
                wait();
            } catch (InterruptedException e) {
            }
    }

    public synchronized void walkup() {
        value++;
        if (value <= 0) notify();
    }

}

class Router {
    int size;
    private Deivec waiting[];
    int index = 0;

    public Router(int N) {
        size = N;
        waiting = new Deivec[N];
        left = new Semaphore(N);
    }
    Semaphore left;
    public void connect(Deivec deivec) {
        if (left.value > 0) {
            System.out.println(deivec.name + " (" + deivec.type + ")" + " arrived");
        } else {
            System.out.println(deivec.name + " (" + deivec.type + ")" + " arrived and waiting");
        }
        left.sleep();
        deivec.connectionnumber = index + 1;
        waiting[index] = deivec;
        index = (index + 1) % size;
        System.out.println("Connection " + deivec.connectionnumber + ": " + deivec.name + " is Occupied");
    }
    public void endConnection() {
        left.walkup();
    }
}

class Deivec extends Thread {
    String name;
    String type;
    Router router;
    int connectionnumber;

    public Deivec(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
    }

    @Override
    public void run() {
        router.connect(this);
        System.out.println("Connection " + connectionnumber + ": " + name + " Login");
        System.out.println("Connection " + connectionnumber + ": " + name + " performs online activity");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
        }
        System.out.println(name + " Logout");
        router.endConnection();

    }
}


public class Network {

    public static void main(String[] args) {
        System.out.println("What is the number of WI-FI Connections?");
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        Router router = new Router(size);
        System.out.println("What is the number of devices Clients want to connect?");
        int num = input.nextInt();
        Deivec deivecs[] = new Deivec[num];
        for (int i = 0; i < num; i++) {
            String name = input.next();
            String type = input.next();
            Deivec c1 = new Deivec(name, type, router);
            deivecs[i] = c1;
        }
        for (int i = 0; i < num; i++) {
            deivecs[i].start();
        }
    }
}
