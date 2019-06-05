#!/bin/bash

set -eu

JAVA_CLASSES_ROOT="java/src/main/java/me/zhanghai/java/functional"
ANDROID_CLASSES_ROOT="android/src/main/java/me/zhanghai/java/functional"

mkdir -p "${ANDROID_CLASSES_ROOT}"
rm -rf "${ANDROID_CLASSES_ROOT}"
cp -r "${JAVA_CLASSES_ROOT}" "${ANDROID_CLASSES_ROOT}"

find "${ANDROID_CLASSES_ROOT}" -iname '*.java' -type f -print0 | xargs -0 sed -Ei \
-e 's/\bjava\.util\.Objects\b/java9.util.Objects/g' \
-e 's/\bjava\.util.function\b/java9.util.function/g'

sed -Ei \
-e '/^\s*import\s+java\.util\.Iterator\s*;\s*$/a\import java9.util.Iterators;' \
-e "s/\b([a-z_$][0-9A-Za-z_$]*)(\s*\.\s*forEachRemaining\s*\()/Iterators\2\1, /" \
"${ANDROID_CLASSES_ROOT}/FunctionalIterator.java"
