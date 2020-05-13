.DEFAULT_GOAL:=help

ROOT_DIR := $(shell dirname $(realpath $(firstword $(MAKEFILE_LIST))))
GREEN  := $(shell tput -Txterm setaf 2)
YELLOW := $(shell tput -Txterm setaf 3)
WHITE  := $(shell tput -Txterm setaf 7)
RESET  := $(shell tput -Txterm sgr0)
TARGET_MAX_CHAR_NUM = 20

.PHONY: help
## Show this help
help:
	@printf 'Usage:\n  ${YELLOW}make${RESET} ${GREEN}<target>${RESET}\n\n'
	@printf 'Targets:\n'
	@awk '/^[a-zA-Z\-\_\/0-9]+:/ { \
		helpMessage = match(lastLine, /^## (.*)/); \
		if (helpMessage) { \
			helpCommand = substr($$1, 0, index($$1, ":")-1); \
			helpMessage = substr(lastLine, RSTART + 3, RLENGTH); \
			printf "  ${YELLOW}%-$(TARGET_MAX_CHAR_NUM)s${RESET} ${GREEN}%s${RESET}\n", helpCommand, helpMessage; \
		} \
	} \
	{ lastLine = $$0 }' $(MAKEFILE_LIST)

.PHONY: dev/build
## Build (and watch, i.e. auto-rebuild on changes) a dev version of the JS bundle
dev/build:
	sbt ~fastOptJS

.PHONY: dev/open
## Open the dev version of the web app
dev/open:
	open dev.html

.PHONY: prod/build
## Build an optimized version of the JS bundle
prod/build:
	sbt fullOptJS

.PHONY: prod/open
## Open the optimized version of the web app
prod/open:
	open prod.html

.PHONY: test
## Run the unit tests
test:
	sbt test
