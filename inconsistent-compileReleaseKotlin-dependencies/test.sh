set -e

echo starting test.sh
rm -rf out log log2

cd "$(dirname $0)"
export OUT_DIR=out
export GRADLE_USER_HOME=$OUT_DIR/.gradle
mkdir -p $OUT_DIR

echo starting first build
./gradlew --no-daemon                :activity:activity-ktx:compileReleaseKotlin :lifecycle:lifecycle-livedata:doResolve 2>&1 | tee log
if grep 'task task.*does not contain' log; then
  echo first grep passed, continuing to second build
else
  echo first grep failed
  exit 1
fi

rm out -rf

echo
echo starting second build
./gradlew --no-daemon -PresolveFirst :activity:activity-ktx:compileReleaseKotlin :lifecycle:lifecycle-livedata:doResolve 2>&1 | tee log2
if grep 'task task.*contains' log2; then
  echo test.sh passed
else
  echo second grep failed
  exit 1
fi
