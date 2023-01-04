set -e

cd "$(dirname $0)"


function runCommand() {
  args="$*"
  echo "$args"
  eval "$args"
}

separator="****************************************"
echo "$separator"
runCommand ./gradlew :help --configuration-cache | tee log
echo "$separator"
runCommand ./gradlew :help --configuration-cache | tee log
echo "$separator"
runCommand ./gradlew :help --configuration-cache -Punused.value=true | tee log
echo "$separator"
if grep -i "reusing" log; then
  echo "The problem is resolved. Yippee!"
else
  echo "Notice that setting an unused Gradle property caused the configuration cache to be invalidated"
  exit 1
fi
