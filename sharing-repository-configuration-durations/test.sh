set -e

runBuilds() {
  count="$1"
  args="$2"
  echo running $count builds with args $args
  for i in $(seq $count); do
    echo $i
    ./gradlew --offline --no-daemon $args > /dev/null
  done
}

runBuilds 2 "-P repoChildrenCopy"
time runBuilds 30 "-P repoChildrenCopy"

runBuilds 2 "-P repoChildrenReference"
time runBuilds 30 "-P repoChildrenReference"
