This project demonstrates that the kotlin compiler does not start until all of the dependencies of the ilibrary to compile are resolved, rather than merely the dependencies of the kotlin compiler itself.

In my tests I observe that the build is about 4-6 seconds slower than when including an empty kotlin project in the settings.gradle for the sole purpose of starting the kotlin compiler earlier

Run test.sh to reproduce this behavior
