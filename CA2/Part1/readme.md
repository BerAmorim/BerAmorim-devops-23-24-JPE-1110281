# Technical Report: Part 1 of CA2 - Gradle Practice

## Introduction

This technical report documents the implementation of Part 1 of CA2, focusing on practicing Gradle with a simple example. The main goal is to modify and extend the functionality of a basic chat application, `gradle_basic_demo`, by introducing several Gradle tasks and adjustments.

## Setting Up the Project

1. **Cloning the Example Application**
   The project began by cloning the example application from the provided Bitbucket repository.

   ```bash
   git clone https://bitbucket.org/pssmatos/gradle_basic_demo.git
   cd gradle_basic_demo
   ```

   This step ensured we had a solid starting point, working with a predefined Gradle project structure.

## Integration of `gradle_basic_demo` into the Repository

During the initial setup, the `gradle_basic_demo` was inadvertently added as a gitlink, which was not the intended structure for this assignment. To correct this and ensure that `gradle_basic_demo` is integrated as a standard set of files within our repository, the following steps were taken:

**Removing the Gitlink from the Repository**
The reference to the gitlink was removed without deleting the actual files from the local filesystem:

```bash
git rm --cached CA2/Part1/gradle_basic_demo
```

This command removes the gitlink reference from the Git index, preserving the physical files.

**Navigating to the `gradle_basic_demo` Directory**
It was necessary to ensure operations were performed in the correct directory:

```bash
cd gradle_basic_demo
```

**Deleting the `.git` Directory within `gradle_basic_demo`**
To stop treating `gradle_basic_demo` as a separate Git repository, the `.git` directory within it was removed:

```bash
rm -rf .git
```

*Note: This action permanently deletes the `.git` directory and all local Git history for `gradle_basic_demo`.*

**Returning to the Project's Root Directory**
After removing the `.git` directory, the next step was to return to the project's root directory to add `gradle_basic_demo` to the repository:

```bash
cd ../..
```

**Adding the Project Files to the Repository**
The `gradle_basic_demo` files were then added to the repository:

```bash
git add CA2/Part1/gradle_basic_demo
```

**Committing the Changes**
A commit was made to include a clear and explanatory message about the action:

```bash
git commit -m "Integrated gradle_basic_demo as normal files into the repository"
```

**Pushing the Changes to the Remote Repository**
Finally, the changes were pushed to the remote repository to reflect the updated structure:

```bash
git push origin main
```

These steps resolved the issue and integrated `gradle_basic_demo` as a normal part of the repository, rather than as a submodule or gitlink. This adjustment ensures that the project structure aligns with the requirements of CA2 Part 1, facilitating straightforward navigation and interaction with the `gradle_basic_demo` within the broader project context.

## Implementing the Requirements

2**Adding a New Task to Execute the Server**
   A Gradle task named `runServer` was added to `build.gradle` to start the chat server easily.

   ```groovy
   task runServer(type: JavaExec) {
       main = 'basic_demo.ChatServerApp'
       classpath = sourceSets.main.runtimeClasspath
       args '59001'
   }
   ```

   This allowed us to start the server using `./gradlew runServer`, simplifying the process of testing the application.
3**Adding a Unit Test and Updating Gradle Script**
   JUnit 4.12 was added as a dependency in `build.gradle` to support unit testing:

   ```groovy
   testImplementation 'junit:junit:4.12'
   ```

   A simple unit test was created for the application, ensuring that future modifications do not break existing functionality.
4**Creating a Backup Task**
   To backup the source files, a task of type `Copy` named `backupSources` was added:

   ```groovy
   task backupSources(type: Copy) {
       from 'src'
       into 'backup'
   }
   ```

   This task helps in maintaining a backup of the source files, which is crucial for recovery in case of accidental deletions or modifications.
5**Archiving Source Files into a Zip File**
   A task named `zipSources` was implemented to archive the source files into a zip format, facilitating easy distribution and versioning of the source code.

   ```groovy
   task zipSources(type: Zip) {
       from 'src'
       archiveBaseName.set('source-archive')
       destinationDirectory.set(file('build/distributions'))
   }
   ```
6**Tagging the Repository**
   Finally, the repository was tagged with `ca2-part1` to mark the completion of Part 1 of the assignment.

   ```bash
    git tag -a ca2-part1 -m "Completed Part 1 of CA2"
    git push origin ca2-part1
    ```
## Conclusion

This report outlined the steps taken to meet the requirements of Part 1 of CA2. Each implemented feature was backed by a rationale aimed at improving the project's build process, maintainability, and usability. By following this tutorial-style documentation, one can reproduce the assignment and understand the decision-making process behind each task.

