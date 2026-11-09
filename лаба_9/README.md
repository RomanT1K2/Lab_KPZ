# Лабораторна робота №9. Варіант 5 — Готель «Карпатські вершини»

Запуск (потрібна JDK 17+):

    mkdir out
    javac -encoding UTF-8 -d out $(find src -name "*.java")
    java -Dfile.encoding=UTF-8 -cp out Main

| Завдання | Шаблон        | Пакет        | Ключові класи                         |
|----------|---------------|--------------|---------------------------------------|
| 1        | Object Pool   | `keycards`   | KeyCardPool, KeyCard, Reception       |
| 2        | Singleton     | `billing`    | BillingService, Department, Charge    |
| 3        | Prototype     | `stay`       | PackagePrototype, StayPackage + вкладені |

Діаграми: `docs/diagrams/*.puml` (експортувати в PNG/SVG).

Стратегія при нестачі карт (завдання 1): тимчасовий механічний ключ.
Обґрунтування: гість отримує доступ у номер одразу, не потрібні черга й потоки,
а механічний ключ повертається при виїзді.
