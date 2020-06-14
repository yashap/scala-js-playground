# Scala.js Playground

Just me playing around with Scala.js

## Dependencies

* `sbt 1.x.x`
* Modern version of `yarn` (I'm using `1.22.4`)
* Modern version of `node` and `npm` (I'm using `12.17.0` and `6.14.4`)

## Dev Workflow 

```bash
# Build (and watch, i.e. auto-rebuild on changes) a dev version of the JS bundle
make dev/build

# Open the dev version of the web app
make dev/open

# Run the unit tests
make test
```
