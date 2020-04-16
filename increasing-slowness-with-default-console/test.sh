set -e

runBuilds() {
  count="$1"
  args="$2"
  echo running $count builds with args "'$args'"
  echo stopping daemon
  ./gradlew --stop
  for i in $(seq $count); do
    echo $i
    time ./gradlew $args
  done
}

runBuilds 1000 "projects --max-workers=1"
echo You should observe that the build duration increased after about 55 builds when using the default console
#runBuilds 100 "project --console=plain"
#echo You should observe that the build duration remained approximately steady when using the plain console
