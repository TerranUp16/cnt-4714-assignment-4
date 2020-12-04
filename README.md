# Assignment 4
![Screenshot](https://github.com/TerranUp16/cnt-4714-assignment-4/blob/main/screenshots/git_header.png)
This project was created under the direction of Dr. Mark Llewellyn for University of Central Florida course CNT-4714.

This project calls for a three-tier, distributed web application. The frontend uses `.jsp`, middle-tier processing is handled via Tomcat servlet, and MySQL provides the database backend (which the Tomcat servlet communicates directly with).

This project comes with a predefined dataset and is configured to hook exclusively to that dataset.

## Usage
The web interface provides an input box for MySQL commands to be issued from. This form is already hooked to the `Project4` database running in a MySQL 8 container. Clicking `Execute Command` runs the current content of the input box.

If a valid `SELECT` command is issued, results will be displayed below in a table.

If a valid `UPDATE|INSERT|DELETE` command is issued, the query will be run, the number of rows updated will be provided below, and the project's business logic will be executed.

If an invalid query is issued, an error message will be displayed below.

## Business Logic
Any changes to the `shipments` table which result in a row going over 100 quantity will cause the associated supplier's `status` to be increased by `5`. When this logic runs, a notification will appear which also clarifies how many rows were changed as a result of the logic.

# Dependencies
* [Docker Desktop](https://www.docker.com/products/docker-desktop)

# Install and Run
## Using `git`
```
git clone https://github.com/TerranUp16/cnt-4714-assignment-4.git ;
cd cnt-4714-assignment-4 ;
powershell .\startService.ps1 ;
```

In a web browser of your choice, navigate to [http://localhost:8080/Project4](http://localhost:8080/Project4)

## Download Code
1. [Download `.zip`](https://github.com/TerranUp16/cnt-4714-assignment-4/archive/master.zip)
2. Unzip `cnt-4714-assignment-4-master.zip`
3. Open a shell/terminal application (ex- PowerShell on Windows, Terminal on Mac)
4. Navigate to extracted `cnt-4714-assignment-4-master` directory/folder
5. Run `powershell .\startService.ps1 ;`

In a web browser of your choice, navigate to [http://localhost:8080/Project4](http://localhost:8080/Project4)

## Stopping Containers
In the same directory you started the containers from with `powershell .\startService.ps1`, run `powershell .\stopService.ps1`.

## Sample Screenshot
![Query performed on data](https://github.com/TerranUp16/cnt-4714-assignment-4/blob/main/screenshots/5d_refined.png)
