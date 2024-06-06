# CA5: Part1 README

### 0. Install jenkins
```bash
brew install jenkins-lts
```

### 1. Start jenkins
```bash
brew services start jenkins-lts
```

### 3. Unlock jenkins

```bash
cat /Users/Bernardo/.jenkins/secrets/initialAdminPassword
```

### 4. Edit the Jenkins Configuration File

1. Navigate to the directory returned by the previous command:
```bash
brew --prefix jenkins-lts
```

2. Navigate to the `Home` directory:
```bash
cd $(brew --prefix jenkins-lts)
```

3. Find the configuration file:
```bash
sudo nano homebrew.mxcl.jenkins-lts.plist
```

4. Inside the file, look for the line that defines the HTTP port (usually something like --httpPort=8080) and change it to --httpPort=9090. The line should look like this:
```bash
<string>--httpPort=9090</string>
```

### 5. Restart Jenkins
```bash
brew services restart jenkins-lts
```

### 6. Access Jenkins
1. Open a browser and navigate to `http://localhost:9090`



