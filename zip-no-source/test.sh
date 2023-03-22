set -e

function echoAndDo() {
  echo "$@"
  eval "$*"
}

function runTest() {
  declareOutputs="$1"
  rm .gradle build -rf
  mkdir build
  echo "********************************************************************************"
  echoAndDo "./gradlew packageOtherOutput debugOutputs --info -P declareOutputs='$declareOutputs' | tee build/log"
  echo "********************************************************************************"
  echo "The status of packageOtherOutput"
  grep "> Task.*packageOtherOutput" build/log
}

runTest false
runTest true

echo

echo Notice that when declareOutputs=false that packageOtherOutput has a status of NO-SOURCE
