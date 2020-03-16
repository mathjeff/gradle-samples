This project demonstrates an inconsistency in the dependencies of :activity:activity-ktx:compileReleaseKotlin

My guess is that the org.jetbrains.kotlin.android plugin is adding the core-runtime-2.1.0-api.jar artifact as an input to :activity:activity-ktx:compileReleaseKotlin if that file exists but is not declaring it as a dependency.

Run test.sh to reproduce the error
