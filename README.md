# Productivity App EECS2311 Team 6
## Abstract

### ITR0 Customer Meeting Video
[![Watch the video](https://github.com/AntMa4002/2311-productivity-app/assets/114103022/4ee77143-eb48-44bf-898b-29a7f73fd19a
)](https://youtu.be/PCvcRVsgMSM)

## Demo Trimmed Customer Meeting Video
[![Watch the video](https://github.com/AntMa4002/2311-productivity-app/assets/114103022/4ee77143-eb48-44bf-898b-29a7f73fd19a
)](https://youtu.be/b8aDrwF0bdo)

ITR3:

Major changes:
- Structure of the code changed so that all of the components are linked with the database
- Behaviour of adding tasks changed so that the user can add the specific hour and minute of the task being due and instead of typing in the priority level of high, medium or low, users can press either of three buttons that do the same thing.
- Behaviour of tasks changed so that they become invalid after the due date has passed encouraging users to complete their tasks on time.

Issues:
- In the progress tracker chart, we originally planned for it to track across sessions but it turned out to be counterproductive as when the tracker is opened for a long time then it becomes hard to read all of the timestamps. To resolve this issue we opted for it to become session specific so it resets when the user logs back in.
- We were not able to solve the issue of users being able to make tasks without having to add a due date and users not being able to change due dates of existing tasks as we believed it to be an insignificant issue to fix for this iteration.
- The Pomodoro timer stays open when the main application is closed. This issue was not able to be resolved this iteration as the timer opens up as a new application. 

**Getting Started:**
1. **Clone the repository**
```git clone https://github.com/Cpoing/2311-productivity-app.git```
2. **Set up the database:**
   Make sure you have PostgreSQL installed on your machine. Then go to the Tables section from Servers > PostgreSQL 16 > postgres > Schemas > public > Tables.
   Make the tables login_table, notes, scores_table, and task_tables with the properties shown below.
![login_table](https://github.com/Cpoing/2311-productivity-app/assets/118622427/4ecfc53f-706b-4306-a894-75ccd0e5de33)
![image](https://github.com/Cpoing/2311-productivity-app/assets/118622427/c0def512-2639-424e-b521-9ccf4656ba55)
![image](https://github.com/Cpoing/2311-productivity-app/assets/118622427/c878c7ef-0f85-45d3-81fe-aa84442fe1c7)
![image](https://github.com/Cpoing/2311-productivity-app/assets/118622427/19850614-db46-46f3-8313-00b430a6a44d)
   NOTE: in the notes table the number column's datatype is SERIAL not integer but it switches to integer after saving
3. Open the DB.java file, under the public boolean init() method, change the password to the password used to open Postgresql.
   ```String password = "your_password";```
4. Download an mp3 file and open the music player button, it will prompt a file search and select the music file.
