# CA5: Part1 README

### 0. Install jenkins
```bash
brew install jenkins-lts
```

### 1. Start jenkins
```bash
brew services start jenkins-lts
```

### 2. Check to see if you have jenkins running locally:

If you didn't specify anything different from the basic setup head on to

```localhost:8080```

### 3. Unlock jenkins

```bash
cat /Users/Bernardo/.jenkins/secrets/initialAdminPassword
```