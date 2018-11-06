set -e

cd "$(dirname $0)"

function runGradle() {
  args="$@"
  echo "./gradlew $args"
  ./gradlew $args
}

echo "************************************************************************"
echo "See that the existing build fails saying it could not find jeff-core.aar"
runGradle :jeff-fragment:assembleDebug || true

echo "************************************************************************"
echo 'Now see that when remove the "@aar" from the dependency that the build passes'
runGradle :jeff-fragment:assembleDebug -PnoAarDepType
