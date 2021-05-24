set -e

cd "$(dirname $0)"


function runCommand() {
  args="$*"
  echo "$args"
  eval "$args"
}

runCommand ./gradlew --project-cache-dir gradle-project-cache :included:compileJava --info
runCommand ./gradlew --project-cache-dir gradle-project-cache :included:compileJava --info

echo "************************************************************************"
echo "Ran the build twice in a row. Check whether the second invocation of :included:compileJava says:"
echo
echo "  Task ':included:compileJava' is not up-to-date because:"
echo "    No history is available."
