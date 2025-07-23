# Command Line Interface (CLI) Task Manager app

A simple app that lets you keep track of on-going tasks. You can create, update, delete, or change the status of your tasks. Made as the first **roadmap.sh**'s backend project.

## Features
1. **Add a task:** creates a new task with a description.
2. **Update a task:** changes the description of an existing task.
3.  **Delete a task:** deletes an existing task.
4. **Change a task's status:** updates the status of an existing task.
5. **List tasks:** lets you see a list of all the task, or filter them by their status.

## Installation
1. **Clone the repository:**

   ```bash
   git clone https://github.com/zdarius3/Task-Tracker-CLI
   cd task_tracker_cli
   ```

2. **Compile the source code:**
    ```bash
    javac -d bin src/cli/CommandLine.java src/logic/*.java
    ```
    

3. **Execute the program:**
    ```bash
    java -cp bin cli.CommandLine
    ```

## Usage
```bash
#Adding a task
add "new task"
#Output: Task added successfully.

#Updating an existing task, assume there exists a task with ID 3
update 3 "updating task description"
#Output: Task updated successfully

#Deleting a task, assume there exist a task with ID 3
delete 3
#Output: Task deleted successfully.

#Marking a task as in-progress, assume there exist a task with ID 3
mark-in-progress 3 
#Output: Task status updated successfully.

#Marking a task as done, assume there exist a task with ID 3
mark-done 3
#Output: Task status updated successfully.

#Listing all tasks
list
#Output: all registered tasks and their properties.

#Listing tasks by status
list todo
list in-progress
list done
#Output: all registered tasks with the specified status, and their properties.

```