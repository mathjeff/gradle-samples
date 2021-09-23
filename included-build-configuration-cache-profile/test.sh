set -e

cd "$(dirname $0)"


function runCommand() {
  args="$*"
  echo "$args"
  eval "$args"
}

runCommand ./gradlew :help --configuration-cache --profile
runCommand ./gradlew :help --configuration-cache --profile
