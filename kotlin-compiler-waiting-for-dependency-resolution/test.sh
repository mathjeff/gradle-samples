set -e

echo starting test.sh
cd "$(dirname $0)"

function runTest() {
  gradleArgs="$@"
  export GRADLE_USER_HOME=gradle_user_home
  git clean -fdx
  killall -9 java
  echo ./gradlew "$gradleArgs"
  ./gradlew "$gradleArgs"
}

function buildOne() {
  echo "###################################################"
  echo Building one project
  runTest :project-with-deps-and-source:compileKotlin
  echo Done building one project. Notice the build duration
  echo "###################################################"
}


function buildBoth() {
  echo "##########################################################"
  echo Building both projects
  runTest compileKotlin
  echo Completed building both projects. Notice the build duration
  echo "##########################################################"
}

buildOne
buildBoth
buildBoth
buildOne

echo Done running some test builds
echo 'Does `./gradlew :project-with-deps-and-source:compileKotlin` take longer than `./gradlew compileKotlin`?'
echo "It may be that in the former case, the kotlin compiler doesn't start until after all dependencies are resolved"
