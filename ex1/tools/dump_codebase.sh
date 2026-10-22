#!/usr/bin/env bash

set -euo pipefail

OUTPUT="tools/codebase_ex1.txt"

rm -f "$OUTPUT"

find . -type f \
    -not -path '*/\.*' \
    -not -path './out/*' \
    -not -path './bin/*' \
    -not -path './tools/*' \
    -not -path '*/self-check*' \
    -not -name '*.class' \
    -not -name '*.jar' \
    -not -name '*.zip' \
    -not -name '*.pdf' \
    -not -name '*.o' \
    -not -name '*.out' \
    -not -name "$OUTPUT" |
while IFS= read -r file; do
    printf '\n=======\n%s\n========\n' "$file" >> "$OUTPUT"
    cat "$file" >> "$OUTPUT"
done

echo "Created $OUTPUT"
