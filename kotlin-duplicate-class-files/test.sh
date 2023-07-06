#!/bin/bash
set -e
export GRADLE_USER_HOME=out
set -o pipefail
function runBuild() {
  echo "./gradlew --no-daemon :compose:ui:ui:compileTestKotlinDesktop > log 2>&1"
  ./gradlew --no-daemon :compose:ui:ui:compileTestKotlinDesktop > log 2>&1
}

rm out -rf
find -name build | xargs --no-run-if-empty rm -rf
find -name .gradle | xargs --no-run-if-empty rm -rf 

if runBuild; then
  echo "First build passed"
else
  echo "First build failed - did not reproduce interesting error"
  exit 1
fi

echo searching for Modifier2.class
find -name Modifier2.class | tee log
if cat log | wc -l | grep 2 >/dev/null; then
  echo "Found multiple copies of Modifier2.class"
else
  echo "Did not reproduce the error: all copies of Modifier2.class:"
  exit 1
fi
