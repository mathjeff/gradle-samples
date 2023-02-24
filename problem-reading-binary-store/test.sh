set -e
cd "$(dirname $0)"

./gradlew :compose:material3:material3:compileReleaseJavaWithJavac
