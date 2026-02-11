import groovy.json.JsonSlurperClassic

def call() {
  echo "[sonarConfig] loading from resources/sonar_config.json"
  def txt = libraryResource('sonar_config.json')
  return new JsonSlurperClassic().parseText(txt)   // returns HashMap (serializable)
}
