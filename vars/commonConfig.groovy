import groovy.json.JsonSlurper

def call() {
  echo "[commonConfig] loading from resources/common_config.json"
  def txt = libraryResource('common_config.json')
  return readJSON(text: txt)
}
