#!/bin/bash

cd "$(dirname "$0")" && cd ..

docker run --env-file ./.env -it --memory="500m" -p 3001:3001 awaken-email-service