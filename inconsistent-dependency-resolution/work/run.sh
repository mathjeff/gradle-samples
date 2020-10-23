echo run.sh starting build, displaying contents in stdout

# A warmup build. This build isn't actually required, but the fact that it doesn't prevent the problem from reproducing is interesting
OUT_DIR=out ./gradlew --no-daemon :leanback:leanback-paging:compileReleaseJavaWithJavac

echo run.sh starting second build, saving contents to log
OUT_DIR=out ./gradlew --no-daemon -PverifyUpToDate >log 2>&1 :publicDocsTask :leanback:leanback-paging:compileReleaseJavaWithJavac

echo run.sh done with builds
