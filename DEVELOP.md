# Previewing the documentation site

You can run `mvn site:run` to preview a site for an individual component.

In order to generate a full, working multi-module site run:
```sh
mvn clean install
mvn site site:stage site:stage-deploy
```

And then open `target/staging/index.html`.
