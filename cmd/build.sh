#!/bin/bash

cd "$(dirname "$0")" && cd ..

docker build . -t awaken-email-service 