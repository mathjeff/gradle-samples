set -e

cd "$(dirname $0)"


function runCommand() {
  args="$*"
  echo "$args"
  eval "$args"
}

runCommand GRADLE_USER_HOME="gradle-user-home" ./gradlew --no-daemon -q
echo "************************************************************************"
echo "Notice that the output of System.getProperty doesn't match the GRADLE_USER_HOME environment variable"
