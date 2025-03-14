# Список терминов семинара
### Структурные паттерны

Структурные паттерны проектирования отвечают за организацию классов и объектов в более крупные структуры, обеспечивая гибкость и эффективность системы. Они помогают упростить взаимодействие между компонентами и улучшить читаемость кода.

---

### 1. **Декоратор (Decorator)**

**Определение:**  
Декоратор — это структурный паттерн, который позволяет динамически добавлять объектам новые функциональности, оборачивая их в объекты-декораторы. Это альтернатива наследованию, которая обеспечивает большую гибкость.

**Пример из жизни:**  
Представьте, что у вас есть кофе. Вы можете добавить в него молоко, сахар или сироп. Каждый из этих ингредиентов "декорирует" базовый кофе, добавляя новые свойства.

**Пример на Java:**

```java
// Базовый интерфейс
interface Coffee {
    String getDescription();
    double getCost();
}

// Конкретная реализация
class SimpleCoffee implements Coffee {
    public String getDescription() {
        return "Простой кофе";
    }

    public double getCost() {
        return 2.0;
    }
}

// Декоратор
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Конкретный декоратор
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return decoratedCoffee.getDescription() + ", с молоком";
    }

    public double getCost() {
        return decoratedCoffee.getCost() + 0.5;
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " за " + coffee.getCost() + " руб.");
    }
}
```

---

### 2. **Адаптер (Adapter)**

**Определение:**  
Адаптер — это структурный паттерн, который позволяет объектам с несовместимыми интерфейсами работать вместе. Он оборачивает один из объектов, чтобы скрыть сложность преобразования.

**Пример из жизни:**  
Адаптер для зарядки телефона, который позволяет использовать европейскую вилку в американской розетке.

**Пример на Java:**

```java
// Целевой интерфейс
interface EuropeanSocket {
    void plugIn();
}

// Адаптируемый класс
class AmericanSocket {
    void plugInAmerican() {
        System.out.println("Подключено к американской розетке");
    }
}

// Адаптер
class SocketAdapter implements EuropeanSocket {
    private AmericanSocket americanSocket;

    public SocketAdapter(AmericanSocket americanSocket) {
        this.americanSocket = americanSocket;
    }

    public void plugIn() {
        americanSocket.plugInAmerican();
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        AmericanSocket americanSocket = new AmericanSocket();
        EuropeanSocket adapter = new SocketAdapter(americanSocket);
        adapter.plugIn();
    }
}
```

---

### 3. **Фасад (Facade)**

**Определение:**  
Фасад — это структурный паттерн, который предоставляет простой интерфейс для взаимодействия с сложной подсистемой. Он скрывает сложность системы, предоставляя удобный API.

**Пример из жизни:**  
Кнопка "Пуск" на компьютере. Вместо того чтобы вручную запускать все процессы, вы просто нажимаете кнопку, и система сама загружает операционную систему.

**Пример на Java:**

```java
// Подсистема
class CPU {
    void start() {
        System.out.println("CPU запущен");
    }
}

class Memory {
    void load() {
        System.out.println("Память загружена");
    }
}

class HardDrive {
    void read() {
        System.out.println("Данные прочитаны с жесткого диска");
    }
}

// Фасад
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.start();
        memory.load();
        hardDrive.read();
        System.out.println("Компьютер запущен");
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}
```

---

### 4. **Заместитель (Прокси)**

**Определение:**  
Заместитель — это структурный паттерн, который предоставляет объект-заменитель для другого объекта. Прокси контролирует доступ к оригинальному объекту, добавляя дополнительную логику (например, ленивую загрузку или проверку прав доступа).

**Пример из жизни:**  
Кредитная карта — это прокси для денег на вашем банковском счете. Вы можете использовать карту для оплаты, не имея прямого доступа к счету.

**Пример на Java:**

```java
// Интерфейс
interface Image {
    void display();
}

// Реальный объект
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Загрузка изображения: " + filename);
    }

    public void display() {
        System.out.println("Показ изображения: " + filename);
    }
}

// Прокси
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        Image image = new ProxyImage("test.jpg");
        image.display(); // Загрузка и показ
        image.display(); // Только показ (изображение уже загружено)
    }
}
```

---

### 5. **Компоновщик (Composite)**

**Определение:**  
Компоновщик — это структурный паттерн, который позволяет сгруппировать объекты в древовидные структуры и работать с ними как с единым объектом.

**Пример из жизни:**  
Организационная структура компании, где сотрудники и отделы могут быть представлены как единое целое.

**Пример на Java:**

```java
// Компонент
interface Employee {
    void showDetails();
}

// Лист
class Developer implements Employee {
    private String name;

    public Developer(String name) {
        this.name = name;
    }

    public void showDetails() {
        System.out.println("Разработчик: " + name);
    }
}

// Композит
class Manager implements Employee {
    private String name;
    private List<Employee> employees = new ArrayList<>();

    public Manager(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void showDetails() {
        System.out.println("Менеджер: " + name);
        for (Employee employee : employees) {
            employee.showDetails();
        }
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        Employee dev1 = new Developer("Иван");
        Employee dev2 = new Developer("Петр");
        Manager manager = new Manager("Алексей");
        manager.addEmployee(dev1);
        manager.addEmployee(dev2);
        manager.showDetails();
    }
}
```

---

### 6. **Мост (Bridge)**

**Определение:**  
Мост — это структурный паттерн, который разделяет абстракцию и реализацию, позволяя им изменяться независимо друг от друга.

**Пример из жизни:**  
Пульт управления и телевизор. Пульт (абстракция) может работать с разными телевизорами (реализациями).

**Пример на Java:**

```java
// Реализация
interface Device {
    void turnOn();
    void turnOff();
}

class TV implements Device {
    public void turnOn() {
        System.out.println("Телевизор включен");
    }

    public void turnOff() {
        System.out.println("Телевизор выключен");
    }
}

class Radio implements Device {
    public void turnOn() {
        System.out.println("Радио включено");
    }

    public void turnOff() {
        System.out.println("Радио выключено");
    }
}

// Абстракция
abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    abstract void power();
}

// Уточненная абстракция
class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    public void power() {
        device.turnOn();
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remote = new AdvancedRemoteControl(tv);
        remote.power();
    }
}
```

---

### 7. **Приспособленец (Flyweight)**

**Определение:**  
Приспособленец — это структурный паттерн, который позволяет эффективно использовать общие объекты, уменьшая количество создаваемых экземпляров.

**Пример из жизни:**  
Символы в текстовом редакторе. Вместо создания нового объекта для каждого символа, редактор использует общие объекты для одинаковых символов.

**Пример на Java:**

```java
import java.util.HashMap;

// Приспособленец
class Character {
    private char symbol;

    public Character(char symbol) {
        this.symbol = symbol;
    }

    public void display() {
        System.out.println("Символ: " + symbol);
    }
}

// Фабрика приспособленцев
class CharacterFactory {
    private HashMap<Character, Character> characters = new HashMap<>();

    public Character getCharacter(char key) {
        Character character = characters.get(key);
        if (character == null) {
            character = new Character(key);
            characters.put(key, character);
        }
        return character;
    }
}

// Использование
public class Main {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();
        Character charA = factory.getCharacter('A');
        charA.display();
        Character charB = factory.getCharacter('B');
        charB.display();
        Character charA2 = factory.getCharacter('A');
        charA2.display(); // Используется существующий объект
    }
}
```

---
