This project demonstrates a build failure when a transitive artifact dependency gets upgraded by a project dependency

The dependencies are:

  dependencies of project jeff-fragment:
     +--- project :jeff-core
     \--- androidx.jeff-loader:jeff-loader:1.0.0
          \--- androidx.jeff-core:jeff-core:1.0.0 -> project :jeff-core

The error is:

  Could not resolve all files for configuration ':jeff-fragment:debugCompileClasspath'.
  > Could not find jeff-core.aar (project :jeff-core).


Run test.sh to reproduce the error
