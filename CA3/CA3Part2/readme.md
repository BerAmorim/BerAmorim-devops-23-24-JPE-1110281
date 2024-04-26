# CA3 Part 2 - Vagrant Environment Setup for Spring Boot Application

## Overview

This part of the assignment involves setting up a virtual environment using Vagrant to run the Spring Boot "basic" application developed in CA2, Part 2.
The setup includes two virtual machines (VMs): one for running Tomcat with the Spring Boot application and another for running the H2 database server.

## Steps

### 1. Initial Solution

The initial Vagrant configuration is based on the repository at https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/.
This configuration is a starting point that provides the necessary setup for creating and provisioning the two VMs.

### 2. Vagrantfile Study

The `Vagrantfile` from the initial solution is studied to understand how it creates and provisions the two VMs:
- `web`: This VM is configured to run Tomcat and the Spring Boot "basic" application.
- `db`: This VM is for executing the H2 server database.

For students with MacBooks that have M1/M2 chips, special considerations are noted in the `readme.md` to ensure compatibility.

### 3. Vagrant Environment Setup

Install Vagrant
```bash
brew install vagrant
```

Install QEMU/libvirt (even if you have UTM installed)
```bash
brew install qemu
brew install libvirt
```
Install vagrant from Hashicorp tap (preferred over community version)
```bash
brew install hashicorp/tap/hashicorp-vagrant
```

Install vagrant provider vagrant-qemu (run following command on terminal)
```bash
vagrant plugin install vagrant-qemu
```

### 4. Vagrant Environment Configuration

Open the terminal

Clone the repository
```bash
git clone https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/
```

Create a new Folder for the Spring Boot application
```bash
mkdir /Users/Bernardo/DEVOPS-1110281/CA3/CA3Part2
```

Copy the contents of the macOS folder (inside the folder for this assignment)
```bash
cp -R vagrant-multi-spring-tut-demo/macOS/* /Users/Bernardo/DEVOPS-1110281/CA3/CA3Part2
```

Check the contents of the folder
```bash
ls -l /Users/Bernardo/DEVOPS-1110281/CA3/CA3Part2
```

### 5. Vagrant Environment Execution

Change to the folder for the Spring Boot application
```bash
cd /Users/Bernardo/DEVOPS-1110281/CA3/CA3Part2
```

Update the Vagrantfile configuration so that it uses your own gradle version of the spring application
```bash
nano Vagrantfile
```

# Update the following lines in the Vagrantfile
```
      git clone https://github.com/BerAmorim/BerAmorim-devops-23-24-JPE-1110281.git
      cd BerAmorim-devops-23-24-JPE-1110281/CA2/Part2/react-and-spring-data-rest-basic
      chmod u+x gradlew
      ./gradlew clean build
      nohup ./gradlew bootRun > /home/vagrant/spring-boot-app.log 2>&1 &
      # To deploy the war file to tomcat9 do the following command:
      # sudo cp ./build/libs/basic-0.0.1-SNAPSHOT.war /var/lib/tomcat9/webapps
```

Run the Vagrant environment
```bash
vagrant up
```

### 6. Access the Spring Boot Application

Access the Spring Boot application in the browser
```bash
localhost:8080
```

### 7. Access the H2 Database

Access the H2 database in the browser
```bash
localhost:8082
```

### 8. Tag the Repository

Tag the repository with the name `CA3Part2`
```bash
git tag CA3Part2
```

### 9. Conclusion

This completes the setup of the Vagrant environment for running the Spring Boot application developed in CA2, Part 2.

