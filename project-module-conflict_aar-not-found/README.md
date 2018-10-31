This project demonstrates a build failure when a transitive artifact dependency gets upgraded by a project dependency

The dependencies are:

  dependencies of project jeff-fragment:
     +--- project :jeff-core
     \--- androidx.jeff-loader:jeff-loader:1.0.0
          \--- androidx.jeff-core:jeff-core:1.0.0 -> project :jeff-core

The error is:

  Could not resolve all files for configuration ':jeff-fragment:debugCompileClasspath'.
  > Could not find jeff-core.aar (project :jeff-core).

So far it seems to me that what's going on is:
  1. Something in the Android Gradle Plugin is visiting dependencies of type external aar module
  2. androidx.jeff-loader:jeff-loader:1.0.0 depends on androidx.jeff-core:jeff-core:1.0.0
  3. The visitor is supposed to be visiting the androidx.jeff-loader:jeff-loader:1.0.0 -> androidx.jeff-core:jeff-core:1.0.0 dependency is instead seeing project :jeff-core
  4. This visitor doesn't know what to do with a project and says it cannot find a .aar inside it.

Run test.sh to reproduce the error
