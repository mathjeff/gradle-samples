set -e

echo "killing all java processes to make it easy to identify which gradle test executor was spawned by this build"
killall java || true

./gradlew test
