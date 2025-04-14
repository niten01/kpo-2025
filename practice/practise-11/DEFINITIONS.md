# Список терминов семинара
### 1. **Что такое Docker? Зачем он нужен?**
**Docker** — это платформа для контейнеризации приложений, позволяющая упаковывать код и все его зависимости в изолированные легковесные контейнеры.

**Зачем он нужен?**
- **Изоляция** приложений и их зависимостей.
- **Переносимость** ("работает на моей машине" больше не проблема).
- **Масштабируемость** (легко развернуть несколько копий).
- **Упрощение** деплоя и CI/CD.

---  

### 2. **Как поднять БД в Docker?**
Пример с PostgreSQL:
```bash
docker run --name my-postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
```  
- `--name` — имя контейнера.
- `-e` — переменные окружения (логин/пароль).
- `-p` — проброс портов (хост:контейнер).
- `-d` — запуск в фоне.

Для MySQL замените `postgres` на `mysql:8.0` и настройте переменные (`MYSQL_ROOT_PASSWORD`).

---  

### 3. **Как подключить БД к приложению?**
В Spring Boot прописать в `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=postgres
spring.datasource.password=mysecretpassword
spring.jpa.hibernate.ddl-auto=update
```  
Для Docker-сети используйте имя контейнера вместо `localhost` (если приложение тоже в Docker).

---  

### 4. **Что такое Repository?**
**Repository** (репозиторий) — это паттерн доступа к данным, абстрагирующий работу с БД. В Spring Data JPA это интерфейс, который автоматически реализует CRUD-операции:
```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
```  

---  

### 5. **Что такое Hibernate?**
**Hibernate** — ORM (Object-Relational Mapping) фреймворк для Java, который:
- Преобразует Java-объекты в записи БД и обратно.
- Управляет связями между таблицами.
- Генерирует SQL-запросы автоматически.  
  Работает через JPA (Java Persistence API) — стандарт для ORM.

---  

### 6. **Связи в Hibernate (One-to-One, One-to-Many, Many-to-Many)**
- **One-to-One**:
  ```java
  @Entity
  public class User {
      @Id
      private Long id;
      
      @OneToOne
      @JoinColumn(name = "profile_id")
      private Profile profile;
  }
  ```  

- **One-to-Many / Many-to-One**:
  ```java
  @Entity
  public class Order {
      @ManyToOne
      @JoinColumn(name = "user_id")
      private User user;
  }

  @Entity
  public class User {
      @OneToMany(mappedBy = "user")
      private List<Order> orders;
  }
  ```  

- **Many-to-Many**:
  ```java
  @Entity
  public class Student {
      @ManyToMany
      @JoinTable(name = "student_course",
          joinColumns = @JoinColumn(name = "student_id"),
          inverseJoinColumns = @JoinColumn(name = "course_id"))
      private Set<Course> courses;
  }
  ```  

---  

### 7. **Общая таблица для разных объектов (Наследование)**
Используйте стратегии наследования JPA:
- **SINGLE_TABLE** (одна таблица для всех классов с дискриминатором):
  ```java
  @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
  @DiscriminatorColumn(name = "type")
  public abstract class Vehicle { ... }

  @Entity
  @DiscriminatorValue("car")
  public class Car extends Vehicle { ... }
  ```  

- **TABLE_PER_CLASS** или **JOINED** — другие варианты.

---  

### 8. **Уникальный факт**
**Первый релиз Docker состоялся в 2013 году**, и за 5 лет он стал настолько популярен, что слово "контейнер" в IT теперь ассоциируется не с виртуальными машинами, а именно с Docker. В 2020 году Kubernetes (оркестратор контейнеров) объявил о deprecated поддержки Docker в пользу других runtime (например, containerd), но Docker до сих пор остается самым удобным инструментом для разработчиков.

Если нужны уточнения по какой-то теме — спрашивайте! 😊