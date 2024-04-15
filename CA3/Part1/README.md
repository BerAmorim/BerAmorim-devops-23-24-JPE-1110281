# Setup and Execution Guide for Development Projects in a Virtual Machine

This README outlines the steps taken to set up and test different development projects on a virtual machine (VM) running Ubuntu 22.04.3 LTS. The focus is on Java-based applications using Spring Boot and Gradle.

## Getting Started

These instructions will get you a copy of the projects up and running on your local machine for development and testing purposes.

## Prerequisites

Make sure you have the following installed:

- VirtualBox or another VM software
- SSH client (for remote connection to the VM)
- Access to a command line interface

## Setup Steps

### Step 1: SSH into the Virtual Machine

First, remotely connect to the VM using SSH. The IP address used was 192.168.64.3, as found in the network configuration of the VM.

```bash
ssh bernardo@192.168.64.3
```

Upon connection, you are greeted by the system status which includes the memory usage, system load, and available updates.

### Step 2: Clone the Spring Boot and Gradle Project
Inside the VM, clone the repository containing the Spring Boot and Gradle project:

```bash
git clone https://github.com/spring-guides/tut-react-and-spring-data-rest.git
cd tut-react-and-spring-data-rest/basic
```

### Step 3: Install JDK and Handle Dependencies
Attempt to run the Spring Boot application. Notice an error about JAVA_HOME not being set, which indicates that Java is not properly configured.

```bash
./mvnw spring-boot:run
```

Realize Java needs to be installed and configured:
    
```bash
sudo apt install openjdk-8-jdk-headless
```

However, encounter issues with fetching the package, indicating potential repository or network issues. Resolve this by updating the package list and retrying the installation:

```bash
sudo apt update
sudo apt install openjdk-8-jdk-headless --fix-missing
```

After successful installation, set JAVA_HOME and update alternatives to ensure Java commands direct to the correct Java version.

## Step 4: Configure JAVA_HOME
Add JAVA_HOME to your environment variables:

```bash
echo "export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64" >> ~/.bashrc
source ~/.bashrc
```

## Step 5: Rerun the Application
With Java installed and configured, rerun the Spring Boot application:

```bash
./mvnw spring-boot:run
```

## Step 6: Access the Web Application from the Host Machine
To verify the web application is running correctly, access it from a browser on your host machine using the VM's IP address and the appropriate port.

## Step 7: Cloning and Setting Up Your Specific Project Repository

Cloning the Repository
Attempted to clone the repository using SSH but encountered permission issues, likely due to missing or misconfigured SSH keys.

```bash
git clone git@github.com:BerAmorim/BerAmorim-devops-23-24-JPE-1110281.git
```
After confirming the authenticity of the host, the connection failed due to permission denial, indicating an issue with the public key setup:

```plaintext
The authenticity of host 'github.com (140.82.121.3)' can't be established.
ED25519 key fingerprint is SHA256:+DiY3wvvV6TuJJhbpZisF/zLDA0zPMSvHdkr4UvCOqU.
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added 'github.com' (ED25519) to the list of known hosts.
git@github.com: Permission denied (publickey).
fatal: Could not read from remote repository.
```

Switched to HTTPS for cloning which was successful:

```bash
git clone https://github.com/BerAmorim/BerAmorim-devops-23-24-JPE-1110281.git
```
Preparing the Project
Navigated to the project directory and listed its contents to verify the structure:

```bash
cd BerAmorim-devops-23-24-JPE-1110281
ls
```
Output included various directories and files, confirming the repository was cloned correctly.

Executing a Spring Boot Project
Navigated to the specific Spring Boot project directory within the repository:

```bash
cd CA1/basic
```

Attempted to run the Spring Boot project using Maven Wrapper:

```bash
./mvnw spring-boot:run
```
The application successfully compiled and ran, serving a web application on port 8080. The process included downloading dependencies, setting up Node.js and NPM, compiling JavaScript with Webpack, and executing the Java backend.


Access the Web Application
The web application is accessible via the browser on the host machine using the VM's IP address and port 8080:

```plaintext
http://192.168.64.3:8080
```

## Step 8: Configuring and Running the React and Spring Data REST Project

Setting Up the Environment
Faced issues with running the project due to Java version compatibility. The project required Java 17, but only Java 8 was initially installed.

Verified the installed Java version:

```bash
java -version
```

Attempted to install Java 17, but faced multiple delays due to locked dpkg processes caused by automatic upgrades running in the background.

```bash
sudo apt install openjdk-17-jdk-headless
```
After several attempts and waiting for other processes to release the lock, successfully installed Java 17 JDK and JRE headless versions.

Running the Project
Navigated to the project directory and executed the project using Gradle:

```bash
./gradlew bootRun
```
Resolving Gradle Issues
Initially faced build failures due to mismatching Java versions and incompatible dependencies. After updating to Java 17, these issues were resolved, and the application started successfully.

Application Output
The application started with Spring Boot, showing logs that confirm the server's initialization and the availability of the service on port 8080:

```plaintext
:: Spring Boot ::        (v3.2.4)

2024-04-15T14:55:10.457Z  INFO 35804 --- [           main] c.g.p.ReactAndSpringDataRestApplication  : Starting ReactAndSpringDataRestApplication using Java 17.0.10 with PID 35804...
Tomcat started on port(s): 8080 (http) with context path ''
```

## Accessing the Web Application
The application is accessible via the browser on the host machine by navigating to the VM's IP address and the specified port:

```plaintext
http://192.168.64.3:8080
```

## Step 9: Creating a New Project Component (CA3)

After working on the previous parts of the project, a new component, named CA3, was initiated. This step involves creating a new folder to host the components or functionalities that will be developed in this phase.

Commands Executed
Navigate to the root directory of the project:
    
```bash
cd ~/BerAmorim-devops-23-24-JPE-1110281
```
Create the directory CA3:

```bash
mkdir CA3
```

Enter the CA3 directory and create the README.md file for initial documentation:

```bash
cd CA3
touch README.md
```

Open the README.md file in a text editor to add the initial documentation.

## Tagging the Repository
At the end of the part 1 of this assignment mark your repository with the tag
ca3-part1:

```bash
git tag ca3-part1
```
```bash
git push origin ca3-part1
```


## Objective of Component CA3
The README.md file was created to facilitate future documentation of the developments and functionalities that will be added under this component.

This documentation template ensures that all steps and intentions are clearly recorded and easily accessible for review or continuation of work in future sessions.

