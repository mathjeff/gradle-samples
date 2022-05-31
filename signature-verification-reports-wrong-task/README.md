This project demonstrates Gradle outputting the wrong task name when reporting a dependency verification failure

When I run `./gradlew --no-daemon buildOnServer` I get output containing:

```
  * What went wrong:
  Execution failed for task ':compose:foundation:foundation-lint:jar'.
  > Dependency verification failed for configuration ':compose:foundation:foundation-lint:bundleInside':
      - On artifact logback-classic-1.2.11.pom (ch.qos.logback:logback-classic:1.2.11) in repository 'maven': Artifact was signed with key '475f3b8e59e6e63aa78067482c7b12f2a511e325' but signature didn't match
```

When I run `:compose:foundation:foundation-lint:jar`, I find that the build passes

When I run `./gradlew --no-daemon buildOnServer` I get output containing:

```
  * What went wrong:
  Execution failed for task ':compose:foundation:foundation-lint:ktlint'.
  > Dependency verification failed for configuration ':compose:foundation:foundation-lint:ktlint':
      - On artifact logback-classic-1.2.11.pom (ch.qos.logback:logback-classic:1.2.11) in repository 'maven': Artifact was signed with key '475f3b8e59e6e63aa78067482c7b12f2a511e325' but signature didn't match
```


Run test.sh to reproduce the error
