#!/bin/bash

# Script location
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" &> /dev/null && pwd)"

# Assume project root is parent of scripts/
PROJECT_DIR="$SCRIPT_DIR/.."

# Output file in scripts folder
OUTPUT_FILE="$SCRIPT_DIR/android_project_structure.txt"

# Ignore pattern for Android/Gradle projects
IGNORE_PATTERN="build|\.gradle|\.idea|\.git|local.properties|.*\.iml|.*\.apk|.*\.aab|.*\.keystore|\.DS_Store"

tree "$PROJECT_DIR" \
    -I "$IGNORE_PATTERN" \
    -a \
    -F \
    --dirsfirst \
    > "$OUTPUT_FILE"

echo "Android project structure saved to $OUTPUT_FILE"
