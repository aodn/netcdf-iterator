FROM ubuntu:20.04

ARG BUILDER_UID=9999
ARG DEBIAN_FRONTEND=noninteractive

ENV TZ=Australia/Hobart
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV JAVA_TOOL_OPTIONS -Duser.home=/home/builder
ENV PATH /home/builder/.local/bin:$PATH

RUN apt-get update && apt-get install -y --no-install-recommends \
    git \
    openjdk-8-jdk \
    maven \
    && rm -rf /var/lib/apt/lists/*

RUN useradd --create-home --no-log-init --shell /bin/bash --uid $BUILDER_UID builder
USER builder
WORKDIR /home/builder
