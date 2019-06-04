# Functional

A Java library for JavaScript-like functional methods.

## Why Functional?

- Streams are too heavy-weight, while simple functional methods can cover most use cases.
- Much less allocation and code, no need for a `ForkJoinPool`.
- Exception transparency support with throwing functional interfaces.

## Integration

```gradle
dependencies {
    // For Java
    implementation 'me.zhanghai.java.functional:java:1.0.0'
    // For Android
    implementation 'me.zhanghai.java.functional:android:1.0.0'
}
```

## Usage

See [Functional.java](library/src/main/java/me/zhanghai/java/functional/Functional.java). Basically all the functional methods on JavaScript [`Array`](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array) are available.

`me.zhanghai.java.functional.throwing` contains the throwing functional interfaces that can be used to achieve exception transparency. For example:

```java
try {
    fileList = Functional.map(directoryStream, (ThrowingFunction<Path, FileItem>) FileItem::load);
} catch (FunctionalException e) {
    if (e.getCause() instanceof DirectoryIteratorException) {
        throw e.getCauseAs(DirectoryIteratorException.class).getCause();
    }
    throw e.getCauseAs(IOException.class);
}
```

The `android` artifact of this library uses [android-retrostreams](https://github.com/retrostreams/android-retrostreams) for `java.util.function`, and it is exposed as an API dependency of that artifact.

## License

    Copyright 2018 Hai Zhang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
