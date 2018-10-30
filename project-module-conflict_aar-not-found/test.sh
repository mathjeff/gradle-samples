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
echo "Now see that when we remove the dependency from project :jeff-fragment to jeff.jeff-loader:jeff-loader:1.0.0 that the build passes"
runGradle :jeff-fragment:assembleDebug -PskipLoaderDep
