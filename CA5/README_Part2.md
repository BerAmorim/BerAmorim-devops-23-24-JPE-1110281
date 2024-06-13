# CA5: Part2 README

The Readme is structure in 1 section:

- **Jenkins Steps**: This part of CA5 focuses on applying Continuous Integration and Delivery, by creating a pipeline in Jenkins to build the
  tutorial spring boot application (CA2/Part2 project);

## CA5: Jenkins Steps

___

### 1. Creating a new Pipeline

#### 1.1. Click on new Item

#### 1.2. Give the item a name and select the Pipeline option

#### 1.3. In the Pipeline section of the setup, link the Pipeline to your git repository:

- 1.3.1. Change the Definition to Pipeline Script from SCM;
- 1.3.2. Change the SCM to git;
- 1.3.3. Input your own repository (use the actual directory for this CA5/Part2);
- 1.3.4. Save;

### 2. Create the Jenkinsfile

#### 2.1. Add the Javadoc stage

- 1. Install the Javadoc Plugin in Jenkins
- 2. Use the handy Snippet Generator to generate the script we need for the Jenkinsfile
- 3. Add the script to the jenkinsfile in a Javadoc stage

#### 2.2. Install the HTML Publisher plugin in Jenkins
-1. Install the HTML Publisher Plugin in Jenkins
-2. Use the handy Snippet Generator to generate the script we need for the Jenkinsfile
-3. Add the script to the jenkinsfile in a Javadoc stage


### 3. Generate a Docker imagine and publish it

#### 3.1. Start by creating the Dockerfile:

```Dockerfile
FROM tomcat:9.0-jdk11-adoptopenjdk-hotspot

# Remove the default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the generated WAR file to the webapps directory of Tomcat
COPY build/libs/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
```

#### 3.2. Install the Docker plugins in Jenkins

#### 3.3. Update the Jenkinsfile with a new Stage
    
```groovy
pipeline {
agent any
environment {
DOCKER_PATH = '/usr/local/bin/docker'
PATH = "/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/sbin:/usr/local/bin"
DOCKER_IMAGE = "beramorim/ca5_part2-image"
}

    stages {
        stage('Check Environment Variables') {
            steps {
                sh 'echo "PATH: $PATH"'
                sh 'echo "DOCKER_PATH: $DOCKER_PATH"'
                sh '$DOCKER_PATH --version'
                sh 'docker --version'
            }
        }
        stage('Checkout') {
            steps {
                echo 'Checking out the code from the repository'
                git branch: 'main', url: 'https://github.com/BerAmorim/BerAmorim-devops-23-24-JPE-1110281.git'
            }
        }
        stage('Assemble') {
            steps {
                echo 'Assembling...'
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean assemble'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    sh './gradlew test'
                    junit 'build/test-results/test/*.xml'
                }
            }
        }
        stage('Javadoc') {
            steps {
                echo 'Generating Javadoc...'
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    sh './gradlew javadoc'
                    javadoc javadocDir: 'build/docs/javadoc', keepAll: false
                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'build/docs/javadoc', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: 'CA2 Part2', useWrapperFileDirectly: true])
                }
            }
        }
        stage('Archive') {
            steps {
                echo 'Archiving...'
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    archiveArtifacts artifacts: 'build/libs/*.war', fingerprint: true
                }
            }
        }
        stage('Publish Image') {
            steps {
                echo 'Building and pushing Docker image...'
                dir('CA2/Part2/react-and-spring-data-rest-basic') {
                    script {
                        def appImage = docker.build("${env.DOCKER_IMAGE}:${env.BUILD_NUMBER}", ".")
                        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                            appImage.push()
                            echo "Built and pushed image with tag: ${env.DOCKER_IMAGE}:${env.BUILD_NUMBER}"
                        }
                    }
                }
            }
        }
    }
}
```
Push the changes and run the pipeline!

## We are done with Part2 of the Assignment!
