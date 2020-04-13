This project demonstrates that this code:

  repositories {
    google()
    mavenLocal()
    jcenter()
  }
  subprojects { subproject ->
    subproject.repositories.addAll(subproject.rootProject.repositories)
  }

is about 0.08s faster to execute, on average, than this code:

  allprojects {
    repositories {
      google()
      mavenLocal()
      jcenter()
    }
  }

when settings.gradle declares ~400 projects.

Run test.sh to reproduce
