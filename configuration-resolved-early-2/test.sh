set -e

cd "$(dirname $0)"
export GRADLE_USER_HOME=out
#rm $GRADLE_USER_HOME -rf

function runGradle() {
  args="$@"
  echo "./gradlew $args"
  ./gradlew $args
}

echo "************************************************************************"
echo Starting build
runGradle --no-daemon :paging:samples:build --dry-run --console=plain --info 2>&1 | tee log || true
echo "Checking for configurations resolved too early"
grep "was resolved during configuration" log || true
echo "End of Configurations that were resolved during configuration time"
