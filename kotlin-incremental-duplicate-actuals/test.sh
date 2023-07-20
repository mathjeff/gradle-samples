#!/bin/bash
set -e

function echoAndDo() {
  echo "$*"
  eval "$*"
}

function revertChanges() {
  echoAndDo git checkout HEAD -- ./compose/foundation/foundation/src/commonMain/kotlin/androidx/compose/foundation/text/BasicText.kt
}
revertChanges

function runBuild() {
  buildDir="$1"
  echoAndDo GRADLE_USER_HOME=gradle-user-home ./gradlew --no-daemon compileTestKotlinDesktop -PoverrideBuildDir="$buildDir"
}

function demonstrateError() {
  echoAndDo rm gradle-user-home build-1 build-2 -rf
  runBuild build-1
  runBuild build-2
  
  echo modifying code
  echo "internal fun tryMe() {}" >> ./compose/foundation/foundation/src/commonMain/kotlin/androidx/compose/foundation/text/BasicText.kt
  
  if runBuild build-2; then
    echo "Failed to reproduce the error. Nice!"
    return 0
  else
    echo "Incremental build failed; let's try a clean build"
  fi

  rm gradle-user-home build-2 -rf
  if runBuild build-2; then
    echo
    echo "Clean build succeeded after incremental build failed"
  else
    echo
    echo "Clean build failed too - that's weird"
    return 1
  fi
}

demonstrateError
revertChanges
