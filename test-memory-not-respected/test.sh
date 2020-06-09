set -e

killall java || true

./gradlew test
