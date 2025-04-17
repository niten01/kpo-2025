# Mini HW Zoo 2

Основные пакеты: `domain`, `application`, `infrastructure`, `presentation` в них располагаются слои чистой архитектуры.
зависимости направлены влево (application зависит только от domain, infrastructure зависит от application и domain и
т.д.)

value objects - `Food`, `EnclosureID`

зависимость presentation от infrastructure представлена через DTO, валидация в них через jakarta

ограничения и доступные вопросы задокументированы в сваггере
после запуска апи можно протестировать в браузере (http://localhost:8080/swagger-ui/index.html#/)

для тестов подглядел и использовал mockMvc