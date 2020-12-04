# Assignment 4
![Screenshot](https://github.com/TerranUp16/cnt-4714-assignment-4/blob/main/screenshots/0_start.png)
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
* [Java 11](https://www.oracle.com/java/technologies/javase-downloads.html)
* [Apache Maven](https://maven.apache.org/download.cgi)
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

## Troubleshooting
> `mvn` not found

Please review [Maven's installation and configuration documentation](https://maven.apache.org/install.html). In particular, make sure `mvn` is configured for `PATH` for the shell/terminal you are using.

> `JAVA_HOME` not found

Please review [Maven's installation and configuration documentation](https://maven.apache.org/install.html). In particular, make sure `JAVA_HOME` is configured for `PATH` for the shell/terminal you are using.

Please also review Oracle's documentation on this for-

* [Windows](https://docs.oracle.com/en/java/javase/14/install/installation-jdk-microsoft-windows-platforms.html#GUID-96EB3876-8C7A-4A25-9F3A-A2983FEC016A)
* [Mac](https://docs.oracle.com/en/java/javase/14/install/installation-jdk-macos.html#GUID-F9183C70-2E96-40F4-9104-F3814A5A331F)
* [Linux](https://docs.oracle.com/en/java/javase/14/install/installation-jdk-linux-platforms.html#GUID-737A84E4-2EFF-4D38-8E60-3E29D1B884B8)

## Sample Screenshot
![Query performed on data](https://github.com/TerranUp16/cnt-4714-assignment-4/blob/main/screenshots/5d_refined.png)
