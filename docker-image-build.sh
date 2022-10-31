#!/bin/sh

BUILD_VERSION=v1.0
docker buildx build --push --build-arg H2_VERSION=$BUILD_VERSION --tag sdes5317/haochih-spring-boot:$BUILD_VERSION --tag sdes5317/haochih-spring-boot:latest --platform=linux/arm64 .