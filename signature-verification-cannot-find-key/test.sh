#!/bin/bash
set -e

cd "$(dirname $0)"
export GRADLE_USER_HOME=out

separator="*******************************************************************************************************"

function runTest() {
  echo "$separator"
  buildSrcDepArgument="$1"
  git checkout HEAD -- gradle
  rm out -rf
  ./gradlew --write-verification-metadata pgp,sha256 --export-keys --dry-run :help --no-daemon $buildSrcDepArgument
  mv gradle/verification-keyring-dryrun.keys gradle/verification-keyring.keys
  mv gradle/verification-metadata.dryrun.xml gradle/verification-metadata.xml
  ./gradlew :help --no-configuration-cache $buildSrcDepArgument
}


if runTest -PbuildSrcDep=true; then
  echo "$separator"
  echo "Success! The problem is resolved!"
  exit 0
else
  echo "$separator"
  echo "Notice that Gradle fails to import signatures when -PbuildSrcDep=true"
fi

if runTest; then
  echo "$separator"
  echo "Notice that Gradle successfully imports signatures when not setting -PbuildSrcDep, unlike above when setting -PbuildSrcDep"
  # unfortunately failed like expected
else
  echo "$separator"
  echo "That's interesting - Gradle did not succesfully importing signatures when not setting -PbuildSrcDep either"
  # unexpected failure
fi
exit 1
