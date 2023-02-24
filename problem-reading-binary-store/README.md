This is a sample project demonstrating an error in reading a binary store:

  Configuration cache state could not be cached: field `classpath` of task `:compose:material3:material3:compileReleaseJavaWithJavac` of type `org.gradle.api.tasks.compile.JavaCompile`: error writing value of type 'org.gradle.api.internal.file.collections.DefaultConfigurableFileCollection'
  > Could not resolve all dependencies for configuration ':compose:material3:material3:releaseRuntimeClasspath'.
     > Problems reading data from Binary store in /usr/local/google/home/jeffrygaston/.gradle/.tmp/gradle12347434936544241483.bin offset 182517 exists? true

Run test.sh to reproduce
