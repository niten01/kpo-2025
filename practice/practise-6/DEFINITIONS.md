# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
### Поведенческие паттерны

Поведенческие паттерны — это шаблоны проектирования, которые определяют способы взаимодействия объектов и распределения ответственности между ними. Они помогают сделать систему более гибкой и упрощают взаимодействие между компонентами.

---

### 1. **Цепочка обязанностей (Chain of Responsibility)**

**Определение:** Паттерн позволяет передавать запросы по цепочке обработчиков. Каждый обработчик решает, может ли он обработать запрос, и передает его следующему обработчику, если не может.

**Пример из жизни:** Представьте, что вы работаете в компании, и у вас есть проблема. Сначала вы обращаетесь к своему менеджеру. Если он не может решить проблему, он передает её своему начальнику, и так далее, пока проблема не будет решена.

**Пример на Java:**
```java
abstract class Handler {
    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public void handleRequest(String request) {
        if (next != null) {
            next.handleRequest(request);
        }
    }
}

class Manager extends Handler {
    public void handleRequest(String request) {
        if (request.equals("Small Problem")) {
            System.out.println("Manager handles the request.");
        } else {
            super.handleRequest(request);
        }
    }
}

class Director extends Handler {
    public void handleRequest(String request) {
        if (request.equals("Big Problem")) {
            System.out.println("Director handles the request.");
        } else {
            super.handleRequest(request);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Handler manager = new Manager();
        Handler director = new Director();

        manager.setNext(director);

        manager.handleRequest("Small Problem"); // Manager handles the request.
        manager.handleRequest("Big Problem");   // Director handles the request.
    }
}
```

---

### 2. **Команда (Command)**

**Определение:** Паттерн инкапсулирует запрос как объект, позволяя параметризовать клиентов с различными запросами, ставить запросы в очередь или поддерживать отмену операций.

**Пример из жизни:** Пульт дистанционного управления. Каждая кнопка на пульте представляет собой команду, которая выполняет определенное действие (включить свет, изменить канал и т.д.).

**Пример на Java:**
```java
interface Command {
    void execute();
}

class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.on();
    }
}

class Light {
    void on() {
        System.out.println("Light is on");
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(lightOn);
        remote.pressButton(); // Light is on
    }
}
```

---

### 3. **Интерпретатор (Interpreter)**

**Определение:** Паттерн используется для определения грамматики языка и интерпретации предложений этого языка.

**Пример из жизни:** Переводчик, который переводит текст с одного языка на другой, интерпретируя каждое слово.

**Пример на Java:**
```java
interface Expression {
    boolean interpret(String context);
}

class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    public boolean interpret(String context) {
        return context.contains(data);
    }
}

class OrExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

public class Main {
    public static void main(String[] args) {
        Expression john = new TerminalExpression("John");
        Expression married = new TerminalExpression("Married");

        Expression isJohnOrMarried = new OrExpression(john, married);

        System.out.println(isJohnOrMarried.interpret("John")); // true
        System.out.println(isJohnOrMarried.interpret("Married")); // true
        System.out.println(isJohnOrMarried.interpret("Single")); // false
    }
}
```

---

### 4. **Итератор (Iterator)**

**Определение:** Паттерн предоставляет способ последовательного доступа к элементам коллекции без раскрытия её внутренней структуры.

**Пример из жизни:** Перелистывание страниц книги. Вы можете переходить от одной страницы к другой, не зная, как устроена книга внутри.

**Пример на Java:**
```java
import java.util.Iterator;
import java.util.List;

class Book {
    private List<String> pages;

    public Book(List<String> pages) {
        this.pages = pages;
    }

    public Iterator<String> iterator() {
        return pages.iterator();
    }
}

public class Main {
    public static void main(String[] args) {
        Book book = new Book(List.of("Page 1", "Page 2", "Page 3"));
        Iterator<String> iterator = book.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

---

### 5. **Посредник (Mediator)**

**Определение:** Паттерн позволяет уменьшить связанность множества классов между собой, вынося взаимодействие в отдельный класс-посредник.

**Пример из жизни:** Диспетчер в аэропорту, который координирует взлеты и посадки самолетов, чтобы избежать столкновений.

**Пример на Java:**
```java
class Mediator {
    private Colleague colleague1;
    private Colleague colleague2;

    public void setColleague1(Colleague colleague1) {
        this.colleague1 = colleague1;
    }

    public void setColleague2(Colleague colleague2) {
        this.colleague2 = colleague2;
    }

    public void send(String message, Colleague sender) {
        if (sender == colleague1) {
            colleague2.receive(message);
        } else {
            colleague1.receive(message);
        }
    }
}

abstract class Colleague {
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public void receive(String message) {
        System.out.println("Colleague1 received: " + message);
    }
}

class ConcreteColleague2 extends Colleague {
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public void receive(String message) {
        System.out.println("Colleague2 received: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        Colleague colleague1 = new ConcreteColleague1(mediator);
        Colleague colleague2 = new ConcreteColleague2(mediator);

        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        colleague1.send("Hello from Colleague1");
        colleague2.send("Hi from Colleague2");
    }
}
```

---

### 6. **Хранитель (Memento)**

**Определение:** Паттерн позволяет сохранять и восстанавливать состояние объекта без нарушения инкапсуляции.

**Пример из жизни:** Сохранение игры. Вы можете сохранить текущее состояние игры и вернуться к нему позже.

**Пример на Java:**
```java
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public Memento save() {
        return new Memento(state);
    }

    public void restore(Memento memento) {
        state = memento.getState();
    }

    public void printState() {
        System.out.println("Current State: " + state);
    }
}

class Caretaker {
    private Memento memento;

    public void saveState(Originator originator) {
        memento = originator.save();
    }

    public void restoreState(Originator originator) {
        originator.restore(memento);
    }
}

public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State 1");
        caretaker.saveState(originator);
        originator.printState(); // Current State: State 1

        originator.setState("State 2");
        originator.printState(); // Current State: State 2

        caretaker.restoreState(originator);
        originator.printState(); // Current State: State 1
    }
}
```

---

### 7. **Наблюдатель (Observer)**

**Определение:** Паттерн определяет зависимость "один ко многим" между объектами, так что при изменении состояния одного объекта все зависящие от него объекты уведомляются и обновляются автоматически.

**Пример из жизни:** Подписка на новостную рассылку. Когда выходит новая статья, все подписчики получают уведомление.

**Пример на Java:**
```java
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message);
}

class NewsAgency {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        NewsChannel channel1 = new NewsChannel("Channel 1");
        NewsChannel channel2 = new NewsChannel("Channel 2");

        agency.addObserver(channel1);
        agency.addObserver(channel2);

        agency.notifyObservers("Breaking News!");
    }
}
```

---

### 8. **Состояние (State)**

**Определение:** Паттерн позволяет объекту изменять своё поведение в зависимости от внутреннего состояния.

**Пример из жизни:** Светофор. В зависимости от состояния (красный, желтый, зеленый), он ведет себя по-разному.

**Пример на Java:**
```java
interface State {
    void handle();
}

class RedState implements State {
    public void handle() {
        System.out.println("Red Light - Stop!");
    }
}

class GreenState implements State {
    public void handle() {
        System.out.println("Green Light - Go!");
    }
}

class TrafficLight {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void change() {
        state.handle();
    }
}

public class Main {
    public static void main(String[] args) {
        TrafficLight light = new TrafficLight();

        light.setState(new RedState());
        light.change(); // Red Light - Stop!

        light.setState(new GreenState());
        light.change(); // Green Light - Go!
    }
}
```

---

### 9. **Стратегия (Strategy)**

**Определение:** Паттерн позволяет выбирать алгоритм во время выполнения программы, инкапсулируя его в отдельный класс.

**Пример из жизни:** Навигатор. Вы можете выбрать стратегию маршрута: самый быстрый, самый короткий, с учетом пробок и т.д.

**Пример на Java:**
```java
interface RouteStrategy {
    void buildRoute(String A, String B);
}

class FastestRouteStrategy implements RouteStrategy {
    public void buildRoute(String A, String B) {
        System.out.println("Fastest route from " + A + " to " + B);
    }
}

class ShortestRouteStrategy implements RouteStrategy {
    public void buildRoute(String A, String B) {
        System.out.println("Shortest route from " + A + " to " + B);
    }
}

class Navigator {
    private RouteStrategy strategy;

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void buildRoute(String A, String B) {
        strategy.buildRoute(A, B);
    }
}

public class Main {
    public static void main(String[] args) {
        Navigator navigator = new Navigator();

        navigator.setStrategy(new FastestRouteStrategy());
        navigator.buildRoute("Home", "Work"); // Fastest route from Home to Work

        navigator.setStrategy(new ShortestRouteStrategy());
        navigator.buildRoute("Home", "Work"); // Shortest route from Home to Work
    }
}
```

---

### 10. **Шаблонный метод (Template Method)**

**Определение:** Паттерн определяет скелет алгоритма, позволяя подклассам переопределять некоторые шаги алгоритма без изменения его структуры.

**Пример из жизни:** Приготовление кофе. Основные шаги (кипячение воды, заваривание) остаются неизменными, но детали (тип кофе, добавки) могут варьироваться.

**Пример на Java:**
```java
abstract class Coffee {
    public final void prepareCoffee() {
        boilWater();
        brewCoffee();
        pourInCup();
        addCondiments();
    }

    void boilWater() {
        System.out.println("Boiling water");
    }

    abstract void brewCoffee();

    void pourInCup() {
        System.out.println("Pouring into cup");
    }

    abstract void addCondiments();
}

class Americano extends Coffee {
    void brewCoffee() {
        System.out.println("Brewing Americano");
    }

    void addCondiments() {
        System.out.println("Adding sugar");
    }
}

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new Americano();
        coffee.prepareCoffee();
    }
}
```

---

### 11. **Посетитель (Visitor)**

**Определение:** Паттерн позволяет добавлять новые операции к объектам без изменения их классов.

**Пример из жизни:** Врач, который посещает пациентов и назначает лечение в зависимости от их состояния.

**Пример на Java:**
```java
interface Visitor {
    void visit(ElementA element);
    void visit(ElementB element);
}

interface Element {
    void accept(Visitor visitor);
}

class ElementA implements Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationA() {
        System.out.println("Operation A");
    }
}

class ElementB implements Element {
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void operationB() {
        System.out.println("Operation B");
    }
}

class ConcreteVisitor implements Visitor {
    public void visit(ElementA element) {
        element.operationA();
    }

    public void visit(ElementB element) {
        element.operationB();
    }
}

public class Main {
    public static void main(String[] args) {
        Element[] elements = {new ElementA(), new ElementB()};
        Visitor visitor = new ConcreteVisitor();

        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}
```

---

### Уникальный факт

**Факт:** Паттерн "Наблюдатель" (Observer) широко используется в современных фреймворках, таких как Spring и JavaFX. Например, в JavaFX он используется для реализации реактивного программирования, где изменения в одном компоненте автоматически обновляют связанные с ним компоненты. Это делает разработку пользовательских интерфейсов более гибкой и удобной.