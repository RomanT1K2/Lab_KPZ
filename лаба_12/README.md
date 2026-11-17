# Лабораторна робота №12. Варіант 5 — Готель «Карпатські вершини»
Шаблони: Flyweight (завд. 1), Composite (завд. 2), Bridge (завд. 3). Мова: Java 17+.

## Запуск
    javac -encoding UTF-8 -d out $(find src -name "*.java")     # Linux/macOS
    java -cp out hotel.Main

Windows (PowerShell):
    chcp 65001
    [Console]::InputEncoding = [System.Text.UTF8Encoding]::new()
    [Console]::OutputEncoding = [System.Text.UTF8Encoding]::new()
    $OutputEncoding = [Console]::OutputEncoding
    javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
    java -cp out hotel.Main

## Структура
    src/hotel/Main.java            - раннер (task1Flyweight, task2Composite, task3Bridge)
    src/hotel/util/Logger.java     - журнал [час] [джерело] подія
    src/hotel/flyweight/           - завдання 1
    src/hotel/composite/           - завдання 2
    src/hotel/bridge/              - завдання 3
    docs/diagrams/                 - діаграми (.puml + .png + .svg)
    docs/console_output.txt        - результат виконання програми
