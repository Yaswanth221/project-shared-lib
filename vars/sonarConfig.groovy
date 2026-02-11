import groovy.json.JsonSlurper

def call() {
  echo "[sonarConfig] loading from resources/sonar_config.json"
  def txt = libraryResource('sonar_config.json')
  return readJSON(text: txt)
}
