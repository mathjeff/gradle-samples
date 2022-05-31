#!/bin/bash
set -e

cd "$(dirname $0)"
export GRADLE_USER_HOME=out
#rm $GRADLE_USER_HOME -rf

function runGradle() {
  separator="************************************************************************"
  args="$@"
  echo "$separator"
  echo "./gradlew $args"
  if ./gradlew $args; then
    RETURN_CODE=0
  else
    RETURN_CODE=1
  fi
  echo "$separator"
  return $RETURN_CODE
}

echo Starting build
mkdir -p out
if runGradle --no-daemon buildOnServer > out/gradle.log 2>&1 ; then
  echo "Build succeeded. Failed to reproduce the problem."
  exit 1
fi
cat out/gradle.log


echo Checking which task was listed as the failing task
if grep --color "Execution failed for task ':compose:foundation:foundation-lint:jar'" out/gradle.log; then
  echo "Notice the failing task is :compose:foundation:foundation-lint:jar"
else
  echo "The problem seems to be resolved. Nice!"
  exit 0
fi


echo "Now we will run the task that is reported as failing"
if runGradle --no-daemon :compose:foundation:foundation-lint:jar; then
  echo "Notice that :compose:foundation:foundation-lint:jar succeeds when run on its own"
else
  echo "Build failed: unable to reproduce the problem."
  exit 1
fi


echo "Now we will run try running a different task"
if runGradle --no-daemon :compose:foundation:foundation-lint:ktlint; then
  echo "This build unexpectedly passed, so we don't have a suggestion for which task Gradle should have reported in the original failure message"
  exit 1
else
  echo "Notice that this other task failed. Gradle probably should have reported this task in the original failure message"

fi
