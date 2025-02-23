# Список терминов семинара
###### Нужно написать определения с примером из жизни или кода
### Порождающие паттерны

Порождающие паттерны — это шаблоны проектирования, которые отвечают за создание объектов. Они помогают сделать систему независимой от процесса создания, композиции и представления объектов. Эти паттерны позволяют гибко управлять процессом создания объектов, что особенно полезно в сложных системах.

---

### Singleton (Одиночка)

**Определение:**  
Паттерн Singleton гарантирует, что у класса есть только один экземпляр, и предоставляет глобальную точку доступа к этому экземпляру.

**Реализация в Java:**

```java
public class Singleton {
    // Приватное статическое поле для хранения единственного экземпляра
    private static Singleton instance;

    // Приватный конструктор, чтобы предотвратить создание экземпляров извне
    private Singleton() {}

    // Публичный метод для получения экземпляра
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

**Пример из жизни:**  
Singleton можно сравнить с президентом страны. В любой момент времени может быть только один действующий президент, и все обращения к нему происходят через единую точку доступа.

---

### Factory (Фабрика)

**Определение:**  
Паттерн Factory предоставляет интерфейс для создания объектов, но позволяет подклассам изменять тип создаваемых объектов.

**Реализация в Java:**

```java
interface Product {
    void use();
}

class ConcreteProduct implements Product {
    @Override
    public void use() {
        System.out.println("Using ConcreteProduct");
    }
}

class Factory {
    public Product createProduct() {
        return new ConcreteProduct();
    }
}
```

**Пример из жизни:**  
Фабрика похожа на ресторан, где вы заказываете блюдо (объект), но вам не нужно знать, как именно оно готовится. Вы просто получаете готовый продукт.

---

### Fabric method (Фабричный метод)

**Определение:**  
Паттерн Fabric method определяет интерфейс для создания объекта, но оставляет подклассам решение о том, какой класс инстанцировать.

**Реализация в Java:**

```java
abstract class Creator {
    public abstract Product factoryMethod();

    public void useProduct() {
        Product product = factoryMethod();
        product.use();
    }
}

class ConcreteCreator extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProduct();
    }
}
```

**Пример из жизни:**  
Фабричный метод можно сравнить с заказом такси через приложение. Вы выбираете тип машины (эконом, комфорт), но приложение само решает, какую конкретно машину вам назначить.

---

### Abstract Factory (Абстрактная фабрика)

**Определение:**  
Паттерн Abstract Factory предоставляет интерфейс для создания семейств связанных или зависимых объектов без указания их конкретных классов.

**Реализация в Java:**

```java
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

class WinButton implements Button {
    @Override
    public void render() {
        System.out.println("Render Windows Button");
    }
}

class WinCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Render Windows Checkbox");
    }
}

interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class WinFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}
```

**Пример из жизни:**  
Абстрактная фабрика похожа на производство мебели. Вы можете заказать весь гарнитур (стол, стул, шкаф) в одном стиле (классика, модерн), и фабрика обеспечит, чтобы все элементы были в одном стиле.

---

### Builder (Строитель)

**Определение:**  
Паттерн Builder позволяет создавать сложные объекты пошагово. Он отделяет конструирование сложного объекта от его представления, так что один и тот же процесс конструирования может создавать разные представления.

**Реализация в Java:**

```java
class Product {
    private String part1;
    private String part2;

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    @Override
    public String toString() {
        return "Product{part1='" + part1 + "', part2='" + part2 + "'}";
    }
}

class Builder {
    private Product product = new Product();

    public Builder buildPart1(String part1) {
        product.setPart1(part1);
        return this;
    }

    public Builder buildPart2(String part2) {
        product.setPart2(part2);
        return this;
    }

    public Product getResult() {
        return product;
    }
}
```

**Пример из жизни:**  
Builder можно сравнить с процессом сборки компьютера. Вы можете выбрать процессор, видеокарту, оперативную память и т.д., и в результате получить готовый компьютер.

---

### Prototype (Прототип)

**Определение:**  
Паттерн Prototype позволяет создавать новые объекты путем копирования существующих объектов (прототипов), вместо создания новых объектов через конструктор.

**Реализация в Java:**

```java
class Prototype implements Cloneable {
    private String data;

    public Prototype(String data) {
        this.data = data;
    }

    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }

    @Override
    public String toString() {
        return "Prototype{data='" + data + "'}";
    }
}
```

**Пример из жизни:**  
Prototype похож на клонирование животных. Например, овечка Долли была создана путем клонирования, а не через обычное размножение.

---

### Уникальный факт

**Факт:**  
Паттерн Singleton был одним из первых паттернов, описанных в книге "Design Patterns: Elements of Reusable Object-Oriented Software" (1994), которая считается классикой в области проектирования программного обеспечения. Однако, несмотря на свою популярность, Singleton часто критикуют за то, что он может нарушать принцип единственной ответственности (Single Responsibility Principle), так как класс одновременно управляет своим жизненным циклом и выполняет свои основные функции.