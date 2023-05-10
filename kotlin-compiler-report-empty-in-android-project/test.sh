set -e

cd "$(dirname $0)"


function runCommand() {
  args="$*"
  echo "$args"
  eval "$args"
}

separator="****************************************"
echo "$separator"
runCommand rm -rf build
echo "$separator"
runCommand ./gradlew assembleDebug --rerun-tasks | cat
echo "$separator"
echo All kotlin-compiler reports:
find -name "kotlin-compiler-report*"
echo "$separator"

if find -name "kotlin-compiler-report*" | xargs --no-run-if-empty grep -H "No Kotlin task was run"; then
  echo "See that after running compileDebugKotlin, the kotlin build report says no Kotlin task was run"
  exit 1
else
  echo "The problem is resolved. Yippee!"
fi
