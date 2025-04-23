# dependencies-json-server

API to receive dependencies from [dependencies-json-plugin](https://github.com/mbogner/dependencies-json-plugin).

## Release

All releases have to be done from main branch.

The following example releases version 1.0.0 and prepares a development version of 1.0.1-SNAPSHOT.

```shell
./gradlew release -Prelease.useAutomaticVersion=true \
  -Prelease.releaseVersion=1.0.0 \
  -Prelease.newVersion=1.0.1-SNAPSHOT
```

You can skip the version definitions to release the snapshot and automatically increment.

```shell
./gradlew release -Prelease.useAutomaticVersion=true
```
