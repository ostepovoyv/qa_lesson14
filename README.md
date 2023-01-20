# Проект по автоматизации тестирования интернет-магазина shop.kz

## 	Содержание

> ➠ [Покрытый функционал](#покрытый-функционал)
>
> ➠ [Технологический стек](#технологический-стек)
>
> ➠ [Запуск тестов ](#запуск-тестов)
>
>
> ➠ [Отчет о результатах тестирования в Allure Report](#-главная-страница-allure-отчета)
>
> ➠ [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)
>
> ➠ [Пример запуска теста в Selenoid](#-пример-запуска-теста-в-selenoid)

##  Покрытый функционал

> Разработаны автотесты на <code>UI</code>.

- [x] Проверка разделов и пунктов меню магазина
- [x] Поиск товара по наименвоанию и артикулу
- [x] Добавление товаров в корзину и возможности оформления 


## Технологический стек

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
</p>

### В данном проекте автотесты написаны на <code>Java</code> с использованием <code>Selenide</code> для UI-тестов.
>
> <code>Selenide</code> - это фреймворк для автоматизированного тестирования веб-приложений на основе <code>Selenium WebDriver</code>.
> 
> <code>Selenoid</code> выполняет запуск браузеров в контейнерах <code>Docker</code>.
>
> <code>Allure Report</code> формирует отчет о запуске тестов.
>
> <code>Gradle</code> автоматизированной сборки проекта.
>
> <code>JUnit 5</code> библиотека для модульного тестирования .
>
> <code>Jenkins</code> выполняет запуск тестов.
> 
> После прохождения тестов отправляются уведомления с помощью бота в <code>Telegram</code>.

## Запуск тестов

### Локальный запуск тестов

```
gradle clean test -Denv=local
```

### Запуск тестов удаленно (selenoid)

```
gradle clean test -Denv=remote 
```

### Запуск тестов в Jenkins

#### Для запуска тестов в Jenkins используется команда
```
gradle clean test -Denv=remote
```
#### Для запуска тестов в Jenkins используется <code>remote.proterties</code>
#### Параметры сборки
>
><code>baseUrl</code> – адрес удаленного сервера, на котором будут запускаться тесты.
> 
><code>browser</code> – браузер для тестов.
> 
><code>browserVersion</code> – версия браузера.
> 
><code>browserSize</code> – размер окна браузера.
> 
><code>remoteUrl</code> – адрес удаленного сервера, на котором будут запускаться тесты.
>
><code>pageLoadTimeout</code> – таймаут, для ожидания загрузки страницы.
> 
> <code>isRemote</code> – флаг, определяющий удаленный запуск тестов.


## <img width="4%" title="Jenkins" src="images/logo/Jenkins.svg"> Удаленный запуск тестов в Jenkins

<p align="center">
<img title="jenkins_job" src="images/screens/jenkins_job.png">
</p>

## <img width="4%" title="Allure_Report" src="images/logo/Allure_Report.svg"> Cтраница Allure отчета

<p align="center">
<img title="Allure1" src="images/screens/Allure1.png">
</p>

## <img width="4%" title="Allure_Report" src="images/logo/Allure_Report.svg"> Список проводимых проверок

<p align="center">
<img title="Allure2" src="images/screens/Allure2.png">
</p>



## <img width="4%" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

> После прохождения всех тестов, автоматически отправялется отчет в<code>Telegram</code>,
> 
> 
> 

<p align="center">
<img title="telegram_notification" src="images/screens/telegram_notification.png">
</p>

## <img width="4%" title="Selenoid" src="images/logo/Selenoid.svg"> Пример запуска теста в Selenoid

> Для каждого теста в отчете прилагается видео.
> 
> Видео для примера 

<p align="center">
<img title="toCart" src="images/gifs/toCart.gif">
</p>