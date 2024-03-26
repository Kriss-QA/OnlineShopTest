Тестовое задание по автоматизированному тестированию

Написать на языке Java e2e тест по авторизации и добавления товара в корзину. 
Проверить, что сумма цены товаров в корзине соответствует сумме цен заказанных товаров. 
В проекте использовать стек Java, Gradle, JUnit5, Playwright , Faker/ либо любой другой генератор данных, Allure2. 
После прохождения тестов должен формироваться Allure-отчет.

1. Установить и запустить Docker desktop
2. В терминале ввести:
    git clone https://github.com/microservices-demo/microservices-demo
    cd microservices-demo
    docker-compose -f deploy/docker-compose/docker-compose.yml up -d
   
Для проверки работоспособности тестового сайта перейти на http://localhost/
После завершения работы с сайтом необходимо ввести в терминале:
    docker-compose -f deploy/docker-compose/docker-compose.yml down
