#!/bin/bash

cd "$(dirname "$0")" && cd ..

export FLASK_APP=api/app.py
export FLASK_ENV=development

flask run

