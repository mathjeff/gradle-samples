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

echo "Running first build to confirm that it succeeds"
if runBuild; then
  echo "First build passed"
else
  echo "First build failed - did not reproduce interesting error"
  exit 1
fi

COMPOSER="$(find -name Composer2.kt | sort | tail -n 1)"
cp $COMPOSER composer-backup

echo
echo "Making a small modification to $COMPOSER"

function modifyCode() {
  echo "sed 's/\( *\)\(private var compositionToken.*\)/\1\2\n\1private var sample1: Int = 0/' -i $COMPOSER"
  sed 's/\( *\)\(private var compositionToken.*\)/\1\2\n\1private var sample1: Int = 0/' -i $COMPOSER
}
modifyCode

echo
echo "Running second build to confirm that it fails"

if runBuild; then
  echo "second build passed - did not reproduce interesting error"
  cp composer-backup $COMPOSER
  exit 1
else
  cp composer-backup $COMPOSER
  if grep "Cannot access.*Modifier" log; then
    echo
    echo "Second build failed with appropriate error - reproduced the interesting error: see ./log"
  else
    echo
    echo "Second build failed with wrong error - did not reproduce interesting error."
    exit 1
  fi
fi
