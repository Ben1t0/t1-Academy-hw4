# Задание 4 открытой школы T1

## Система мониторинга с использованием Spring Kafka.

### Описание проекта</a>
Система включает в себя 2 сервиса:
- Producer Service для отправки метрик, 
- Consumer Service для их обработки и анализа, а также REST API для просмотра метрик.

<a id="swagger">Документация по API обоих сервисов реализованна при помощи Swagger:</a>
- [Producer Service API](http://localhost:8081/swagger-ui/index.html)
- [Consumer Service API](http://localhost:8080/swagger-ui/index.html)

### Описание Producer Service
Сервис состоит из клиента для отправки сообщений в брокер Apache Kafka и модуля мониторинга приложения io.micrometer
(spring-boot-starter-actuator). Для выбора набора отправляемых метрик требуется написать их названия в application.yml
Все выбранные метрики отправляются единым пакетом в топик, указанный в application.yml, по [запросу](#swagger).

```yaml
app:
  metrics:
    - jvm.memory.max
    - disk.free
    - process.uptime
    - application.started.time
```

### Описание Consumer Service
Сервис состоит из клиента для получения сообщений из брокера Apache Kafka и системы хранения полученных метрик.
В этом сервисе реализован [RESTApi](#swagger) который позволяет получить все принятые метрики, либо получить выгрузку
по какой-то конкретной метрике.


### Инструкции по запуску
Проект выполнен на базе Docker. Так же подготовлен docker-compose.yml файл для упрощенного разворачивания 
обоих сервисов и брокера Kafka.
Для запуска в корневом каталоге проекта выполнить команду 
```shell
docker compose up -d
```

### Настройки Apache Kafka
Создается топик для отправки метрик. Название указывается в application.yml. Топик имеет одну партицию так как в этом 
проекте используется один получатель.
Указан сериализатор сообщений (JacksonSerializer) в файле конфигурации Kafka.

```yaml
app:
  kafka:
    topic: "metrics-topic"
``` 
Остальные настройки оставлены по умолчанию

### Тестирование
- В корневом каталоге есть запросы для [Postman](./T1%20Academy%20HW4.postman_collection.json)