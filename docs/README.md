# User Guide

## Features
**Note**: Words in `UPPER_CASE` are the parameters to be supplied by the user. 
E.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as in `todo Call mum`.

### Adding a task (to-do, deadline, event)
Adds a task to the task list.

### Marking a task as completed
Marks a task in the task list as completed.

### Deleting a task
Deletes a task from the task list.

### Exiting the program
Exits the program.

### Finding tasks
Searches for tasks in the task list.

### Displaying the task list
Shows the whole task list.

### Saving the data
Saves the task list data in the hard disk.

## Usage

### `todo` - Adding a to-do
Adds a to-do to the task list. 

Format: `todo DESCRIPTION`
- `DESCRIPTION` cannot be blank.

Example of usage:

`todo Read books`

Expected outcome:

Adds a to-do with the description `Read books` to the task list.

### `deadline` - Adding a deadline
Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by DUE_DATE`
- `DESCRIPTION` cannot be blank.
- `DUE_DATE` **must be in the format YYYY-MM-DD**.

Example of usage:

`deadline Finish homework /by 2020-10-01`

Expected outcome:

Adds a deadline with the description `Finish homework` and a due date at October 10 2020.

### `event` - Adding an event
Adds an event to the task list.

Format: `event DESCRIPTION /at DATE_TIME`
- `DESCRIPTION` cannot be blank.
- `DATE_TIME` can be in any format.

Example of usage:

`event Movie screening /at December 24 4pm-6pm`

Expected outcome:

Adds an event with the description `Movie screening`, happening at `December 24 4pm-6pm`.

### `done` - Marking a task as completed
Marks a task in the task list as completed.

Format: `done TASK_NUMBER`
- `TASK_NUMBER` **must be a positive integer**.

Example of usage:

`done 2`

Expected outcome:

Marks the second task in the list as completed.

### `delete` - Deleting a task
Deletes a task from the task list.

Format: `delete TASK_NUMBER`
- `TASK_NUMBER` **must be a positive integer**.

Example of usage:

`delete 3`

Expected outcome:

Deletes the third task in the task list.

### `bye` - Exiting the program
Exits the program.

Format: `bye`

### `find` - Finding tasks
Finds tasks that contain the search phrase.

Format: `find SEARCH_PHRASE`
- The search is case insensitive.
- All tasks containing the search phrase are returned.

Example of usage:

`find read`

Expected outcome:

Returns tasks that contain the word `read`.

### `list` - Displaying the task list
Shows the whole task list.

Format: `list`

### Saving the data
Task list data is saved automatically in the hard disk after any command that changes the data.
There is no need to save manually.
