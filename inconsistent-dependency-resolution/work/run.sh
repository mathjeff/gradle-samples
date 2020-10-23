echo run.sh starting build
OUT_DIR=out ./gradlew --no-daemon -PverifyUpToDate >log 2>&1 :docs-fake:compilePublicReleaseJavaWithJavac :publicDocsTask :leanback:leanback-paging:mergeReleaseGeneratedProguardFiles
echo run.sh done with build
