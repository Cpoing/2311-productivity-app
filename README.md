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

Getting Started:
1. Clone the repository
```git clone https://github.com/Cpoing/2311-productivity-app.git```
