set -e

cd "$(dirname $0)"

function run() {
  echo "$*"
  eval "$*"
}

run ./gradlew compileKotlin --rerun-tasks
echo
echo "Notice that no verbose compiler output appears above."
echo
echo "However, notice that kotlin compilation was successful"
echo
run "ls build/classes/kotlin/main"
echo
echo "Also notice that some verbosity settings were given to the compile task"
echo
run tail -n 3 build.gradle
