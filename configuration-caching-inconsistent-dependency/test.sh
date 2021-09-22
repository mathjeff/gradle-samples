#!/bin/bash
set -e
function runShell() {
  echo "$*"
  eval "$@"
}

function runTest() {
  configurationCaching="$1"
  args="publish"
  if [ "$configurationCaching" == "true" ]; then
    args="$args --configuration-cache"
  fi
  pomPath=build/repo/sample/sample/1.0/sample-1.0.pom

  echo "***************************************************"
  echo Running first build with configuration caching "$configurationCaching"
  runShell ./gradlew $args
  cp "$pomPath" build/before.pom
  echo
  echo "***************************************************"
  echo Running second build with configuration caching "$configurationCaching"
  runShell ./gradlew $args
  cp "$pomPath" build/after.pom
  echo "Differences between the two .pom files:"
  if diff build/before.pom build/after.pom; then
    echo "No differences"
  else
    echo "Found differences! Does reusing the configuration cache cause the published dependency to change?"
  fi
}

cd "$(dirname $0)"
runShell rm build .gradle/configuration-cache -rf
runTest false
echo "***************************************************"
echo 'Note that with configuration caching disabled, the dependency says "kotlinx-coroutines-core-jvm"'
runTest true
