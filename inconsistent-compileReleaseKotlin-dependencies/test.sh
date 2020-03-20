set -e

echo starting test.sh
rm -rf out log log2

cd "$(dirname $0)"
export OUT_DIR=out
export GRADLE_USER_HOME=$OUT_DIR/.gradle
mkdir -p $OUT_DIR

echo starting first build
./gradlew --no-daemon                :activity:activity-ktx:compileReleaseKotlin :lifecycle:lifecycle-livedata:doResolve 2>&1 | tee log
if grep 'fail - task task.*does not contain' log; then
  echo "First grep gave expected result, continuing to second build"
else
  echo "First grep failed. Maybe the problem has been fixed? Yippee!"
  exit 1
fi

rm out -rf

echo
echo starting second build
./gradlew --no-daemon -PresolveFirst :activity:activity-ktx:compileReleaseKotlin :lifecycle:lifecycle-livedata:doResolve 2>&1 | tee log2

if grep 'pass - task task.*contains' log2; then
  echo
  echo "test.sh reproduced the problem."
  echo "Inputs of :activity:activity-ktx:compileReleaseKotlin seem to be affected by the presence or absence of the -PresolveFirst property"
  echo
  echo "Specifically, it seems that:"
  echo "  Task :activity:activity-ktx:compileReleaseKotlin"
  echo "  declares core-runtime-2.1.0-api.jar as an input if and only if"
  echo "  :androidx.lifecycle:lifecycle-livedata-core has already been resolved before the execution of this task"
else
  echo
  echo "Second grep failed. Maybe the problem has been fixed? Yippee!"
  exit 1
fi
