This project demonstrates a build failure when a module dependency gets replaced (via dependencySubstitution) with a project dependency

The error is:

  Could not resolve all files for configuration ':jeff-fragment:debugCompileClasspath'.
  > Could not find jeff-core.aar (project :jeff-core).

Run test.sh to reproduce the error
