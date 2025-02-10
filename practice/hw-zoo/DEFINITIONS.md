# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
## DIP
  Модули верхнего уровня не должны зависеть от модулей нижнего уровня. Оба должны зависеть от абстракций.
  Абстракции не должны зависеть от деталей. Детали должны зависеть от абстракций.
## юнит тесты (junit), что такое + примеры
  Юнит-тестирование – это процесс тестирования отдельных модулей или компонентов программы для проверки их правильной работы. JUnit – популярный фреймворк для юнит-тестирования в Java.
  Например, тестирование функции сложения в калькуляторе.
 ## +1 уникальный факт связанный с темами выше или семинаром
  Можно при тестировании заменить реальные зависимости на заглушки (mocks)

### Service Locator
**Service Locator** — это паттерн проектирования, позволяющий получать зависимости из централизованного хранилища (локатора сервисов).

**Пример:**
```java
class ServiceLocator {
    private static final Map<Class<?>, Object> services = new HashMap<>();

    public static <T> void register(Class<T> key, T service) {
        services.put(key, service);
    }

    public static <T> T getService(Class<T> key) {
        return key.cast(services.get(key));
    }
}

// Использование
ServiceLocator.register(Logger.class, new ConsoleLogger());
Logger logger = ServiceLocator.getService(Logger.class);
logger.log("Hello, world!");
```

---

### DIP (Dependency Inversion Principle)
**Принцип инверсии зависимостей** (DIP) гласит, что модули верхнего уровня не должны зависеть от модулей нижнего уровня. Оба должны зависеть от абстракций.

**Пример:**
```java
interface NotificationSender {
    void send(String message);
}

class EmailSender implements NotificationSender {
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

class NotificationService {
    private final NotificationSender sender;

    public NotificationService(NotificationSender sender) {
        this.sender = sender;
    }

    public void notifyUser() {
        sender.send("Your order is ready!");
    }
}

// Использование
NotificationSender sender = new EmailSender();
NotificationService service = new NotificationService(sender);
service.notifyUser();
```

---

### IoC (Inversion of Control)
**Инверсия управления** (IoC) — это принцип, согласно которому управление зависимостями передаётся внешнему контейнеру (например, Spring).

**Пример (Spring IoC):**
```java
@Component
class EmailService {
    public void sendEmail() {
        System.out.println("Email sent!");
    }
}

@Service
class UserService {
    private final EmailService emailService;

    @Autowired
    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void process() {
        emailService.sendEmail();
    }
}
```

---

### Singleton
**Singleton** — это паттерн, гарантирующий, что класс имеет только один экземпляр.

**Пример:**
```java
class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}

// Использование
Singleton singleton = Singleton.getInstance();
```

---

### Prototype
**Prototype** — это паттерн, позволяющий создавать копии объектов.

**Пример:**
```java
class Prototype implements Cloneable {
    String name;

    public Prototype(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

// Использование
Prototype original = new Prototype("Original");
Prototype copy = (Prototype) original.clone();
System.out.println(copy.name); // Original
```

---

### Юнит-тесты JUnit
**JUnit** — это фреймворк для тестирования в Java.

**Пример:**
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void testAddition() {
        assertEquals(5, 2 + 3);
    }
}
```

---

### Mockito (mock + spy)
**Mockito** — библиотека для создания заглушек (mock) и частичных заглушек (spy).

**Пример:**
```java
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

class MockitoExample {
    @Test
    void testMock() {
        List<String> mockList = mock(List.class);
        when(mockList.size()).thenReturn(10);

        assertEquals(10, mockList.size());
    }

    @Test
    void testSpy() {
        List<String> spyList = spy(new ArrayList<>());
        spyList.add("Hello");

        assertEquals("Hello", spyList.get(0));
    }
}
```

---

### @SpringBootTest
Аннотация **@SpringBootTest** загружает контекст Spring Boot для интеграционного тестирования.

**Пример:**
```java
@SpringBootTest
class ApplicationTests {
    @Test
    void contextLoads() {
    }
}
```

---

### @Autowired
**@Autowired** — аннотация для автоматического внедрения зависимостей.

**Пример:**
```java
@Component
class ServiceA {}

@Component
class ServiceB {
    @Autowired
    private ServiceA serviceA;
}
```

---

### @Component
**@Component** помечает класс как Spring-бин, который можно внедрять в другие компоненты.

**Пример:**
```java
@Component
class MyComponent {
    public void doSomething() {
        System.out.println("Hello from component!");
    }
}
```

---

### Уникальный факт
В 2016 году один из создателей Spring — Юрген Холлер — сказал, что IoC в Spring на 90% построен на **паттерне Service Locator**, а не чистой DI!