#!/usr/bin/env bash

set -euo pipefail

OUTPUT="tools/codebase_ex2.txt"

rm -f "$OUTPUT"

find . -type f \
    -not -path '*/\.*' \
    -not -path './out/*' \
    -not -path './bin/*' \
    -not -path './tools/*' \
    -not -path '*/self-check*' \
    -not -name 'PARSER' \
    -not -name 'LEXER' \
    -not -name 'Lexer.java' \
    -not -name 'Parser.java' \
    -not -name 'TokenNames.java' \
    -not -name '*.class' \
    -not -name '*.jar' \
    -not -name '*.zip' \
    -not -name '*.pdf' \
    -not -name '*.jpeg' \
    -not -name '*.jpg' \
    -not -name '*.png' \
    -not -name '*.o' \
    -not -name '*.out' |

while IFS= read -r file; do
    printf '\n=======\n%s\n========\n' "$file" >> "$OUTPUT"
    cat "$file" >> "$OUTPUT"
done

echo "Created $OUTPUT"
