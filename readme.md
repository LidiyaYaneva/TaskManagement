Task Management App

This program is developed according to an Assignment for the purpose of Intership Application.
It is intended to show the best of my current  programming abilities.

1. Setup Guide

1.1 To run this program you need to have Intellij IDEA installed, or other IDE, that works with Java, Maven and Spring.
1.2 The app uses MySQL Database.
You need to have a MySQL tool, such as MySQL Workbench, where you have defined your own username and password for establishing connections.
1.3 Open the application.yml file in the project and set the username and password to your own.
1.4 Change the url in .yml file if you have address other than 3306 or if you already have database schema called "task_management"
1.5 If you have another app running on port 8080, change the port in .yml file to some other free port.
1.6 Start the application from Intellij.
1.7 Open a browser with localhost:8080 or the port that you have defined in the .yml file.
1.8 If you would like to insert example test data, first click on the button Seed Employees, which is on the home page.
After that navigate again to the home page and click Seed Tasks.
After the data is inserted you will no longer see those options.
If you have any records in the database, these options will not be available.

2. Folder Structure Overview

The project is divided by the following packages:
- models - contains entities, enums, dtos and validations. The entities defined are Employee, Task and Client. Task uses the enum TaskStatus.
- repositories - holds a repository for each entity
- services - contains service interfaces and their implementations
- web - contains controllers
- configuration - defines Beans for ModelMapper and Gson. ModelMapper is used to map entities to dtos. The project uses a single instance of ModelMapper. Gson is used to read json files.
- initialization - contains a Component, which holds the logic to initialize predefined data

The resources contain static data and Thymeleaf templates.
- Static data has bootstrap and css files. It also holds json files with examples to be inserted in the database.
- Thymeleaf templates are html files that allow the pages to be responsive.

3. Description of the additional functionalities

The app contains a Statistics section with navigation to different statistics.
Initially it shows a table with the tasks that are completed the previous month.
The first option for top 5 employees is required in the assignment. It uses the tasks from the table above.
The additional statistics are:
- Current tasks in progress - total count and task information
- Tasks completed after the due date - total count and task information with number of days delayed
- Display clients with total count of tasks

Other additional functionalities are:
- number of active and completed tasks per employee, which can be seen in the employees section
- mark a task as completed and set finish date to now.
- search by employee name and by task title
- seed example data from json file

As this is a task management application, I have decided that to be able to track the tasks it is important each task to have a TaskStatus. This is an enum that contains ACTIVE, PAUSED, CANCELLED and COMPLETED.
I have previously tracked tasks at work and I needed to have a distinction between due date and actual finish date. This is why I have also added finish date as a Task field.
Important part of my previous job was to have information about days delayed for each finished task and this is why I added this statistic in the project.
A manager needs to be able to see on which tasks employees are currently working, so current tasks in progress can be very useful.
To track the productivity of each employee it is good to have number of tasks active and completed per employee.
I have implemented an easy way for the user to mark a task as completed. It's finish date is set automatically to the current day.
It is also useful for the user to be able to easily find a task or a employee and that is why I implemented a search by title or by name.

Each task can be internal - for the company, or for a client's project. I have added a client entity, which for now has only id and name.
To simplify the assignment and be able to complete it on time, I have generated  example clients. CRUD actions for the clients can be further developed. For now the application shows a number of projects connected to each client.

In order to easily test the application I have implemented a way to import example employees and tasks. Of course the user can choose to insert their own examples.


In the end I would like to state that the Top 5 employees statistic should be further clarified. If each employee has completed the same number of tasks, there is no criteria to decide which one should be put in the top 5 and which one will drop to the sixth place and under.
There should be either a defined criteria by which each place can be defined in a certain way, or every place (first, second third, forth, fifth) can have a list of employees that completed the same number of tasks.



