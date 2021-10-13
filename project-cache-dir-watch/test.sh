set -e

cd "$(dirname $0)"


function runCommand() {
  args="$*"
  echo "$args"
  eval "$args"
}

runCommand ./gradlew :help
runCommand ./gradlew --project-cache-dir gradle-project-cache :help
