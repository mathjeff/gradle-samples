set -e

function echoAndDo() {
  echo "$@"
  eval "$*"
}

function runTest() {
  runPreZip="$1"
  rm -rf .gradle build maven-publication/build
  mkdir -p maven-publication/build
  echo "********************************************************************************"
  echoAndDo "GRADLE_USER_HOME=build/gradle-user-home ./gradlew zipPublication --info --no-daemon -PpreZip=$runPreZip | tee maven-publication/build/log"
  echo "********************************************************************************"
  echo "runPreZip=$runPreZip"
  grep "> Task.*zipPublication" maven-publication/build/log
}

runTest true
runTest false

echo Notice that when runPreZip=true then zipPublication has a status of NO-SOURCE
