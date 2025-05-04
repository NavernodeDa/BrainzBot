#!/bin/bash

DIR="./"
LANGUAGE="english"

while [[ "$#" -gt 0 ]]; do
  case "$1" in
    -d|--directory)
      DIR="$2"
      shift 2
      ;;
    -l|--language)
      LANGUAGE="$2"
      shift 2
      ;;
    -h|--help)
      echo "Usage: $0 [-d DIRECTORY] [-l LANGUAGE]"
      echo "Runs music bot via Gradle."
      echo
      echo "Arguments:"
      echo "  -d, --directory   Path to the project containing 'gradlew' (default: current folder)"
      echo "  -l, --language    Language for the bot (default: 'english')"
      exit 0
      ;;
    *)
      echo -e "\e[1;31mUnknown option: $1. Use --help for usage information.\e[0m"
      exit 1
      ;;
  esac
done

printf "\n"
echo -e "\033[1;32m🔍 Searching for Gradlew...\033[0m"

if [ ! -f "$DIR/gradlew" ]; then
  echo -e "\e[1;31mError: 'gradlew' file not found in $DIR! Maybe use --help?\e[0m"
  exit 1
fi

cd "$DIR" || exit 1

echo -e "\033[1;32m🚀 Running Gradle with language: $LANGUAGE...\033[0m"
echo -e "\033[1;32m📜 Log:\033[0m"
./gradlew run --quiet --args="$LANGUAGE"